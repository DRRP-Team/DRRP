const fs = require('fs');//Библиотека для работы с файлами

if(process.argv.length !== 4) return console.error("Использовать так: <источник> <выходной файл>");
const fileFrom = process.argv[2]; //От куда читать
const fileTo = process.argv[3];//Куда писать

const file = fs.readFileSync(fileFrom); //Читаем файл

const replics = file.toString().split("\n\n"); //Разбиваем по репликам
let output = ""; //Выходная переменная
for(const i in replics){
    let tmp = replics[i].replace(/-\n/g,'');
    tmp = tmp.replace(/\n/g,' ');
    output += `\ncase ${i}:`;
    output += `\n\tprint(s:"${tmp}");`;
    output += "\nbreak;\n";
}

fs.writeFileSync(fileTo, output);// Записываем выхлоп в файл
console.log(output); //И выводим на экран
