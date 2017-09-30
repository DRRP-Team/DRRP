const fs = require("fs");
const struct = require("js-struct");

const THINGS = require("./things");

const bspcolor_t = struct.Struct([
    struct.Type.uint8('b'),
    struct.Type.uint8('g'),
    struct.Type.uint8('r')
]);

const bspheader_t = struct.Struct([
    bspcolor_t('floorcolor'),
    bspcolor_t('celingcolor'),
    bspcolor_t('unknown0'),
    struct.Type.uint8('levelid'),
    bspcolor_t('unknown1')
]);

const linedef_t = struct.Struct([
    struct.Type.uint8('x0'),
    struct.Type.uint8('y0'),
    struct.Type.uint8('x1'),
    struct.Type.uint8('y1'),
    struct.Type.uint16('walltex'),
    struct.Type.uint16('flags')
]);

const Count = struct.Struct([
    struct.Type.uint16('count')
]);

const thing_t = struct.Struct([
    struct.Type.uint8('x'),
    struct.Type.uint8('y'),
    struct.Type.uint8('id'),
    struct.Type.uint16('flags')
]);

const mh = [];

const vertex_t = {
    x: 0,
    y: 0,
};

class Parser {
    constructor(from, to) {
        this.from = from;
        this.to = to;
    }

    parse() {
        const texmap = this.getMappings();

        const map = this.parseMap(this.from);

        fs.writeFileSync(this.to, this.generate(map.lines, map.count, texmap, map.things));

        this.display(map.lines, map.count, map.things);
    }

    getMappings() {
        let mf = fs.readFileSync("mappings.bin");

        // mfh.read(reinterpret_cast<char*>(&mh), sizeof(mh));
        for (let i = 0; i < 4; i++)
            mh[i] = mf.readUInt32LE(i * 4);

        // mfh.ignore(8 * mh.group1size + 8 * mh.group2size);
        var offset = 16 + 8 * mh[0] + 8 * mh[1];

        // texmap = new uint16_t[mh.group3size];
        let texmap = [];

        // mfh.read(reinterpret_cast<char*>(texmap), sizeof(uint16_t) * mh.group3size);
        for (let i = 0; i < mh[2]; i++) {
            texmap.push(mf.readUInt16LE(offset + i * 2));
        }

        // delete mf;

        return texmap;
    }

    parseMap(from) {
        this.buffer = fs.readFileSync(from);

        let h = bspheader_t.read(this.buffer, 0);
        let offset = bspheader_t.size;
        let count = Count.read(this.buffer, offset).count;
        // let count = struct.Type.uint8.read(this.buffer, offset); //getSize
        offset += count * 10; //start lines
        offset += 2;
        count = Count.read(this.buffer, offset).count;// * 156;
        offset += 2;
        // count     = struct.Type.uint8.read(this.buffer, offset); //getCount
        //offset += count ; //removeCount
        let lines = [];
        for (let i = 0; i < count; i++) { //parsing lines
            lines.push(linedef_t.read(this.buffer, offset));
            offset += linedef_t.size;
        }

        var things = this.parseThings(offset);

        return {lines, count, things};
    }

    parseThings(offset) {
        let count = Count.read(this.buffer, offset).count;
        offset += 2;
        var things = [];
        for(let i = 0; i < count; i++) {
            things.push(thing_t.read(this.buffer, offset));
            offset += thing_t.size;
        }
        return things;
    }

    generate(lines, count, texmap, things) {
        let ss = "";
        ss += "sector {\n";
        ss += "\theightceiling = 64;\n";
        ss += "\ttexturefloor = \"floor\";\n";
        ss += "\ttextureceiling = \"ceiling\";\n";
        ss += "}\n\n";
        for (let i = 0; i < count; i++) {
            ss += "vertex {\n";
            ss += "\tx = " + lines[i].x1 * 8 + ";\n";
            ss += "\ty = " + (256 - lines[i].y1) * 8 + ";\n";
            ss += "}\n\n";
            ss += "vertex {\n";
            ss += "\tx = " + lines[i].x0 * 8 + ";\n";
            ss += "\ty = " + (256 - lines[i].y0) * 8 + ";\n";
            ss += "}\n\n";
            ss += "sidedef {\n";
            ss += "\tsector = 0;\n";
            ss += "\ttexturemiddle = \"drdc" + texmap[lines[i].walltex] + "\";\n";
            ss += "}\n\n";
            ss += "linedef {\n";
            ss += "\tv2 = " + (i * 2) + ";\n";
            ss += "\tv1 = " + (i * 2 + 1) + ";\n";
            ss += "\tsidefront = " + i + ";\n";
            ss += "}\n\n";
        }
        for(let i = 0; i < things.length; i++) {
            if (!THINGS[things[i].id.toString()]) continue;
            ss += "thing {\n";
            ss += "\ttype = " + THINGS[things[i].id.toString()] + ";\n";
            ss += "\tx = " + things[i].x * 8 + ";\n";
            ss += "\ty = " + (256 - things[i].y) * 8 + ";\n";
            ss +=
`    skill1 = true;
    skill2 = true;
    skill3 = true;
    skill4 = true;
    skill5 = true;
    skill6 = true;
    skill7 = true;
    skill8 = true;
    single = true;
    coop = true;
    dm = true;
    class1 = true;
    class2 = true;
    class3 = true;
    class4 = true;
    class5 = true;
`;
            ss += "}\n\n";
        }
        return ss;
    }

    display(lines, count, things) {
        let ctx = document.getElementsByTagName("canvas")[0].getContext("2d");

        function setColor(color){
            ctx.fillStyle = color;
            ctx.strokeStyle = color;
        }

        ctx.textAlign = "center";
        ctx.textBaseline = "middle";

        function drawLine(x,y,x1,y1){
            ctx.beginPath();
            ctx.moveTo(x, y);
            ctx.lineTo(x1, y1)
            ctx.stroke();
            ctx.closePath();
        }

        function drawCircle(x, y, id) {
            setColor("red");
            ctx.beginPath();
            ctx.arc(x, y, 6, 0, 2*Math.PI, false);
            ctx.fill();
            ctx.closePath();
            setColor("blue");
            ctx.fillText(id, x, y);
        }

        ctx.font = "8px sans-serif";
        
        setColor("red");

        for (let i = 0; i < count; i++) {
            drawLine(lines[i].x0 * 3, lines[i].y0 * 3, lines[i].x1 * 3, lines[i].y1 * 3);
        }

        for(let i = 0; i < things.length; i++) {
            drawCircle(things[i].x * 3, things[i].y*3, things[i].id);
        }
    }
}

function main(from, to) {
    // debugger;
    // // let position = 0;



    // // stream.read()
    let parser = new Parser(from, to);
    debugger;
    parser.parse();
}

const a = require("nw.gui").Window.get();
a.width  = 768;
a.height = 768;

main(process.argv[2] || "intro.bsp", process.argv[3] || "out.tm");