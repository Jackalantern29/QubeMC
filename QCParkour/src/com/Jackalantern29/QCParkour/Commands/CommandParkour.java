package com.Jackalantern29.QCParkour.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jackalantern29.QCParkour.QubeCraft;

public class CommandParkour implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("parkour")) {
			if(args.length == 0) {
				if(sender instanceof Player) {
					Player player = (Player)sender;
					player.teleport(QubeCraft.getParkourLobby());
					player.sendMessage(QubeCraft.getInstance().getConfig().getString("ParkourLobbyMessage").replace("&", "§"));
				} else {
					sender.sendMessage("You need to be a player to perform this command!");
				}
				return true;
			} else if(args.length == 1) {
				if(sender instanceof Player) {
					Player player = (Player)sender;
					if(args[0].equalsIgnoreCase("easy")) {
						player.teleport(QubeCraft.getParkourEasy());
						player.sendMessage(QubeCraft.getInstance().getConfig().getString("ParkourEasyMessage").replace("&", "§"));
						if(!QubeCraft.getParkourPlayers().contains(player))
							QubeCraft.getParkourPlayers().add(player);
						QubeCraft.setPlayerParkour(player, "Easy", 0);
					} else if(args[0].equalsIgnoreCase("medium")) {
						player.teleport(QubeCraft.getParkourMedium());
						player.sendMessage(QubeCraft.getInstance().getConfig().getString("ParkourMediumMessage").replace("&", "§"));
						if(!QubeCraft.getParkourPlayers().contains(player))
							QubeCraft.getParkourPlayers().add(player);
						QubeCraft.setPlayerParkour(player, "Medium", 0);
					} else if(args[0].equalsIgnoreCase("hard")) {
						player.teleport(QubeCraft.getParkourHard());
						player.sendMessage(QubeCraft.getInstance().getConfig().getString("ParkourHardMessage").replace("&", "§"));
						if(!QubeCraft.getParkourPlayers().contains(player))
							QubeCraft.getParkourPlayers().add(player);
						QubeCraft.setPlayerParkour(player, "Hard", 0);
					} else if(args[0].equalsIgnoreCase("leave")) {
						if(QubeCraft.getParkourPlayers().contains(player)) {
							player.sendMessage(QubeCraft.getInstance().getConfig().getString("ParkourLeaveMessage").replace("&", "§"));
							QubeCraft.getParkourPlayers().remove(player);
							QubeCraft.removePlayerParkour(player);
						} else {
							player.sendMessage(QubeCraft.getInstance().getConfig().getString("NotInParkourMessage").replace("&", "§"));
						}
					} else {
						player.sendMessage("§cThat difficulty does not exist!");
					}
				} else {
					sender.sendMessage("You need to be a player to perform this command!");
				}
				return true;
			} else {
				sender.sendMessage("§cError: Too many arguments!");
			}
		}
		return false;
	}
	
}