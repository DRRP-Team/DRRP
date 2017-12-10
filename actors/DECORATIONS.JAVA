//10701-10800

/*ACTOR Bed 10702
{
	Game Doom
/*ACTOR Bed 10702
{
	Game Doom
	Radius 49
	Radius 49
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
}*/

ACTOR DRPGBarrel : ExplosiveBarrel replaces ExplosiveBarrel  10701
{
	DamageFactor "FireExtDamage", 0
	Health 1
	States {
		Spawn:
			BAR1 AB 6
		Loop
		Death:
			BEXP A 5 Bright
			BEXP B 5 Bright A_Scream
			BEXP C 5 Bright
			BEXP D 0 Bright A_Quake(6, 5, 0, 128, "world/barrelx")
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
	+SOLID
	+SHOOTABLE
	+NOBLOOD
	+FLOORCLIP
	+INCOMBAT
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