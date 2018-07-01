const fs = require("fs");
const struct = require("js-struct");

const THINGS = require("./things");
const DECALS = require("./decals");

const Config = {
    width: 768,
    height: 768
}

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

        fs.writeFileSync(this.to, this.generate(map.lines, map.count, texmap, map.things, map.decals));

        this.display(map.lines, map.count, map.things, map.decals);
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
        count = Count.read(this.buffer, offset).count; // * 156;
        offset += 2;
        // count     = struct.Type.uint8.read(this.buffer, offset); //getCount
        //offset += count ; //removeCount
        let lines = [];
        for (let i = 0; i < count; i++) { //parsing lines
            lines.push(linedef_t.read(this.buffer, offset));
            offset += linedef_t.size;
        }

        let things = this.parseThings(offset);

        let decals = this.parseDecals(things);

        return {
            lines,
            count,
            things,
            decals
        };
    }

    parseThings(offset) {
        let count = Count.read(this.buffer, offset).count;
        offset += 2;
        var things = [];
        for (let i = 0; i < count; i++) {
            things.push(thing_t.read(this.buffer, offset));
            offset += thing_t.size;
        }
        return things;
    }

    parseDecals(things) {
        let decals = [];
        for (let thing of things) {
            if (!DECALS[thing.id.toString()]) continue;

            let isNotFence = (thing.flags & 2050) ? 0 : 1;
            let x0, y0, x1, y1;
            if (thing.flags & 32) { // east
                x0 = (thing.x * 8 + isNotFence);
                x0 = (thing.x * 8 + isNotFence);
                y0 = (thing.y * 8 + 32);
                y0 = (thing.y * 8 + 32);
                x1 = (thing.x * 8 + isNotFence);
                x1 = (thing.x * 8 + isNotFence);
                y1 = (thing.y * 8 - 32);
                y1 = (thing.y * 8 - 32);
            } else if (thing.flags & 64) { // west
                x0 = (thing.x * 8 - isNotFence);
                x0 = (thing.x * 8 - isNotFence);
                y0 = (thing.y * 8 - 32);
                y0 = (thing.y * 8 - 32);
                x1 = (thing.x * 8 - isNotFence);
                x1 = (thing.x * 8 - isNotFence);
                y1 = (thing.y * 8 + 32);
                y1 = (thing.y * 8 + 32);
            } else if (thing.flags & 16) { // south
                x0 = (thing.x * 8 - 32);
                x0 = (thing.x * 8 - 32);
                y0 = (thing.y * 8 + isNotFence);
                y0 = (thing.y * 8 + isNotFence);
                x1 = (thing.x * 8 + 32);
                x1 = (thing.x * 8 + 32);
                y1 = (thing.y * 8 + isNotFence);
                y1 = (thing.y * 8 + isNotFence);
            } else if (thing.flags & 8) { // north
                x0 = (thing.x * 8 + 32);
                x0 = (thing.x * 8 + 32);
                y0 = (thing.y * 8 - isNotFence);
                y0 = (thing.y * 8 - isNotFence);
                x1 = (thing.x * 8 - 32);
                x1 = (thing.x * 8 - 32);
                y1 = (thing.y * 8 - isNotFence);
                y1 = (thing.y * 8 - isNotFence);
            }

            decals.push({
                id: thing.id,
                texture: DECALS[thing.id],
                x0,
                y0, //: 2048 - y0,
                x1,
                y1, //: 2048 - y1
            })
        }
        return decals;
    }

    generate(lines, count, texmap, things, decals) {
        let ss = "";
        ss += "sector {\n";
        ss += "\theightceiling = 64;\n";
        ss += "\ttexturefloor = \"floor\";\n";
        ss += "\ttextureceiling = \"ceiling\";\n";
        ss += "}\n\n";

        let sideid = 0;
        var vertices = [];

        function findVertex(x, y) {
            for (var i = 0; i < vertices.length; i++) {
                if (vertices[i][0] == x && vertices[i][1] == y) return i;
            }
            vertices.push([x, y]);
            return vertices.length - 1;
        }

        //vertexes
        for (let i = 0; i < count; i++) {
            var v0 = findVertex(lines[i].x1 * 8, (256 - lines[i].y1) * 8);
            var v1 = findVertex(lines[i].x0 * 8, (256 - lines[i].y0) * 8);
            ss += "sidedef {\n";
            ss += "\tsector = 0;\n";
            ss += "\ttexturemiddle = \"drdc" + texmap[lines[i].walltex] + "\";\n";
            ss += "}\n\n";
            ss += "linedef {\n";
            ss += "\tv2 = " + v0 + ";\n";
            ss += "\tv1 = " + v1 + ";\n";
            ss += "\tsidefront = " + (sideid++) + ";\n";
            ss += "}\n\n";
        }

        //things
        for (let i = 0; i < things.length; i++) {
            if (!THINGS[things[i].id.toString()]) continue;
            ss += "thing {\n";
            ss += "\ttype = " + THINGS[things[i].id.toString()] + ";\n";
            ss += "\tx = " + things[i].x * 8 + ";\n";
            ss += "\ty = " + (256 - things[i].y) * 8 + ";\n";

            ss += "\tskill1 = true;\n";
            ss += "\tskill2 = true;\n";
            ss += "\tskill3 = true;\n";
            ss += "\tskill4 = true;\n";
            ss += "\tskill5 = true;\n";
            ss += "\tskill6 = true;\n";
            ss += "\tskill7 = true;\n";
            ss += "\tskill8 = true;\n";
            ss += "\tsingle = true;\n";
            ss += "\tcoop = true;\n";
            ss += "\tdm = true;\n";
            ss += "\tclass1 = true;\n";
            ss += "\tclass2 = true;\n";
            ss += "\tclass3 = true;\n";
            ss += "\tclass4 = true;\n";
            ss += "\tclass5 = true;\n";

            ss += "}\n\n";
        }

        //decals

        for (let decal of decals) {
            var v0 = findVertex(decal.x0, (2048 - decal.y0));
            var v1 = findVertex(decal.x1, (2048 - decal.y1));

            ss += "sidedef {\n"
            ss += "\tsector = 0;\n";
            ss += "\ttexturemiddle = \"" + DECALS[decal.id] + "\";\n";
            ss += "}\n\n";

            ss += "sidedef {\n"
            ss += "\tsector = 0;\n";
            ss += "\ttexturemiddle = \"" + DECALS[decal.id] + "\";\n";
            ss += "}\n\n";

            ss += "linedef {\n";
            ss += "\tv1 = " + v0 + ";\n";
            ss += "\tv2 = " + v1 + ";\n";
            ss += "\tsidefront = " + (sideid++) + ";\n";
            ss += "\tsideback = " + (sideid++) + ";\n";
            ss += "\ttwosided = true;\n";
            ss += "\tblocking = true;\n";
            ss += "\timpassable = true;\n";
            ss += "}\n\n";
        }

        var vs = "";
        for (let vertex of vertices) {
            vs += "vertex {\n";
            vs += "\tx = " + vertex[0] + ";\n";
            vs += "\ty = " + vertex[1] + ";\n";
            vs += "}\n\n";
        }

        return vs + ss;
    }

    display(lines, count, things, decals) {
        let ctx = document.getElementsByTagName("canvas")[0].getContext("2d");

        function setColor(color) {
            ctx.fillStyle = color;
            ctx.strokeStyle = color;
        }

        ctx.textAlign = "center";
        ctx.textBaseline = "middle";

        function drawLine(x, y, x1, y1) {
            ctx.beginPath();
            ctx.moveTo(x, y);
            ctx.lineTo(x1, y1)
            ctx.stroke();
            ctx.closePath();
        }

        function drawCircle(x, y, id) {
            setColor("darkgreen");
            ctx.beginPath();
            ctx.arc(x, y, 6, 0, 2 * Math.PI, false);
            ctx.fill();
            ctx.closePath();
            setColor("pink");
            ctx.fillText(id, x, y);
        }

        ctx.font = "8px sans-serif";

        setColor("black");

        ctx.fillRect(0, 0, innerWidth, innerHeight);

        setColor("red");

        for (let i = 0; i < count; i++) {
            drawLine(lines[i].x0 * 3, lines[i].y0 * 3, lines[i].x1 * 3, lines[i].y1 * 3);
        }

        // setColor("green");

        for (let i = 0; i < things.length; i++) {
            drawCircle(things[i].x * 3, things[i].y * 3, things[i].id);
            drawCircle(things[i].x * 3, things[i].y * 3, things[i].id);
        }

        setColor("cyan");
        for (let decal of decals) {
            drawLine(decal.x0 / (2048 / Config.width), decal.y0 / (2048 / Config.height), decal.x1 / (2048 / Config.width), decal.y1 / (2048 / Config.height));
            // drawCircle(decal.x0*3,768-decal.y0*3,"X");
            // drawCircle(decal.x1*3,768-decal.y1*3,"X");
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
a.width = Config.width;
a.height = Config.height;

// main(process.argv[2] || "level03.bsp", process.argv[3] || "out.tm");
{
    let from = prompt("Введите полное имя .bsd файла");
    if(from) main(from, from + ".ts");
}
document.addEventListener("click", function(){
    let from = prompt("Введите полное имя .bsd файла");
    if(from) main(from, from + ".ts");
})