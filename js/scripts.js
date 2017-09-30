$$ = Dom2;
window.onload = Main;
var Dialog = {
    open: function () {
        var chooser = $$('#open')[0];
        chooser.onchange = function () {
            $$('#input').html(require('fs').readFileSync(this.value));
        };

        chooser.click();
    },
    save: function () {
        var chooser = $$('#save')[0];
        chooser.onchange = function () {
            require('fs').writeFileSync(this.value, $$('#output').gettext());
        };

        chooser.click();
    }
}

var Files = {
    palettes: "",
    wtexels: "",
    save: ""
}

var base64s, paletteColors;

var Control = {
    palette: 0,
    texture: 0,
    nextPalette: function () {
        if (Control.palette < paletteColors.length-1) {
            Control.palette+=16;
            // textures(Files.wtexels, paletteColors);
            Do();
            Control.show();
        }
    },
    previousPalette: function () {
        if (Control.palette > 0) {
            Control.palette-=16;
            // textures(Files.wtexels, paletteColors);
            Do();
            Control.show();
        }
    },
    nextTexture: function () {
        if (Control.texture < out.length - 1) {
            Control.texture++;
            Control.show();
        }
    },
    previousTexture: function () {
        if (Control.texture > 0) {
            Control.texture--;
            Control.show();
        }
    },
    show: function () {
        $$("#img")[0].src = out[Control.texture];
        console.log(out[Control.texture]);
    }
}

function Main() {
    $$('#palettes')[0].addEventListener("change", function (evt) {
        Files.palettes = this.value;
        ok("Палитра задана!");
    }, false);
    $$('#wtexels')[0].addEventListener("change", function (evt) {
        Files.wtexels = this.value;
        ok("Текстуры заданы!");
    }, false);
    $$('#save')[0].addEventListener("change", function (evt) {
        Files.save = this.value;
        ok("Папка сохранения задана!");
    }, false);
}

const fs = require('fs');
const exec = require("child_process").execSync;


function palette(file) {
    var buffer = fs.readFileSync(file);
    var numColors = buffer.readInt32LE(0);
    paletteColors = [];

    for (var i = 4; i < numColors + 4; i += 2) {
        var tmp = buffer.readUInt16LE(i);
        var r = tmp & 0x1F;
        r = r << 3 | (r >> 2);
        var g = tmp >> 5 & 0x3F;
        g = g << 2 | (g >> 4);
        var b = tmp >> 11 & 0x1F;
        b = b << 3 | (b >> 2);
        var color = (0xFF000000 | r << 16 | g << 8 | b << 0);
        paletteColors.push(color);
    }
    return paletteColors;
}



function walls(file, paletteColors) {
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
        fs.writeFileSync(Files.save + "/texels" + i + ".raw", new Buffer(outputBuffer));
        outputBuffer = [];
    }
    return;
}


function textures(file, paletteColors) {
    console.log("textures", file, paletteColors);
    var texels = fs.readFileSync(file);
    var textureBuffer = texels.slice(4);
    var outputBuffer = [];
    var i = Control.palette;
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
    var buffer = outputBuffer;
    var canvas = $$("#c")[0];
    canvas.width = 64;
    canvas.height = 64;
    var ctx = canvas.getContext("2d");
    ctx.resetTransform();
    var data = ctx.createImageData(64, 1664);
    for (var i = 0; i < buffer.length; i += 3) {
        data.data[i / 3 * 4] = buffer[i];
        data.data[i / 3 * 4 + 1] = buffer[i + 1];
        data.data[i / 3 * 4 + 2] = buffer[i + 2];
        data.data[i / 3 * 4 + 3] = 0xff;
    }
    var base64s = [];
    for (var i = 0; i < buffer.length / 4096 / 3; i++) {
        ctx.putImageData(data, 0, -i * 64);
        base64s.push(canvas.toDataURL("image/png"));
    }
    outputBuffer = [];
    return base64s;
}

var out = [];
function Do() {
    if (Validate()) {

        var paletteColors = palette(Files.palettes);

        out = textures(Files.wtexels, paletteColors);
        Control.show();
    }
}







function convert() {
    var bash = '#!/bin/bash\n\
    cd "'+ Files.save + '"\n\
    mkdir raw\n\
    mv *.raw raw/\n\
    find raw/ -name \\*.raw -exec convert -size 64x1664 -depth 8 rgb:{} -rotate 90 -flop {}.png \\;\n\
    mkdir png\n\
    mv raw/*.png png/\n\
    mkdir tiles\n\
    find png/ -name \\*.png -exec convert {} -crop 64x64 {}_%d.png \\;\n\
    cd png\n\
    mv *.png_* ../tiles';
    fs.writeFileSync(Files.save + "/1.sh", bash);
    require("child_process").exec('bash "' + Files.save + '/1.sh"', (error) => {
        if (error) {
            console.error(error);
            End("bad");
            return;
        }
        console.log("Fuck year!");
        End("good");
        return;
    }
    );
}

// var paletteColors = palette("palettes.bin");
// walls("wtexels.bin", paletteColors);
// convert();

function warn(text) {
    $$("#warn").html(`<yellow>${text}</yellow>`);
}

function ok(text) {
    $$("#warn").html(`<green>${text}</green>`);
}

function log(text) {
    var d = new Date();
    var ds = ("00" + (d.getDate() + 1)).slice(-2) + "/" +
        ("00" + d.getMonth()).slice(-2) + "/" +
        d.getFullYear() + " " +
        ("00" + d.getHours()).slice(-2) + ":" +
        ("00" + d.getMinutes()).slice(-2) + ":" +
        ("00" + d.getSeconds()).slice(-2);
    $$("#log")[0].innerHTML += `[${ds}] ${text}<br/>`;
}

function Validate() {
    if (!Files.palettes) {
        warn("Укажите палитру!");
        return false;
    }
    if (!Files.wtexels) {
        warn("Укажите текстуры!");
        return false;
    }
    // if (!Files.save) {
    //     warn("Укажите папку сохранения!");
    //     return false;
    // }
    return true;
}

function Decomp() {
    if (Validate()) {
        ok("Поехали!");
        log("Расхреначиваем палитру на цифры...");
        var paletteColors = palette(Files.palettes);
        log("Палитра расхреначена!");
        log("На основе расхреначенной палитры, выдераем хреном текстуры...");
        walls(Files.wtexels, paletteColors);
        log("Хрен действует! Текстуры выдраны!");
        log("Приводим порождения Хрена в человеческий облик...");
        log("<red>Осторожно! Хрен имеет хреновый облик, и для перевода требуется много ресурсов и времени!<br/>Так что могут прийти хреновы слуги - лаги!</red>");
        log("Начинаем!");
        convert();
    }
}

function End(ending) {
    if (ending == "good") {
        log("<green>Расхреначено!</green>");
        log("Можете посетить папку вывода!");
    } else if (ending == "bad") {
        log("<red>Увы, Хрен выявился сильнее вашего компьютера...<br/>Попробуйте ещё раз!</red>");
    } else {
        log("<red>Проихошла некая хренотень... Свяжитесь с разработчиками!</red>");
    }
}