/**
 * Copyright (c) 2017-2018 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

version "2.5"

#include "zscript/weapons.zs"
#include "zscript/doorcode.zs"
#include "zscript/conversation.zs"
#include "zscript/shaders.zs"
#include "zscript/flashlight.zs"
#include "zscript/hud.zs"
#include "zscript/notebook.zs"
#include "zscript/monsters.zs"

class DRRPFire_zspatch : Actor {
    override bool CanCollideWith (Actor other, bool passive) {
        String cn = other.getClassName();
        if(cn == "DoomRPGPlayer"
                || cn == "Phantom"
                || cn == "DRRPLostSoul"
                || cn == "Nightmare"

                || cn == "Beholder"
                || cn == "Rahovart"
                || cn == "DRRPPainElemental"

                || cn == "Infernis"
                || cn == "DRRPArchVile"
                || cn == "Apollyon"
                //      || cn == "FireExtPuff"
                || (other.bMISSILE == true && cn != "FireExtPuff")) {
            return false;
        }
        return true;
    }
}
