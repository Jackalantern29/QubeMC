package com.Jackalantern29.SurvivalWorldEdit.Commands;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandTree implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tree")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("survivalworldedit.command.tree")) {
				player.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			
			//Check if player has bonemeal
			boolean hasBonemeal = false;
			int countBonemeal = 0;
			for(ItemStack item : player.getInventory().getContents()) {
				if(item != null) {
					if(item.getType().equals(Material.INK_SACK)) {
						if(item.getData().getData() == 15) {
							hasBonemeal = true;
							countBonemeal = (countBonemeal + item.getAmount());
						}
					}
				}
			}
			
			for(ItemStack item : player.getInventory().getContents()) {
				if(item != null) {
					if(hasBonemeal) {
						if(countBonemeal >= 3) {
							if(player.getInventory().contains(Material.SAPLING)) {
								if(item.getType().equals(Material.SAPLING)) {
									//byte data = item.getData().getData();
									Location loc = player.getTargetBlock((Set<Material>)null, 10).getLocation();
									if((loc.getBlock().getType().equals(Material.DIRT)) || (loc.getBlock().getType().equals(Material.GRASS))) {
										if(loc.add(0, 1, 0).getBlock().getType().equals(Material.AIR)) {
											loc.getWorld().generateTree(loc.add(0, 1, 0), TreeType.TREE);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
}
