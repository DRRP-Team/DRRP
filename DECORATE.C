/**
 * Copyright (c) 2017 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

#include actors/ITEMS.JAVAZ
#include actors/WEAP2.java
#include actors/SPARKS.JAVA
#include actors/DAMAGES.JAVA
#include actors/WEAPONS.JAVA
#include actors/MONSTERS.JAVA
#include actors/CHARACTERS.JAVA
#include actors/DECORATIONS.JAVA

/** Manifest
 * Items        10600-10700
 * Weapons      10556-10599
 * Characters   10500-10555
 * Decorations  10701-10800 
 * Monsters
 *  1 level  =  10259-10299
 *  2 level  =  10300-10358
 *  3 level  =  10359-10400
*/


// HELLO FROM RUSSIA AND UKRAINE!


Actor DoomRPGPlayer : DoomPlayer {
    Player.WeaponSlot 1, FireExt, DRRPAxe
    Player.WeaponSlot 2, DRRPPistol
    Player.WeaponSlot 3, DRRPShotgun, DRRPSuperShotgun
    Player.WeaponSlot 4, DRRPChaingun
    //Player.WeaponSlot 5, DRRPRocketLauncher
    Player.WeaponSlot 6, DRRPPlasmagun
    //Player.WeaponSlot 7, DRRPBFG9000
    Player.WeaponSlot 8, HellHoundGun, CerberusGun, DemonWolfGun
    Player.RunHealth 245654
    Health 30
    Player.MaxHealth 30
    Player.DisplayName "Marine"
    Player.MugShotMaxHealth 30
    Player.StartItem "DRRPPistol"
    Player.StartItem "Fist"
    Player.StartItem "Clip", 50
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