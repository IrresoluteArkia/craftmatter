package com.irar.craftmatter.config;

import net.minecraftforge.common.config.Configuration;

public class ConfigStore {

	public static void setConfigs(Configuration config) {
        for(ConfigBooleans currConf : ConfigBooleans.values()){
            currConf.currentValue = config.get(currConf.category, currConf.name, currConf.def, currConf.desc).getBoolean();
        }
        
        for(ConfigInts currConf : ConfigInts.values()){
            currConf.currentValue = config.get(currConf.category, currConf.name, currConf.def, currConf.desc, currConf.min, currConf.max).getInt();
        }
	}

}
