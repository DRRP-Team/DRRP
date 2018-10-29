/**
 * Copyright (c) 2018 DRRP-Team
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

"use strict";

const fs = require("fs");

module.exports = class ByteTools {
    constructor(buffer = Buffer.from(), offset = 0) {
        this.buffer = buffer;
        this.offset = offset;
    }

    // basics
    tell() {
        return this.offset;
    }

    seek(offset, mode = "START") {
        switch (mode) {
            case "CUR":
                return this.offset += offset;
            case "START":
            default:
                return this.offset = offset;
        }
    }

    // read

    // int8

    readInt8() {
        const tmp = this.buffer.readInt8(this.offset);

        this.offset += 1;

        return tmp;
    }

    readUInt8() {
        const tmp = this.buffer.readUInt8(this.offset);

        this.offset += 1;

        return tmp;
    }


    // int16

    readInt16LE() {
        const tmp = this.buffer.readInt16LE(this.offset);

        this.offset += 2;

        return tmp;
    }

    readUInt16LE() {
        const tmp = this.buffer.readUInt16LE(this.offset);

        this.offset += 2;

        return tmp;
    }

    readInt16BE() {
        const tmp = this.buffer.readInt16BE(this.offset);

        this.offset += 2;

        return tmp;
    }

    readUInt16BE() {
        const tmp = this.buffer.readUInt16BE(this.offset);

        this.offset += 2;

        return tmp;
    }

    // int32

    readInt32LE() {
        const tmp = this.buffer.readInt32LE(this.offset);

        this.offset += 4;

        return tmp;
    }

    readUInt32LE() {
        const tmp = this.buffer.readUInt32LE(this.offset);

        this.offset += 4;

        return tmp;
    }

    readInt32BE() {
        const tmp = this.buffer.readInt32BE(this.offset);

        this.offset += 4;

        return tmp;
    }

    readUInt32BE() {
        const tmp = this.buffer.readUInt32BE(this.offset);

        this.offset += 4;

        return tmp;
    }

    // seek

    // int8

    seekInt8() {
        return this.buffer.readInt8(this.offset);
    }

    seekUInt8() {
        return this.buffer.readUInt8(this.offset);
    }


    // int16

    seekInt16LE() {
        return this.buffer.readInt16LE(this.offset);
    }

    seekUInt16LE() {
        return this.buffer.readUInt16LE(this.offset);
    }

    seekInt16BE() {
        return this.buffer.readInt16BE(this.offset);
    }

    seekUInt16BE() {
        return this.buffer.readUInt16BE(this.offset);
    }

    // int32

    seekInt32LE() {
        return this.buffer.readInt32LE(this.offset);
    }

    seekUInt32LE() {
        return this.buffer.readUInt32LE(this.offset);
    }

    seekInt32BE() {
        return this.buffer.readInt32BE(this.offset);
    }

    seekUInt32BE() {
        return this.buffer.readUInt32BE(this.offset);
    }

    // skip

    // int8

    skipInt8() {
        return this.offset += 1;
    }

    skipUInt8() {
        return this.offset += 1;
    }


    // int16

    skipInt16LE() {
        return this.offset += 2;
    }

    skipUInt16LE() {
        return this.offset += 2;
    }

    skipInt16BE() {
        return this.offset += 2;
    }

    skipUInt16BE() {
        return this.offset += 2;
    }

    // int32

    skipInt32LE() {
        return this.offset += 4;
    }

    skipUInt32LE() {
        return this.offset += 4;
    }

    skipInt32BE() {
        return this.offset += 4;
    }

    skipUInt32BE() {
        return this.offset += 4;
    }
};