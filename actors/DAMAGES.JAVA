/**
 *Copyright (c) 2018 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

Actor DRRPFire : DRRPFire_zspatch 10250 {
    //$Category Damage
    Health 10
    Radius 33
    Monster
    +SOLID
    +NOBLOOD
    +NOTARGET
    +NOTRIGGER
    +SHOOTABLE
    -ISMONSTER
    -COUNTKILL

    Mass 0x7FFFFFFF
    DamageType DRRPFireDmg
    DamageFactor "DRRPFireExtDmg", 1.0
    DamageFactor "Normal", 0

    States {
        Spawn:
            FIRE A 0 Bright
            FIRE A 0 Bright A_JumpIf( Args[ 0 ] != 0, "Spawn.Start" )
            FIRE A 0 Bright A_SetArg( 0, 255 )
        Spawn.Start:
            FIRE A 0 Bright
            FIRE A 0 Bright A_PlaySound( "fire/loop3", CHAN_7, ( Args[ 0 ] * 1.0 ) / 255, true, 2.5 )
            //FIRE A 0 Bright A_Jump( 256, 1, 2, 3, 4, 5, 6, 7, 8 )
            FIRE A 0 Bright A_Jump( 256, Random( 1, 8 ) )
        Spawn.Loop:
            FIRE ABCDEFGH 2 BRIGHT A_Explode( 3, 32, XF_NOTMISSILE )
            Loop
        Pain.DRRPFireExtDmg:
            TNT1 A 0
            Goto Spawn.Loop
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
    +NOBLOOD
    +NOTARGET
    +NOTRIGGER
    -SOLID
    -ISMONSTER
    -COUNTKILL
    -SHOOTABLE
    
    Mass 0x7FFFFFFF
    DamageType DPPRFireDmg    
    DamageFactor "Normal", 0
    RenderStyle Add

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

Actor Explosion : Rocket {
    Spawn Parent Death
    SpawnID 253
}