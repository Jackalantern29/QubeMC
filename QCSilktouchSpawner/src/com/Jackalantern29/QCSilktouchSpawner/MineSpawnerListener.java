package com.Jackalantern29.QCSilktouchSpawner;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MineSpawnerListener implements Listener {
	@EventHandler
	public void onMineSpawner(BlockBreakEvent event) {
		if(event.getBlock().getType() == Material.MOB_SPAWNER) {
			Player player = event.getPlayer();
			if(event.isCancelled()) {
				return;
			}
			if(player.hasPermission("qubecraft.silktouch")) {
				if(player.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
			        if (!Cooldown.tryCooldown(event.getPlayer(), "Hand", 43200000L)) {
			          int seconds = (int)(Cooldown.getCooldown(event.getPlayer(), "Hand") / 1000L) % 60;
			          int minutes = (int)(Cooldown.getCooldown(event.getPlayer(), "Hand") / 60000L % 60L);
			          int hours = (int)(Cooldown.getCooldown(event.getPlayer(), "Hand") / 3600000L % 24L);
			          String time = hours + " hours " + minutes + " minutes " + seconds + " seconds.";
			          player.sendMessage(QubeCraft.prefix + QubeCraft.getInstance().getConfig().getString("SilktouchSpawnerCooldown").replace("&", "§").replace("%time%", time));
			          event.setCancelled(true);
			          return;
			        }

			        ItemStack item = new ItemStack(Material.MOB_SPAWNER, 1);
			        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), item);
			        player.sendMessage(QubeCraft.prefix + QubeCraft.getInstance().getConfig().getString("SilktouchSpawnerSuccess").replace("&", "§"));
				} else {
					ItemStack spawneritem = new ItemStack(Material.IRON_INGOT, 8);
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), spawneritem);
					player.sendMessage(QubeCraft.prefix + QubeCraft.getInstance().getConfig().getString("SpawnerShattered").replace("&", "§"));
				}
			}
		}
	}

}
