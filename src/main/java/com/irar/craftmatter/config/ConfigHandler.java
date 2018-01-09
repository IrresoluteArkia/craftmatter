package com.irar.craftmatter.config;

import java.io.File;

import com.irar.craftmatter.Ref;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler{

    public static Configuration config;

    public ConfigHandler(File configFile){
        MinecraftForge.EVENT_BUS.register(this);

        config = new Configuration(configFile);
        config.load();

        changeConfigs();
    }

    public static void changeConfigs(){
        ConfigStore.setConfigs(config);

        if(config.hasChanged()){
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.getModID().equalsIgnoreCase(Ref.MODID)){
            changeConfigs();
        }
    }
}