//10701-10800

ACTOR DRRPBarrel : ExplosiveBarrel replaces ExplosiveBarrel  10701
{
	DamageFactor DRRPFireExtDmg, 0.0
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
	DamageFactor DRRPFireExtDmg, 0.0
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


ACTOR YardLamp 19690 {
//$Category DDecorations
  Radius 5
  Height 215
  ProjectilePassHeight -16
  +SOLID
  States
  {
  Spawn:
    PSTE A -1
    Stop
  }
}

Actor Lightningactor777 19689 {
//$Category DDecorations
	-SOLID
	States {
		Spawn:
			ZOT1 D 0
			ZOT1 D 0 A_PlaySound("weapons/sigilhit", 7)
			ZOT1 ABCDEDCBA 3
		stop
	}
}

ACTOR TunnelLights 19691
{
//$Category DDecorations
	+NOGRAVITY
	+NOCLIP
	+SPAWNCEILING
	Radius 8
	Height 1
	XScale 0.9
	YScale 0.9
	Renderstyle Add
	Alpha 0.1
	States
	{
	Spawn:
		LITS A -1 BRIGHT
		Stop
	}
}

Actor RubbleSmall 19692 {
//$Category DDecorations
-SOLID
-SHOOTABLE
+FORCEXYBILLBOARD
Mass 999999
Scale 1.0
Radius 24
Height 16
states
	{
	Spawn:
	TNT1 A 0
	TNT1 A 0 A_Jump(255, "Type1", "Type2")
	Goto Type1
	
	Type1:
	    RUBL A 1
		RUBL A -1
		Stop
		
	Type2:
	    RUBL B 1
		RUBL B -1
		Stop
	}
}

Actor RubbleMedium: RubbleSmall 1332 {
//$Category DDecorations
+SOLID
+SHOOTABLE
+NOBLOOD
Radius 32
Height 32
Scale 1.0
states
	{
	Spawn:
	    RUBL C 1
		RUBL C -1
		Stop
	}
}


Actor RubbleLarge: RubbleMedium 1333 {
//$Category DDecorations
Radius 64
Height 56
Scale 1.3
states
	{
	Spawn:
	    RUBL D 1
		RUBL D -1
		Stop
	}
}

Actor CeilLamp1 1316 {
//$Category DDecorations
Radius 8
Height 32
+NOGRAVITY
+NOCLIP
+SOLID
+SPAWNCEILING
Scale 0.25
states
	{
	Spawn:
	    ULGT A 1 BRIGHT
		Loop
	}
}

Actor CeilLamp2: CeilLamp1 1317 {
//$Category DDecorations
+NOGRAVITY
+NOCLIP
+SPAWNCEILING
+SOLID
Radius 8
Height 32
Scale 0.25
states
	{
	Spawn:
	    ULGT D 1 BRIGHT
		Loop
	}
}

Actor CeilLamp3 1726 {
//$Category DDecorations
Radius 8
Height 24
+NOGRAVITY
+NOCLIP
+SPAWNCEILING
+SOLID
Scale 0.25
states
	{
	Spawn:
	    ULGT C 1 BRIGHT
		Loop
	}
}
