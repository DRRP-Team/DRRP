//1 level = 10259-10299
//2 level = 10300-10358
//3 level = 10359-10400



ACTOR MBFHelperDog1 : MBFHelperDog replaces MBFHelperDog 10246 {
//$Category DMonsters
	Health 25
}
ACTOR RedDog : MBFHelperDog1 10346 {
//$Category DMonsters
	Health 50
	Translation "64:79=32:47"
}

// Zombies

actor ZombiePvt : ZombieMan 10274 {
	Health 10
	Translation "16:47=152:159"
}

actor ZombieLt : ZombieMan 10275 {
	Health 30
	Translation "0:255=192:207"
}

//Demon

actor BullDemon : Demon 10259 {
//$Category DMonsters
	Health 75
	Translation "16:47=128:143", "168:191=144:151"
	//Translation "16:47=[255,255,255]:[140,130,43]", "168:191=[0,0,0]:[255,255,255]"
}

actor Belphegor : Demon 10359 {
//$Category DMonsters
	Health 225
	Translation "16:31=192:204", "32:32=198:198", "33:33=199:199", "34:34=202:202", "35:35=204:204", "36:39=201:207", "40:47=240:242"
}

//Imp

actor Impling : DoomImp 10260 {
//$Category DMonsters
	Health 30
	Translation "64:79=112:127", "61:63=112:118"
}

actor ImpLord : DoomImp 10360 {
//$Category DMonsters
	Health 90
	Translation "64:79=170:191"
}


actor YellowCacodemon : Cacodemon 10262 {
//$Category DMonsters
	Health 200
	Translation "173:191=[215,222,0]:[107,65,0]", "16:47=[214,190,123]:[107,81,0]", "167:167=164:164", "192:207=32:39", "240:247=40:47", "112:127=16:47"
}

//Lost soul

actor Phantom : LostSoul 10263 {
//$Category DMonsters
	Health 50
	Translation "128:151=152:159", "64:79=152:159"
}

actor Nightmare : LostSoul 10363 {
//$Category DMonsters
	Health 150
	Translation "128:151=195:207", "64:79=200:207"
}

//Chaingunner

actor Troop : ChaingunGuy 10264 {
//$Category DMonsters
	Health 35
	Translation "32:47=163:167"
}


//Viles


actor DRRPVile : ArchVile replaces ArchVile {
	Scale 0.95
}

actor Apollyon : DRRPVile 10273 {
//$Category DMonsters
	Health 350
	Translation "16:223=%[0.00,0.00,0.00]:[2.00,0.78,0.78]"
}

//Revenant

actor DRRPRevenant : Revenant replaces Revenant {
	Scale 0.95
}

//Minecraft

actor DRRPCubus : Fatso replaces Fatso {
	Scale 0.92
	Radius 30
	Height 62
}