class NotebookMenu : OptionMenu {
	Override
	void Init(Menu parent, OptionMenuDescriptor desc) {
		super.Init(parent, desc);
		OptionMenuItemStaticText item = new("OptionMenuItemStaticText");
		item.Init("Test");
		desc.mItems.push(item);
	}
}