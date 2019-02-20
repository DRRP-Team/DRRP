/**
 * Copyright (c) PROPHESSOR 2019
 */

const json = require('./translation.json');

const translators = {}; // { nickname: [ rating, count ] }

// Get the list of translators' nicknames
for (const string of json) {
    const translations = string[2];

    for (const translation of translations) {
        if(!translators[translation[1]]) {
            translators[translation[1]] = [translation[2], 1];
        } else {
            translators[translation[1]][0] += translation[2];
            translators[translation[1]][1]++;
        }
    }
}

const output = [];

// Calcualte quality and prepare to output
for (const nickname in translators) {
    const translator = translators[nickname];

    output.push([nickname, translator[1], translator[0], Math.round((translator[0] / translator[1]) * 100)]);
}

output.sort((a, b) => b[3] - a[3]);

// Display the beautiful table
// You may just console.log(output.join('\n'));
const Table = require('cli-table');
const table = new Table({
    head: ['#', 'Ник', 'Переведено', 'Рейтинг', 'Качество (%)']
});

for (const i in output) {
    const line = output[i];

    table.push([+i + 1, ...line]);
}

console.log(table.toString());