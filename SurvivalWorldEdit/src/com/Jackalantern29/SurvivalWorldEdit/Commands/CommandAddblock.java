package com.Jackalantern29.SurvivalWorldEdit.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Jackalantern29.SurvivalWorldEdit.QubeMC;

public class CommandAddblock implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("addblock")) {
			if(!sender.hasPermission("survivalworldedit.command.addblock")) {
				sender.sendMessage("You do not have permission to use this command.");
				return true;
			}
			if(args.length == 0) {
				sender.sendMessage("§cNot enough args");
				sender.sendMessage("§c/addblock <block> <tool> [return block]");
				return true;
			} else if(args.length == 2) {
				Material material;
				byte data;
				if(args[0].contains(":")) {
					try {
						material = Material.valueOf(args[0].split(":")[0].toUpperCase());
					} catch(IllegalArgumentException e) {
						sender.sendMessage("§cCan't figure out what block '" + args[0].split(":")[0] + "' refers to");
						return true;
					}
					data = Byte.valueOf(args[0].split(":")[1]);
				} else {
					try {
						material = Material.valueOf(args[0].toUpperCase());
					} catch(IllegalArgumentException e) {
						sender.sendMessage("§cCan't figure out what block '" + args[0] + "' refers to");
						return true;
					}
					data = Byte.valueOf((byte)0);
				}
				if(args[1].equalsIgnoreCase("pickaxe") || args[1].equalsIgnoreCase("axe") || args[1].equalsIgnoreCase("spade") || args[1].equalsIgnoreCase("shovel") || args[1].equalsIgnoreCase("shears")) {
					String tool = "_" + args[1].replace("shovel", "spade").toUpperCase();
					String newString = material.name() + ":" + data + " - " + tool.replace("_", "") + ", " + material.name() + ":" + data;
					if(QubeMC.getInstance().getConfig().getStringList("blocks").contains(newString)) {
						sender.sendMessage("§aThat block is already on the list.");
						return true;
					}
					return true;
				} else {
					sender.sendMessage("§cInvalid tool.");
					sender.sendMessage("§cTool options: Pickaxe, Axe, Spade, Shovel, Shears");
					return true;
				}
			}
		}
		return false;
	}

}
