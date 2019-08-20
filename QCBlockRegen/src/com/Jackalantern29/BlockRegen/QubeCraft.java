package com.Jackalantern29.BlockRegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class QubeCraft extends JavaPlugin implements Listener {
	private boolean blackListedHeads;
	private final List<Material> blackListedBlocks = new ArrayList<Material>();
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		for(String string : getConfig().getStringList("blacklist"))
			        if (string.equalsIgnoreCase("player_head")) {
			          this.blackListedHeads = true;
			        }
			        else {
			          Material match = Material.matchMaterial(string.toUpperCase());
			          if (match != null)
			            this.blackListedBlocks.add(match);
			        }
			    }

	public void onDisable() {

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTntExplore(EntityExplodeEvent event) {
		HashMap<Location, BlockData> blocks = new HashMap<Location, BlockData>();
		List<Block> blocks2 = new ArrayList<Block>();
		for(Block block : event.blockList())
			blocks2.add(block);
		event.blockList().clear();
	    for (Block block : blocks2) {
	    	if (isBlackListed(block)) {
	          block.getLocation().getWorld().dropItemNaturally(block.getLocation(), (ItemStack)block.getDrops().iterator().next());
	          block.setType(Material.AIR);
	    	} else {
	        	blocks.put(block.getLocation(), new BlockData(block.getType(), block.getData()));
	        	block.setType(Material.AIR);
	        }
	    }
	    new BukkitRunnable() {
			
			@Override
			public void run() {
    			if(blocks.size() != 0) {
    				if((blocks.values().iterator().next().getMaterial() == Material.SAND) 
    						|| (blocks.values().iterator().next().getMaterial() == Material.REDSTONE)
    						|| (blocks.values().iterator().next().getMaterial() == Material.REDSTONE_COMPARATOR_OFF)
    						|| (blocks.values().iterator().next().getMaterial() == Material.REDSTONE_COMPARATOR_ON)
    						|| (blocks.values().iterator().next().getMaterial() == Material.REDSTONE_TORCH_OFF)
    						|| (blocks.values().iterator().next().getMaterial() == Material.REDSTONE_TORCH_ON)
    						|| (blocks.values().iterator().next().getMaterial() == Material.DIODE)
    						|| (blocks.values().iterator().next().getMaterial() == Material.DIODE_BLOCK_OFF)
    						|| (blocks.values().iterator().next().getMaterial() == Material.DIODE_BLOCK_ON)
    						|| (blocks.values().iterator().next().getMaterial() == Material.REDSTONE_WIRE)
    						|| (blocks.values().iterator().next().getMaterial() == Material.RAILS)
    						|| (blocks.values().iterator().next().getMaterial() == Material.ACTIVATOR_RAIL)
    						|| (blocks.values().iterator().next().getMaterial() == Material.DETECTOR_RAIL)
    						|| (blocks.values().iterator().next().getMaterial() == Material.POWERED_RAIL)
    						|| (blocks.values().iterator().next().getMaterial() == Material.STRING)
    						|| (blocks.values().iterator().next().getMaterial() == Material.ANVIL)
    						|| (blocks.values().iterator().next().getMaterial() == Material.ACACIA_DOOR)
    						|| (blocks.values().iterator().next().getMaterial() == Material.BIRCH_DOOR)
    						|| (blocks.values().iterator().next().getMaterial() == Material.DARK_OAK_DOOR)
    						|| (blocks.values().iterator().next().getMaterial() == Material.IRON_DOOR)
    						|| (blocks.values().iterator().next().getMaterial() == Material.JUNGLE_DOOR)
    						|| (blocks.values().iterator().next().getMaterial() == Material.SPRUCE_DOOR)
    						|| (blocks.values().iterator().next().getMaterial() == Material.WOOD_DOOR)) {
    					if(new Location(blocks.keySet().iterator().next().getWorld(), blocks.keySet().iterator().next().getX(), blocks.keySet().iterator().next().getY() - 1, blocks.keySet().iterator().next().getZ()).getBlock().getType() == Material.AIR) {
    						blocks.remove(blocks.keySet().iterator().next());
    						blocks.put(blocks.keySet().iterator().next(), new BlockData(blocks.values().iterator().next().getMaterial(), blocks.values().iterator().next().getData()));
    						return;
    					}
    				}
        			blocks.keySet().iterator().next().getBlock().setTypeIdAndData(blocks.values().iterator().next().getMaterial().getId(), blocks.values().iterator().next().getData(), true);
    				blocks.remove(blocks.keySet().iterator().next());
    			} else {
    				this.cancel();
    			}
			}
		}.runTaskTimer(this, 0, 5);
	}

	public boolean hasHeadsBlocked() {
		return this.blackListedHeads;
	}

	public boolean isBlackListed(Block block) {
		if ((hasHeadsBlocked()) && (block.getType() == Material.SKULL)
				&& (((Skull) block.getState()).getSkullType() == SkullType.PLAYER)) {
			return true;
		}
		return this.blackListedBlocks.contains(block.getType());
	}
}