/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */


class PlayerFeature : Inventory {
	Default {
	    Inventory.MaxAmount 999;
	}
}

class PlayerAccuracy    : PlayerFeature {}
class PlayerAgility     : PlayerFeature {}
class PlayerStrength    : PlayerFeature {}
class PlayerDefence     : PlayerFeature {}
class PlayerLevel       : PlayerFeature {}
class PlayerStamina     : PlayerFeature {}
