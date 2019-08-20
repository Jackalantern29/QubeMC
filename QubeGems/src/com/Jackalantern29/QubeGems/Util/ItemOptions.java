package com.Jackalantern29.QubeGems.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemOptions {
	
	public static ItemStack createItem(Material material, int amount, int data, String name, String...strings) {
		ItemStack item = new ItemStack(material, amount, Short.valueOf("" + data));
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> list = new ArrayList<>();
		for(String s : strings) {
			list.add(s);
		}
		meta.setLore(list);
		item.setItemMeta(meta);
		return item;
	}
}
