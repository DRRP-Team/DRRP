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
            // debugger;
            // console.log("end of loop ", "i10", i10, "i11", i11, "i5", i5, "i4", i4, "i2", i2,"i3",i3);
            // }
        }
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
    createImage: function (_buffer) {
        let buffer = this._outBuffer;//outputBuffer;

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
