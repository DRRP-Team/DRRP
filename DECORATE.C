#include actors/MONSTERS.JAVA
//#include actors/BRAINFUCKERS.JAVA
#include actors/DECORATIONS.JAVA
#include actors/ITEMS.JAVA
#include actors/WEAPONS.JAVA
#include actors/SPARKS.JAVA
#include actors/CHARACTERS.JAVA


// Characters 10500-10555
// Weapons 10556-10599
// Items 10600-10700
// Monster
// 1 level = 10259-10299
// 2 level = 10300-10358
// 3 level = 10359-10400
// Decorations 10701-10800


// HELLO FROM RU AND UA!


// #############################
// TODO: Spheres and Berserk - autouse = 0
// #############################


Actor DoomRPGPlayer : DoomPlayer {
	Player.WeaponSlot 1, Chainsaw, FireExt, DRRPAxe
	Health 30
	Player.MaxHealth 30
	Player.DisplayName "Marine"
	Player.MugShotMaxHealth 30
	//DamageType "Fire"
	/*States {
		Burn:
			
			BURN A 3 Bright
			BURN B 3 Bright A_SpawnItemEx("FireDroplet", 0, 0, 24, 0, 0, -1)
			BURN B 0 Bright A_Explode(64, 64, XF_NOTMISSILE, false, 0, 0, 10, "BulletPuff")
			BURN C 3 Bright A_Wander
			BURN D 3 Bright A_NoBlocking
			BURN E 5 Bright A_SpawnItemEx("FireDroplet", 0, 0, 24, 0, 0, -1)
			BURN E 0 Bright A_Explode(64, 64, XF_NOTMISSILE, false, 0, 0, 10, "BulletPuff")
			BURN FGH 5 Bright A_Wander
			BURN I 5 Bright A_SpawnItemEx("FireDroplet", 0, 0, 24, 0, 0, -1)
			BURN I 0 Bright A_Explode(64, 64, XF_NOTMISSILE, false, 0, 0, 10, "BulletPuff")
			BURN JKL 5 Bright A_Wander
			BURN M 5 Bright A_SpawnItemEx("FireDroplet", 0, 0, 24, 0, 0, -1)
			BURN M 0 Bright A_Explode(64, 64, XF_NOTMISSILE, false, 0, 0, 10, "BulletPuff")
			BURN N 5 Bright
			BURN OPQPQ 5 Bright
			BURN RSTU 7 Bright
			BURN V -1
			Stop
	}*/
}

Actor DRRPFire 10250 {
	//$Category Damage
	Health 10
	Monster
	-SOLID
	+NOBLOOD
	-ISMONSTER
	+NOTARGET
	-COUNTKILL
	+NOTRIGGER
	+SHOOTABLE
    
	Mass 0x7FFFFFFF
	DamageType "Fire"
	
	DamageFactor "Normal", 0
	
	States {
	Spawn:
	    FIRE A 0 Bright
		FIRE A 0 Bright A_PlaySound( "fire/loop3", CHAN_7, 1, true, 2.5 )
		FIRE A 0 Bright A_Jump( 256, 1, 2, 3, 4, 5, 6, 7, 8 )
	SpawnLoop:
		FIRE ABCDEFGH 2 BRIGHT A_Explode( 3, 32, XF_NOTMISSILE )
		Loop
	Pain.FireExtDamage:
		TNT1 A 0
		Goto SpawnLoop
	Death:
	XDeath:
		TNT1 A 0
		Stop
	}
}

Actor DRRPLava 10251 {
	//$Category Damage
	Health 10
	Monster
	-SOLID
	+NOBLOOD
	-ISMONSTER
	+NOTARGET
	-COUNTKILL
	+NOTRIGGER
	-SHOOTABLE
    
	Mass 0x7FFFFFFF
	DamageType "Fire"
	
	DamageFactor "Normal", 0
	
	States {
	Spawn:
	    SPKO D 0 Bright
		SPKO D 0 Bright A_PlaySound( "ICOSCRE", CHAN_7, 1, true, 2.5 )
		SPKO D 0 Bright A_Jump( 256, 1, 2, 3, 4, 5, 6, 7, 8 )
	SpawnLoop:
		SPKO ABCDEFGH 2 BRIGHT A_Explode( 7, 32, XF_NOTMISSILE )
		Loop
	Pain.FireExtDamage:
		TNT1 A 0
		Goto SpawnLoop
	Death:
	XDeath:
		TNT1 A 0
		Stop
	}
}

Actor Explosion : Rocket
{
	Spawn Parent Death
	SpawnID 253
}