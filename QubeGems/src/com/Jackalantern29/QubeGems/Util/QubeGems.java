package com.Jackalantern29.QubeGems.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class QubeGems {
	static File file = new File("../QubeMC - Data/QubeGems/Players");
	private static YamlConfiguration config;
	public static void createProfile(UUID uuid) {
		File p = new File(file, uuid.toString() + ".yml");
		if(!p.exists()) {
			try {
				p.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
			config.set("Gems", 0);
			config.set("Particles.ReddustHaloHead", false);
			config.set("Particles.FlameHaloFeet", false);
			config.set("Particles.DripLavaHaloWaist", false);
			config.set("Particles.DragonBreathCape", false);
			try {
				config.save(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void createConfig() {
		File c = new File("../QubeMC - Data/QubeGems", "config.yml");
		if(!c.exists()) {
			try {
				c.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			config.set("Particles.ReddustHaloHead", 100);
			config.set("Particles.FlameHaloFeet", 125);
			config.set("Particles.DripLavaHaloWaist", 150);
			config.set("Particles.DragonBreathCape", 200);
			List<String> s = new ArrayList<>();
			s.add("INK_SAC:4;1 = 5");
			config.set("Items", s);
			try {
				config.save(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(c);
	}
	public static YamlConfiguration getConfig() {
		return config;
	}
	public static File getConfigFile() {
		return new File("../QubeMC - Data/QubeGems", "config.yml");
	}
	public static void addGems(UUID uuid, int amount) {
		File p = new File(file, uuid.toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
		config.set("Gems", config.getInt("Gems") + amount);
		try {
			config.save(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void removeGems(UUID uuid, int amount) {
		File p = new File(file, uuid.toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
		config.set("Gems", config.getInt("Gems") - amount);
		try {
			config.save(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setGems(UUID uuid, int value) {
		File p = new File(file, uuid.toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
		config.set("Gems", value);
		try {
			config.save(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setParticle(UUID uuid, String particle, String type, String location, boolean value) {
		File p = new File(file, uuid.toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
		config.set("Particles." + particle + type + location, value);
		try {
			config.save(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean hasParticle(UUID uuid, String particle, String type, String location) {
		File p = new File(file, uuid.toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
		return config.getBoolean("Particles." + particle + type + location);
	}
	public static int getParticlePrice(String particle, String type, String location) {
		return config.getInt("Particles." + particle + type + location);
	}
	
	public static int getGems(UUID uuid) {
		File p = new File(file, uuid.toString() + ".yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(p);
		return config.getInt("Gems");
	}
	@SuppressWarnings("deprecation")
	public static List<ItemStack> getItems() {
		List<ItemStack> list = new ArrayList<>();
		for(String s : config.getStringList("Server." + Bukkit.getServerName().replace("QubeMC - ", "").replace(" ", "") + ".Items")) {
			ItemStack item = null;
			if(s.split(":")[0].equals("MYSTERY_KEY")) {
				Material material = Material.TRIPWIRE_HOOK;
				short data = Short.valueOf(s.split(":")[1].split(";")[0]);
				int amount = Integer.valueOf(s.split(";")[1].split(" = ")[0]);
				item = new ItemStack(material, amount, data);
				item = ItemOptions.createItem(material, amount, data, "§9§lMystery Crate Key", "§eUse this key at §c/crates");
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			} else if(s.split(":")[0].equals("PRESTIGIOUS_KEY")) {
				Material material = Material.TRIPWIRE_HOOK;
				short data = Short.valueOf(s.split(":")[1].split(";")[0]);
				int amount = Integer.valueOf(s.split(";")[1].split(" = ")[0]);
				item = new ItemStack(material, amount, data);
				item = ItemOptions.createItem(material, amount, data, "§5§lPrestigious Crate Key", "§eUse this key at §c/crates");
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
			} else if(s.split(":")[0].equals("MOOSHROOM_SPAWN_EGG")) {
				item = new ItemStack(Material.MONSTER_EGG, Integer.valueOf(s.split(";")[1].split(" = ")[0]));
		        net.minecraft.server.v1_9_R2.ItemStack stack = CraftItemStack.asNMSCopy(item);
		        NBTTagCompound tagCompound = stack.getTag();
		        if(tagCompound == null){
		            tagCompound = new NBTTagCompound();
		        }
		        NBTTagCompound id = new NBTTagCompound();
		        id.setString("id", EntityType.MUSHROOM_COW.getName());
		        tagCompound.set("EntityTag", id);
		        stack.setTag(tagCompound);
		        item = CraftItemStack.asBukkitCopy(stack);
		        ItemMeta meta = item.getItemMeta();
		        meta.setDisplayName("§aMushroom Cow");
		        item.setItemMeta(meta);
			}
			else {
				Material material = Material.valueOf(s.split(":")[0]);
				short data = Short.valueOf(s.split(":")[1].split(";")[0]);
				int amount = Integer.valueOf(s.split(";")[1].split(" = ")[0]);
				item = new ItemStack(material, amount, data);
			}
			list.add(item);
		}
		return list;
	}
	
	public static int getItemPrice(ItemStack item) {
		for(String s : config.getStringList("Server." + Bukkit.getServerName().replace("QubeMC - ", "").replace(" ", "") + ".Items")) {
			if(s.split(":")[0].equals("MYSTERY_KEY") || s.split(":")[0].equals("PRESTIGIOUS_KEY") || s.split(":")[0].equals("MOOSHROOM_SPAWN_EGG"))
				return Integer.valueOf(s.split(" = ")[1]);
			Material material = Material.valueOf(s.split(":")[0]);
			short data = Short.valueOf(s.split(":")[1].split(";")[0]);
			int amount = Integer.valueOf(s.split(";")[1].split(" = ")[0]);
			if(material.equals(item.getType()) && data == item.getDurability() && amount == item.getAmount())
				return Integer.valueOf(s.split(" = ")[1]);
		}
		return 0;
	}

}
