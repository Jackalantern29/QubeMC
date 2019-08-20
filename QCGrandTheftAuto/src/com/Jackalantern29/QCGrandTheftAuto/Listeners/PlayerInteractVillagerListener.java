package com.Jackalantern29.QCGrandTheftAuto.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractVillagerListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		if(event.getRightClicked().getType().equals(EntityType.VILLAGER)) {
		}
	}

}
