/* DISCLAIMER: NOT ALLOWED FOR NERVOUS PEOPLE */
const fs = require('fs');

const $$ = Dom2;

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
    palettes: "/home/usernameak/doom_rpg/newPalettes.bin",
    wtexels: "/home/usernameak/doom_rpg/tex00.bin",
    mappings: "/home/usernameak/doom_rpg/newMappings.bin",
    //    save: ""
}

var base64s, paletteColors;

var Control = {
    palette: 16 * 0,
    texture: 0,
    nextPalette: function () {
        if (Control.palette < paletteColors.length - 1) {
            Control.palette += 16;
            Do();
            Control.show();
        }
    },
    previousPalette: function () {
        if (Control.palette > 0) {
            Control.palette -= 16;
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
    $$('#mappings')[0].addEventListener("change", function (evt) {
        Files.mappings = this.value;
        ok("Какая то ерунда задана!");
    }, false);
    /*    $$('#save')[0].addEventListener("change", function (evt) {
    Files.save = this.value;
    ok("Папка сохранения задана!");
}, false);*/
}

var Palette = {
    main: function (file) {
        paletteColors = [];
        var ptr = -1;
        var buffer = fs.readFileSync(file);
        var dist = 1, i7, i8, i10, i11;
        for (let i = 0; i < 1024; i++) {
            ptr += dist << 1;
            for (i10 = 0; i10 < i; i10++) {
                var r = ((i5 = buffer.readUInt16LE(ptr * 2)) & 0x1F) << 3;
                var g = (i5 >> 5 & 0x1F) << 3;
                var b = (i5 >> 10 & 0x1F) << 3;
                var color = 0xFF000000 | r << 16 | g << 8 | b << 0;
                paletteColors.push(color);
            }
            i10 = (i | 0x8000);
        }
        return paletteColors;
    }
};

function randomize() {
    c = c.map(function (i) { return Math.floor(Math.random() * 100) });
}

var Texture = {
    _texels: null,
    _palettes: null,
    _outBuffer: null,
    _palette: null,
    main: function (file, paletteColors) {
        console.log("textures", file, paletteColors);
        this._texels = fs.readFileSync(file);
        this._outBuffer = [];
        this._palettes = paletteColors;
        this._palette = Control.palette;

        return this.parse();
    },
    parse: function () {

        var arrayOfShort1 = fs.readFileSync(Files.mappings).slice(8192, 10240);

        var ptr = 0;
        var i11;

        for (let i10 = 0; i10 < 1024; i10++) {
            i11 = ((arrayOfShort1.readUInt16LE(i10) & 0x4000) == 0) ? 0 : 1;
            var i1 = ((arrayOfShort1.readUInt16LE(i10) & 0x8000) == 0) ? 0 : 1;
            var i3 = (arrayOfShort1.readUInt16LE(i10) & 0x3FFF) + 1;//1
            //var i2;
            // if (1) {//(i11) && (!i1)) {
            /*if (i7 != i8) {
            u.a(localInputStream, i8 - i7, true);
            }*/
            //p
            // console.log(i7 != i8);
            // this.paletting(arrayOfShort1.readUInt16LE(i10));
            this.paletting(ptr);

            i2 = (i10 | 0x8000);
            for (i4 = i10 + 1; i4 < 1024; i4++) {
                if (arrayOfShort1.readUInt16LE(i4) == i2) {
                    arrayOfShort1.writeUInt16LE((0xC000 | i5), i4);
                }
            }
            arrayOfShort1.writeUInt16LE((0x4000 | i5), i10);
            i5++;
            //i7 = i8 += i3;
            ptr += i3;
            debugger;
            // console.log("end of loop ", "i10", i10, "i11", i11, "i5", i5, "i4", i4, "i2", i2,"i3",i3);
            // }
        }
        console.log(arrayOfShort1);
        // paletting(texels,Control.palette,ptr);
        return this.createImage();
    },
    paletting: function (p) {
        // for (let k = 0; k < texels.length; k++) {
        const pal = this._palette;
        const palettes = this._palettes;
        const texels = this._texels;

        let color1 = palettes[(texels[p] & 0xF) + pal];
        let red1 = 0xFF & (color1 >> 16);
        let blue1 = 0xFF & (color1 >> 0);
        let green1 = 0xFF & (color1 >> 8);
        let color2 = palettes[(texels[p] >> 4 & 0xF) + pal];
        let red2 = 0xFF & (color1 >> 16);
        let blue2 = 0xFF & (color1 >> 0);
        let green2 = 0xFF & (color1 >> 8);

        this._outBuffer.push(red1, green1, blue1, red2, green2, blue2);
        // }
    },
    createImage: function () {
        var buffer = this._outBuffer;//outputBuffer;

        let canvas = $$("#c")[0];
        canvas.width = 64;
        canvas.height = 64;
        let ctx = canvas.getContext("2d");

        ctx.resetTransform();
        let data = ctx.createImageData(64, 1664);
        for (let i = 0; i < buffer.length; i += 3) {
            data.data[i / 3 * 4] = buffer[i];
            data.data[i / 3 * 4 + 1] = buffer[i + 1];
            data.data[i / 3 * 4 + 2] = buffer[i + 2];
            data.data[i / 3 * 4 + 3] = 0xff;
        }
        let base64s = [];
        for (let i = 0; i < buffer.length / 4096 / 3; i++) {
            ctx.putImageData(data, 0, -i * 64);
            base64s.push(canvas.toDataURL("image/png"));
        }
        this._outBuffer = [];
        return base64s;
    }
};

var out = [];
function Do() {
    if (Validate()) {

        var paletteColors = Palette.main(Files.palettes);

        out = Texture.main(Files.wtexels, paletteColors);
        Control.show();
    }
}

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
window.onload = Main;