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

public class CommandReplace implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("replace")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("survivalworldedit.command.replace")) {
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
				player.sendMessage("§cToo few parameters!");
				player.sendMessage("§cUsage: /replace [from-block] <to-block>");
			} else if(args.length == 1) {
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
				Blocks.replaceBlocks(player, new BlockData(material, damage), null);
				return true;
			} else if(args.length == 2) {
				Material mat1;
				byte data1;
				
				Material mat2;
				byte data2;
				
				if(args[0].contains(":")) {
					if(StringUtils.isNumeric(args[0].split(":")[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0].split(":")[0])) != null) {
							mat1 = Material.getMaterial(Integer.parseInt(args[0].split(":")[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0].split(":")[0] + "'");
							return true;
						}
					} else {
						try {
							mat1 = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0].split(":")[0] + "' refers to");
							return true;
						}
					}
					data1 = Byte.valueOf(args[0].split(":")[1]);
				} else {
					if(StringUtils.isNumeric(args[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0])) != null) {
							mat1 = Material.getMaterial(Integer.parseInt(args[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0] + "'");
							return true;
						}
					} else {
						try {
							mat1 = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0] + "' refers to");
							return true;
						}
					}
					data1 = Byte.valueOf("0");
				}
				
				if(args[1].contains(":")) {
					if(StringUtils.isNumeric(args[1].split(":")[0])) {
						if(Material.getMaterial(Integer.parseInt(args[1].split(":")[0])) != null) {
							mat2 = Material.getMaterial(Integer.parseInt(args[1].split(":")[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[1].split(":")[0] + "'");
							return true;
						}
					} else {
						try {
							mat2 = Material.valueOf(args[1].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[1].split(":")[0] + "' refers to");
							return true;
						}
					}
					data2 = Byte.valueOf(args[1].split(":")[1]);
				} else {
					if(StringUtils.isNumeric(args[1])) {
						if(Material.getMaterial(Integer.parseInt(args[1])) != null) {
							mat2 = Material.getMaterial(Integer.parseInt(args[1]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[1] + "'");
							return true;
						}
					} else {
						try {
							mat2 = Material.valueOf(args[1].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[1] + "' refers to");
							return true;
						}
					}
					data2 = Byte.valueOf("0");
				}
				if(!ItemOption.isBlockOnList(new BlockData(mat2, data2))) {
					player.sendMessage("§cCannot use that block/item.");
					return true;
				}
				Blocks.replaceBlocks(player, new BlockData(mat1, data1), new BlockData(mat2, data2));
				return true;
			}
			
		}
		return false;
	}

}
