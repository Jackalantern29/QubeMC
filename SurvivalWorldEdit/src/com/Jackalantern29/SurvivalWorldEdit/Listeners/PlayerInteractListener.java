package com.Jackalantern29.SurvivalWorldEdit.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Jackalantern29.SurvivalWorldEdit.Util.Blocks;
import com.wasteofplastic.askyblock.ASkyBlockAPI;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		//Location loc = player.getTargetBlock((Set<Material>)null, 10).getLocation();
		ItemStack item = player.getInventory().getItemInMainHand();
		if(player.hasPermission("survivalworldedit.setpos")) {
			try {
				Location loc = event.getClickedBlock().getLocation();
				if(item.getType().equals(Material.STONE_AXE)) {
					if(!player.isOp()) {
						if(!ASkyBlockAPI.getInstance().playerIsOnIsland(player)) {
							player.sendMessage("§cYou can only perform this function on your island.");
							event.setCancelled(true);
							return;
						}
					}
					if(!item.getItemMeta().getDisplayName().equals("§6§lWand"))
						return;
					if(!item.getItemMeta().hasLore())
						return;
					if(action.equals(Action.LEFT_CLICK_BLOCK)) {
						if(Blocks.isPosNull(player.getUniqueId(), 1)) {
							player.sendMessage("§dFirst position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
							Blocks.setPos(player.getUniqueId(), 1, loc);
						} else {
							if(!Blocks.getPos(player.getUniqueId(), 1).equals(loc)) {
								player.sendMessage("§dFirst position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
								Blocks.setPos(player.getUniqueId(), 1, loc);
							}
						}
					} else if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
						if(Blocks.isPosNull(player.getUniqueId(), 2)) {
							player.sendMessage("§dSecond position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
							Blocks.setPos(player.getUniqueId(), 2, loc);
						} else {
							if(!Blocks.getPos(player.getUniqueId(), 2).equals(loc)) {
								player.sendMessage("§dSecond position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
								Blocks.setPos(player.getUniqueId(), 2, loc);
							}
						}
					}
					event.setCancelled(true);
				}
			}catch(NullPointerException e) {
				
			}
		}
	}
}
