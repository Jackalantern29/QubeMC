package com.Jackalantern29.QCEnderDragon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeCraft extends JavaPlugin implements Listener {
	static HashMap<String, Double> damage = new HashMap<String, Double>();
	
	public void onEnable() {
		File configFile = new File(getDataFolder() + "/config.yml");
		if (!configFile.exists()) {
			saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onBuild(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		if(block.getWorld().getName().equals("world_the_end")) {
			if((player.getGameMode() != GameMode.CREATIVE) || (!player.isOp())) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof EnderDragon) {
			Player player = (Player) event.getDamager();
			if(!damage.containsKey(player.getName())) {
				damage.put(player.getName(), 0.0);
			}
			damage.replace(player.getName(), damage.get(player.getName()) + event.getDamage());
		}
	}
	@EventHandler
	public void onKill(EntityDeathEvent event) {
		if(event.getEntity() instanceof EnderDragon) {
			Player player = event.getEntity().getKiller();
			String name = "";
			Location location = new Location(Bukkit.getWorld("world_the_end"), -2, 62, -1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(1, 0, -3);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(1, 0, 0);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, -1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, -2);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, -1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(1, 0, 0);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, 1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(1, 0, -1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, -1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = location.add(0, 0, -1);
			location.getBlock().setType(Material.ENDER_PORTAL);
			location = new Location(Bukkit.getWorld("world_the_end"), -1, 65, 0);
			location.getBlock().setType(Material.TORCH);
			location = new Location(Bukkit.getWorld("world_the_end"), 0, 65, -1);
			location.getBlock().setType(Material.TORCH);
			location = new Location(Bukkit.getWorld("world_the_end"), 1, 65, 0);
			location.getBlock().setType(Material.TORCH);
			location = new Location(Bukkit.getWorld("world_the_end"), 0, 65, 1);
			location.getBlock().setType(Material.TORCH);
			
			ArrayList<String> list = new ArrayList<String>();
			
			int tempnumber = 0;
			
			for (Map.Entry<Double, String> ent : getTop().entrySet()) {
				list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("%player%", player.getName()).replace("%damager%", ent.getValue()).replace("%damage%", "" + ent.getKey()));
				name = ent.getValue();
			}			
			for (int i = list.size() - 1; i >= 0; i--) {
				tempnumber++;
				
				if (tempnumber <= 1) {
					Bukkit.broadcastMessage(list.get(i));
					player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
					player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
					Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
					Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
					NPC npc1 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonKillerStatueID"));
					NPC npc2 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonDamagerStatueID"));
					
					npc1.setName("§d" + player.getName());
					npc2.setName("§d" + name);
					Block block1 = new Location(Bukkit.getWorld(getConfig().getString("DragonKillerSign.World")), getConfig().getDouble("DragonKillerSign.X"), getConfig().getDouble("DragonKillerSign.Y"), getConfig().getDouble("DragonKillerSign.Z")).getBlock();
					if(block1.getState() instanceof Sign) {
						Sign sign = (Sign)block1.getState();
						sign.setLine(1, player.getName());
						sign.update();
					}
					Block block2 = new Location(Bukkit.getWorld(getConfig().getString("DragonDamagerSign.World")), getConfig().getDouble("DragonDamagerSign.X"), getConfig().getDouble("DragonDamagerSign.Y"), getConfig().getDouble("DragonDamagerSign.Z")).getBlock();
					if(block2.getState() instanceof Sign) {
						Sign sign = (Sign)block2.getState();
						sign.setLine(1, name);
						sign.update();
					}
					
				}
			}
			damage.clear();
		}
	}
	public TreeMap<Double, String> getTop() {
	    TreeMap<Double, String> scoreMap = new TreeMap<Double, String>();
	    for (String name : damage.keySet()) {
	    	double wins = damage.get(name);
	    	scoreMap.put(Double.valueOf(wins), name);
	    }		
		return scoreMap;
	}
}
