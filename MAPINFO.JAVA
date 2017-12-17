gameinfo {
    PlayerClasses = "DoomRPGPlayer"
}

clearepisodes
Episode INTRO
{
    Name = "DOOM RPG"
    NoSkillMenu
}

map TITLEMAP "Introduction"
{
 music = "D_TITLE"
 SkyBox = DARK0305
}

map TITLEMAP2 "Introduction"
{
 music = "D_PROTECT"
 SkyBox = DARK0305
}

map TITLEMAP3 "Introduction"
{
 music = "D_BLIZZ"
 SkyBox = DARK0305
}


Map INTRO "Intro"
{
    LevelNum = 1
    Cluster = 1
    SkyBox = SKY2
    Music = D_DEAD
}

Map INTRO2 "Titlemap"{
	Music = "D_TITLE"
}

cluster 1 {
    hub
}

map HUB "Junction" {
    levelnum = 2
    cluster = 1
    skybox = sky2
    music = D_DEAD
}

map SEC1 "Sector 1" {
    levelnum = 3
    cluster = 1
    skybox = sky2
    music = D_DOOM
	EventHandlers = "DoorCodeInputHandler"
}

map SEC2 "Sector 2" {
    levelnum = 4
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC3 "Sector 3" {
    levelnum = 5
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC4 "Sector 4" {
    levelnum = 6
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC5 "Sector 5" {
    levelnum = 7
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC6 "Sector 6" {
    levelnum = 8
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC7 "Sector 7" {
    levelnum = 9
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map XHUB "Junction" {
    levelnum = 10
    cluster = 1
    skybox = sky2
    music = D_DEAD
}

map REAC "Reactor" {
    levelnum = 11
    cluster = 1
    skybox = sky2
    music = D_BLIZZ
}



Intermission Inter_Cast
{
	Wiper
	{
		WipeType = Crossfade
	}
	Image
	{
		// This is only here to initialize the background and the music
		Background = "STARS"
		Time = -1
		Music = "C_PROT"
	}
	Link = Doom2Cast
}

Intermission Doom2Cast
{
	// Cast
	// {
		// CastClass = "DRRPFire"
		// CastName = "Fucking fire (Herodemon's rage)"
		// AttackSound = "Missile", 1, "fire/loop1"
	// }
	Cast
	{
		CastClass = "Hellhound"
		CastName = "Hell Hound (Herodemon's rage)"
	}
	Cast
	{
		CastClass = "Cerberus"
		CastName = "Cerberus"
		// AttackSound = "Missile", 1, "rlmonsters/battlerifle"
	}
	Cast
	{
		CastClass = "DemonWolf"
		CastName = "Demon Wolf"
	}
	Cast
	{
		CastClass = "ZombiePvt"
		CastName = "Zombie Private"
	}
	Cast
	{
		CastClass = "ZombieLt"
		CastName = "Zombie Lt"
	}
	Cast
	{
		CastClass = "ZombieCpt"
		CastName = "Zombie Capitan"
	}
	Cast
	{
		CastClass = "Impling"
		CastName = "Impling"
	}
	Cast
	{
		CastClass = "Imp"
		CastName = "Imp"
	}
	Cast
	{
		CastClass = "ImpLord"
		CastName = "Imp Lord"
	}
	Cast
	{
		CastClass = "BullDemon"
		CastName = "Bull Demon"
	}
	Cast
	{
		CastClass = "Pinky"
		CastName = "Pinky"
	}
	Cast
	{
		CastClass = "Belphegor"
		CastName = "Belphegor"
	}
	Cast
	{
		CastClass = "Malrath"
		CastName = "Malrath"
	}
	Cast
	{
		CastClass = "Cacodemon1"
		CastName = "Cacodemon"
	}
	Cast
	{
		CastClass = "Wretched"
		CastName = "Wretched"
	}
	Cast
	{
		CastClass = "Phantom"
		CastName = "Phantom"
	}
	Cast
	{
		CastClass = "LostSoul1"
		CastName = "Lost Soul"
	}
	Cast
	{
		CastClass = "Nightmare"
		CastName = "Nightmare"
	}
	Cast
	{
		CastClass = "Troop"
		CastName = "Troop"
	}
	Cast
	{
		CastClass = "Commando"
		CastName = "Commando"
	}
	Cast
	{
		CastClass = "Assassin"
		CastName = "Assassin"
	}
	Cast
	{
		CastClass = "Infernis"
		CastName = "Infernis"
	}
	Cast
	{
		CastClass = "ArchVile"
		CastName = "Arch-Vile"
	}
	Cast
	{
		CastClass = "Apollyon"
		CastName = "Apollyon"
	}
	Cast
	{
		CastClass = "Ghoul"
		CastName = "Ghoul"
	}
	Cast
	{
		CastClass = "Fiend"
		CastName = "Fiend"
	}
	Cast
	{
		CastClass = "Revenant1"
		CastName = "Revenant"
	}
	Cast
	{
		CastClass = "Behemoth"
		CastName = "Behemoth"
	}
	Cast
	{
		CastClass = "Mancubus"
		CastName = "Mancubus"
	}
	Cast
	{
		CastClass = "Druj"
		CastName = "Druj"
	}
	Link = Inter_NCast	// Proceed to Nightmare Cast Call
}