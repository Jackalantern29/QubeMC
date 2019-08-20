package com.Jackalantern29.QCBedrock.Listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Jackalantern29.QCBedrock.QubeCraft;

public class MineBedrockListener implements Listener {
	QubeCraft plugin;
	
	public MineBedrockListener(QubeCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBedrockMine(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType() == Material.BEDROCK) {
				if(player.getGameMode() == GameMode.SURVIVAL) {
					if(player.getWorld() == Bukkit.getWorld("world")) {
						if(player.hasPermission("bedrock.mine")) {
							if(event.getClickedBlock().getY() == 0) {
								if(!this.plugin.getConfig().getString("Level1Mine").equals("none"))
									player.sendMessage(this.plugin.getConfig().getString("Level1Mine").replace("&", "§"));
							} else {
								if(player.getLevel() >= 1) {
									if(player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE) {
										player.giveExpLevels(-1);
										event.getClickedBlock().setType(Material.AIR);
										if(!this.plugin.getConfig().getString("MineSuccess").equals("none"))
											player.sendMessage(this.plugin.getConfig().getString("MineSuccess").replace("&", "§"));
										
										ItemStack item = player.getInventory().getItemInMainHand();
										item.setDurability((short) (player.getInventory().getItemInMainHand().getDurability() - 1));
										ItemMeta meta = item.getItemMeta();
										item.setItemMeta(meta);
										player.getInventory().setItemInMainHand(item);
									}
								} else {
									if(!this.plugin.getConfig().getString("NotEnoughExp").equals("none"))
										player.sendMessage(this.plugin.getConfig().getString("NotEnoughExp").replace("&", "§"));
								}
							}
						}
					}
				}
			}
		}
	}

}
