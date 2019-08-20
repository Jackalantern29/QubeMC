package com.Jackalantern29.QubeGems.Commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Jackalantern29.QubeGems.Util.QubeGems;

public class CommandGiveQubeGems implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("givequbegems")) {
			if(!sender.hasPermission("qubegems.command.give")) {
				sender.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			if(args.length == 0 || args.length == 1) {
				sender.sendMessage("§cUsage: /giveqg <player> <amount>");
				return true;
			} else {
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				if(target == null) {
					sender.sendMessage("§cCould not find player.");
					return true;
				}
				if(!StringUtils.isNumeric(args[1])) {
					sender.sendMessage("§cError: <amount> is not a number.");
					return true;
				}
				QubeGems.addGems(target.getUniqueId(), Integer.parseInt(args[1]));
				sender.sendMessage("Added §a" + args[1] + " §fto user §a" + target.getName());
				return true;
			}
		}
		return false;
	}

}
