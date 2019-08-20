package com.Jackalantern29.QCParkour.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jackalantern29.QCParkour.QubeCraft;

public class CommandSetparkour implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("setparkour")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can perform this command!");
				return true;
			}
			Player player = (Player)sender;
			Location location = player.getLocation();
			if(!player.hasPermission("qubecraft.command.setparkour")) {
				player.sendMessage("§cYou do not have permission to do this!");
				return true;
			}
			if(args.length == 0) {
				player.sendMessage("§e/setparkour lobby");
				player.sendMessage("§e/setparkour easy");
				player.sendMessage("§e/setparkour medium");
				player.sendMessage("§e/setparkour medium checkpoint1");
				player.sendMessage("§e/setparkour hard");
				player.sendMessage("§e/setparkour hard checkpoint1");
				player.sendMessage("§e/setparkour hard checkpoint2");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("lobby")) {
					QubeCraft.setParkourLobby(location);
					return true;
				} else if(args[0].equalsIgnoreCase("easy")) {
					QubeCraft.setParkourEasy(location);
					return true;
				} else if(args[0].equalsIgnoreCase("medium")) {
					QubeCraft.setParkourMedium(location);
					return true;
				} else if(args[0].equalsIgnoreCase("hard")) {
					QubeCraft.setParkourHard(location);
					return true;
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("medium")) {
					if(args[1].equalsIgnoreCase("checkpoint1"))
						QubeCraft.setParkourMediumCheck1(location);
					return true;
				} else if(args[0].equalsIgnoreCase("hard")) {
					if(args[1].equalsIgnoreCase("checkpoint1"))
						QubeCraft.setParkourHardCheck1(location);
					else if(args[1].equalsIgnoreCase("checkpoint2"))
						QubeCraft.setParkourHardCheck2(location);
					return true;
				}
			}
		}
		return false;
	}
	

}
