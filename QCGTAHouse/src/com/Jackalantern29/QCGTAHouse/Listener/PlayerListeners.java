package com.Jackalantern29.QCGTAHouse.Listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.Jackalantern29.QCGTAHouse.QubeMC;
import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;

public class PlayerListeners implements Listener {
	
	@EventHandler
	public void onPlayerSign(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign)event.getClickedBlock().getState();
				if(sign.getLine(0).equals("[Rent]")) {
					if((sign.getLine(1) != null) || (!sign.getLine(1).equals(""))) {
						QubeMC.getInstance().getWorldGuard().getRegionManager(sign.getWorld()).getRegion(sign.getLine(1)).getMembers().addPlayer(player.getUniqueId());
						player.sendMessage("§aSuccessfully bought a house.");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerEnterRegion(RegionEnterEvent event) {
		if(!event.isCancellable())
	    	return;
		Player player = event.getPlayer();
		if(event.getRegion().getOwners().contains(player.getUniqueId())) {
			player.sendMessage("§9Welcome back to your house, §c" + player.getName() + "§9.");
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 9999, 0, false, false));
		} else {
			StringBuilder str = new StringBuilder();
			for(UUID uuid : event.getRegion().getMembers().getUniqueIds()) {
              	  if(str.toString().length() > 0)
              		  str.append(", ");
              	  str.append(Bukkit.getPlayer(uuid).getName());
            }
			player.sendMessage("§9This house belongs to: §c" + str.toString() + "§9.");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerLeaveRegion(RegionLeaveEvent event) {
		if(!event.isCancellable())
	    	return;
		Player player = event.getPlayer();
		if(event.getRegion().getOwners().contains(player.getUniqueId())) {
			player.sendMessage("§9Come back soon, §c" + player.getName() + "§9.");
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
		}
	}

}
