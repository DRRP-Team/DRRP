const fs = require('fs');
const exec = require("child_process").execSync;

function palette(file) {
    var buffer = fs.readFileSync(file);
    var numColors = buffer.readInt32LE(0) / 2;
    var paletteColors = [];

    for (var i = 4; i < numColors + 4; i += 2) {
        var tmp = buffer.readUInt16LE(i);
        var r = tmp & 0x1F;
        r = r << 3 | r >> 2;
        var g = tmp >> 5 & 0x3F;
        g = g << 2 | g >> 4;
        var b = tmp >> 11 & 0x1F;
        b = b << 3 | b >> 2;
        var color = (0xFF000000 | r << 16 | g << 8 | b << 9);
        paletteColors.push(color);
    }
    return paletteColors;
}

function walls(file) {
    var texels = fs.readFileSync(file);
    var textureBuffer = texels.slice(4);
    var outputBuffer = [];

    for (var i = 0; i < paletteColors.length; i += 16) {
        for (var j = 0; j < textureBuffer.length; j++) {
            var color1 = paletteColors[(textureBuffer[j] & 0xF) + i];
            var red1 = 0xFF & (color1 >> 16);
            var blue1 = 0xFF & (color1);
            var green1 = 0xFF & (color1 >> 8);
            var color2 = paletteColors[(textureBuffer[j] >> 4 & 0xF) + i];
            var red2 = 0xFF & (color1 >> 16);
            var blue2 = 0xFF & (color1);
            var green2 = 0xFF & (color1 >> 8);

            outputBuffer.push(red1, green1, blue1, red2, green2, blue2);
        }
        fs.writeFileSync("texels"+i+".raw", new Buffer(outputBuffer));
    }
}

function convert(){
    var bash = "#!/bin/bash\n\
    mkdir raw\n\
    mv *.raw raw/\n\
    find raw/ -name \\*.raw -exec convert -size 64x1664 -depth 8 rgb:{} -rotate 90 -flop {}.png \\;\n\
    mkdir png\n\
    mv raw/*.png png/\n\
    mkdir tiles\n\
    find png/ -name \\*.png -exec convert {} -crop 64x64 {}_%d.png \\;\n\
    mv png/texels*.png tiles";
    fs.writeFileSync("1.sh", bash);
    require("child_process").exec("bash 1.sh",(error)=>{
        if(error){
            console.error(error);
            return;
        }
        console.log("Fuck year!");
        return;
    }
    );
}

var paletteColors = palette("palettes.bin");
walls("wtexels.bin", paletteColors);
convert();