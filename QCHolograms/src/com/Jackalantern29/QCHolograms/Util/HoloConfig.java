package com.Jackalantern29.QCHolograms.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.Jackalantern29.QCHolograms.QubeCraft;

public class HoloConfig {
	public static boolean createHologram(Location location, String name, String message) {
		File stats = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
		if (!stats.exists()) {
			try {
				stats.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			SC.set("Location.World", location.getWorld().getName());
			SC.set("Location.X", location.getX());
			SC.set("Location.Y", location.getY() + 1.7);
			SC.set("Location.Z", location.getZ());
			SC.set("Message", message);
			try {
				SC.save(stats);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ArmorStand armorstand = (ArmorStand) location.getWorld().spawnEntity(location.add(0, 1.7, 0), EntityType.ARMOR_STAND);
			armorstand.setCustomName(message);
			armorstand.setCustomNameVisible(true);
			armorstand.setVisible(false);
			armorstand.setGravity(false);
			armorstand.setMarker(true);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean addHologramMessage(String name, String message) {
		int number = 0;
		for(String string : new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms").list()) {
			if(string.contains(name)) {
				number++;
			}
		}
		File stats = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + "(" + number + ").yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
		Location location = getHologramLocation(name);
		if (!stats.exists()) {
			try {
				stats.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			SC.set("Location.World", location.getWorld().getName());
			SC.set("Location.X", location.getX());
			SC.set("Location.Y", location.getY() - (0.3 * number));
			SC.set("Location.Z", location.getZ());
			SC.set("Message", message);
			try {
				SC.save(stats);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ArmorStand armorstand = (ArmorStand) location.getWorld().spawnEntity(location.add(0, -(0.3 * number), 0), EntityType.ARMOR_STAND);
			armorstand.setCustomName(message);
			armorstand.setCustomNameVisible(true);
			armorstand.setVisible(false);
			armorstand.setGravity(false);
			armorstand.setMarker(true);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean deleteHologram(String name) {
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(hologram);
		if(!hologram.exists()) {
			return false;
		} else {
			for(Entity entity : Bukkit.getWorld(SC.getString("Location.World")).getEntities()) {
				if(entity instanceof ArmorStand) {
					ArmorStand armorstand = (ArmorStand)entity;
					if((armorstand.isMarker()) && (!armorstand.isVisible())) {
						if((armorstand.getLocation().getX() == SC.getDouble("Location.X")) && (armorstand.getLocation().getY() == SC.getDouble("Location.Y")) && (armorstand.getLocation().getZ() == SC.getDouble("Location.Z")) && (armorstand.getCustomName().equals(SC.getString("Message").replace("&", "§")))) {
							armorstand.remove();
						}
					}
				}
			}
			hologram.delete();
			return true;
		}
	}
	public static boolean delHologramLine(String name) {
		int number = -1;
		for(String string : new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms").list()) {
			if(string.contains(name))
				number++;
		}
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + "(" + number + ").yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(hologram);
		if(!hologram.exists()) {
			return false;
		} else {
			for(Entity entity : Bukkit.getWorld(SC.getString("Location.World")).getEntities()) {
				if(entity instanceof ArmorStand) {
					ArmorStand armorstand = (ArmorStand)entity;
					if((armorstand.isMarker()) && (!armorstand.isVisible())) {
						if((armorstand.getLocation().getX() == SC.getDouble("Location.X")) && (armorstand.getLocation().getY() == SC.getDouble("Location.Y")) && (armorstand.getLocation().getZ() == SC.getDouble("Location.Z")) && (armorstand.getCustomName().equals(SC.getString("Message").replace("&", "§")))) {
							armorstand.remove();
						}
					}
				}
			}
			hologram.delete();
			return true;
		}
	}
	
	public static boolean doesHologramExsits(String name) {
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		return hologram.exists();
	}
	
	public static boolean changeHologramMessage(String name, String message) {
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(hologram);
		if(!hologram.exists()) {
			return false;
		} else {
			for(Entity entity : Bukkit.getWorld(SC.getString("Location.World")).getEntities()) {
				Bukkit.getPlayer("Jackalantern29").sendMessage("Found1");
				if(entity instanceof ArmorStand) {
					Bukkit.getPlayer("Jackalantern29").sendMessage("Found2");
					ArmorStand armorstand = (ArmorStand)entity;
					if((armorstand.isMarker()) && (!armorstand.isVisible())) {
						Bukkit.getPlayer("Jackalantern29").sendMessage("Found3");
						if((armorstand.getLocation().getX() == SC.getDouble("Location.X")) && (armorstand.getLocation().getY() == SC.getDouble("Location.Y")) && (armorstand.getLocation().getZ() == SC.getDouble("Location.Z")) && (armorstand.getCustomName().equals(SC.getString("Message").replace("&", "§").replace("%player%", Bukkit.getOnlinePlayers().size() + "")))) {
							Bukkit.getPlayer("Jackalantern29").sendMessage("Found4");
							SC.set("Message", message);
							try {
								SC.save(hologram);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			return true;
		}
	}
	
	public static boolean changeHologramName(String name, String filename) {
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		if(!hologram.exists()) {
			return false;
		} else {
			hologram.renameTo(hologram);
			return true;
		}
	}
	
	public static boolean teleportHologram(String name, Location loc) {
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(hologram);
		if(!hologram.exists()) {
			return false;
		} else {
			SC.set("Location.X", loc.getX());
			SC.set("Location.Y", loc.getY());
			SC.set("Location.Z", loc.getZ());
			try {
				SC.save(hologram);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	public static Location getHologramLocation(String name) {
		File hologram = new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms", name + ".yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(hologram);
		Location loc = new Location(Bukkit.getWorld(SC.getString("Location.World")), SC.getDouble("Location.X"), SC.getDouble("Location.Y"), SC.getDouble("Location.Z"));
		return loc;
	}
}