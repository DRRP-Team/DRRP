//10600-10700
Actor GreenCard : DoomKey 10622 {
    //$Category Keys
    Inventory.PickupMessage "Picked up a green keycard"
    Inventory.Icon "STKEYS9"
    Inventory.PickupSound "misc/k_pkup"
    States
    {
        Spawn:
            GKEY A 10
            GKEY B 10 Bright
            Loop
    }
}

ACTOR YellowCard1 : YellowCard replaces YellowCard {
    Inventory.PickupSound "misc/k_pkup"
}

ACTOR BlueCard1 : BlueCard replaces BlueCard {
    Inventory.PickupSound "misc/k_pkup"
}

ACTOR RedCard1 : RedCard replaces RedCard {
    Inventory.PickupSound "misc/k_pkup"
}

ACTOR SmallMedikit : HealthPickup replaces Medikit 10600 {
//$Category Items
    +FLOORCLIP
    +INVENTORY.INVBAR

    Health                  25
    Inventory.MaxAmount     99
    HealthPickup.AutoUse    0
    Tag                     "Small medikit"
    Inventory.Icon          "YMEDI0"
    Inventory.PickupMessage "Picked up a small medikit"
    States
    {
        Spawn:
            YMED A -1
            Stop
    }
}

ACTOR LargeMedikit : HealthPickup 10601 {
//$Category Items
    +FLOORCLIP
    +INVENTORY.INVBAR

    Health                  75
    Inventory.MaxAmount     99
    HealthPickup.AutoUse    0
    Tag                     "Large medikit"
    Inventory.Icon          "RMEDI0"
    Inventory.PickupMessage "Picked up a large medikit"
    States
    {
        Spawn:
            RMED A -1
            Stop
    }
}

ACTOR HealthPowerUp : UpgradeStamina 10603 {
    Inventory.Amount 10
    Inventory.MaxAmount 70
}

//Ammo 10610-10619
ACTOR Balloon : Ammo 10610 {
//$Category DAmmo
    Inventory.Amount        20
    Ammo.BackpackAmount     20
    Inventory.MaxAmount     300
    Ammo.BackpackMaxAmount  600
    Inventory.Icon          "BALLA0"
    Inventory.PickupSound   "weapon/gas"
    Inventory.PickupMessage "Picked up a baloon!"
    States
    {
    Spawn:
        BALL A -1
        Stop
    }
}

//Coins 10620-10629
ACTOR Credit : Inventory 10620 {
//$Category Items
    +DROPPED
    +NOTDMATCH
    +FLOORCLIP
    +INVENTORY.INVBAR

    Inventory.MaxAmount     0x7fffffff
    Tag                     "Credit"
    Inventory.Icon          "CREDH0"
    Inventory.PickupMessage "Picked up a credit."

    States {
        Spawn:
            COIN A -1
        stop
    }
}

ACTOR GoldCredit : CustomInventory 10621 {
//$Category Items
    +DROPPED
    +NOTDMATCH
    +FLOORCLIP

    Tag                     "5 credits"
    Inventory.Icon          "COINA0"
    Inventory.PickupMessage "Picked up 5 credits."

    Translation "112:127=%[0.00,0.00,0.00]:[2.00,1.01,0.00]"

    States {
        Spawn:
            COIN A -1
            Stop
        Pickup:
            TNT1 A 0 A_GiveInventory("Credit", 5)
            Stop
    }
}

Actor Berserker : CustomInventory replaces Berserk 10623
{
    +COUNTITEM
    +INVENTORY.INVBAR

    Inventory.MaxAmount     99
    Inventory.Icon          "BERSH0"
    Inventory.PickupMessage "$GOTBERSERK" // "Berserk!"
    Inventory.PickupSound   "misc/p_pkup"

    Translation "193:207=171:191", "240:247=185:191"

    States
    {
        Spawn:
            SOUL ABCDCB 6 Bright
            Loop
        Use:		
            TNT1 A 0 A_GiveInventory("PowerStrength")
            TNT1 A 0 HealThing(100, 0)
            TNT1 A 0 A_SelectWeapon("DRRPAxe") // Fist
            Stop
    }
}

Actor InventorySoulsphere: CustomInventory replaces Soulsphere {
    +COUNTITEM
    +INVENTORY.INVBAR

    Inventory.MaxAmount     99
    Inventory.Icon          "SOULH0"
    Inventory.PickupSound   "misc/p_pkup"
    Inventory.PickupMessage "$GOTSUPER"

    States
    {
        Spawn:
            SOUL ABCDCB 6 Bright
            Loop
        Use:
            //TNT1 A 0 A_GiveInventory( "HealthBonus", 100 )
            TNT1 A 0 A_GiveInventory( "HealthBonus", 999 )
            TNT1 A 0 A_GiveInventory( "BlueArmor", 1 )
            Stop
    }
}

Actor DogCollar : CustomInventory 10624 {
    +INVENTORY.INVBAR

    Tag                     "Dog Collar"
    Inventory.Icon          "DOGCH0"
    Inventory.PickupMessage "Got the dog collar."

    States {
        Spawn:
            DOGC A -1
            Stop
        Use:
            TNT1 A 0
            //TNT1 A 0 A_JumpIf( CheckClass( "Cerberus", AAPTR_LINETARGET ), "CerberusLabel" )
            TNT1 A 0 A_JumpIf( CheckClass( "Cerberus", AAPTR_PLAYER_GETTARGET ), "CerberusLabel" )
            TNT1 A 0 A_JumpIf( CheckClass( "Hellhound", AAPTR_PLAYER_GETTARGET ), "HellhoundLabel" )
            TNT1 A 0 A_JumpIf( CheckClass( "DemonWolf", AAPTR_PLAYER_GETTARGET ), "DemonWolfLabel" )
            Fail
        CerberusLabel:
            TNT1 A 0
            TNT1 A 0 A_Remove(AAPTR_PLAYER_GETTARGET)
            TNT1 A 0 A_GiveInventory( "CerberusGun" )
            TNT1 A 0 A_SelectWeapon( "CerberusGun" )
            Stop
        HellhoundLabel:
            TNT1 A 0
            TNT1 A 0 A_Remove(AAPTR_PLAYER_GETTARGET)
            TNT1 A 0 A_GiveInventory( "HellhoundGun" )
            TNT1 A 0 A_SelectWeapon( "HellhoundGun" )
            Stop
        DemonWolfLabel:
            TNT1 A 0
            TNT1 A 0 A_Remove(AAPTR_PLAYER_GETTARGET)
            TNT1 A 0 A_GiveInventory( "DemonWolfGun" )
            TNT1 A 0 A_SelectWeapon( "DemonWolfGun" )
            Stop
    }
}

ACTOR DRRPHealthBonus : Health replaces HealthBonus {
    Inventory.Amount 1
    Inventory.PickupMessage "$GOTHTHBONUS" // "Picked up a health bonus."

    States
    {
        Spawn:
            BON1 ABCDCB 6
            Loop
    }
}