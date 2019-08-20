package com.Jackalantern29.DeathMessages;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Stats {	
	public static void createStatFile(Player player) {
		File stats = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Players", player.getUniqueId().toString() + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
		if(!stats.exists()) {
	        try {
	            stats.createNewFile();
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
			SC.set("ShowDeathMessages", QubeCraft.getInstance().getConfig().getBoolean("ShowDeathMessagesDefault"));
			try {
				SC.save(stats);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void setShowDeathMessages(Player player, Boolean arg1) {
		File stats = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Players", player.getUniqueId().toString() + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
		SC.set("ShowDeathMessages", arg1);
		try {
			SC.save(stats);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean getShowDeathMessages(Player player) {
		File stats = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Players", player.getUniqueId().toString() + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
		return SC.getBoolean("ShowDeathMessages");
	}
}