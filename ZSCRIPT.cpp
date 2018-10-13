/**
 * Copyright (c) 2017-2018 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

version "2.5"

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

class DoorCodeInputActor : Actor {
    static int DoorCode(int code, int front, int back) {
        EventHandler.SendNetworkEvent("opendoorinput", code, front, back);
        return 1;
    }
}


/* 
 * ConversationLineController
 *
 * ScriptCall("ConversationLineController", "GetArgument", lineid);
 * ScriptCall("ConversationLineController", "SetArgument", lineid, value);
 *
 */
class ConversationLineController play {

	static int GetArgument(uint lineID) {
	    LineIdIterator it = LineIdIterator.Create(lineID);
	    int itLineID      = it.Next();

		if (itLineID > 0) {
			Line convLine = level.Lines[itLineID];

			return convLine.args[4];
		} else {
			return -1;
        }
	}

	static bool SetArgument(uint lineID, uint lineArg) {
	    LineIdIterator it = LineIdIterator.Create(lineID);
	    int itLineID      = it.Next();

		if (itLineID > 0) {
			Line convLine    = level.Lines[itLineID];
			convLine.args[4] = lineArg;

			return true;
		}

		return false;
	}

}



class ShaderControllerActor : EventHandler {
    static int SetEnabled(int player, String name, bool enabled) {
        if(player != 0) {
            Shader.SetEnabled(players[player - 1], name, enabled);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetEnabled(players[i], name, enabled);
            }
        }

        return 1;
    }

    static int SetUniformFloat(int player, String name, String uniform, float value) {
        if(player != 0) {
            Shader.SetUniform1f(players[player - 1], name, uniform, value);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetUniform1f(players[player - 1], name, uniform, value);
            }
        }

        return 1;
    }

    static int SetUniformInt(int player, String name, String uniform, int value) {
        if(player != 0) {
            Shader.SetUniform1i(players[player - 1], name, uniform, value);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetUniform1i(players[player - 1], name, uniform, value);
            }
        }

        return 1;
    }
}



class DoorCodeInputHandler : EventHandler {
    String codeStr;
    String realCodeStr;
    int doorfront;
    int doorback;

    override void OnRegister() {
        SetOrder(666);
    }

    override bool UiProcess(UiEvent ev) {
        if(ev.Type == UiEvent.Type_KeyDown && ((ev.keyChar >= 48 && ev.keyChar <= 57) || ev.keyChar == 8)) {
            SendNetworkEvent("putcodechar", ev.keyChar);
        } else if(ev.Type == UiEvent.Type_KeyDown && ev.keyChar == 27) {
            SendNetworkEvent("closedoorinput", 0);
        }
        return true;
    }

    override void RenderOverlay(RenderEvent e) {
        if(!self.IsUiProcessor) return;

        Font font = Font.FindFont("SMALLFONT");
        String str = String.format("Enter the code: %s", codeStr);
        screen.DrawText(
                font,
                Font.CR_WHITE,
                screen.GetWidth() / 2 - font.StringWidth(str) / 2,
                screen.GetHeight() / 2 - font.GetHeight() / 2,
                str
                );
    }

    override void NetworkProcess(ConsoleEvent e) {
        if(e.Name == "closedoorinput") {
            self.IsUiProcessor = false;
            if(e.Args[0] == 1) {
                players[e.Player].mo.A_PlaySound("acess/grant1");
                LineIdIterator it = LineIdIterator.Create(doorfront);
                int lineid = it.Next();

                if(lineid > 0) {
                    Line line = level.Lines[lineid];
                    Side myside = line.sidedef[0];
                    myside.SetTexture(Side.top, TexMan.CheckForTexture("drpga09", TexMan.Type_Any));
                    line.special = 12;
                    line.args[0] = 0;
                    line.args[1] = 16;
                    line.args[2] = 105;
                    line.args[3] = 0;
                    line.args[4] = 0;
                }
                it = LineIdIterator.Create(doorback);
                lineid = it.Next();
                if(lineid > 0) {
                    Line line = level.Lines[lineid];
                    Side myside = line.sidedef[0];
                    myside.SetTexture(Side.top, TexMan.CheckForTexture("drpga09b", TexMan.Type_Any));
                    line.special = 12;
                    line.args[0] = 0;
                    line.args[1] = 16;
                    line.args[2] = 105;
                    line.args[3] = 0;
                    line.args[4] = 0;
                }
            } else if(e.Args[0] == 2) {
                players[e.Player].mo.A_PlaySound("acess/deny1");
            }
        } else if(e.Name == "opendoorinput") {
            self.doorfront = e.Args[1];
            self.doorback = e.Args[2];
            LineIdIterator it = LineIdIterator.Create(doorfront);
            int lineid = it.Next();
            if(lineid > 0) {
                Line line = level.Lines[lineid];
                if(line.special == 12) {
                    Console.Printf("Door is already open.");
                    return;
                }
            }
            self.realCodeStr = String.format("%d", e.Args[0]);
            self.codeStr = "";
            for(int i = 0; i < self.realCodeStr.Length(); i++) {
                self.codeStr.AppendFormat("_");
            }
            self.IsUiProcessor = true;

        } else if(e.Name == "putcodechar") {
            for(int i = 0; i < self.codeStr.Length(); i++) {
                if(self.codeStr.CharAt(i) != '_') continue;

                if(e.Args[0] != 8) {
                    self.codeStr = String.format("%s%c", self.codeStr.Left(i), e.Args[0]);
                    for(int j = self.codeStr.Length(); j < self.realCodeStr.Length(); j++) {
                        self.codeStr.AppendFormat("_");
                    }
                    break;
                } else {
                    self.codeStr = self.codeStr.Left(max(i-1, 0));
                    for(int j = self.codeStr.Length(); j < self.realCodeStr.Length(); j++) {
                        self.codeStr.AppendFormat("_");
                    }
                    break;
                }
            }
            for(int i = 0; i < self.codeStr.Length(); i++) {
                if(self.codeStr.CharAt(i) == '_') {
                    return;
                }
            }

            SendNetworkEvent("closedoorinput", 2 - (self.codeStr == self.realCodeStr));
        }
    }
}

// Flashlight mod


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
    Default
    {
        +DYNAMICLIGHT.ATTENUATE
    }


    enum ELocation
    {
        HELMET = 0,
        RIGHT_SHOULDER = 1,
        LEFT_SHOULDER = 2,
        CAMERA = 3
    };

    PlayerPawn toFollow;

    Vector3 offset;

    Vector3 finalOffset;

    double zBump; // z offset for locations that are not CAMERA

    double angleDiff;
    double turnAnglePerTic;
    bool ready;
    bool on;

    ELocation location;

    void updateFromCvars () {

        Color c = CVar.FindCVar("flashlight_color").GetString();

        args[0] = c.r;
        args[1] = c.g;
        args[2] = c.b;
        args[3] = CVar.FindCVar("flashlight_intensity").GetInt();

        self.SpotInnerAngle = CVar.FindCVar("flashlight_inner").GetFloat();
        self.SpotOuterAngle = CVar.FindCVar("flashlight_outer").GetFloat();

        self.location = CVar.FindCVar("flashlight_location").GetInt();


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
