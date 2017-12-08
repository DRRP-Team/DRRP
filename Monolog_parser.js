var fs = require('fs');//Библиотека для работы с файлами

if(process.argv.length !== 4) return console.error("Использовать так: <источник> <выходной файл>");
var fileFrom = process.argv[2]; //От куда читать
var fileTo = process.argv[3];//Куда писать

var file = fs.readFileSync(fileFrom); //Читаем файл

var replics = file.toString().split("\n\n"); //Разбиваем по репликам
var output = ""; //Выходная переменная
for(var i in replics){
    var tmp = replics[i].replace(/-\n/g,'');
    tmp = tmp.replace(/\n/g,' ');
    output += "\ncase "+i+":";
    output += '\n\tprint(s:"'+tmp+'");';
    output += "\nbreak;\n";
}

fs.writeFileSync(fileTo, output);// Записываем выхлоп в файл
//console.log(output); //И выводим на экран