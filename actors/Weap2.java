Actor DRRPPistol: Pistol replaces Pistol
{
	Decal BulletChip
	States
	{
	Fire:
		PISG A 4
		PISG B 1 A_FirePistol
		//PISG A 0 A_ZoomFactor( FRandom( 0.985, 0.999 ), ZOOM_INSTANT )
		PISG B 0 A_SetAngle( Angle + FRandom( -0.3, 0.3 ) )
		PISG B 2 Bright A_SetPitch( Pitch - FRandom( -0.1, 0.4 ) )
		//PISG B 0 A_ZoomFactor( 1 )
		PISG B 0 Bright A_SetPitch( Pitch - FRandom( -0.3, 0.3 ) )
		PISG B 1
		PISG B 0 Bright A_SetPitch( Pitch + FRandom( 0.2, 0.4 ) )
		PISG B 2 
		PISG C 4
		PISG B 5 A_ReFire
		Goto Ready	
	}
}

Actor DRRPShotgun: Shotgun replaces Shotgun
{
	Decal BulletChip
	States
	{
	Fire:
		SHTG A 3
		SHTG A 2 A_FireShotgun
		SHTG A 2 Bright
		SHTG A 3
		SHTG BC 5
		SHTG D 4
		SHTG CB 5
		SHTG A 3
		SHTG A 7 A_ReFire
		Goto Ready
	Flash:
		TNT1 A 0 A_Light1
		SHTF A 1 Bright A_SetPitch( Pitch - FRandom( -0.1, 0.4 ) ) 
		SHTF A 1 Bright 
		SHTF A 0 A_ZoomFactor( FRandom( 0.985, 0.999 ) )
		SHTF A 0 A_Recoil( 1 )
		SHTF A 0 A_SetAngle( Angle + FRandom( -1.5, 1.5 ) )
		SHTF A 1 Bright A_SetPitch( Pitch - FRandom( -0.1, 0.5 ) ) 
		SHTF A 0 A_SetAngle( Angle + FRandom( -1, 1 ) )
		SHTF A 1 Bright A_SetPitch( Pitch - FRandom( 0.2, 1 ) ) 
		SHTF B 0 A_ZoomFactor( 1 )
		SHTF B 0 A_Light2
		SHTF B 3 Bright 
		Stop
	}
}

Actor DRRPSuperShotgun: SuperShotgun replaces SuperShotgun
{
	States
	{
	Fire:
	    SHT2 A 3
	    SHT2 A 1 A_FireShotgun2
		SHT2 A 2 Bright
		SHT2 A 4
	    SHT2 B 7
	    SHT2 C 7 A_CheckReload
	    SHT2 D 7 A_OpenShotgun2
	    SHT2 E 7
	    SHT2 F 7 A_LoadShotgun2
	    SHT2 G 6
	    SHT2 H 6 A_CloseShotgun2
	    SHT2 A 5 A_ReFire
	    Goto Ready
	Flash:
		SHT2 I 0 A_SetPitch( Pitch - FRandom( -0.9, 1.9 ) ) 
		SHTF A 0 A_SetAngle( Angle + FRandom( -1.0, 1.0 ) )
	    SHT2 I 1 Bright A_Light1
		SHT2 I 0 A_SetPitch( Pitch - FRandom( -0.3, 1.9 ) ) 
		SHTF A 0 A_Recoil( 1 )
		SHTF A 0 A_SetAngle( Angle + FRandom( -1.6, 1.6 ) )
	    SHT2 I 1 Bright A_SetPitch( Pitch - FRandom( -0.45, 1.5 ) )
		SHTF A 0 A_ZoomFactor( FRandom( 0.9, 0.95 ) )
		SHT2 I 1 Bright A_SetPitch( Pitch - FRandom( -0.4, 0.8 ) )
		SHTF A 0 A_SetAngle( Angle + FRandom( -0.4, 0.4 ) )
	    SHT2 I 1 Bright A_SetPitch( Pitch - FRandom( -0.3, 0.6 ) )
		SHT2 I 0 Bright A_ZoomFactor( 0.95 )
	    SHT2 J 1 Bright A_Light2
		SHT2 I 0 Bright A_ZoomFactor( 0.97 )
	    SHT2 J 1 Bright A_Light2
		SHT2 I 0 Bright A_ZoomFactor( 0.99 )
	    SHT2 J 1 Bright A_Light2
		SHT2 I 0 Bright A_ZoomFactor( 1 )
	    Goto LightDone
	}
}