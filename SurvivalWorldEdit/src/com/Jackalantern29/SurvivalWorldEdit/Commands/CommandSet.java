package com.Jackalantern29.SurvivalWorldEdit.Commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jackalantern29.SurvivalWorldEdit.Util.BlockData;
import com.Jackalantern29.SurvivalWorldEdit.Util.Blocks;
import com.Jackalantern29.SurvivalWorldEdit.Util.ItemOption;

public class CommandSet implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("set")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("survivalworldedit.command.set")) {
				player.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			if(Blocks.isPosNull(player.getUniqueId(), 0)) {
				player.sendMessage("§cMake a region selection first.");
				return true;
			}
			if(Blocks.isPosNull(player.getUniqueId(), 1)) {
				player.sendMessage("§cMake a region selection first.");
				return true;
			}
			if(args.length == 0) {
				player.sendMessage("§cMissing value for <fillPattern>.");
				return true;
			} else if(args.length == 1) {
				//List<Block> blocks = Blocks.getBlocks(player.getUniqueId());
				Material material;
				byte damage;
				if(args[0].contains(":")) {
					if(StringUtils.isNumeric(args[0].split(":")[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0].split(":")[0])) != null) {
							material = Material.getMaterial(Integer.parseInt(args[0].split(":")[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0].split(":")[0] + "'");
							return true;
						}
					} else {
						try {
							material = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0].split(":")[0] + "' refers to");
							return true;
						}
					}
					damage = Byte.valueOf(args[0].split(":")[1]);
				} else {
					if(StringUtils.isNumeric(args[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0])) != null) {
							material = Material.getMaterial(Integer.parseInt(args[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0] + "'");
							return true;
						}
					} else {
						try {
							material = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0] + "' refers to");
							return true;
						}
					}
					damage = Byte.valueOf("0");
				}
				if(!ItemOption.isBlockOnList(new BlockData(material, damage))) {
					player.sendMessage("§cCannot use that block/item.");
					return true;
				}
				Blocks.setBlocks(player, new BlockData(material, damage));
				//Blocks.addUndoBlocks(player.getUniqueId(), list);
				return true;
			}
		}
		return false;
	}

}
