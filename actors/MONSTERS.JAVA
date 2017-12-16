
//Oh... Why? You did broke all...
//1 level = 10259-10299
//2 level = 10300-10399
//3 level = 10400-10499
// Ok... Monsters: 10259 - 10499


//Monsters
//Dogs
	//Hellhound - 10246
	//Cerberus - 10346
	//DemonWolf - 10446
//Zombies
	//ZombiePvt 10274
	//ZombieLt 10374
	//ZombieCpt 10474
//Demons
	//BullDemon 10259
	//Pinky 10459 FIXME:
	//Belphegor 10359
//Imps
	//Impling 10260
	//Imp 10261 FIXME:
	//ImpLord 10360
//Cacos
	//Malrath 10262
	//Cacodemon1 10362
	//Wretched 10462
//Lost souls
	//Phantom 10263
	//LostSoul1 10463 FIXME:
	//Nightmare 10363
//Chaingunners
	//Troop 10264
	//Commando 10364
	//Assassin 10464
//Viles
	//Infernis 10273
	//ArchVile1 10373
	//Apollyon 10473
//Revenants
	//Ghoul 10277
	//Fiend 10377
	//Revenant1 10477
//Cubuses
	//Behemoth 10278
	//Mancubus 10378
	//Druj 10478
//Paines
	//Beholder TODO:
	//Rahovart
	//PainElemental1 TODO:
//Goats
	//Ogre TODO:
	//HellKnight1 TODO:
	//BaronOfHell TODO:
//Bosses
	//Kronos TODO:
	//Cyberdemon TODO:

ACTOR Hellhound : MBFHelperDog replaces MBFHelperDog 10246 {
//$Category DMonsters
	Health 25
}
ACTOR Cerberus : MBFHelperDog 10346 {
//$Category DMonsters
	Health 50
	Translation "64:79=32:47"
}
ACTOR DemonWolf : MBFHelperDog 10446 {
//$Category DMonsters
	Health 50
	Translation "64:79=32:47"
}

// Zombies

ACTOR ZombiePvt : ZombieMan 10274 {
	Health 10
	Translation "16:47=152:159"
}

ACTOR ZombieLt : ShotgunGuy 10374 {
	Health 30
	Translation "0:255=192:207"
}

ACTOR ZombieCpt 10474 {
	Health 30
	Translation "128:143=176:189", "144:151=178:191", "16:47=180:191", "152:159=180:191", "112:127=176:191"
}

//Demon

ACTOR BullDemon : Demon 10259 {
//$Category DMonsters
	Health 75
	Translation "16:47=128:143", "168:191=144:151"
	//Translation "16:47=[255,255,255]:[140,130,43]", "168:191=[0,0,0]:[255,255,255]"
}

ACTOR Pinky : Demon 10459 { //FIXME: id
//$Category DMonsters
	Health 75
	Translation "16:47=128:143", "168:191=144:151"
	//Translation "16:47=[255,255,255]:[140,130,43]", "168:191=[0,0,0]:[255,255,255]"
}

ACTOR Belphegor : Demon 10359 {
//$Category DMonsters
	Health 225
	Translation "16:47=%[0.00,0.09,0.20]:[0.49,1.17,2.00]"
}

//Imp

ACTOR Impling : DoomImp 10260 {
//$Category DMonsters
	Health 30
	Translation "64:79=112:127", "61:63=112:118"
}

ACTOR Imp : DoomImp replaces DoomImp 10261 {
//$Category DMonsters
	Health 30
	Translation "64:79=112:127", "61:63=112:118"
}

ACTOR ImpLord : DoomImp 10360 {
//$Category DMonsters
	Health 90
	Translation "64:79=170:191"
}

// Cacos

ACTOR Malrath : Cacodemon 10262 {
//$Category DMonsters
	Health 200
	Translation "168:191=160:167", "16:47=160:166", "112:127=176:191", "192:207=176:191", "240:247=191:191" //"173:191=[215,222,0]:[107,65,0]", "16:47=[214,190,123]:[107,81,0]", "167:167=164:164", "192:207=32:39", "240:247=40:47", "112:127=16:47"
}

ACTOR Cacodemon1 : Cacodemon replaces Cacodemon 10362 {
//$Category DMonsters
	Health 400
}

ACTOR Wretched : Cacodemon 10462 {
//$Category DMonsters
	Health 800
	Translation "168:191=193:207", "16:47=193:201", "112:127=160:167", "192:207=168:191", "160:167=207:207", "223:223=207:207"
}

//Lost soul

ACTOR Phantom : LostSoul 10263 {
//$Category DMonsters
	Health 50
	Translation "128:151=152:159", "64:79=152:159"
}

ACTOR LostSoul1: LostSoul replaces LostSoul 10463 { //FIXME: id
//$Category DMonsters
	Health 100
	Translation "128:151=152:159", "64:79=152:159"
}

ACTOR Nightmare : LostSoul 10363 {
//$Category DMonsters
	Health 150
	Translation "128:151=195:207", "64:79=200:207"
}

//Chaingunner
ACTOR Troop : ChaingunGuy 10264 {
//$Category DMonsters
	Health 35
	Translation "32:47=163:167"
}

ACTOR Commando : ChaingunGuy 10364 {
//$Category DMonsters
	Health 70
	Translation "16:47=192:207"
}

ACTOR Assassin : ChaingunGuy replaces ChaingunGuy 10464 {
//$Category DMonsters
	Health 70
	Translation "16:47=192:207" //TODO:
}


//Viles


ACTOR DRRPVile : ArchVile replaces ArchVile {
	Scale 0.95
}

ACTOR Infernis : DRRPVile 10273 {
//$Category DMonsters
	Health 250
	Translation "0:15=198:198", "208:223=197:197", "236:239=198:198", "13:15=198:198", "48:79=195:195", "128:151=197:197", "64:79=196:196"
}

ACTOR ArchVile1: DRRPVile 10373 {
//$Category DMonsters
	Health 300
}

ACTOR Apollyon : DRRPVile 10473 {
//$Category DMonsters
	Health 350
	Translation "64:79=32:47", "48:63=16:31", "128:143=16:39", "236:239=44:47", "13:15=45:47", "144:151=32:39" // "16:223=%[0.00,0.00,0.00]:[2.00,0.78,0.78]"
}

//Revenant

ACTOR DRRPRevenant : Revenant replaces Revenant {
//$Category DMonsters
	Scale 0.95
}

ACTOR Ghoul : DRRPRevenant 10277 {
//$Category DMonsters
	Translation "128:143=112:127", "144:151=120:127", "48:79=112:127", "160:167=114:126", "13:15=126:127", "232:235=124:124", "20:20=116:116", "236:239=125:127", "21:47=160:16
}

ACTOR Fiend : DRRPRevenant 10377 {
//$Category DMonsters
	Translation "128:159=192:207", "48:79=192:207", "160:167=240:240", "236:239=243:243", "170:170=240:240", "21:47=176:191", "20:20=207:207", "168:191=176:191", "184:184=242:242", "9:9=207:207", "144:151=192:207", "13:15=241:241"
}

ACTOR Revenant1 : DRRPRevenant 10477 {
//$Category DMonsters
}

//Minecraft - Cube world

ACTOR DRRPCubus : Fatso replaces Fatso {
	Scale 0.92
	Radius 30
	Height 62
}

ACTOR Behemoth : DRRPCubus 10278 {
//$Category DMonsters
	Translation "48:79=16:45", "144:151=16:45", "144:151=16:45", "236:239=46:46", "13:15=46:46", "141:141=46:46", "138:138=46:46", "142:142=45:45", "143:143=45:45", "16:47=176:189"
}

ACTOR Mancubus : DRRPCubus 10378 {
//$Category DMonsters
}

ACTOR Druj : DRRPCubus 10478 {
//$Category DMonsters
	Translation "48:79=16:45", "144:151=16:45", "144:151=16:45", "236:239=46:46", "13:15=46:46", "141:141=46:46", "138:138=46:46", "142:142=45:45", "143:143=45:45", "16:47=176:189"
}

//Pain
ACTOR Behemoth : PainElemental 10278 {
//$Category DMonsters
	Translation "48:79=16:45", "144:151=16:45", "144:151=16:45", "236:239=46:46", "13:15=46:46", "141:141=46:46", "138:138=46:46", "142:142=45:45", "143:143=45:45", "16:47=176:189"
}