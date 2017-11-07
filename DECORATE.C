#include actors/MONSTERS.JAVA
#include actors/BRAINFUCKERS.JAVA
#include actors/DECORATIONS.JAVA
#include actors/ITEMS.JAVA
#include actors/WEAPONS.JAVA
#include actors/SPARKS.JAVA
#include actors/CHARACTERS.JAVA

//Characters 10500-10555
//Weapons 10556-10599
//Items 10600-10700
//Monster
//1 level = 10259-10299
//2 level = 10300-10358
//3 level = 10359-10400
//Decorations 10701-10800

//HELLO FROM RU AND UA!

//#############################
//TODO: Spheres and Berserk - autouse = 0
//#############################

actor DoomRPGPlayer : DoomPlayer {
	Player.WeaponSlot 1, Chainsaw, FireExt, DRRPAxe
	Health 30
	Player.MaxHealth 30
	Player.DisplayName "Marine"
	//DamageType "Fire"
	/*states {
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
		stop
	}*/
}

actor DRRPFire 10250 {
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
			FIRE A 0 BRIGHT A_PlaySound("fire/loop3", CHAN_7, 1,true,2.5)
			FIRE A 0
            FIRE ABCDEFGH 1 BRIGHT A_Explode(3, 32,XF_NOTMISSILE)
        loop
        Pain.FireExtDamage:
            TNT1 A 0
        goto Spawn
        Death:
        XDeath:
            TNT1 A 0
        stop
    }
}

ACTOR Explosion : Rocket
{
    Spawn Parent Death
    SpawnID 253
}
