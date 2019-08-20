package com.Jackalantern29.QCFlags;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class QubeMC extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);	
	}
	
	public void onDisable() {
		
	}
	@EventHandler 
	public void onEntitySpawn(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack mainhand = player.getInventory().getItemInOffHand();
		//ItemStack offhand = player.getInventory().getItemInOffHand();
		if((mainhand.getType().equals(Material.BOAT) || mainhand.getType().equals(Material.BOAT_ACACIA) || mainhand.getType().equals(Material.BOAT_BIRCH) || mainhand.getType().equals(Material.BOAT_DARK_OAK) || mainhand.getType().equals(Material.BOAT_JUNGLE) || mainhand.getType().equals(Material.BOAT_SPRUCE))) {
			if(WorldGuardPlugin.inst().getRegionManager(player.getWorld()).getRegion("spawn") != null) {
				ProtectedRegion region = WorldGuardPlugin.inst().getRegionManager(player.getWorld()).getRegion("spawn");
				double x1; double y1; double z1; double x2; double y2; double z2;
				x1 = region.getMinimumPoint().getX(); y1 = region.getMinimumPoint().getY(); z1 = region.getMinimumPoint().getZ();
				x2 = region.getMaximumPoint().getX(); y2 = region.getMaximumPoint().getY(); z2 = region.getMaximumPoint().getZ();
				Vector min = new Vector(x1, y1, z1);
				Vector max = new Vector(x2, y2, z2);
				if(player.getLocation().toVector().isInAABB(min, max)) {
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onSheepChangeBlock(EntityChangeBlockEvent event) {
		if(event.getEntity() instanceof Sheep) {
			Sheep sheep = (Sheep)event.getEntity();
			if(WorldGuardPlugin.inst().getRegionManager(sheep.getWorld()).getRegion("spawn") != null) {
				ProtectedRegion region = WorldGuardPlugin.inst().getRegionManager(sheep.getWorld()).getRegion("spawn");
				double x1; double y1; double z1; double x2; double y2; double z2;
				x1 = region.getMinimumPoint().getX(); y1 = region.getMinimumPoint().getY(); z1 = region.getMinimumPoint().getZ();
				x2 = region.getMaximumPoint().getX(); y2 = region.getMaximumPoint().getY(); z2 = region.getMaximumPoint().getZ();
				Vector min = new Vector(x1, y1, z1);
				Vector max = new Vector(x2, y2, z2);
				if(sheep.getLocation().toVector().isInAABB(min, max)) {
					event.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void onPvP(EntityDamageByEntityEvent event) {
		if((event.getDamager() instanceof Player) && (event.getEntity() instanceof Player)) {
			Player target = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			if(WorldGuardPlugin.inst().getRegionManager(damager.getWorld()).getRegion("spawn") != null) {
				ProtectedRegion region = WorldGuardPlugin.inst().getRegionManager(damager.getWorld()).getRegion("spawn");
				double x1; double y1; double z1; double x2; double y2; double z2;
				x1 = region.getMinimumPoint().getX(); y1 = region.getMinimumPoint().getY(); z1 = region.getMinimumPoint().getZ();
				x2 = region.getMaximumPoint().getX(); y2 = region.getMaximumPoint().getY(); z2 = region.getMaximumPoint().getZ();
				Vector min = new Vector(x1, y1, z1);
				Vector max = new Vector(x2, y2, z2);
				if(damager.getLocation().toVector().isInAABB(min, max)) {
					event.setCancelled(true);
					target.setFireTicks(0);
				}
			}
		}
		
	}
}
