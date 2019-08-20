package com.Jackalantern29.QubeGems.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jackalantern29.QubeGems.GUI.QubeGemsGUI;

public class CommandQubeGems implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("qubegems")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use that command.");
				return true;
			}
			Player player = (Player)sender;
			QubeGemsGUI.openMain(player);
			return true;
		}
		return false;
	}
	

}
