package com.Jackalantern29.DeathMessages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDeathMessage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("deathmessage")) {
			if(args.length == 0) {
				sender.sendMessage("§cUsage: /" + label + " toggle");
				return true;
			} else if(args.length == 1) {
				if(sender instanceof Player) {
					Player player = (Player)sender;
					if(Stats.getShowDeathMessages(player)) {
						Stats.setShowDeathMessages(player, false);
						player.sendMessage("§aYou will no longer see death messages.");
					} else {
						Stats.setShowDeathMessages(player, true);
						player.sendMessage("§aYou will now see death messages.");
					}
				} else
					sender.sendMessage("Only players can execute this command.");
				return true;
			} else {
				sender.sendMessage("§cError: Too many args.");
				return true;
			}
		}
		return false;
	}
	

}
