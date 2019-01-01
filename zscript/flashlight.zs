/**
 *Copyright (c) 2018-2019 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

// Flashlight mod: https://forum.zdoom.org/viewtopic.php?t=59429

class Util {
    play static void setupFlashlight (int playerID, int flashID) {
        PlayerPawn act = PlayerPawn(ActorIterator.Create(playerID).Next());

        if (act) {
            ActorHeadLight light = ActorHeadLight(Actor.Spawn("ActorHeadLight"));
            light.ChangeTid(flashID);
            light.toFollow = act;
            light.activate(null);
            Util.LogDebug("Light created and 'attached'");
        }
    }

    static void LogDebug (String text) {
        if (CVar.GetCvar("debug")) {
            Console.printf("[ZScript]  "..text);
        }
    }

    static int round (double num) {
        int result = (num - floor(num) > 0.5) ? ceil(num) : floor(num);

        return result;
    }
}

class ActorHeadLight : Spotlight {
    Default {
        +DYNAMICLIGHT.ATTENUATE
    }

    enum ELocation {
        HELMET          = 0,
        RIGHT_SHOULDER  = 1,
        LEFT_SHOULDER   = 2,
        CAMERA          = 3
    };

    PlayerPawn  toFollow;
    Vector3     offset;
    Vector3     finalOffset;

    double      zBump; // z offset for locations that are not CAMERA
    double      angleDiff;
    double      turnAnglePerTic;
    bool        ready;
    bool        on;

    ELocation location;

    void updateFromCvars () {
        Color c = CVar.FindCVar("flashlight_color").GetString();

        args[0] = c.r;
        args[1] = c.g;
        args[2] = c.b;
        args[3] = CVar.FindCVar("flashlight_intensity").GetInt();

        self.SpotInnerAngle     = CVar.FindCVar("flashlight_inner").GetFloat();
        self.SpotOuterAngle     = CVar.FindCVar("flashlight_outer").GetFloat();
        self.location           = CVar.FindCVar("flashlight_location").GetInt();

        Util.LogDebug("Light loc offset: "..self.offset);
    }

    override void Activate(Actor activator) {
        updateFromCvars();
        on = true;
        super.Activate(activator);
    }

    override void DeActivate(Actor activator) {
        on = false;
        super.DeActivate(activator);
    }

    override void Tick() {
        if (ready && on) {
            switch (location) {
                case HELMET:
                    offset = (0, 0, toFollow.player.viewheight + zBump);
                    break;
                case RIGHT_SHOULDER:
                    offset = (toFollow.radius, 0, toFollow.player.viewheight + zBump);
                    break;
                case LEFT_SHOULDER:
                    offset = (toFollow.radius * -1, 0, toFollow.player.viewheight + zBump);
                    break;
                case CAMERA:
                    offset = (0, 0, toFollow.ViewHeight);
                    break;
                default:
                    offset = (0, 0, 0);
            }

            A_SetAngle(toFollow.angle, 0);

            Vector2 finalOffset2D = RotateVector ((offset.x, offset.y), toFollow.angle - 90.0);
            finalOffset = (finalOffset2D.x, finalOffset2D.y, offset.z);

            A_SetPitch(toFollow.pitch, SPF_INTERPOLATE);
            SetOrigin(self.toFollow.pos + finalOffset, true);
        } else if (on && self.toFollow) {
            ready = true;
            zBump = (toFollow.height - toFollow.viewheight) / 2;
            Util.LogDebug('zBump assigned: '..zBump);
        }

        Super.Tick();
    }
}
