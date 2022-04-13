/**
 * Copyright (c) 2022 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

const regexp = monster => new RegExp(`SpawnForced\\s?\\("(${monster})",\\s?getMediumX\\((\\d+)\\),\\s?getMediumY\\((\\d+)\\),\\s(\\d+),\\s(\\d+)\\);`, 'g');
const regreplace = `SpawnWithFog("$1", getMediumX($2), getMediumY($3), $4, $5);`;

const regexp2 = monster => new RegExp(`SpawnForced\\s?\\("(${monster})",\\s?getMediumX\\((\\d+)\\),\\s?getMediumY\\((\\d+)\\),\\s(\\d+)\\);`, 'g');
const regreplace2 = `SpawnWithFog("$1", getMediumX($2), getMediumY($3), $4, 0);`;

const monsterList = ['Troop', 'Impling', 'Malwrath', 'Ghoul', 'ZombieCpt', 'Wretched', 'DemonWolf', 'Apollyon', 'Nightmare', 'Belphegor', 'DRRPPainElemental', 'Assassin', 'BullDemon', 'ZombiePvt', 'Hellhound', 'ZombieLt', 'Phantom', 'Infernis', 'Behemoth',
  'DRRPCacodemon',
  'Pinky',
  'Fiend',
  'DRRPArchVile',
  'Cerberus',
  'Druj',
  'DRRPRevenant',
  'DRRPBaronOfHell',
  'DRRPImp',
];

const files = ['INTRO.acs', 'SEC1.acs', 'SEC2.acs', 'SEC3.acs', 'SEC4.acs', 'SEC5.acs', 'SEC6.acs', 'SEC7.acs', 'XHUB.acs', 'REAC.acs'];

const fs = require('fs');

for (const filename of files) {
  const fullFile = `maps/${filename}`;
  console.log(fullFile);
  let file = fs.readFileSync(fullFile, 'utf-8');

  for (const monster of monsterList) {
    file = file.replace(regexp(monster), regreplace);
    file = file.replace(regexp2(monster), regreplace2);
  }

  fs.writeFileSync(fullFile, file, 'utf-8');
}