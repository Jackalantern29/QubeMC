package com.Jackalantern29.QCTreeGrow;

import org.bukkit.TreeType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeMC extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTreeGrow(StructureGrowEvent event) {
		TreeType type = event.getSpecies();
		if(type == TreeType.MEGA_REDWOOD) {
			event.setCancelled(true);
			event.getWorld().generateTree(event.getLocation(), TreeType.TALL_REDWOOD);
			event.getLocation().getBlock().setTypeIdAndData(17, (byte) 1, false);
		}
		if(type == TreeType.JUNGLE) {
			event.setCancelled(true);
			event.getWorld().generateTree(event.getLocation(), TreeType.SMALL_JUNGLE);
			event.getLocation().getBlock().setTypeIdAndData(17, (byte) 3, false);

		}
	}

}
