/**
 *Copyright (c) 2018 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

class DoorCodeInputActor {

    static int DoorCode(Actor activator, string info, int code, int front, int back) {
        EventHandler.SendNetworkEvent("opendoorinput@@@@" .. info, code, front, back);
		
			
 
        return 1;
    }
}

class DoorCodeInputStubItem : Inventory {
    String code;
    String displayTooltip;
    int doorfront;
    int doorback;
}

class DoorCodeInputHandler : EventHandler {

    override void OnRegister() {
        SetOrder(666);
    }

    /*override bool UiProcess(UiEvent ev) {
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
    }*/

    override void NetworkProcess(ConsoleEvent e) {
		Array<string> command;
		e.Name.Split(command, "@@@@");
		if(command[0] == "opendoorinput") {
			if(command.size() == 2) {
				players[e.player].mo.A_GiveInventory("DoorCodeInputStubItem");
				DoorCodeInputStubItem item = DoorCodeInputStubItem(players[e.player].mo.findInventory("DoorCodeInputStubItem"));
				item.code = String.format("%d", e.args[0]);
				item.displayTooltip = command[1];
				item.doorfront = e.args[1];
				item.doorback = e.args[2];
				Menu.SetMenu("DoorCodeInputMenu");
			}
        } else if (command[0] == "closedoorinput") {
            // self.IsUiProcessor = false;
			
			DoorCodeInputStubItem item = DoorCodeInputStubItem(players[e.player].mo.findInventory("DoorCodeInputStubItem"));
			if(item == null) return;

            if (e.Args[0] == 1) {
                players[e.Player].mo.A_PlaySound("access/grant1");

                LineIdIterator it = LineIdIterator.Create(item.doorfront);
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
                it = LineIdIterator.Create(item.doorback);
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
        }
    }
}

class DoorCodeInputMenu : GenericMenu {
    String codeStr;
    String realCodeStr;
	String displayTooltip;
	
	Override
	void init(Menu parent) {
		DoorCodeInputStubItem item = DoorCodeInputStubItem(players[consoleplayer].mo.findInventory("DoorCodeInputStubItem"));
		if(item == null) {
			codeStr = "____";
			realCodeStr = "0000";
			displayTooltip = "you shouldn't see this\n";
		} else {
			realCodeStr = item.code;
			codeStr = "";
			displayTooltip = item.displayTooltip;

            for(int i = 0; i < self.realCodeStr.Length(); i++) {
                codeStr.AppendFormat("_");
            }
		}
	}
	
	Override
	void drawer() {
		super.drawer();
		let tex = TexMan.CheckForTexture("M_WINDOW", TexMan.Type_MiscPatch);
		if (tex.IsValid()) {
			Vector2 v = TexMan.GetScaledSize(tex);
			int x = 320;
			int y = 320;
			screen.DrawTexture(tex, false, x, y, DTA_LeftOffset, int(v.x / 2), DTA_VirtualWidth, 640, DTA_VirtualHeight, 480);				
			Font font = Font.FindFont("SMALLFONT");
			screen.DrawText(font, Font.CR_WHITE, 244, 322, displayTooltip .. " " .. self.codeStr, DTA_VirtualWidth, 640, DTA_VirtualHeight, 480);
		}
	}
	
	Override
	bool OnUIEvent(UIEvent ev) { 
		super.OnUIEvent(ev);
        if (ev.Type == UiEvent.Type_KeyDown && ((ev.keyChar >= 48 && ev.keyChar <= 57) || ev.keyChar == 8)) {
            for(int i = 0; i < self.codeStr.Length(); i++) {
                if (self.codeStr.CharAt(i) != '_') continue;

                if (ev.keyChar != 8) {
                    self.codeStr = String.format("%s%c", self.codeStr.Left(i), ev.keyChar);

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
                    return true;
                }
            }
			
			if(codeStr == realCodeStr) {
				EventHandler.SendNetworkEvent("closedoorinput", codeStr == realCodeStr ? 1 : 0);
				Close();
			}
        } else if (ev.Type == UiEvent.Type_KeyDown && ev.keyChar == 27) {
            Close();
        }

        return true;
	}
}