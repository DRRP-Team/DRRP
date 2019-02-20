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

for (const nickname in translators) {
    const translator = translators[nickname];

    console.log('for', nickname, 'in', translators);
    output.push([nickname, translator[1], translator[0], Math.round((translator[0] / translator[1]) * 100)]);
}

output.sort((a, b) => b[3] - a[3]);

console.info('Рейтинг переводчиков');
console.info('\tНик\tПереведено\tРейтинг\tКачество');

for (const i in output) {
    const line = output[i];

    console.info(`${+i + 1}\t${line.join('\t')}%`);
}