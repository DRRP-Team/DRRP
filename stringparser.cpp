/* Copyright (c) 2017 DRRP Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 * 
 * Данная утилита позволяет сконвертировать строки из оригинального DooM RPG формата в текстовый (.txt).
 * После конвертации используйте stringparser.js
 */

#include <fstream>
#include <iostream>
#include <cstdint>

using namespace std;
int main(int argc, const char **argv) {
    if(argc != 3) {
        cout << "Usage: " << argv[0] << " <input.str> <output.str>\n";
        return 1;
    }
    ifstream ifs(argv[1], ifstream::binary);
    ofstream ofs(argv[2], ofstream::trunc);
    uint16_t count;
    ifs.read((char *) &count, sizeof(uint16_t));
    for (uint16_t i = 0; i < count; i++)
    {
        uint16_t size;
        ifs.read((char *)&size, sizeof(uint16_t));
        char *s = new char[size + 1];
        ifs.read(s, size);
        s[size] = 0;
        for (uint16_t i = 0; i < size; i++) {
            if(s[i] == '|') s[i] = '\n';
        }
        ofs << s << "\n\n";
    }
    return 0;
}