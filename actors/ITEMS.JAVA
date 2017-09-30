//10600-10700
actor GreenCard : Key 10622
{
//$Category Keys
  Translation "168:191=112:127", "16:47=112:127", "1:2=126:127"
  Radius 20
  Height 16
  +NOTDMATCH
  Inventory.PickupMessage "Picked up a green keycard"
  Inventory.Icon "STKEYS9"
  Inventory.PickupSound "misc/k_pkup"
  States
  {
  Spawn:
    RKEY A 10
    RKEY B 10 Bright
    Loop
  }
}

actor YellowCard1 : YellowCard replaces YellowCard
{
  Inventory.PickupSound "misc/k_pkup"
}

actor BlueCard1 : BlueCard replaces BlueCard
{
  Inventory.PickupSound "misc/k_pkup"
}

actor RedCard1 : RedCard replaces RedCard
{
  Inventory.PickupSound "misc/k_pkup"
}

actor SmallMedikit : HealthPickup replaces Medikit 10600 {
//$Category Items
	Health 25
	Inventory.MaxAmount 99
	+FLOORCLIP
	+INVENTORY.INVBAR
	Tag "Small medikit"
	Inventory.Icon "YMEDI0"
	Inventory.PickupMessage "Picked up a small medikit"
	HealthPickup.AutoUse 0
	States
	  {
	  Spawn:
		YMED A -1
		Stop
	  }
}

actor LargeMedikit : HealthPickup 10601 {
//$Category Items
	Health 75
	Inventory.MaxAmount 99
	+FLOORCLIP
	+INVENTORY.INVBAR
	Tag "Large medikit"
	Inventory.Icon "RMEDI0"
	Inventory.PickupMessage "Picked up a large medikit"
	HealthPickup.AutoUse 0
	States
	  {
	  Spawn:
		RMED A -1
		Stop
	  }
}

actor HealthPowerUp : UpgradeStamina 10603 {
	Inventory.Amount 10
	Inventory.MaxAmount 70
}

//Ammo 10610-10619
ACTOR Balloon : Ammo 10610
{
//$Category DAmmo
  Inventory.PickupMessage "Picked up a baloon!"
  Inventory.Amount 20
  Inventory.MaxAmount 300
  Ammo.BackpackAmount 20
  Ammo.BackpackMaxAmount 600
  Inventory.Icon "BALLA0"
  Inventory.PickupSound "weapon/gas"
  States
  {
  Spawn:
    BALL A -1
    Stop
  }
}

//Coins 10620-10629
actor Credit : Inventory 10620 {
//$Category Items
	+DROPPED
	+NOTDMATCH
	+FLOORCLIP
	Inventory.MaxAmount 0x7fffffff
	+INVENTORY.INVBAR
	Tag "Credit"
	Inventory.Icon "COINA0"
	Inventory.PickupMessage "Picked up a credit."
	States {
		Spawn:
			COIN A -1
		stop
	}
}

actor GoldCredit : CustomInventory 10621 {
//$Category Items
	+DROPPED
	+NOTDMATCH
	+FLOORCLIP
	Translation "112:127=%[0.00,0.00,0.00]:[2.00,1.01,0.00]"
	Tag "5 credits"
	Inventory.Icon "COINA0"
	Inventory.PickupMessage "Picked up 5 credits."
	States {
		Spawn:
			COIN A -1
		stop
		Pickup:
			TNT1 A 0 A_GiveInventory("Credit", 5)
		stop
	}
}

actor Berserker : Berserk replaces Berserk 10623 {
//$Category Items
	Translation "193:207=171:191", "240:247=185:191"
	States
	{
		Spawn:
		SOUL ABCDCB 6 Bright
		Loop
	}
}

actor DogCollar : CustomInventory 10624 {
	Inventory.Icon "DOGCA0"
	Tag "Dog Collar"
	Inventory.PickupMessage "Got dog collar"
	+INVENTORY.INVBAR
	States {
		Spawn:
			DOGC A -1
		stop
		Use:
			TNT1 A 0
		fail
	}
}