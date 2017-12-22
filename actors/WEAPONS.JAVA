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

Actor DRRPAxe : Weapon 10557 {
	Obituary "%o was chopped by %k"
	Inventory.PickupMessage "Picked up an axe"
	Tag "Axe"
	Inventory.Icon "AXEIA0"
	Weapon.SelectionOrder 1880
	Inventory.PickupSound "TAUNT"
	SeeSound "weapons/plasmax"
	+WEAPON.MELEEWEAPON
	States {
		Spawn:
			AXEI A -1
			Stop
		Ready:
			AXEF A 1 A_WeaponReady
			Loop
		Select:
			AXEF A 1 A_Raise
			Loop
		Deselect:
			AXEF A 1 A_Lower
			Loop
		Fire:
			//AXEF B 0 A_PlaySound("ZBMB1", CHAN_WEAPON)
			AXEF A 0 A_PlaySound("ZBMB2", CHAN_WEAPON, 1.0, true)
		Hold:
			AXEF B 1 A_GiveInventory("DRRPAxeLoad")
			AXEF B 0 A_ReFire
			AXEF C 5 A_StopSound(CHAN_WEAPON)
			AXEF D 3
			AXEF E 3
		StopHold:
			AXEF E 0 A_CustomPunch(1, TRUE, 0, "DRRPAxePuff")
			AXEF E 0 A_TakeInventory("DRRPAxeLoad", 1)
			AXEF E 0 A_JumpIfInventory("DRRPAxeLoad", 1, "StopHold")
			AXEF D 3
			AXEF C 3
		Goto Ready
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