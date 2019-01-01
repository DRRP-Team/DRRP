/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

class NotebookItem : Inventory {
    Array<String> languageStrings;
}

class NotebookTooltipItem : Inventory {}

class NotebookHandler : EventHandler {
	Override
	void RenderOverlay(RenderEvent e) {
		if(players[consoleplayer].mo.CountInv("NotebookTooltipItem") >= 1) {
			Font font = Font.FindFont("SMALLFONT");
			int key1, key2;
			[key1, key2] = Bindings.GetKeysForCommand("opennotebook");
			if(key1 == 0 && key2 == 0) return;
			String s = KeyBindings.NameKeys(key1, key2);
			s.ToUpper();
			String ss = StringTable.Localize( "$DRRP_D_NOTEBOOK_TIP_PART1" ) 
					.. s .. StringTable.Localize( "$DRRP_D_NOTEBOOK_TIP_PART2" );
			Screen.drawText(font, Font.CR_GOLD, Screen.GetWidth() - font.StringWidth(ss), 0, ss);
		}
	}
	
	Override
	void NetworkProcess(ConsoleEvent e) {
		if(e.name == "killnotebook") {
			players[e.player].mo.A_TakeInventory("NotebookTooltipItem", 0);
		}
	}
}

class NotebookMenu : OptionMenu {
    Override
    void Init(Menu parent, OptionMenuDescriptor desc) {
        super.Init(parent, desc);
		EventHandler.SendNetworkEvent("killnotebook");
        playerinfo player = players[consoleplayer];
        desc.mItems.clear();
        if(player == null || player.mo == null) {
            OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
            item.Init( StringTable.Localize( "$DRRP_D_NOTEBOOK_NOTINGAME" ) );
            desc.mItems.push(item);
            return;
        }
        NotebookItem nbitem = NotebookItem(player.mo.FindInventory("NotebookItem"));
        if(nbitem == null || nbitem.languageStrings.size() == 0) {
            OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
            item.Init( StringTable.Localize( "$DRRP_D_NOTEBOOK_EMPTY" ) );
            desc.mItems.push(item);
        } else {
            for(int i = nbitem.languageStrings.size() - 1; i >= 0; i--) {
                String langstring = nbitem.languageStrings[i];
                Array<String> langstring_spl;
                langstring_spl.clear();
                langstring.split(langstring_spl, "\n");
                for(int j = 0; j < langstring_spl.size(); j++) {
                    OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
                    item.Init(langstring_spl[j]);
                    desc.mItems.push(item);
                }
                {
                    OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
                    item.Init("");
                    desc.mItems.push(item);
                }
            }
        }
    }
}

class NotebookAPI {
    play static void AddNotebookEntry(Actor activator, String entry) {
        if(activator.CountInv("NotebookItem") <= 0) {
            activator.A_GiveInventory("NotebookItem");
        }
        
        NotebookItem nbitem = NotebookItem(activator.FindInventory("NotebookItem"));
        
		if(nbitem.languageStrings.find(entry) == nbitem.languageStrings.size()) {
			nbitem.languageStrings.push(entry);
		}
		
		activator.A_GiveInventory("NotebookTooltipItem");
    }
}
