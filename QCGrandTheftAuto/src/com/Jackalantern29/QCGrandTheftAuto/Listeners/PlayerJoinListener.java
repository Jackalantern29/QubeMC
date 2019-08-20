package com.Jackalantern29.QCGrandTheftAuto.Listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.Jackalantern29.QCGrandTheftAuto.QubeMC;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(QubeMC.rent.containsKey(player.getUniqueId())) {
			player.sendMessage(QubeMC.getInstance().getConfig().getString("RentExecuted").replace("&", "§").replace("%amount%", "" + QubeMC.rent.get(player.getUniqueId())));
			QubeMC.rent.remove(player.getUniqueId());
		}
		if(!QubeMC.wantedLevel.containsKey(player.getUniqueId()))
			return;
		if(QubeMC.wantedLevel.get(player.getUniqueId()) >= 1) {
			for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
				if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
					Creature pigzombie = (Creature)entity;
					if(pigzombie.getTarget() == null) {
						pigzombie.setTarget(player);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		QubeMC.timer.remove(player);
	}

}
