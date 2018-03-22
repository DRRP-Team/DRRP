//Mask: 10 + <monster_skill + 1> + <ID>: e. g., 2 monster skill = 10300-10399.
//So, Monsters: 10259-10499.

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
	//DRRPPainElemental 10279
	//Rahovart 10379
	//Beholder 10479
//Goats
	//BaronOfHell 10280
	//HellKnight1 10380
	//Ogre 10480
//Bosses
	//Kronos TODO:
	//Cyberdemon TODO:



// Dogs -------------------------------------------------------------------------------

Actor Hellhound : MBFHelperDog replaces MBFHelperDog 10246 {
//$Category DMonsters
	Health 25
	DamageFactor DRRPShotgunDmg, 2.0
	DamageFactor DRRPPlasmaDmg, 2.0
}

Actor Cerberus : MBFHelperDog 10346 {
//$Category DMonsters
	Health 50
	Translation "64:79=32:47"
}

Actor DemonWolf : MBFHelperDog 10446 {
//$Category DMonsters
	Health 50
	Translation "64:79=32:47"
}


// Zombies ----------------------------------------------------------------------------

Actor ZombieManDRRP: ZombieMan replaces ZombieMan
{
	DropItem "Clip", 0, 0
	DamageFactor DRRPAxeDmg, 2.0
}

Actor ShotgunGuyDRRP: ShotgunGuy replaces ShotgunGuy
{
	DropItem "Shotgun", 0, 0
	DamageFactor DRRPAxeDmg, 2.0
}

Actor ZombiePvt : ZombieMan 10274 {
	Health 10
	Translation "16:47=152:159"
}

Actor ZombieLt : ShotgunGuy 10374 {
	Health 30
	Translation "0:255=192:207"
}

Actor ZombieCpt : ZombieMan 10474 {
	Health 60
	Translation "128:143=176:189", "144:151=178:191", "16:47=180:191", "152:159=180:191", "112:127=176:191"
}


// Demons -----------------------------------------------------------------------------

Actor BullDemon: Demon 10259 {
	//$Category DMonsters
	Health 75
	Translation "16:47=128:143", "168:191=144:151"
	//Translation "16:47=[255,255,255]:[140,130,43]", "168:191=[0,0,0]:[255,255,255]"
}

Actor Pinky: Demon 10459 { //FIXME: id
	//$Category DMonsters
	Health 75
}

Actor Belphegor: Demon 10359 {
	//$Category DMonsters
	Health 225
	Translation "16:47=%[0.00,0.09,0.20]:[0.49,1.17,2.00]"
}


// Imps -------------------------------------------------------------------------------

Actor Impling: DoomImp 10260 {
	//$Category DMonsters
	Health 30
	Translation "64:79=112:127", "61:63=112:118"
}

Actor Imp: DoomImp replaces DoomImp 10261 {
	//$Category DMonsters
	Health 30
}

Actor ImpLord: DoomImp 10360 {
	//$Category DMonsters
	Health 90
	Translation "64:79=170:191"
}


// Cacodemons -------------------------------------------------------------------------

Actor Malrath: Cacodemon 10262 {
	//$Category DMonsters
	Health 200
	Translation "168:191=160:167", "16:47=160:166", "112:127=176:191", "192:207=176:191", "240:247=191:191" //"173:191=[215,222,0]:[107,65,0]", "16:47=[214,190,123]:[107,81,0]", "167:167=164:164", "192:207=32:39", "240:247=40:47", "112:127=16:47"
}

Actor Cacodemon1: Cacodemon replaces Cacodemon 10362 {
	//$Category DMonsters
	Health 400
}

Actor Wretched: Cacodemon 10462 {
	//$Category DMonsters
	Health 800
	Translation "168:191=193:207", "16:47=193:201", "112:127=160:167", "192:207=168:191", "160:167=207:207", "223:223=207:207"
}


// Lost souls -------------------------------------------------------------------------

Actor LostSoul1: LostSoul replaces LostSoul 10463 { //FIXME: id
//$Category DMonsters
	DamageFactor DRRPFireExtDmg, 5.0
	DamageFactor DRRPFireDmg, 0.0
	Health 100
	States
	{
	Death.DRRPFireExtDmg:
	    LSOL F 6 Bright A_Gravity
	    LSOL G 5 Bright A_Scream
	    LSOL H 5 Bright A_NoBlocking
	    LSOL I 5 Bright
	    Stop	
	}
}

Actor Phantom: LostSoul1 10263 {
	//$Category DMonsters
	Health 50
	Translation "128:151=152:159", "64:79=152:159"
}

Actor Nightmare: LostSoul1 10363 {
	//$Category DMonsters
	Health 150
	Translation "128:151=195:207", "64:79=200:207"
}


// Chaingunners -----------------------------------------------------------------------

Actor ChaingunGuyDRRP: ChaingunGuy replaces ChaingunGuy
{
	DropItem "Chaingun", 0, 0
	DamageFactor DRRPPlasmaDmg, 2.0
	States {
		Pain:
	    CPOS G 0 A_Pain
		CPOS G 4 A_MonsterRefire( 40, "PainOut" )
		Goto Missile + 1
	PainOut:
		CPOS G 2
	    Goto See
	}
}

Actor Troop: ChaingunGuyDRRP 10264 {
	//$Category DMonsters
	Health 35
	Translation "32:47=163:167"
}

Actor Commando: ChaingunGuyDRRP 10364 {
	//$Category DMonsters
	Health 70
	Translation "16:47=192:207"
}

Actor Assassin: ChaingunGuyDRRP replaces ChaingunGuy 10464 {
	//$Category DMonsters
	Health 70
	Translation "16:47=192:207" //TODO:
}



// Arch-Viles -------------------------------------------------------------------------

Actor DRRPVile : ArchVile replaces ArchVile {
	DamageFactor DRRPAxeDmg, 7.5
	DamageFactor DRRPFireDmg, 0.0
	Scale 0.95
}

Actor Infernis: DRRPVile 10273 {
//$Category DMonsters
	Health 250
	Translation  "48:79=192:207", "128:143=192:207", "13:15=240:247", "144:151=240:247", "236:239=240:247", "144:151=240:247", "208:223=192:207", "0:2=247:247"
}

Actor ArchVile1: DRRPVile 10373 {
//$Category DMonsters
	Health 300
}

Actor Apollyon: DRRPVile 10473 {
//$Category DMonsters
	Health 350
	Translation "64:79=32:47", "48:63=16:31", "128:143=16:39", "236:239=44:47", "13:15=45:47", "144:151=32:39" // "16:223=%[0.00,0.00,0.00]:[2.00,0.78,0.78]"
}


// Revenants --------------------------------------------------------------------------

Actor DRRPRevenant: Revenant replaces Revenant {
//$Category DMonsters
	Scale 0.75
	States{
		Death:
			SKEL L 7
			SKEL M 7 A_SpawnItem( "Explosion", 0.0, 0.0, False, False )
			SKEL N 7 A_Scream
			SKEL O 7 A_NoBlocking
			SKEL P 7
			SKEL Q -1
			Stop
	}
}

Actor Ghoul: DRRPRevenant 10277 {
//$Category DMonsters
	Translation "128:143=112:127", "144:151=120:127", "48:79=112:127", "160:167=114:126", "13:15=126:127", "232:235=124:124", "20:20=116:116", "236:239=125:127", "21:47=160:16"
}

Actor Fiend: DRRPRevenant 10377 {
//$Category DMonsters
	Translation "128:159=192:207", "48:79=192:207", "160:167=240:240", "236:239=243:243", "170:170=240:240", "21:47=176:191", "20:20=207:207", "168:191=176:191", "184:184=242:242", "9:9=207:207", "144:151=192:207", "13:15=241:241"
}

Actor Revenant1: DRRPRevenant 10477 {
//$Category DMonsters
}


// Mancubuses -------------------------------------------------------------------------
//Dull joke by UsernameAK: "Minecraft -- cube world".

Actor DRRPCubus: Fatso replaces Fatso {
	Scale 0.92
	Radius 30
	Height 62
	DamageFactor DRRPRocketDmg, 2.0
}

Actor Behemoth: DRRPCubus 10278 {
	//$Category DMonsters
	Translation "48:79=192:202", "112:127=176:191", "128:143=192:198", "160:167=194:199", "144:151=192:207", "13:15=240:247", "236:239=240:247", "16:47=176:191"
}

Actor Mancubus: DRRPCubus 10378 {
	//$Category DMonsters
}

Actor Druj: DRRPCubus 10478 {
	//$Category DMonsters
	Translation "48:79=16:45", "144:151=16:45", "144:151=16:45", "236:239=46:46", "13:15=46:46", "141:141=46:46", "138:138=46:46", "142:142=45:45", "143:143=45:45", "16:47=176:189"
}


// Pain Elementals --------------------------------------------------------------------

Actor DRRPPainElemental: PainElemental replaces PainElemental 10279
{
	//$Category DMonsters
	DamageFactor DRRPFireDmg, 0.0
}

Actor Rehovart: DRRPPainElemental 10379
{
	//$Category DMonsters
	Health 200
	//Translation Greenish/Brown:Blue
}

Actor Beholder: DRRPPainElemental 10479
{
	//$Category DMonsters
	Health 100
	//Translation Greenish/Brown:Green
}


// Barons of Hell ---------------------------------------------------------------------

Actor DRRPBaronOfHell: BaronOfHell replaces BaronOfHell 10280
{
	Scale 0.85
	DamageFactor DRRPSSGDmg, 5.0
	//DamageFactor DRRPFireExtDmg, 25.0
	//$Category DMonsters
}

Actor DRRPHellKnight: DRRPBaronOfHell 10380
{
	//$Category DMonsters
	Health 500
	//Translation Baron:HellKnight
}

Actor Ogre: DRRPHellKnight 10480
{
	//$Category DMonsters
	Health 250
	//Translation HellKinghtSkin:Green
}
