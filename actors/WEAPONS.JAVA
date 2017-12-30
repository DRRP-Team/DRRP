//10556-10600

ACTOR FireExt : Weapon 10556
{
//  Weapon.SelectionOrder 1900
	Obituary "LOL, %o was extinguished by %k"
	Inventory.Pickupmessage "Got Fire Extinguisher"
	Tag "Fire Extinguisher"
	Inventory.Icon "FEXTI0"
	Weapon.AmmoUse 1
	Weapon.AmmoGive 40
	Weapon.AmmoType "Balloon"
	Weapon.SelectionOrder 100
	Inventory.PickupSound "voice/wtf"
	States
	{
	Ready: // Original sprite -- "DW2A"
		FIEX A 1 A_WeaponReady
		Loop
	Deselect:
		FIEX A 1 A_Lower
		Loop
	Select:
		FIEX A 1 A_Raise
		Loop
	Fire:
		FIEX B 0 A_Recoil( 4 )
		FIEX B 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		FIEX BB 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
	FireLoop:
		FIEX C 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		FIEX CC 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		FIEX C 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		FIEX CC 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		FIEX C 0 A_Recoil( 1 )
		FIEX A 0 A_ReFire( "FireLoop" )
		FIEX B 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		FIEX BB 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		FIEX B 0 A_Recoil( 1 )
		FIEX A 0 A_ReFire
		//DW2A AAAAAAAAAAAAAAAAAAAA 1 
		Goto Ready
	Spawn:
		FEXT I -1
		Stop
	}
}

Actor DRRPAxeLoad : Inventory {
	Inventory.MaxAmount 40
}

Actor DRRPAxe: Weapon 10557 
{
	Obituary "%o was chopped by %k"
	Inventory.PickupMessage "Picked up an axe."
	Tag "Axe"
	Inventory.Icon "AXEIA0"
	Weapon.SelectionOrder 1880
	Inventory.PickupSound "TAUNT"
	SeeSound "weapons/plasmax"
	Scale 0.65
	+WEAPON.MELEEWEAPON
	States {
		Spawn:
			AXEI A -1
			Stop
		Ready:
			AXEG A 1 A_WeaponReady
			Loop
		Select:
			AXEG A 1 A_Raise
			Loop
		Deselect:
			AXEG A 1 A_Lower
			Loop
		Fire:
			//AXEF B 0 A_PlaySound("ZBMB1", CHAN_WEAPON)
			AXEG A 0 A_PlaySound( "ZBMB2", CHAN_WEAPON, 1.0, True )
			AXEG A 0 A_GunFlash
			AXEH A 2
		Hold:		// [AXE H]old A-F; [AXE G]un exclude "C" frame
			AXEH A 0 A_GiveInventory( "DRRPAxeLoad" )
			AXEH A 0 A_JumpIfInventory( "DRRPAxeLoad", 0, 6 )
			AXEH A 0 A_JumpIfInventory( "DRRPAxeLoad", 31, 6 )
			AXEH A 0 A_JumpIfInventory( "DRRPAxeLoad", 21, 6 )
			AXEH A 0 A_JumpIfInventory( "DRRPAxeLoad", 12, 6 )
			AXEH A 0 A_JumpIfInventory( "DRRPAxeLoad", 6, 6 )
			AXEH A 0 A_JumpIfInventory( "DRRPAxeLoad", 2, 6 )
			AXEH F 1
			Goto HoldRefire
			AXEH E 1
			Goto HoldRefire
			AXEH D 1
			Goto HoldRefire
			AXEH C 1
			Goto HoldRefire
			AXEH B 1
			Goto HoldRefire
			AXEH A 1
		HoldRefire:
			AXEG B 0 A_ReFire( "Hold" )
			Goto FireHold
		Flash:
			//TNT1 A 1 A_SetAngle( Angle - ( 40 - CountInv( "DRRPAxeLoad" ) ) / 16 )
			//TNT1 A 0 A_SetPitch( Pitch - ( 2 - CountInv( "DRRPAxeLoad" ) / 20 ) )
			//Loop						// Alas, with this piece of code turns was very ugly...
			
			TNT1 A 0 A_SetPitch( Pitch - 0.3 )
			TNT1 A 1 A_SetAngle( Angle - 3 )
			TNT1 A 0 A_SetPitch( Pitch - 0.3 )
			TNT1 A 1 A_SetAngle( Angle - 2.5 )
			TNT1 A 0 A_SetPitch( Pitch - 0.35 )
			TNT1 A 1 A_SetAngle( Angle - 2 )
			TNT1 A 0 A_SetPitch( Pitch - 0.4 )
			TNT1 A 2 A_SetAngle( Angle - 1.7 )
			TNT1 A 0 A_SetPitch( Pitch - 0.4 )
			TNT1 A 2 A_SetAngle( Angle - 1.4 )
			TNT1 A 0 A_SetPitch( Pitch - 0.4 )
			TNT1 A 2 A_SetAngle( Angle - 1.1 )
			TNT1 A 0 A_SetPitch( Pitch - 0.45 )
			TNT1 A 2 A_SetAngle( Angle - 0.9 )
			TNT1 A 0 A_SetPitch( Pitch - 0.45 )
			TNT1 A 2 A_SetAngle( Angle - 0.7 )
			TNT1 A 0 A_SetPitch( Pitch - 0.5 )
			TNT1 A 2 A_SetAngle( Angle - 0.6 )
			TNT1 A 0 A_SetPitch( Pitch - 0.4 )
			TNT1 A 2 A_SetAngle( Angle - 0.5 )
			TNT1 A 0 A_SetPitch( Pitch - 0.35 )
			TNT1 A 2 A_SetAngle( Angle - 0.4 )
			TNT1 A 0 A_SetPitch( Pitch - 0.3 )
			TNT1 A 2 A_SetAngle( Angle - 0.3 )
			TNT1 A 0 A_SetPitch( Pitch - 0.2 )
			TNT1 A 2 A_SetAngle( Angle - 0.2 )
			TNT1 AAAAAA 2 A_SetAngle( Angle - 0.05 )
			Stop
		FireHold:
			AXEG BDE 2
			AXEG F 0 A_GunFlash( "Flash2" )
			AXEG GH 1
			AXEG I 2 A_StopSound( CHAN_WEAPON )
			AXEG JK 1
		StopHold:
			AXEG L 0 A_CustomPunch( 1, TRUE, 0, "DRRPAxePuff", 80, 0, 0, "ArmorBonus", "weapon/axe/hit", "weapon/axe/miss" )
			AXEG L 0 A_TakeInventory( "DRRPAxeLoad", 1 )
			AXEG L 0 A_JumpIfInventory( "DRRPAxeLoad", 1, "StopHold" )
			AXEG LM 1
			AXEG NO 2
			TNT1 A 8
			AXEG EB 2
			AXEH CA 3
			Goto Ready
		Flash2:
			TNT1 A 0 A_SetPitch( Pitch - 1 )
			TNT1 A 1 A_SetAngle( Angle - 3 )
			TNT1 A 0 A_SetPitch( Pitch - 1 )
			TNT1 A 1 A_SetAngle( Angle - 2 )
			TNT1 A 0 A_SetPitch( Pitch - 1 )
			TNT1 A 1 A_SetAngle( Angle - 0.5 )
			
			TNT1 A 0 A_SetPitch( Pitch - 1 )
			TNT1 A 1 A_SetAngle( Angle + 8 )
			TNT1 A 0 A_SetPitch( Pitch + 0.4 )
			TNT1 A 1 A_SetAngle( Angle + 5.5 )
			TNT1 A 0 A_SetPitch( Pitch + 1.2 )
			TNT1 A 1 A_SetAngle( Angle + 4.5 )
			TNT1 A 0 A_SetPitch( Pitch + 2 )
			TNT1 A 1 A_SetAngle( Angle + 3.7 )
			TNT1 A 0 A_SetPitch( Pitch + 1.5 )
			TNT1 A 1 A_SetAngle( Angle + 3 )
			TNT1 A 0 A_SetPitch( Pitch + 1 )
			TNT1 A 1 A_SetAngle( Angle + 2 )
			TNT1 A 0 A_SetPitch( Pitch + 0.8 )
			TNT1 A 1 A_SetAngle( Angle + 1.2 )
			TNT1 A 0 A_SetPitch( Pitch + 0.55 )
			TNT1 A 1 A_SetAngle( Angle + 1 )
			TNT1 A 0 A_SetPitch( Pitch + 0.5 )
			TNT1 A 2 A_SetAngle( Angle + 0.8 )
			TNT1 A 0 A_SetPitch( Pitch + 0.4 )
			TNT1 A 2 A_SetAngle( Angle + 0.6 )
			TNT1 A 0 A_SetPitch( Pitch + 0.25 )
			TNT1 A 2 A_SetAngle( Angle + 0.45 )
			TNT1 A 0 A_SetPitch( Pitch - 0.3 )
			TNT1 A 2 A_SetAngle( Angle + 0.3 )
			TNT1 A 0 A_SetPitch( Pitch - 0.1 )
			TNT1 A 2 A_SetAngle( Angle + 0.1 )
			Stop
	}
}

Actor DRRPAxePuff : BulletPuff {
	SeeSound "DSBLUD03"
	AttackSound "ROCK"	
}


Damagetype FireExtDamage {
	Factor 1
	ReplaceFactor
}

Actor FireExtPuff
{
  Height 5
  Radius 5
  +FORCEXYBILLBOARD
  RenderStyle Add
  Alpha 0.85
  Projectile
  Speed 30
  Scale 0.5
  Damage 1
  DamageType FireExtDamage
  Gravity 10
  SeeSound "weapons/plasmax"
  DeathSound "weapons/plasmax"
  States
  {
  Spawn:
    STEM A 1 A_SetTranslucent(0.6, 1)
    STEM B 1 A_SetTranslucent(0.55, 1)
    STEM C 1 A_SetTranslucent(0.5, 1)
    STEM D 1 A_SetTranslucent(0.45, 1)
    STEM E 1 A_SetTranslucent(0.4, 1)
    STEM F 1 A_SetTranslucent(0.35, 1)
    STEM G 1 A_SetTranslucent(0.3, 1)
    STEM H 1 A_SetTranslucent(0.25, 1)
    STEM I 1 A_SetTranslucent(0.2, 1)
    STEM J 1 A_SetTranslucent(0.15, 1)
    STEM K 1 A_SetTranslucent(0.1, 1)
    STEM L 1 A_SetTranslucent(0.05, 1)
  Death:
    STEM L 0
    Stop
  }
}

Actor FireExtPuffNoDamage : FireExtPuff {
	Damage 0
}