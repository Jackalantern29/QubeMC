package com.Jackalantern29.SurvivalWorldEdit.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandWand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("wand")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("survivalworldedit.command.wand")) {
				player.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			ItemStack item = new ItemStack(Material.STONE_AXE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§6§lWand");
			List<String> list = new ArrayList<>();
			list.add("§7WorldEdit Wand");
			meta.setLore(list);
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			item.setItemMeta(meta);
			player.getInventory().addItem(item);
			player.sendMessage("§dLeft click: select pos #1; Right click: select pos #2");
			return true;
		}
		return false;
	}
}
