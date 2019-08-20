package com.Jackalantern29.QCTab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTab implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tab")) {
			if(!sender.hasPermission("admin.command.tab")) {
				sender.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			if(args.length == 0) {
				sender.sendMessage(" - Tab Commands - ");
				sender.sendMessage("/tab create [group] [priority] [prefix]");
				sender.sendMessage("/tab delete [group]");
				sender.sendMessage("/tab add [player] [group] [server]");
			} else if(args.length == 1) {
				
			} else if(args.length == 2) {
				
			} else if(args.length == 3) {
				
			} else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("create")) {
					String name = args[1];
					int priority = Integer.valueOf(args[2]);
					String prefix = args[3].replace("_", " ");
					Group group = new Group(name);
					group.createGroup(name, priority, prefix);
					sender.sendMessage("Successfully create group §" + prefix + name + "§r.");
					return true;
				} else if(args[0].equalsIgnoreCase("add")) {
					//Player target = Bukkit.getPlayerExact(args[1]);	
					//PlayerData data = new PlayerData(target);
					//data.setGroup(args[3], args[2]);
					return true;
				}
			}
		}
		return false;
	}

}
