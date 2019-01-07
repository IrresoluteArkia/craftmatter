package com.irar.craftmatter.config;

public enum ConfigBooleans {
	ANTI_GRENADE_ENABLED("Antimatter grenade enabled", "Grenade", true, "Whether or not the antimatter grenade will throw."),
	CRAFT_IN_TABLE("Craft matter in crafting table", "Matter", false, "Whether or not craft matter can be made directly in the crafting table"),
	SHOW_TOOLTIP("Show matter tooltip", "Tooltip", true, "Whether or not to show the matter tooltip on every item");

	public final String name;
    public final String category;
    public final boolean def;
    public final String desc;
	public boolean currentValue;
	
	ConfigBooleans(String name, String category, boolean def, String desc){
		this.name = name;
		this.category = category;
		this.def = def;
		this.desc = desc;
	}

}
