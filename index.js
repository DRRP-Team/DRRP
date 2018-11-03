/**
 * Copyright (c) 2018 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

const fs = require('fs');

const ByteTools = require('./ByteTools');

class App {
    static getUnsignedByte(x) {
        return x & 0xff;
    }

    static main(args) {
        if(args.length != 3) {
            console.info("Usage: <path/to/file.bsp>");
            return 1;
        }

        const bsp = fs.readFileSync(args[2]);

        const file = new ByteTools(bsp);

        console.log(`Floor   color: rgb(${file.readUInt8()}, ${file.readUInt8()}, ${file.readUInt8()})`);
        console.log(`Ceiling color: rgb(${file.readUInt8()}, ${file.readUInt8()}, ${file.readUInt8()})`);
        console.log(`Loading color: rgb(${file.readUInt8()}, ${file.readUInt8()}, ${file.readUInt8()})`);
        console.log(`Level id: ${file.readUInt8()}`);

        const playerstart = file.readUInt16LE();
        console.log(`Player start: (${playerstart % 32};${~~(playerstart / 32)})`);

        console.log(`Player rotation: ${file.readUInt8()}`);

        file.seek(file.readUInt16LE() * 10, 'CUR');
        console.log(`Skipped to: ${file.tell()}`);

        file.seek(file.readUInt16LE() * 8, 'CUR');
        console.log(`Skipped to: ${file.tell()}`);

        file.seek(file.readUInt16LE() * 5, 'CUR');
        console.log(`Skipped to: ${file.tell()}`);

        const scripts = [];

        {
            const count = file.readInt16LE();

            console.log("# " + count + " scripts found");

            console.log("[SCRIPTS]");
            for (let i = 0; i < count; i++) {
                const packedInt = file.readInt32LE();

                const x = (packedInt & 0x1F);
                const y = ((packedInt & 0x3E0) >> 5);
                scripts.push([x, y]);
                console.log(`Script ${i} (${x};${y})`);
            }
            console.log();
        }

        const out = {
            playerstart: [playerstart % 32, ~~(playerstart / 32)],
            scripts: scripts
        };

        fs.writeFileSync(`${args[2]}.json`, JSON.stringify(out, 0, 4));
        console.info(`UMP Map ${args[2]}.json generated successfuly!`);
    }

    static main2(args) {
        if(args.length != 3) {
            console.info("Usage: <path/to/file.bsp>");
            return 1;
        }

        const bsp = fs.readFileSync(args[2]);

        const file = new ByteTools(bsp);
        
        file.seek(13, "CUR");
        debugger;

        file.seek(file.readUInt16LE() * 10, "CUR");
        debugger;

        file.seek(file.readUInt16LE() * 8, "CUR");

        file.seek(file.readInt16LE() * 5, "CUR");

        {
            const count = file.readInt16LE();

            console.log("# " + count + " events found");
            console.log("# offset=" + (file.tell() - 2));

            console.log("[EVENTS]");
            for (let i = 0; i < count; i++) {
                const packedInt = file.readInt32LE();

                const x = (packedInt & 0x1F);
                const y = ((packedInt & 0x3E0) >> 5);
                const start = ((packedInt & 0x7FC00) >> 10);
                const length = ((packedInt & 0x1F80000) >> 19);
                const unkn = packedInt & 33030144;
                const unkn2 = packedInt & 0xFE000000;
                console.log(`X=${x} Y=${y} START=L_${start} END=L_${(start + length - 1)} UNKN=${unkn} UNKN2=${unkn2}`);
            }
            console.log();
        }

        console.log("[COMMANDS]");

        const count = file.readInt16LE();

        console.log("# " + count + " cmds found");
        console.log("# offset=" + (file.tell() - 2));

        for (let i = 0; i < count; i++) {
            const cmdid = file.readInt8();
            const arg1 = file.readInt32LE();
            const arg2 = file.readInt32LE();
            const cmdname = "UNKNOWN_" + cmdid;
            const cmdparams = "ARG1=" + arg1;

            let cmdopts = "( ";

            if ((arg2 & 0x100) != 0) {
                cmdopts += "USE ";
            }

            if ((arg2 & 0xF) != 0) {
                cmdopts += "ENTER_FROM( ";
                if ((arg2 & 0x8) != 0) {
                    cmdopts += "WEST ";
                }
                if ((arg2 & 0x4) != 0) {
                    cmdopts += "SOUTH ";
                }
                if ((arg2 & 0x2) != 0) {
                    cmdopts += "EAST ";
                }
                if ((arg2 & 0x1) != 0) {
                    cmdopts += "NORTH ";
                }
                cmdopts += ") ";
            }

            if ((arg2 & 0xF0) != 0) {
                cmdopts += "LEAVE_TO( ";
                if ((arg2 & 0x80) != 0) {
                    cmdopts += "EAST ";
                }
                if ((arg2 & 0x40) != 0) {
                    cmdopts += "NORTH ";
                }
                if ((arg2 & 0x20) != 0) {
                    cmdopts += "WEST ";
                }
                if ((arg2 & 0x10) != 0) {
                    cmdopts += "SOUTH ";
                }
                cmdopts += ") ";
            }

            if ((arg2 & 0x200) != 0) {
                cmdopts += "MODIFYWORLD ";
            }

            cmdopts += "IF_VAR( " + ((arg2 & 0xFFFF0000) >> 16) + " ) ";
            cmdopts += ")";

            switch (cmdid) {
                case 1:
                    cmdname = "TELEPORT";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " DIR=" + ((arg1 >> 20) & 0x3FF); 
                    break;
                case 2:
                    cmdname = "CH_LEVEL";
                    cmdparams = "LEVEL_NAME=" + (arg1 & 0xFF) + " UNKN=" + ((arg1 & 2147483647) >> 8) + " COMPLETE=" + (arg1 & -2147483648);
                    break;
                case 3:
                    cmdname = "RUNPOS";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " VAL=" + ((arg1 >> 16) & 0xFFFF); 
                    break;
                case 4:
                    cmdname = "STBARMSG";
                    cmdparams = "STR=" + arg1;
                    break;
                case 5:
                    cmdname = "PLAYER_DAMAGE";
                    cmdparams = "DMG=" + arg1;
                    break;
                case 7:
                    cmdname = "SHOW_THING";
                    cmdparams = "ID=" + (arg1 & 0xFF) + " STATE=" + ((arg1 >> 8) & 0xFF); 
                    break;
                case 8:
                    cmdname = "SHOW_MONOLOG";
                    cmdparams = "STR=" + arg1;
                    break;
                case 9:
                    cmdname = "AUTOMAP";
                    cmdparams = "";
                    break;
                case 10:
                    cmdname = "PASSCODE_OR_HALT";
                    cmdparams = "PASS=" + (((arg1) & 0xFF) + 1) + " MSG=" + (((arg1 >> 8) & 0xFF) + 1);
                    break;
                case 11:
                    cmdname = "SET_VAR";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " VAL=" + ((arg1 >> 16) & 0xFFFF); 
                    break;
                case 12:
                    cmdname = "DOOR_LOCK";
                    cmdparams = "LINE=" + arg1;
                    break;
                case 13:
                    cmdname = "DOOR_UNLOCK";
                    cmdparams = "LINE=" + arg1;
                    break;
                case 15:
                    cmdname = "DOOR_OPEN";
                    cmdparams = "LINE=" + arg1;
                    break;
                case 16:
                    cmdname = "DOOR_CLOSE";
                    cmdparams = "LINE=" + arg1;
                    break;
                case 18:
                    cmdname = "HIDE_THINGS_AT";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF); 
                    break;
                case 19:
                    cmdname = "INC_VAR";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF); 
                    break;
                case 20:
                    cmdname = "DEC_VAR";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF); 
                    break;
                case 21:
                    cmdname = "INC_STAT";
                    cmdparams = "AMOUNT=" + ((arg1 >> 8) & 0xFF) + " STAT=" + ((arg1) & 0xFF);
                    break;
                case 22:
                    cmdname = "DEC_STAT";
                    cmdparams = "AMOUNT=" + ((arg1 >> 8) & 0xFF) + " STAT=" + ((arg1) & 0xFF);
                    break;
                case 23:
                    cmdname = "CHECK_STAT_OR_SHOW_MONOLOG";
                    cmdparams = "STR=" + ((arg1 >> 16) & 0xFFFF) + " AMOUNT=" + ((arg1 >> 8) & 0xFF) + " STAT=" + ((arg1) & 0xFF);
                    break;
                case 24:
                    cmdname = "STBARMSG_ALT";
                    cmdparams = "STR=" + arg1;
                    break;
                case 25:
                    cmdname = "EXPLODE";
                    cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " TYPE=" + ((arg1 >> 24) & 0xFF); 
                    break;
                case 26:
                    cmdname = "SHOW_MONOLOG_WITH_SOUND";
                    cmdparams = "STR=" + arg1;
                    break;
                case 27:
                    cmdname = "NEXT_LEVEL_START_POS";
                    cmdparams = "ARG1=" + arg1 + " X=" + ((arg1 >> 8) & 0xFF) + " Y=" + ((arg1 >> 16) & 0xFF);
                    break;
                case 29:
                    cmdname = "EARTHQUAKE";
                    cmdparams = "TIME=" + (arg1 & 0xFFFFFF) + " INTENSITY=" +  ((arg1 >> 24) & 0xFF);
                    break;
                case 30:
                    cmdname = "FLOORCOLOR";
                    cmdparams = "COLOR=" + (arg1 & 0xFFFFFF);
                    break;
                case 31:
                    cmdname = "CEILCOLOR";
                    cmdparams = "COLOR=" + (arg1 & 0xFFFFFF);
                    break;
                case 32:
                    cmdname = "TAKE_OR_RETURN_WEAPONS";
                    cmdparams = "ACTION=" + arg1;
                    break;
                case 33:
                    cmdname = "SHOP";
                    cmdparams = "ID=" + arg1;
                    break;
                case 34:
                    cmdname = "SET_THING_STATE";
                    cmdparams += " X=" + (arg1 & 0x1F) + " Y=" + ((arg1 >> 5) & 0x1F) + " VAL=" + ((arg1 >> 16) & 0xFF); 
                    break;
                case 35:
                    cmdname = "PARTICLE";
                    cmdparams = "COLOR=" + (arg1 & 0xFFFFFF) + " EFFECT=" +  ((arg1 >> 24) & 0xFF);
                    break;
                case 36:
                    cmdname = "FRAME";
                    break;
                case 37:
                    cmdname = "SLEEP";
                    cmdparams = "MSEC=" + arg1;
                    break;
                case 38:
                    cmdname = "UNKNOWN_REACTOR_LIFT";
                    break;
                case 39:
                    cmdname = "SHOW_MONOLOG_IF_LEVEL_UNFINISHED";
                    cmdparams = "STR=" + (arg1 & 0xFFFF) + " LEVEL_ID=" +  ((arg1 >> 8) & 0xFFFF);
                    break;
                case 40:
                    cmdname = "NOTEBOOK_ADD";
                    cmdparams = "STR=" + arg1;
                    break;
                case 41:
                    cmdname = "REQUIRE_KEYCARD";
                    cmdparams = "KEY=" + arg1;
                    break;

                default:
                    break;
            }

            console.log(`L_${i}: ${cmdopts} ${cmdname} ${cmdparams}`);
        }
    }
}


App.main(process.argv);