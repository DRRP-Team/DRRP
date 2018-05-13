// 10500-10555

/*
 * Scientists * -----------------------------------------------------------------
 */

Actor Scientist 10500 
{
	//$Category Characters
	+SOLID
	+USESPECIAL
	Radius 20
	Height 56
	Health 5
	States
	{
	Spawn:
		SCIE A 0
		SCIE A 0 A_JumpIf( Args[ 4 ] == 0, 2 )
		SCIE A 0 A_ChangeFlag( "SHOOTABLE", True )
		SCIE A 1
		Wait
	Death:
        POSS H 5
        POSS I 5 A_Scream
        POSS J 5 A_NoBlocking
        POSS K 5
        POSS L -1
        Stop
	}
}

Actor Scientist1 : Scientist 10501 // Black
{
	//$Category Characters
    Translation "16:79=64:79"
}

Actor DrGuerard: Scientist 10502 // Dr. Gerard
{
	//$Category Characters
    Translation "80:106=[132,174,165]:[41,73,66]", "192:192=[132,174,165]:[132,174,165]"
}


/*
 * Civilians * ------------------------------------------------------------------
 * FIXME: civil>i<an *
 */

Actor Civilan0 10510 // Orange
{
	//$Category Characters
	+SOLID
	+USESPECIAL
	Radius 20
	Height 56
	Health 5
	States
	{
	Spawn:
		CIV0 A 0
		CIV0 A 0 A_JumpIf( Args[ 4 ] == 0, 2 )
		CIV0 A 0 A_ChangeFlag( "SHOOTABLE", True )
		CIV0 A 1
		Wait
	Death:
        POSS H 5
        POSS I 5 A_Scream
        POSS J 5 A_NoBlocking
        POSS K 5
        POSS L -1
        Stop
	}
}

Actor Civilan1 : Civilan0 10511 // Purple
{
	//$Category Characters
    Translation "160:167=250:254"
}

Actor Civilan2 : Civilan0 10512 // Green
{
	//$Category Characters
    Translation "160:167=112:127"
}


/*
 * Marines * --------------------------------------------------------------------
 */

Actor Marine0 10520 
{
	//$Category Characters
	+SOLID
	+USESPECIAL
	Radius 20
	Height 56
	Health 5
	States
	{
	Spawn:
		DPR0 A 0
		DPR0 A 0 A_JumpIf( Args[ 4 ] == 0, 2 )
		DPR0 A 0 A_ChangeFlag( "SHOOTABLE", True )
		DPR0 A 1
		Wait
	Death:
        POSS H 5
        POSS I 5 A_Scream
        POSS J 5 A_NoBlocking
        POSS K 5
        POSS L -1
        Stop
	}
}

Actor Marine1: Marine0 10521 { // Blue
	//$Category Characters
    Translation "112:122=195:207", "123:127=243:247"
}


/*
 * Other * ----------------------------------------------------------------------
 */

Actor DRRPShop 10555  {
    -SOLID
    Height 64
    Radius 32 // 32 is Too fat
    States {
	Spawn:
		TNT1 A -1
		Stop
    }
}
