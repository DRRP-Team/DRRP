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