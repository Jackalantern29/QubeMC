package com.Jackalantern29.QCGrandTheftAuto.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.Jackalantern29.QCGrandTheftAuto.QubeMC;

public class PlayerDeathListener implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		QubeMC.wantedLevel.replace(player.getUniqueId(), 0);
		QubeMC.timer.replace(player, 0);
	}

}
