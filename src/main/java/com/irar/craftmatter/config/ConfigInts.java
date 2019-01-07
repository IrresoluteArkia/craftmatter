package com.irar.craftmatter.config;

public enum ConfigInts {
	ANTI_GRENADE_MAX_ANTIMATTER("Maximum antimatter grenade size", "Grenade", 100000, 0, 10000000, "Maximum amount of antimatter able to be crafted into a single grenade"),
	ANTI_GRENADE_BLOCKS_PER_TICK("Blocks per tick", "Grenade", 30, 1, 50000, "Blocks destroyed by antimatter grenades per grenade per tick");
	
    public final String name;
    public final String category;
    public final int def;
    public final int min;
    public final int max;
    public final String desc;
    public int currentValue;
    
    ConfigInts(String name, String category, int def, int min, int max, String desc){
    	this.name = name;
    	this.category = category;
    	this.def = def;
    	this.min = min;
    	this.max = max;
    	this.desc = desc;
    }
}
