/* Copyright (c) 2017-2018 DRRP Team
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 *
 * Данная утилита позволяет преобразовать строки из (.str) формата в LANGUAGE файл.
 * Эта программа заменяет собой stringparser.cpp и stringparser.js
 */

"use strict";

const fs = require("fs");
const ByteUtils = require("./ByteTools");

const dict = {
    "A": "А",
    "B": "В",
    "C": "С",
    "D": "Д",
    "E": "Е",
    "F": "Г",
    "G": "Ж",
    "H": "Н",
    "I": "Щ",
    "J": "П",
    "K": "К",
    "L": "Л",
    "M": "М",
    "N": "Ц",
    "O": "Ф",
    "P": "Р",
    "Q": "Ю",
    "R": "Я",
    "S": "Б",
    "T": "Т",
    "U": "И",
    "V": "Ч",
    "W": "Ш",
    "X": "Х",
    "Y": "У",
    "Z": "Э",

    "a": "а",
    "c": "с",
    "d": "д",
    "e": "е",
    "f": "г",
    "g": "ж",
    "h": "н",
    "i": "ь",
    "j": "п",
    "k": "к",
    "l": "l",
    "m": "м",
    "n": "ц",
    "o": "о",
    "p": "р",
    "q": "ю",
    "r": "я",
    "s": "б",
    "t": "т",
    "u": "и",
    "v": "ч",
    "w": "ш",
    "x": "х",
    "y": "у",

    "\xc0": "в",
    "\xc1": "й",
    "\xc2": "л",
    "\xc3": "ф",
    "\xc4": "ф",
    "\xc5": "э",
    "\xc6": "з",
    "\xc7": "щ",
    "\xc8": "э",
    "\xc9": "s",
    "\xca": "ы",
    "\xcb": "d",
    "\xcc": "t",
    "\xcd": "h",
    "\xce": "r",
    "\xcf": "g",
    "\xd0": "J",
    "\xd1": "D",
    "\xd2": "F",
    "\xd3": "u",
    "\xd4": "W",
    "\xd5": "L",
    "\xd6": "Й",
    "\xd7": "I",
    "\xd8": "S",
    "\xd9": "f",
    "\xda": "w",
    "\xdb": "j",
    "\xdc": "i",
    "\xdd": "n",
    "\xde": "&",
    "\xdf": "з",
    "ë": "з",
    "0": "О"
};

const ascii2win1251 = str => str.split("").map(e => dict[e] || e).join("");

if (process.argv.length !== 6) return console.error("Использовать так: <источник.bsp> <выходной файл> <режим: rus, eng> <префикс, например, SEC1>");

const fileFrom  = process.argv[2];
const fileTo    = process.argv[3];
const mode      = process.argv[4];
const prefix    = process.argv[5];
if (mode !== "rus" && mode !== "eng") return console.error("Неверный режим! Доступны только 'rus' и 'eng'");

const file = new ByteUtils(fs.readFileSync(fileFrom));
const count = file.readUInt16LE();

let out = "";

for (let i = 0; i < count; i++) {
    const size = file.readUInt16LE();
    const val = JSON.stringify(file.buffer.toString("latin1", file.tell(), file.tell() + size));

    file.seek(size, "CUR");

    out += `DRRP_${prefix}_${i} = `;

    if (mode === "rus") out += ascii2win1251(val).replace(/\|/g, "\\n");
    else out += val.replace(/\|/g, "\\n");

    out += ";\n";
}

fs.writeFileSync(fileTo, out, "utf8");