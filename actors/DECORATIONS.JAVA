//10701-10800

Actor DRRPBarrel : ExplosiveBarrel replaces ExplosiveBarrel  10701 {
//$Category DDecorations
    DamageFactor DRRPFireExtDmg, 0.0
    Health 1
    BloodColor "FF FF 00"
    States 
    {
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

Actor Table 10702 {
//$Category DDecorations
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

Actor BlueLamp : Column 10703 {
//$Category DDecorations
    Radius 20
    Translation "224:231=192:195", "160:167=196:199"
}

Actor DRRPChair 10750 {
//$Category DDecorations
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

Actor Rod : ExplosiveBarrel 10704 {
//$Category DDecorations
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

Actor Crate 10705 {
//$Category DDecorations
    +SOLID
    +SHOOTABLE
    +NOBLOOD
    +FLOORCLIP
    +INCOMBAT
    DamageFactor "FireExtDamage", 0
    BloodColor "AA AA AA"
    Health 1
    Height 64
    Radius 32
    Mass 10000000
    States {
        Spawn:
            CRAT A -1
            Stop
        Death:
            CRAT A 0 A_Quake(1, 18, 0, 128)
            CRAT A 18
            CRAT B 0 A_SpawnItemEx("CrateSpawner")
            CRAT B 0 A_NoBlocking
            CRAT B -1
            Stop
    }
}

Actor CrateSpawner : RandomSpawner {
//$Category DDecorations
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

Actor Wardrobe 10706 {
//$Category DDecorations
    Game Doom
    +SOLID
    +NOBLOODDECALS
    -SHOOTABLE
    Radius 20
    Height 27
    ProjectilePassHeight -16
    Mass 0x7FFFFFFF
    States
    {
        Spawn:
            DCAB A -1
            Stop
    }
}

Actor Bed 10707 {
//$Category DDecorations
    +SOLID
    Radius 14
    Height 57
    Mass 0x7FFFFFFF
    States {
        Spawn:
            CHAI A -1
            Stop
    }
}

Actor Exit 10708 {
//$Category DDecorations
    -SOLID
    Radius 14
    Height 64
    Mass 0x7FFFFFFF
    States {
        Spawn:
            EXIT A -1
            Stop
    }
}

Actor Sink 10709 {
//$Category DDecorations
    +SOLID
    +SHOOTABLE
    Radius 14
    Height 64
    BloodColor "AA AA AA"
    Health 50
    Scale 0.8
    Mass 0x7FFFFFFF
    States {
        Spawn:
            XSIN A -1
            Stop
        Death:
            TNT1 A 0
            TNT1 A 0 A_SpawnItem( "AqueductFountain" )
            Stop
    }
}

Actor Toilet 10710 {
//$Category DDecorations
    +SOLID
    +SHOOTABLE
    Radius 14
    Height 64
    BloodColor "AA AA AA"
    Health 50
    Scale 0.8
    Mass 0x7FFFFFFF
    States {
        Spawn:
            XTOI A -1
            Stop
        Death:
            TNT1 A 0
            TNT1 A 0 A_SpawnItem( "AqueductFountain" )
            Stop
    }
}

Actor AqueductFountain {
//$Category DDecorations
    -SOLID
    Scale 0.7
    States {
        Spawn:
            CEFE AAAABBBBCCCCAAAABBBBCCCC 1 A_SetScale( ScaleX / 100 * 99 )
            CEFE AAAABBBBCCCCAAAABBBBCCCC 1 A_SetScale( ScaleX / 1000 * 993 )
            CEFE AAAABBBBCCCCAAAABBBBCCCCAAAABBBBCCCC 1 A_SetScale( ScaleX / 1000 * 995 )
            CEFE AAAABBBBCCCCAAAABBBBCCCCAAAABBBBCCCCAAAABBBBCCCC 1 A_SetScale( ScaleX / 1000 * 998 )
        SpawnLoop:
            CEFE ABC 4
            Loop
    }
}

Actor CEFEcon 10743 {
//$Category DDecorations
    -SOLID
    Scale 0.4
    States {
        Spawn:
            CEFE ABC 5
            Loop
    }
}
Actor CEFRcon : CEFEcon 10744 {
//$Category DDecorations
    States {
        Spawn:
            CEFR ABC 5
            Loop
    }
}

Actor Alarm1 10745 {
//$Category DDecorations
    -SOLID
    +SPAWNCEILING
    +NOGRAVITY
    Scale 0.5
    States {
        Spawn:
            ALAR A 0 A_PlaySound("alarm/alarm3", CHAN_5, 1.0, false, ATTN_NONE)
            ALAR ABCDABCDABCDABCDABCD 3
            Loop
    }
}

Actor Alarm2 : KlaxonWarningLight 10746 {
//$Category DDecorations
    States {
        Spawn:
            TNT1 A 0
            Goto See
    }
}


Actor YardLamp 19690 {
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
            Stop
    }
}

Actor TunnelLights 19691
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
    States
    {
        Spawn:
            TNT1 A 0
            TNT1 A 0 A_Jump( 127, "Type2" )
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
    States
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
    States
    {
        Spawn:
            RUBL D 1
            RUBL D -1
            Stop
    }
}

Actor CeilLamp1 1316 {
//$Category DDecorations
    +NOGRAVITY
    +NOCLIP
    +SOLID
    +SPAWNCEILING
    Radius 8
    Height 32
    Scale 0.25
    States
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
    States
    {
        Spawn:
            ULGT D 1 BRIGHT
            Loop
    }
}

Actor CeilLamp3 1726 {
//$Category DDecorations
    +NOGRAVITY
    +NOCLIP
    +SPAWNCEILING
    +SOLID
    Radius 8
    Height 24
    Scale 0.25
    States
    {
        Spawn:
            ULGT C 1 BRIGHT
            Loop
    }
}
