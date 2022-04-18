#!/usr/bin/python3

# Russian big letters

import os

FROM1 = 0xe0
FROM2 = 0xff

TO1 = 0x410
TO2 = 0x42F

files = []

for id in range(FROM2 - FROM1 + 1):
    FROM = f'00{hex(FROM1 + id)[2:]}.png'
    TO = f'0{hex(TO1 + id)[2:]}.png'
    print(f'{FROM} -> {TO}')
    os.system(f'mv defsmallfont/{FROM} defsmallfont/{TO}')
