#!/usr/bin/python3

import os

files = []

for (dirpath, dirnames, filenames) in os.walk('defsmallfont'):
    #f.extend(filenames)
    files = filenames
    break

for file in files:
    newname = f'00{hex(int(file[0:4]))[2:]}.png'
    os.system(f'cp defsmallfont/{file} out/{newname}')
