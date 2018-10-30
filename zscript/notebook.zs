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
		
		nbitem.languageStrings.push(entry);
	}
}