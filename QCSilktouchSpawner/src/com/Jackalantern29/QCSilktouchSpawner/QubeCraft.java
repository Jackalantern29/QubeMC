package com.Jackalantern29.QCSilktouchSpawner;

import org.bukkit.plugin.java.JavaPlugin;

public class QubeCraft extends JavaPlugin {
	static QubeCraft plugin;
	public static String prefix = null;
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		prefix = getConfig().getString("Prefix").replace("&", "§");
		getServer().getPluginManager().registerEvents(new MineSpawnerListener(), this);
	}
	public void onDisable() {
		
	}
	public static QubeCraft getInstance() {
		return plugin;
	}
	
}