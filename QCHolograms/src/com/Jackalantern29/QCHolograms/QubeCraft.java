package com.Jackalantern29.QCHolograms;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jackalantern29.QCHolograms.Commands.CommandHologram;

public class QubeCraft extends JavaPlugin {
	public static QubeCraft plugin;
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		getCommand("hologram").setExecutor(new CommandHologram());
		File Holograms = new File(getDataFolder() + File.separator + "Holograms");
		if (!Holograms.exists()) {
			try {
				getLogger().info("Holograms folder created!");
				Holograms.mkdir();
			} catch (SecurityException e) {
				getLogger().info("Holograms folder can not created!");
				e.printStackTrace();
			}
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(File file : new File(getDataFolder() + File.separator + "Holograms").listFiles()) {
					YamlConfiguration SC = YamlConfiguration.loadConfiguration(file);
					boolean exists = false;
					Location loc = new Location(Bukkit.getWorld(SC.getString("Location.World")), SC.getDouble("Location.X"), SC.getDouble("Location.Y"), SC.getDouble("Location.Z"));
					for(Entity entity : Bukkit.getWorld(SC.getString("Location.World")).getEntities()) {
						if(entity instanceof ArmorStand) {
							ArmorStand armorstand = (ArmorStand)entity;
							if((armorstand.isMarker()) && (!armorstand.isVisible())) {
								if((armorstand.getLocation().getX() == SC.getDouble("Location.X")) && (armorstand.getLocation().getY() == SC.getDouble("Location.Y")) && (armorstand.getLocation().getZ() == SC.getDouble("Location.Z"))) {
									exists = true;
									armorstand.setCustomName(SC.getString("Message").replace("&", "§").replace("%player%", Bukkit.getOnlinePlayers().size() + ""));
									armorstand.setCustomNameVisible(true);
									armorstand.setVisible(false);
									armorstand.setGravity(false);
									armorstand.setMarker(true);
								}
							}
						}
					}
					if(exists == false) {
						ArmorStand armorstand1 = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
						armorstand1.setCustomName(SC.getString("Message"));
						armorstand1.setCustomNameVisible(true);
						armorstand1.setVisible(false);
						armorstand1.setGravity(false);
						armorstand1.setMarker(true);
					}
				}
			}
		}, 0, 5);
	}
	
	public void onDisable() {
		
	}
	
	public static QubeCraft getInstance() {
		return plugin;
	}
}