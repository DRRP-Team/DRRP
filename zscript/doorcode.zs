/**
 *Copyright (c) 2018 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

class DoorCodeInputActor : Actor {

    static int DoorCode(int code, int front, int back) {
        EventHandler.SendNetworkEvent("opendoorinput", code, front, back);
 
        return 1;
    }
}

class DoorCodeInputHandler : EventHandler {
    int doorfront;
    int doorback;
    String codeStr;
    String realCodeStr;

    override void OnRegister() {
        SetOrder(666);
    }

    override bool UiProcess(UiEvent ev) {
        if (ev.Type == UiEvent.Type_KeyDown && ((ev.keyChar >= 48 && ev.keyChar <= 57) || ev.keyChar == 8)) {
            SendNetworkEvent("putcodechar", ev.keyChar);
        } else if (ev.Type == UiEvent.Type_KeyDown && ev.keyChar == 27) {
            SendNetworkEvent("closedoorinput", 0);
        }

        return true;
    }

    override void RenderOverlay(RenderEvent e) {
        if (!self.IsUiProcessor) return;

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
        if (e.Name == "closedoorinput") {
            self.IsUiProcessor = false;

            if (e.Args[0] == 1) {
                players[e.Player].mo.A_PlaySound("access/grant1");

                LineIdIterator it = LineIdIterator.Create(doorfront);
                int lineid = it.Next();

                if (lineid > 0) {
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

                if (lineid > 0) {
                    Line line = level.Lines[lineid];
                    Side myside = line.sidedef[0];

                    line.special = 12;
                    line.args[0] = 0;
                    line.args[1] = 16;
                    line.args[2] = 105;
                    line.args[3] = 0;
                    line.args[4] = 0;

                    myside.SetTexture(Side.top, TexMan.CheckForTexture("drpga09b", TexMan.Type_Any));
                }
            } else if (e.Args[0] == 2) {
                players[e.Player].mo.A_PlaySound("access/deny1");
            }
        } else if (e.Name == "opendoorinput") {
            self.doorfront = e.Args[1];
            self.doorback = e.Args[2];

            LineIdIterator it = LineIdIterator.Create(doorfront);
            int lineid = it.Next();

            if (lineid > 0) {
                Line line = level.Lines[lineid];

                if (line.special == 12) {
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
        } else if (e.Name == "putcodechar") {
            for(int i = 0; i < self.codeStr.Length(); i++) {
                if (self.codeStr.CharAt(i) != '_') continue;

                if (e.Args[0] != 8) {
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
                if (self.codeStr.CharAt(i) == '_') {
                    return;
                }
            }

            SendNetworkEvent("closedoorinput", 2 - (self.codeStr == self.realCodeStr));
        }
    }
}