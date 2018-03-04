/* Copyright (c) 2017 DRRP Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 * 
 * Данная утилита позволяет преобразовать строки из сконвертированного (.txt) формата в скриптовый (.acs).
 * Используйте эту программу после stringparser.cpp
 */

var fs = require('fs');//Библиотека для работы с файлами

if(process.argv.length !== 4) return console.error("Использовать так: <источник> <выходной файл>");
var fileFrom = process.argv[2]; //От куда читать
var fileTo = process.argv[3];//Куда писать

var file = fs.readFileSync(fileFrom); //Читаем файл

var replics = file.toString().split("\n\n"); //Разбиваем по репликам
var output = ""; //Выходная переменная
for(var i in replics){
    var tmp = replics[i];
    tmp = tmp.replace(/\n/g,'\\n').replace(/"/g, '\\"'); //Заменяем фактические переносы строк на теоретические и экранируем кавычки
    output += "case "+i+":";
    output += '\n\tACS_NamedExecute("window", 0, "'+tmp+'");'; //Используем скрипт окна для отображения текста
    output += "\n\tbreak;\n";
}

fs.writeFileSync(fileTo, output);// Записываем выхлоп в файл
//console.log(output); //И выводим на экран
