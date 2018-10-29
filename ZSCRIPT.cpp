/**
 * Copyright (c) 2017-2018 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

version "2.5"

#include "DRRPZForms/Include.zs"
#include "zscript/weapons.zs"
#include "zscript/doorcode.zs"
#include "zscript/conversation.zs"
#include "zscript/shaders.zs"
#include "zscript/flashlight.zs"
#include "zscript/hud.zs"
#include "zscript/notebook.zs"

class DRRPFire_zspatch : Actor {

    override bool CanCollideWith (Actor other, bool passive) {
        String cn = other.getClassName();
        if(cn == "DoomRPGPlayer"
                || cn == "Nightmare"
                || cn == "LostSoul1"
                || cn == "Phantom"
                || cn == "Beholder"
                || cn == "Rahovart"
                || cn == "PainElemental1"
                || cn == "PainElemental"
                || cn == "Infernis"
                || cn == "ArchVile1"
                || cn == "Apollyon"
                //      || cn == "FireExtPuff"
                || (other.bMISSILE == true && cn != "FireExtPuff")) {
            return false;
        }
        return true;
    }
}