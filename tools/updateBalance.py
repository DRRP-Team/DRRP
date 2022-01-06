#!/usr/bin/python3

import re
import csv
import sys
import requests
import proph
from proph import *
proph.DEBUG = False

CSV_URL = "https://docs.google.com/spreadsheets/d/1iSDhgSsMNdXCU_ly_6RKTImMCVE9hu1L2Mn4_El_yRs/export?format=csv&id=1iSDhgSsMNdXCU_ly_6RKTImMCVE9hu1L2Mn4_El_yRs"
GID_MONSTERS_HEALTH = 47473635

# From DRRP root
FILE_MONSTERS = '/actors/Monsters.dec'

# Absolute path to DRRP root
DIR = sys.path[0] + '/..'

def getCsvPageUrl(gid):
    return CSV_URL + "&gid=%d" % gid

def getCsvPage(gid):
    response = requests.get(getCsvPageUrl(gid))
    
    if not response.ok: raise Exception("Check the url and page id")

    return csv.reader(response.iter_lines(decode_unicode=True))

def getRegex(prop, monsterclass, monsterlevel): return '(\d+)(;?\s*)//(\s*%%%s %d %s%%)' % (monsterclass, monsterlevel, prop)

def getRegexReplace(new_value): return '%d\\2//\\3' % new_value

def replaceValue(filecontent, prop, monsterclass, monsterlevel, new_value):
    return re.sub(getRegex(prop, monsterclass, monsterlevel), getRegexReplace(new_value), filecontent)

# Update monsters health

def getMonstersHealth():
    ''' { monsterclass: List(...levelhealth) } '''
    rows = List(getCsvPage(GID_MONSTERS_HEALTH))

    header, level1, level2, level3 = rows.map(List)

    assert((header[0], level1[0], level2[0], level3[0]) == ('Class', '1 Level', '2 Level', '3 Level'))

    classes = {}

    for i, classname in header.items():
        if classname == 'Class': continue

        classes[classname] = (List([level1[i], level2[i], level3[i]])
                              .map(lambda val: val and int(val))
                              .filter())

    debug(classes)

    return classes

def generateMonstersHealthTable():
    ''' List<[monsterclass: str, monsterlevelid: int, newmonsterhealth: int]> '''
    table = List()

    monsters = getMonstersHealth()

    for monsterclass in monsters:
        monster = monsters[monsterclass]

        for levelid, health in monster.items():
            table.append([monsterclass, levelid + 1, health])
            debug(table[-1])

    return table

def updateMonstersHealth():
    health_table = generateMonstersHealthTable()

    with open(DIR + FILE_MONSTERS, 'r') as file:
        content = file.read()

    for row in health_table:
        content = replaceValue(content, 'Health', *row)

    with open(DIR + FILE_MONSTERS, 'w') as file:
        file.write(content)

    print("Monsters Health Updated!")


updateMonstersHealth()
