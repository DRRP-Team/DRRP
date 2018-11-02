/**
 * Copyright (c) 2017-2018 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

class DRRPMonsterTools {
    static int GenerateDamage(Actor par0, Weapon par1, Actor par2) {
        int var5 = random(0, 256);
        int var6 = (GetWeaponMinDamage(par1) << 8) + (var5 * (GetWeaponMaxDamage(par1) - GetWeaponMinDamage(par1) << 8) >> 8);
        int var8 = (par0.CountInv("PlayerStrength") << 16) / (par2.CountInv("PlayerDefence") << 8);
        int var7 = (var6 * var8 >> 8) * 512 >> 8;
        int var9 = 512;//getFixedPointDamageFactor(par1, par2);
        var7 = var7 * var9 >> 8;
        int var10 = GetWeaponDefenseFactor(par1);
        int var11 = 4096;
        int var12 = 2;

        for(int var13 = var7 * 76 >> 8; var11 < var10; var11 = var12 * 64 * var12 * 64) {
            var7 -= var13;
            ++var12;
        }

        if (var7 < 256) {
            var7 = 256;
        } else if (var7 > 255744) {
            var7 = 255744;
        }

        int var14 = var7 * 10 >> 8;
        return var7 - var14 + 128 >> 8;
    }
    
    static int GetWeaponMinDamage(Weapon weap) {
        if(weap is 'DRRPAxe') {
            return 3;
        }/* else if(weap is ("DRRPF" .. "ireExt")) {
            return 1;
        } */else if(weap is 'DRRPPistol') {
            return 6;
        } else if(weap is 'DRRPShotgun') {
            return 6;
        } else if(weap is 'DRRPChaingun') {
            return 3;
        } else if(weap is 'DRRPSuperShotgun') {
            return 12;
        } else if(weap is 'DRRPPlasmagun') {
            return 6;
        } else if(weap is 'DRRPRocketLauncher') {
            return 15;
        } else if(weap is 'DRRPBFG9000') {
            return 60;
        }
        return 0;
    }
    
    static int GetWeaponMaxDamage(Weapon weap) {
        if(weap is 'DRRPAxe') {
            return 12;
        }/* else if(weap is ("DRRPFireExt" .. "")) {
            return 2;
        }*/ else if(weap is 'DRRPPistol') {
            return 7;
        } else if(weap is 'DRRPShotgun') {
            return 10;
        } else if(weap is 'DRRPChaingun') {
            return 6;
        } else if(weap is 'DRRPSuperShotgun') {
            return 18;
        } else if(weap is 'DRRPPlasmagun') {
            return 8;
        } else if(weap is 'DRRPRocketLauncher') {
            return 36;
        } else if(weap is 'DRRPBFG9000') {
            return 105;
        }
        return 0;
    }
    static int GetWeaponDefenseFactor(Weapon weap) {
        if(weap is 'DRRPAxe') {
            return 4096;
        }/* else if(weap is ("DRRPFireExt" .. "")) {
            return 4096;
        }*/ else if(weap is 'DRRPPistol') {
            return 4121;
        } else if(weap is 'DRRPShotgun') {
            return 4100;
        } else if(weap is 'DRRPChaingun') {
            return 4105;
        } else if(weap is 'DRRPSuperShotgun') {
            return 4098;
        } else if(weap is 'DRRPPlasmagun') {
            return 4112;
        } else if(weap is 'DRRPRocketLauncher') {
            return 4160;
        } else if(weap is 'DRRPBFG9000') {
            return 4160;
        }
        return 4096;
    }
}
