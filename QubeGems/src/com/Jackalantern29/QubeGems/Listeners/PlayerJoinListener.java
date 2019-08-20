package com.Jackalantern29.QubeGems.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Jackalantern29.QubeGems.Util.QubeGems;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		QubeGems.createProfile(player.getUniqueId());
		player.sendMessage(event.getJoinMessage());
	}

}
