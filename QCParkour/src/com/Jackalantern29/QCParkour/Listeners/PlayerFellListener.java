package com.Jackalantern29.QCParkour.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.Jackalantern29.QCParkour.QubeCraft;

public class PlayerFellListener implements Listener {
	
	@EventHandler
	public void onPlayerFellInLava(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(QubeCraft.getParkourPlayers().contains(player)) {
			if((event.getTo().getBlock().getType() == Material.STATIONARY_LAVA) || (event.getTo().getBlock().getType() == Material.LAVA)) {
				if(QubeCraft.getPlayerDifficulty(player).equals("Easy")) {
					player.teleport(QubeCraft.getParkourEasy());
				} else if(QubeCraft.getPlayerDifficulty(player).equals("Medium")) {
					if(QubeCraft.getPlayerCheckpoint(player) == 0) {
						player.teleport(QubeCraft.getParkourMedium());
					} else if(QubeCraft.getPlayerCheckpoint(player) == 1) {
						player.teleport(QubeCraft.getParkourMediumCheckpoint1());
					}
				} else if(QubeCraft.getPlayerDifficulty(player).equals("Hard")) {
					if(QubeCraft.getPlayerCheckpoint(player) == 0) {
						player.teleport(QubeCraft.getParkourHard());
					} else if(QubeCraft.getPlayerCheckpoint(player) == 1) {
						player.teleport(QubeCraft.getParkourHardCheckpoint1());
					} else if(QubeCraft.getPlayerCheckpoint(player) == 2) {
						player.teleport(QubeCraft.getParkourHardCheckpoint2());
					}
				}
			} 
			try {
				if(event.getTo().distance(QubeCraft.getParkourMediumCheckpoint1()) <= 1) {
					QubeCraft.setPlayerParkour(player, QubeCraft.getPlayerDifficulty(player), 1);
				} else if(event.getTo().distance(QubeCraft.getParkourHardCheckpoint1()) <= 1) {
					QubeCraft.setPlayerParkour(player, QubeCraft.getPlayerDifficulty(player), 1);
				} else if(event.getTo().distance(QubeCraft.getParkourHardCheckpoint2()) <= 1) {
					QubeCraft.setPlayerParkour(player, QubeCraft.getPlayerDifficulty(player), 2);
				}
			} catch(IllegalArgumentException e) {
				
			}
		}
	}
}