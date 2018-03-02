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
		FIEX A 0 A_StopSound( CHAN_WEAPON )
		FIEX A 1 A_Lower
		Wait
	Select:
		FIEX A 1 A_Raise
		Loop
	Fire:
		FIEX B 0 A_Recoil( 2 )
		TNT1 A 0 A_PlaySound("weapon/fireext/loop", CHAN_WEAPON, 1, 1)
		FIEX B 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 3/*10*/ ), True )
		FIEX B 0 A_Recoil( 1 )
		FIEX BB 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		FIEX B 0 ACS_NamedExecute("fireextfade", 0, 1)
	FireLoop:
		//TNT1 A 0 A_Quake( 1, Random( 1, 4 ), 0, 16 )
		TNT1 A 0 A_PlaySound( "weapon/fireext/loop", CHAN_WEAPON, 1, 1 )
		TNT1 A 0 A_SetAngle( FRandom( 0.1, -0.1 ) + Angle )
		TNT1 A 0 A_SetPitch( FRandom( 0.1, -0.2 ) + Pitch )
		//FIEX C 0 A_Recoil( 1 )
		FIEX C 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		TNT1 A 0 A_SetBlend( "CC CC CC", FRandom( 0.05, 0.15 ), Random( 3, 10 ) )
		TNT1 A 0 A_SetAngle( FRandom( 0.1, -0.1 ) + Angle )
		TNT1 A 0 A_SetPitch( FRandom( 0.1, -0.2 ) + Pitch )
		FIEX CC 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		TNT1 A 0 A_SetAngle( FRandom( 0.1, -0.1 ) + Angle )
		TNT1 A 0 A_SetPitch( FRandom( 0.1, -0.2 ) + Pitch )
		FIEX C 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		TNT1 A 0 A_SetAngle( FRandom( 0.1, -0.1 ) + Angle )
		TNT1 A 0 A_SetPitch( FRandom( 0.1, -0.2 ) + Pitch )
		FIEX CC 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		TNT1 A 0 A_SetAngle( FRandom( 0.1, -0.1 ) + Angle )
		TNT1 A 0 A_SetPitch( FRandom( 0.1, -0.2 ) + Pitch )
		FIEX A 0 A_ReFire( "FireLoop" )
		FIEX B 1 A_FireCustomMissile( "FireExtPuff", Random( 0, 10 ), True )
		FIEX BB 1 A_FireCustomMissile( "FireExtPuffNoDamage" , Random( 0, 10 ), False )
		FIEX B 0 A_SetAngle( FRandom( 1, -1 ) + Angle )
		TNT1 A 0 A_SetPitch( FRandom( 1, -1 ) + Pitch )
		FIEX A 0 A_ReFire
		TNT1 A 0 A_StopSound( CHAN_WEAPON )
		TNT1 A 0 A_PlaySound( "weapon/fireext/stop", CHAN_WEAPON )
		FIEX B 0 ACS_NamedExecute("fireextfade", 0, 0)
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
			AXEG L 0 A_CustomPunch( 1, TRUE, 0, "DRRPAxePuff", 80, 0, 0, "ArmorBonus" )
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
	States
	{
	Spawn:
	STEM LLLLL 0 A_SpawnParticle( Gray, RandomPick( 0, SPF_FULLBRIGHT ), Random( 3, 35 ), FRandom( 0.75, 4 ), 0, 
					Random( -5, 5 ), Random( -5, 5 ), Random( -5, 5 ), // Offsets
					Random( -10, 10 ), Random( -10, 10 ), Random( -10, 10 ), // Speed
					Random( -10, 10 ), Random( -10, 10 ), Random( -10, 10 ) ) // Acceleration
		STEM A 1 A_SetTranslucent(0.7, 1)
		STEM B 1 A_SetScale(1.3, 1.3)//A_SetTranslucent(0.65, 1)
		STEM C 1 A_SetTranslucent(0.6, 1)
		STEM D 1 A_SetScale(1.5, 1.5)//A_SetTranslucent(0.55, 1)
		STEM E 1 A_SetTranslucent(0.5, 1)
		STEM F 1 A_SetScale(1.8, 1.8)//A_SetTranslucent(0.45, 1)
		STEM G 1 A_SetTranslucent(0.4, 1)
		STEM H 1 A_SetScale(2.0, 2.0)//A_SetTranslucent(0.35, 1)
		STEM I 1 A_SetTranslucent(0.3, 1)
		STEM J 1 A_SetTranslucent(0.2, 1)
		STEM K 1 A_SetTranslucent(0.1, 1)
		STEM L 1 A_SetTranslucent(0.05, 1)
	Death:
		STEM LLLLLLLLL 0 A_SpawnParticle( Gray, 0, Random( 5, 70 ), FRandom( 0.5, 2 ), 0, 
					0, Random( -5, 0 ), Random( -5, 5 ), // Offsets
					Random( -10, 10 ), Random( -10, 10 ), Random( -10, 10 ), // Speed
					Random( -5, 5 ), Random( -5, 5 ), Random( -5, 5 ) ) // Acceleration
		Stop
  }
}

Actor FireExtPuffNoDamage : FireExtPuff {
	Damage 0
}

// Dogs

ACTOR HellHoundGun : Weapon
{
	Weapon.AmmoUse 0
	Weapon.AmmoGive 100
	Weapon.SelectionOrder 1
	//Weapon.AmmoType "DogGunHealth"
	+WEAPON.WIMPY_WEAPON
	Inventory.Pickupmessage "DOGGY!"
	Tag "$TAGHDOG"
	Inventory.PickupSound "weapons/fireext/pick"
	States
	{
	Ready:
		HDGG AAAAAAAA 1 A_WeaponReady//A_Jump(1, "MeleePunch")//{ A_WeaponReady; A_JumpIfInventory("MeleePunch", 1, "MeleePunch"); }
		//"####" BBBBBBBB 1 { A_WeaponReady; A_JumpIfInventory("MeleePunch", 1, "MeleePunch"); }
		Loop
	Deselect:
		//HDGG A 1 
		//HDGG A 0 A_JumpIf(health <= 0, "DeadDude")
		//goto AltFire
		HDGG A 1 A_Lower
		Loop
	Select:
		HDGG A 1 Offset(0,90)
		HDGG A 0 A_PlaySound("monsters/hellhound/see")
		HDGG A 1 Offset(0,80)
		HDGG A 1 Offset(0,70)
		HDGG A 1 Offset(0,60)
		HDGG A 1 Offset(0,55)
		HDGG A 1 Offset(0,49)
		HDGG A 1 Offset(0,44)
		HDGG A 1 Offset(0,39)
		HDGG A 1 Offset(0,35)
		HDGG A 1 Offset(0,33)
		HDGG A 1 Offset(0,32) 
		GoTo Ready
	Fire:
		HDGG A 0 A_PlaySound("monsters/hellhound/bite")
		HDGG CD 2
		HDGG E 2 A_CustomPunch(random(8, 18), TRUE, CPF_STEALARMOR,"DogGunPuff",80,0.5,0,"DogArmorBonus","weapons/doggun/bite")
		HDGG F 2 
		HDGG DCB 2
		//HDGG A 0 A_GunFlash
		Goto Ready
	AltFire:
		HDGG A 1 A_PlaySound("monsters/hellhound/die")
		TNT1 A 0 A_TakeInventory("HellHoundGun", 9999)
		//TNT1 A 0 
		Stop
 	Spawn:
		TNT1 A 1
		Stop
	}
}

ACTOR CerberusGun : HellHoundGun
{
	States
	{
	Select:
		CDGG A 1 Offset(0,90)
		CDGG A 0 A_PlaySound("monsters/hellhound/see")
		CDGG A 1 Offset(0,80)
		CDGG A 1 Offset(0,70)
		CDGG A 1 Offset(0,60)
		CDGG A 1 Offset(0,55)
		CDGG A 1 Offset(0,49)
		CDGG A 1 Offset(0,44)
		CDGG A 1 Offset(0,39)
		CDGG A 1 Offset(0,35)
		CDGG A 1 Offset(0,33)
		CDGG A 1 Offset(0,32) 
		Goto Ready
	Ready:
		CDGG AAAAAAAA 1 A_WeaponReady
		Loop
	Deselect:
		CDGG A 1 A_Lower
		Loop
	Select:
		CDGG A 1 Offset(0,90)
		CDGG A 0 A_PlaySound("monsters/cerberus/see")
		CDGG A 1 Offset(0,80)
		CDGG A 1 Offset(0,70)
		CDGG A 1 Offset(0,60)
		CDGG A 1 Offset(0,55)
		CDGG A 1 Offset(0,49)
		CDGG A 1 Offset(0,44)
		CDGG A 1 Offset(0,39)
		CDGG A 1 Offset(0,35)
		CDGG A 1 Offset(0,33)
		CDGG A 1 Offset(0,32) 
		GoTo Ready
	Fire:
		CDGG A 0 A_PlaySound("monsters/hellhound/bite")
		CDGG CD 2
		CDGG E 2 A_CustomPunch(random(10, 25), TRUE, CPF_STEALARMOR,"DogGunPuff",80,0.5,0,"DogArmorBonus","weapons/doggun/bite")
		CDGG F 2 
		CDGG DCB 2
		//HDGG A 0 A_GunFlash
		Goto Ready
	AltFire:
		CDGG A 1 A_PlaySound("monsters/hellhound/die")
		TNT1 A 0 A_TakeInventory("CerberusGun", 9999)
		//TNT1 A 0 
		Stop
 	Spawn:
		TNT1 A 1
		Stop
	}
}

ACTOR DemonWolfGun : HellHoundGun
{
	States
	{
	Ready:
		DDGG AAAAAAAA 1 A_WeaponReady
		Loop
	Select:
		DDGG A 1 Offset(0,90)
		DDGG A 0 A_PlaySound("monsters/demondog/see")
		DDGG A 1 Offset(0,80)
		DDGG A 1 Offset(0,70)
		DDGG A 1 Offset(0,60)
		DDGG A 1 Offset(0,55)
		DDGG A 1 Offset(0,49)
		DDGG A 1 Offset(0,44)
		DDGG A 1 Offset(0,39)
		DDGG A 1 Offset(0,35)
		DDGG A 1 Offset(0,33)
		DDGG A 1 Offset(0,32) 
		GoTo Ready
	Deselect:
		DDGG A 1 A_Lower
		Loop
	Select:
		DDGG A 1 Offset(0,90)
		DDGG A 0 A_PlaySound("monsters/demondog/see")
		DDGG A 1 Offset(0,80)
		DDGG A 1 Offset(0,70)
		DDGG A 1 Offset(0,60)
		DDGG A 1 Offset(0,55)
		DDGG A 1 Offset(0,49)
		DDGG A 1 Offset(0,44)
		DDGG A 1 Offset(0,39)
		DDGG A 1 Offset(0,35)
		DDGG A 1 Offset(0,33)
		DDGG A 1 Offset(0,32) 
		GoTo Ready
	Fire:
		DDGG A 0 A_PlaySound("monsters/hellhound/bite")
		DDGG CD 2
		DDGG E 2 A_CustomPunch(random(15, 50), TRUE, CPF_STEALARMOR,"DogGunPuff",80,0.5,0,"DogArmorBonus","weapons/doggun/bite")
		DDGG F 2 
		DDGG DCB 2
		//HDGG A 0 A_GunFlash
		Goto Ready
	AltFire:
		DDGG A 1 A_PlaySound("monsters/hellhound/die")
		TNT1 A 0 A_TakeInventory("DemonWolfGun", 9999)
		//TNT1 A 0 
		Stop
 	Spawn:
		TNT1 A 1
		Stop
	}
}
/*
ACTOR DemonWolfGun : HellHoundGun
{
	Tag "$TAGDDOG"
	States
	{
	Deselect:
		DDGG A 1 
		DDGG A 0 A_JumpIf(health <= 0, "DeadDude")
		goto AltFire
	Ready:
		DDGG AAAAAAAA 1 A_Jump(1, "MeleePunch")//{ A_WeaponReady; A_JumpIfInventory("MeleePunch", 1, "MeleePunch"); }
		//"####" BBBBBBBB 1 { A_WeaponReady; A_JumpIfInventory("MeleePunch", 1, "MeleePunch"); }
		Loop
	Select:
		DDGG A 1 Offset(0,90) A_PlaySound("monsters/demondog/see")
		DDGG A 1 Offset(0,80)
		DDGG A 1 Offset(0,70)
		DDGG A 1 Offset(0,60)
		DDGG A 1 Offset(0,55)
		DDGG A 1 Offset(0,49)
		DDGG A 1 Offset(0,44)
		DDGG A 1 Offset(0,39)
		DDGG A 1 Offset(0,35)
		DDGG A 1 Offset(0,33)
		DDGG A 1 Offset(0,32) 
		goto Ready
	Fire:
		DDGG A 0 A_PlaySound("monsters/hellhound/bite")
		DDGG CD 2
		DDGG E 2 A_CustomPunch(7 * random(1, 5), TRUE, CPF_STEALARMOR,"DogGunPuff",80,0.5,0,"DogArmorBonus","weapons/doggun/bite")
		DDGG F 2 
		DDGG DCB 2
		Goto Ready
	AltFire:
		TNT1 A 0 A_TakeInventory("DemonWolfGun", 9999)
		Stop
 	Spawn:
		TNT1 A 1
		Stop
	}
}

ACTOR CerberusGun : HellHoundGun
{
	Tag "$TAGCDOG"
	States
	{
	Deselect:
		CDGG A 1 
		CDGG A 0 A_JumpIf(health <= 0, "DeadDude")
		goto AltFire
	Ready:
		CDGG AAAAAAAA 1 A_Jump(1, "MeleePunch")//{ A_WeaponReady; A_JumpIfInventory("MeleePunch", 1, "MeleePunch"); }
		//"####" BBBBBBBB 1 { A_WeaponReady; A_JumpIfInventory("MeleePunch", 1, "MeleePunch"); }
		Loop
	Select:
		CDGG A 1 Offset(0,90) A_PlaySound("monsters/demondog/see")
		CDGG A 1 Offset(0,80)
		CDGG A 1 Offset(0,70)
		CDGG A 1 Offset(0,60)
		CDGG A 1 Offset(0,55)
		CDGG A 1 Offset(0,49)
		CDGG A 1 Offset(0,44)
		CDGG A 1 Offset(0,39)
		CDGG A 1 Offset(0,35)
		CDGG A 1 Offset(0,33)
		CDGG A 1 Offset(0,32) 
		goto Ready
	Fire:
		CDGG A 0 A_PlaySound("monsters/hellhound/bite")
		CDGG CD 2
		CDGG E 2 A_CustomPunch(7 * random(1, 5), TRUE, CPF_STEALARMOR,"DogGunPuff",80,0.5,0,"DogArmorBonus","weapons/doggun/bite")
		CDGG F 2 
		CDGG DCB 2
		Goto Ready
	AltFire:
		TNT1 A 0 A_TakeInventory("CerberusGun", 9999)
		Stop
 	Spawn:
		TNT1 A 1
		Stop
	}
}
*/
Actor DogArmorBonus : BasicArmorBonus
{
	Radius 20
	Height 16
	Inventory.Pickupmessage "$GOTARMBONUS"
	Inventory.Icon "BON2A0"
	Armor.Savepercent 33.335
	Armor.Saveamount 1
	Armor.Maxsaveamount 100
	+COUNTITEM
	+INVENTORY.ALWAYSPICKUP
	States
	{
	Spawn:
		BON2 ABCDCB 6
		loop
	}
}

ACTOR DogGunPuff
{
	+NOBLOCKMAP
	+NOGRAVITY
	+ALLOWPARTICLES
	+RANDOMIZE
	damagetype DogBite
	RenderStyle Translucent
	Alpha 0.5
	VSpeed 1
	Mass 5
	AttackSound "weapons/melee/hitwall"
	SeeSound "weapons/melee/hit"
	Obituary "$OB_METADOG_BITE"
	States
	{
	Spawn:
		PUFF A 4 Bright
		PUFF B 4
	Melee:
		PUFF CD 4
		Stop
	}
}