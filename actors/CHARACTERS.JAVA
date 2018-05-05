// 10500-10555

/**
 * Scientists
 */

Actor Scientist 10500 {
//$Category Characters
    +SOLID
    +USESPECIAL

    Radius 20
    Height 56

    States {
        Spawn:
            SCIE A 1
            Loop
    }
}

Actor Scientist1 : Scientist 10501 { // black
//$Category Characters
    Translation "16:79=64:79"
}

Actor DrGuerard : Scientist 10502 { // gerard
//$Category Characters
    Translation "80:106=[132,174,165]:[41,73,66]", "192:192=[132,174,165]:[132,174,165]"
}

Actor ScientistKillable : Scientist 10503 {
//$Category Characters
    +SHOOTABLE
    Health 5
    States {
    Death:
        POSS H 5
        POSS I 5 A_Scream
        POSS J 5 A_NoBlocking
        POSS K 5
        POSS L -1
        Stop
    }
}

Actor Scientist1Killable : Scientist1 10504 {
//$Category Characters
    +SHOOTABLE

    Health 5
    States {

        Death:
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
    }
}

Actor GuerardKillable : DrGuerard 10505 {
//$Category Characters
    +SHOOTABLE

    Health 5

    States {
        Death:
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
        }
}


/**
 * Civilians
 * FIXME: civil>i<an
 */

Actor Civilan0 10510 {
//$Category Characters
    +SOLID

    Radius 20
    Height 56

    States {
        Spawn:
            CIV0 A 1
            Loop
        }
}

Actor Civilan1 : Civilan0 10511 { // purple
//$Category Characters
    Translation "160:167=250:254"
}

Actor Civilan2 : Civilan0 10512 { // green
//$Category Characters
    Translation "160:167=112:127"
}

Actor CivilanKillable : Civilan0 10513 {
//$Category Characters
    +SHOOTABLE

    Health 5

    States {
        Death:
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
    }
}

Actor Civilan1Killable : Civilan1 10514 {
//$Category Characters
    +SHOOTABLE

    Health 5

    States {
        Death:
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
    }
}

Actor Civilan2Killable : Civilan2 10515 {
//$Category Characters
    +SHOOTABLE

    Health 5

    States {
        Death:
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
    }
}


/**
 * Marines
 */

Actor Marine0 10520 {
//$Category Characters
    +SOLID

    Radius 20
    Height 56

    States {
        Spawn:
            DPR0 A 1
        Loop
    }
}

Actor Marine1 : Marine0 10521 { // blue
//$Category Characters
    Translation "122:127=240:247", "117:122=204:207"
}

Actor MarineKillAble : Marine0 10523 {
//$Category Characters
    +SHOOTABLE

    Health 5

    States {
        Death:
                    
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
    }
}

Actor Marine1Killable : Marine1 10524 {
//$Category Characters
    +SHOOTABLE

    Health 5

    States {
        Death:
                    
            POSS H 5
            POSS I 5 A_Scream
            POSS J 5 A_NoBlocking
            POSS K 5
            POSS L -1
            Stop
    }
}

Actor DRRPShop 10555  {
    -SOLID

    Height 64
    Radius 32 // 32 is Too fat

    States {
        Spawn:
            TNT1 A -1
        stop
    }
}
