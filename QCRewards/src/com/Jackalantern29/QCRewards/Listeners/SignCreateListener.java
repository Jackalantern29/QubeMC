package com.Jackalantern29.QCRewards.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.Jackalantern29.QCRewards.QubeMC;

public class SignCreateListener implements Listener {
	
	@EventHandler
	public void onSignCreate(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("qubecraft.admin")) {
			if(event.getLine(0).equalsIgnoreCase("[coinshop]")) {
				
				player.sendMessage("§cYou do not have permission create a CoinShop!");
				event.getBlock().breakNaturally();
			}
		} else {
			if(event.getLine(0).equalsIgnoreCase("[coinshop]")) {
				event.setLine(0, QubeMC.plugin.getConfig().getString("SignTitle").replace("&", "").replace("§", ""));
				if(event.getLine(3).contains("QC"))
					event.setLine(3, "§2" + event.getLine(3));
				else
					event.setLine(3, "§2" + event.getLine(3) + " QC");
			}
		}
	}

}
