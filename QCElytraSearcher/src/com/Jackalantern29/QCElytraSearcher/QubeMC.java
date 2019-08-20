package com.Jackalantern29.QCElytraSearcher;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeMC extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					World world = player.getWorld();
					if(world.getEnvironment().equals(Environment.THE_END)) {
						for(Entity entity : world.getEntities()) {
							if(entity instanceof ItemFrame) {
								ItemFrame itemframe = (ItemFrame)entity;
								if(itemframe.getItem().getType().equals(Material.ELYTRA)) {
									if((itemframe.getLocation().add(0, 3, -7).getBlock().getType().equals(Material.SKULL)) && (((Skull)itemframe.getLocation().add(0, 3, -7).getBlock().getState()).getSkullType().equals(SkullType.DRAGON))) {
										itemframe.getLocation().add(0, 3, -7).getBlock().setType(Material.AIR);
									} else if((itemframe.getLocation().add(0, 3, 7).getBlock().getType().equals(Material.SKULL)) && (((Skull)itemframe.getLocation().add(0, 3, 7).getBlock().getState()).getSkullType().equals(SkullType.DRAGON))) {
										itemframe.getLocation().add(0, 3, 7).getBlock().setType(Material.AIR);
									} else if((itemframe.getLocation().add(7, 3, 0).getBlock().getType().equals(Material.SKULL)) && (((Skull)itemframe.getLocation().add(7, 3, 0).getBlock().getState()).getSkullType().equals(SkullType.DRAGON))) {
										itemframe.getLocation().add(7, 3, 0).getBlock().setType(Material.AIR);
									} else if((itemframe.getLocation().add(-7, 3, 0).getBlock().getType().equals(Material.SKULL)) && (((Skull)itemframe.getLocation().add(-7, 3, 0).getBlock().getState()).getSkullType().equals(SkullType.DRAGON))) {
										itemframe.getLocation().add(-7, 3, 0).getBlock().setType(Material.AIR);
									}
									itemframe.remove();
								}
								
							}
						}
					}
				}
			}
		}, 0, 20);
	}
	
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onPlayerRightClickEntity(PlayerInteractAtEntityEvent event) {
		Player player = event.getPlayer();
		Entity rightclicked = event.getRightClicked();
		if(player.getWorld().getEnvironment().equals(Environment.THE_END)) {
			if(event.getHand().equals(EquipmentSlot.HAND)) {
				ItemStack item = player.getInventory().getItemInMainHand();
				if(rightclicked.getType().equals(EntityType.ITEM_FRAME)) {
					if(item.getType().equals(Material.ELYTRA)) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
							
							@Override
							public void run() {
								rightclicked.getWorld().dropItemNaturally(rightclicked.getLocation(), ((ItemFrame)rightclicked).getItem());
								rightclicked.getWorld().dropItemNaturally(rightclicked.getLocation(), new ItemStack(Material.ITEM_FRAME));
								rightclicked.remove();						}
						}, 1);
					}
				}
			} else if(event.getHand().equals(EquipmentSlot.OFF_HAND)) {
				ItemStack item = player.getInventory().getItemInOffHand();
				if(item.getType().equals(Material.ELYTRA)) {
					if(rightclicked.getType().equals(EntityType.ITEM_FRAME)) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
