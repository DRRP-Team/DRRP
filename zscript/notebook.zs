class NotebookItem : Inventory {
	Array<String> languageStrings;
}

class NotebookMenu : OptionMenu {
	Override
	void Init(Menu parent, OptionMenuDescriptor desc) {
		super.Init(parent, desc);
		playerinfo player = players[consoleplayer];
		NotebookItem nbitem = NotebookItem(player.mo.FindInventory("NotebookItem"));
		desc.mItems.clear();
		if(nbitem == null || nbitem.languageStrings.size() == 0) {
			OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
			item.Init("Notebook is empty");
			desc.mItems.push(item);
		} else {
			for(int i = 0; i < nbitem.languageStrings.size(); i++) {
				OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
				item.Init(nbitem.languageStrings[i]);
				desc.mItems.push(item);
			}
		}
	}
}

class NotebookAPI {
	play static void AddNotebookEntry(Actor activator, string entry) {
		if(activator.CountInv("NotebookItem") <= 0) {
			activator.A_GiveInventory("NotebookItem");
		}
		
		NotebookItem nbitem = NotebookItem(activator.FindInventory("NotebookItem"));
		
		nbitem.languageStrings.push(entry);
	}
}