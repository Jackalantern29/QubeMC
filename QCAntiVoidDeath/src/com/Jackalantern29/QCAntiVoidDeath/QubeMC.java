package com.Jackalantern29.QCAntiVoidDeath;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeMC extends JavaPlugin {
	
	public void onEnable() {
		saveDefaultConfig();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(player.getLocation().getWorld().equals(Bukkit.getWorld(getConfig().getString("World")))) {
						if(player.getLocation().getY() <= 5) {
							File file = new File("plugins/Essentials", "spawn.yml");
							YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
							Location spawn = new Location(Bukkit.getWorld(config.getString("spawns.default.world")), config.getDouble("spawns.default.x"), config.getDouble("spawns.default.y"), config.getDouble("spawns.default.z"), Float.valueOf(config.getString("spawns.default.yaw")), Float.valueOf(config.getString("spawns.default.pitch")));
							player.teleport(spawn);
							player.sendMessage("" + getConfig().getString("Fall_Message").replace("&", "§"));
						}
					}
				}
			}
		}, 0, 1);
	}
	
	public void onDisable() {
		
	}

}
