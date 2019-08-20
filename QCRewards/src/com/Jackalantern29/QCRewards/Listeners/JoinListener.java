package com.Jackalantern29.QCRewards.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Jackalantern29.QCRewards.QubeMC;
import com.Jackalantern29.QCRewards.Stats;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(!QubeMC.plugin.mysql.hasConnection())
			return;
		Stats.createStatFile(player.getUniqueId());
		Stats.setLastUsername(player.getUniqueId());
		Stats.setOnlineTime(player.getUniqueId(), Stats.getSQLOnlineTime(player.getUniqueId()));
	}

}
