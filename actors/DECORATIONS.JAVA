//10701-10800

ACTOR DRPGBarrel : ExplosiveBarrel replaces ExplosiveBarrel  10701
{
	DamageFactor "FireExtDamage", 0
	Health 1
	BloodColor "FF FF 00"
	States {
		Spawn:
			BAR1 AB 6
		Loop
		Death:
			BEXP A 5 Bright
			BEXP B 5 Bright A_Scream
			BEXP C 0 Bright A_Quake(6, 5, 0, 128, "world/barrelx")
			BEXP C 5 Bright
			BEXP D 5 Bright A_Explode
			BEXP E 10 Bright
			TNT1 A 1050 Bright A_BarrelDestroy
			TNT1 A 5 A_Respawn
		Wait
		Wait
	}
}

ACTOR Table 10702 {
	Game Doom
	Radius 20
	Height 27
	ProjectilePassHeight -16
	+SOLID
	+NOBLOODDECALS
    Mass 0x7FFFFFFF
    -SHOOTABLE
	States
	{
	Spawn:
		DRTB A -1
	Stop
	}
}

actor BlueLamp : Column 10703 {
	Radius 20
	Translation "224:231=192:195", "160:167=196:199"
}

actor DRRPChair 10750 {
	Radius 14
	Height 37
	+SOLID
    Mass 0x7FFFFFFF
	States {
		Spawn:
			CHAI A -1
		Stop
	}
}

ACTOR Rod : ExplosiveBarrel 10704
{
	Scale 0.5
	Health 666
	Mass 10000000
	States {
		Spawn:
			ROD1 ABCDEF 2
		Loop
		Death:
			BEXP A 5 Bright
			BEXP B 5 Bright A_Scream
			BEXP C 5 Bright
			BEXP D 0 Bright A_Quake(4, 5, 0, 128, "world/barrelx")
			BEXP D 5 Bright A_Explode
			BEXP E 10 Bright
			TNT1 A 5 ACS_Execute(101,0)
			TNT1 A 1050 Bright A_BarrelDestroy
			TNT1 A 5 A_Respawn
		Wait
	}
}

actor Crate 10705 {
	DamageFactor "FireExtDamage", 0
	+SOLID
	+SHOOTABLE
	+NOBLOOD
	+FLOORCLIP
	+INCOMBAT
	BloodColor "AA AA AA"
	Health 1
	Height 64
	Radius 32
	Mass 10000000
	states {
		Spawn:
			CRAT A -1
		stop
		Death:
			CRAT A 0 A_Quake(1, 18, 0, 128)
			CRAT A 18
			CRAT B 0 A_SpawnItemEx("CrateSpawner")
			CRAT B 0 A_NoBlocking
			CRAT B -1
		stop
	}
}
actor CrateSpawner : RandomSpawner {
	DropItem "HealthBonus"
	DropItem "ArmorBonus"
	DropItem "SmallMedikit"
	DropItem "GreenArmor"
	DropItem "Shell"
	DropItem "Cell"
	DropItem "Clip"
	DropItem "RocketAmmo"
	DropItem "Explosion"
}

ACTOR Wardrobe 10706 {
	Game Doom
	Radius 20
	Height 27
	ProjectilePassHeight -16
	+SOLID
	+NOBLOODDECALS
    Mass 0x7FFFFFFF
    -SHOOTABLE
	States
	{
	Spawn:
		DCAB A -1
	Stop
	}
}

actor Bed 10707 {
	Radius 14
	Height 57
	+SOLID
    Mass 0x7FFFFFFF
	States {
		Spawn:
			CHAI A -1
		Stop
	}
}

actor Exit 10708 {
	Radius 14
	Height 64
	-SOLID
    Mass 0x7FFFFFFF
	States {
		Spawn:
			EXIT A -1
		Stop
	}
}

actor Sink 10709 {
	Radius 14
	Height 64
	+SOLID
	+SHOOTABLE
	BloodColor "AA AA AA"
	Health 50
	Scale 0.8
    Mass 0x7FFFFFFF
	States {
		Spawn:
			XSIN A -1
		Stop
		Death:
			XTOI A 0 A_NoBlocking
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.7)
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.6)
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.5)
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.4)
		goto Death1
		Death1:
			CEFE ABC 5
		loop
	}
}

actor Toilet 10710 {
	Radius 14
	Height 64
	+SOLID
	+SHOOTABLE
	BloodColor "AA AA AA"
	Health 50
	Scale 0.8
    Mass 0x7FFFFFFF
	States {
		Spawn:
			XTOI A -1
		Stop
		Death:
			XTOI A 0 A_NoBlocking
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.7)
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.6)
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.5)
			CEFE ABC 5
			CEFE A 0 A_SetScale(0.4)
		goto Death1
		Death1:
			CEFE ABC 5
		loop
	}
}

actor CEFEcon 10743 {
	-SOLID
	Scale 0.4
	States {
		Spawn:
			CEFE ABC 5
		loop
	}
}
actor CEFRcon : CEFEcon 10744 {
	States {
		Spawn:
			CEFR ABC 5
		loop
	}
}

actor Alarm1 10745 {
	-SOLID
	+SPAWNCEILING
	+NOGRAVITY
	Scale 0.5
	States {
		Spawn:
			ALAR A 0 A_PlaySound("alarm/alarm3")
			ALAR ABCDABCDABCDABCDABCD 3
		loop
	}
}

actor Alarm2 : KlaxonWarningLight 10746 {
	states {
		spawn:
		TNT1 A 0
		goto see
	}
}