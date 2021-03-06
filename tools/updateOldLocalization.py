#!/usr/bin/python3
import csv
import sys

DIR = sys.path[0] + '/../'

NEW = 'LANGUAGE.CSV'

ENU = 'LANGUAGE.ENU'
RUS = 'TEMP/GZDoom3xPatch/LANGUAGE.RUS'

COPYRIGHT = '''/**
 * Copyright (c) 2018-2021 DRRP-Team
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

'''

enu = open(DIR + ENU, 'w')
rus = open(DIR + RUS, 'w', encoding='cp1251', errors='replace', newline='')

enu.write(COPYRIGHT)
rus.write(COPYRIGHT)

enu.write('[enu default]\n')
rus.write('[rus]\n')

enu.write('\n')
rus.write('\n')

with open(DIR + NEW) as _in:
    rows = csv.reader(_in)
    rows.__next__()
    rows.__next__()
    
    for row in rows:
        _enu, sid, comment, _, _rus = row

        enu.write(f'    {sid} = "{_enu}";' + (f' // {comment}' if comment else '') + '\n')
        if rus:
            rus.write(f'    {sid} = "{_rus}";' + (f' // {comment}' if comment else '') + '\n')

print('Updated!')
