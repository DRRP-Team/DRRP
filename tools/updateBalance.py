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
GID_MONSTERS_DAMAGES = 1366787516

# From DRRP root
FILE_MONSTERS = '/zscript/Monsters.zsc'

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

# Update monsters damages

def toInt(*args):
    return List(args).map(lambda val: val and int(val)).filter()

def getMonstersDamages():
    ''' { monsterclass: List(...levelhealth) } '''
    rows = List(getCsvPage(GID_MONSTERS_DAMAGES))

    header, l1min, l2min, l3min, l1max, l2max, l3max = List(rows[:7]).map(List)

    assert((header[0], l1min[0], l2min[0], l3min[0], l1max[0], l2max[0], l3max[0]) == ('Class', '1 Level Min', '2 Level Min', '3 Level Min', '1 Level Max', '2 Level Max', '3 Level Max'))

    classes = {}

    for i, classname in header.filter().items():
        if classname == 'Class': continue

        monsterclass = List()

        monsterclass.append(toInt(l1min[i], l1max[i]))
        monsterclass.append(toInt(l2min[i], l2max[i]))
        monsterclass.append(toInt(l3min[i], l3max[i]))

        classes[classname] = monsterclass

    debug(classes)

    return classes

def generateMonstersDamagesTable():
    ''' List<[monsterclass: str, monsterlevelid: int, damagetype: str, damage: int]> '''
    table = List()

    monsters = getMonstersDamages()

    for monsterclass in monsters:
        monster = monsters[monsterclass]

        for levelid, damages in monster.items():
            if not damages: continue
            table.append([monsterclass, levelid + 1, 'MinDamage',damages[0]])
            table.append([monsterclass, levelid + 1, 'MeleeMinDamage',damages[0]])
            table.append([monsterclass, levelid + 1, 'MaxDamage',damages[1]])
            table.append([monsterclass, levelid + 1, 'MeleeMaxDamage',damages[1]])
            debug(table[-4:])

    return table

def updateMonstersDamages():
    damage_table = generateMonstersDamagesTable()

    with open(DIR + FILE_MONSTERS, 'r') as file:
        content = file.read()

    for monsterclass, monsterlevel, damagetype, damage in damage_table:
        content = replaceValue(content, damagetype, monsterclass, monsterlevel, damage)

    with open(DIR + FILE_MONSTERS, 'w') as file:
        file.write(content)

    print("Monsters Damages Updated!")

# Execute

updateMonstersHealth()
updateMonstersDamages()