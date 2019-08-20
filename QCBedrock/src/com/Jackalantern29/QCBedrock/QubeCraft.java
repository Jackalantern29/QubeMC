package com.Jackalantern29.QCBedrock;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.Jackalantern29.QCBedrock.Listener.MineBedrockListener;

public class QubeCraft extends JavaPlugin {
	
	public void onEnable() {
		
		File ConfigFile = new File(getDataFolder() + "/config.yml");
		if (!ConfigFile.exists()) {
			saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(new MineBedrockListener(this), this);
	}
	public void onDisable() {
		
	}

}
