package com.Jackalantern29.QCDragonRespawn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class QubeMC extends JavaPlugin implements Listener {
	//public static HashMap<UUID, HashMap<EnderDragon, BossBar>> bars = new HashMap<>();
	BossBar bar = Bukkit.createBossBar("Ender Dragon", BarColor.PINK, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	BossBar bar1 = Bukkit.createBossBar("§cEnder Dragon", BarColor.RED, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	BossBar bar2 = Bukkit.createBossBar("§9Ender Dragon", BarColor.BLUE, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	static HashMap<String, Double> damage1 = new HashMap<String, Double>();
	static HashMap<String, Double> damage2 = new HashMap<String, Double>();
	static HashMap<String, Double> damage = new HashMap<String, Double>();
	double health1 = 0.0;
	double maxhealth1 = 0.0;
	double health2 = 0.0;
	double maxhealth2 = 0.0;
	double health = 0.0;
	double maxhealth = 0.0;
	boolean value1;
	boolean value2;
	boolean value;
	static int left = 0;
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				value = false;
				value1 = false;
				value2 = false;
				for(Entity entity : Bukkit.getWorld("world_the_end").getEntities()) {
					if(entity instanceof EnderDragon) {
						//jack.sendMessage(entity.getName() + " | " + entity.getCustomName());
						if(entity.getName().equals("Ender Dragon")) {
							value = true;
							EnderDragon wither = (EnderDragon)entity;
							if(wither.getLocation().distance(new Location(Bukkit.getWorld("world_the_end"), 0, 66, 0)) >= 408) {
								wither.teleport(new Location(Bukkit.getWorld("world_the_end"), 0, 66, 0));
							}
							health = wither.getHealth();
							maxhealth = wither.getMaxHealth();
						}
						if(entity.getName().equals("§c§lEnder Dragon")) {
							value1 = true;
							EnderDragon wither = (EnderDragon)entity;
							if(wither.getLocation().distance(new Location(Bukkit.getWorld("world_the_end"), 0, 66, 0)) >= 408) {
								wither.teleport(new Location(Bukkit.getWorld("world_the_end"), 0, 66, 0));
							}
							health1 = wither.getHealth();
							maxhealth1 = wither.getMaxHealth();
							for(Player player : Bukkit.getOnlinePlayers()) {
								wither.setGlowing(true);
								player.getScoreboard().getTeam("Red").addEntry(wither.getUniqueId().toString());
							}
						}
						if(entity.getName().equals("§9§lEnder Dragon")) {
							value2 = true;
							EnderDragon wither = (EnderDragon)entity;
							if(wither.getLocation().distance(new Location(Bukkit.getWorld("world_the_end"), 0, 66, 0)) >= 408) {
								wither.teleport(new Location(Bukkit.getWorld("world_the_end"), 0, 66, 0));
							}
							health2 = wither.getHealth();
							maxhealth2 = wither.getMaxHealth();
							for(Player player : Bukkit.getOnlinePlayers()) {
								wither.setGlowing(true);
								player.getScoreboard().getTeam("Blue").addEntry(wither.getUniqueId().toString());

							}
						}
					}
				}
				if(value) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(player.getWorld().getName().equalsIgnoreCase("world_the_end")) {
							bar.addPlayer(player);
						} else {
							bar.removePlayer(player);;
						}
					}		
					bar.setProgress(health / maxhealth);
					if(bar.getProgress() == 0.0) {
						bar.removeAll();
					}
				} else {
					bar.removeAll();
				}
				if(value1) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(player.getWorld().getName().equalsIgnoreCase("world_the_end")) {
							bar1.addPlayer(player);
						} else {
							bar1.removePlayer(player);;
						}
					}		
					bar1.setProgress(health1 / maxhealth1);
					if(bar1.getProgress() == 0.0) {
						bar1.removeAll();
					}
				} else {
					bar1.removeAll();
				}
				if(value2) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(player.getWorld().getName().equalsIgnoreCase("world_the_end")) {
							bar2.addPlayer(player);
						} else {
							bar2.removePlayer(player);;
						}
					}				
					bar2.setProgress(health2 / maxhealth2);
					if(bar2.getProgress() == 0.0) {
						bar2.removeAll();
					}
				} else {
					bar2.removeAll();
				}
			}
		}, 0, 1);
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("respawndragon")) {
			if(sender.hasPermission("command.respawndragon")) {
				if(args.length == 0) {
					EnderDragon dragon = (EnderDragon) Bukkit.getWorld("world_the_end").spawnEntity(new Location(Bukkit.getWorld("world_the_end"), 0, 74, 0), EntityType.ENDER_DRAGON);
					dragon.setAI(true);
					dragon.setPhase(Phase.CIRCLING);
					dragon.resetMaxHealth();
					dragon.setMaxHealth(dragon.getMaxHealth() * 2);
					left = 1;
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("duo")) {

						EnderDragon dragon1 = (EnderDragon) Bukkit.getWorld("world_the_end").spawnEntity(new Location(Bukkit.getWorld("world_the_end"), 0, 74, 0), EntityType.ENDER_DRAGON);
						dragon1.setCustomName("§c§lEnder Dragon");
						dragon1.setCustomNameVisible(true);
						dragon1.setAI(true);
						dragon1.setPhase(Phase.CIRCLING);
						dragon1.resetMaxHealth();
						
						EnderDragon dragon2 = (EnderDragon) Bukkit.getWorld("world_the_end").spawnEntity(new Location(Bukkit.getWorld("world_the_end"), 0, 74, 0), EntityType.ENDER_DRAGON);
						dragon2.setCustomName("§9§lEnder Dragon");
						dragon2.setCustomNameVisible(true);
						dragon2.setAI(true);
						dragon2.setPhase(Phase.CIRCLING);
						dragon2.resetMaxHealth();						
						left = 2;
						return true;
					} /*else if(args[0].equalsIgnoreCase("fake")) {
						NPC dragon = CitizensAPI.getNPCRegistry().getById(109);
						EnderDragon edragon = (EnderDragon)dragon.getEntity();
						if(edragon.hasAI())
							edragon.setAI(false);
						else
							edragon.setAI(true);
						return true;
					} else if(args[0].equalsIgnoreCase("enderman")) {
						NPC enderman = CitizensAPI.getNPCRegistry().getById(92);
						Enderman eenderman = (Enderman)enderman.getEntity();
						eenderman.setCarriedMaterial(new MaterialData(Material.DRAGON_EGG));
						eenderman.setTarget(eenderman);
						return true;
					}*/
				}
			} else {
				sender.sendMessage("§cYou do not have permission to use this command.");
			}
			return true;
		}
		return false;
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
	public void onPreprocessCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if(player.getWorld().getName().equals("world_the_end")) {
			if(event.getMessage().contains("back")) {
				player.sendMessage("§cThis command is disabled in this world.");
				event.setCancelled(true);
			}
			if(event.getMessage().contains("return")) {
				player.sendMessage("§cThis command is disabled in this world.");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if(event.getRespawnLocation().getWorld().getName().equals("world_the_end")) {
			event.setRespawnLocation(new Location(Bukkit.getWorld("world"), 0.5, 73, 0));
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof EnderDragon) {
			Player player = (Player) event.getDamager();
			EnderDragon dragon = (EnderDragon) event.getEntity();
			if(dragon.getName().equals("Ender Dragon")) {
				if(!damage.containsKey(player.getName())) {
					damage.put(player.getName(), 0.0);
				}
				damage.replace(player.getName(), damage.get(player.getName()) + event.getDamage());
			}
			if(dragon.getName().equals("§c§lEnder Dragon")) {
				if(!damage1.containsKey(player.getName())) {
					damage1.put(player.getName(), 0.0);
				}
				damage1.replace(player.getName(), damage1.get(player.getName()) + event.getDamage());
			}
			if(dragon.getName().equals("§9§lEnder Dragon")) {
				if(!damage2.containsKey(player.getName())) {
					damage2.put(player.getName(), 0.0);
				}
				damage2.replace(player.getName(), damage2.get(player.getName()) + event.getDamage());
			}
		}
	}
	@EventHandler
	public void onKill(EntityDeathEvent event) {
		if(event.getEntity() instanceof EnderDragon) {
			EnderDragon dragon = (EnderDragon)event.getEntity();
			Player player = event.getEntity().getKiller();
			String name = "";
			ArrayList<String> list = new ArrayList<String>();
			
			int tempnumber = 0;
			if(dragon.getName().equals("Ender Dragon")) {
				event.setDroppedExp(0);
				player.giveExp(12000);
				Bukkit.getPlayer(name).giveExp(12000 / 4);
				for (Map.Entry<Double, String> ent : getTop(0).entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("{player}", player.getName()).replace("{damager}", ent.getValue()).replace("{damage}", "" + ent.getKey()).replace("{dragon}", "§5Ender Dragon"));
					name = ent.getValue();
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						//player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						//Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
//						NPC npc1 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonKillerStatueID1"));
//						NPC npc2 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonDamagerStatueID1"));
//						
//						npc1.setName("§d" + player.getName());
//						npc2.setName("§d" + name);
//						Equipment equipment1 = npc1.getTrait(Equipment.class);
//						equipment1.set(EquipmentSlot.BOOTS, player.getInventory().getBoots());
//						equipment1.set(EquipmentSlot.LEGGINGS, player.getInventory().getLeggings());
//						equipment1.set(EquipmentSlot.CHESTPLATE, player.getInventory().getChestplate());
//						equipment1.set(EquipmentSlot.HELMET, player.getInventory().getHelmet());
//						equipment1.set(EquipmentSlot.HAND, player.getInventory().getItemInMainHand());
//						equipment1.set(EquipmentSlot.OFF_HAND, player.getInventory().getItemInOffHand());
//						
//						Equipment equipment2 = npc2.getTrait(Equipment.class);
//						equipment2.set(EquipmentSlot.BOOTS, Bukkit.getPlayer(name).getInventory().getBoots());
//						equipment2.set(EquipmentSlot.LEGGINGS, Bukkit.getPlayer(name).getInventory().getLeggings());
//						equipment2.set(EquipmentSlot.CHESTPLATE, Bukkit.getPlayer(name).getInventory().getChestplate());
//						equipment2.set(EquipmentSlot.HELMET, Bukkit.getPlayer(name).getInventory().getHelmet());
//						equipment2.set(EquipmentSlot.HAND, Bukkit.getPlayer(name).getInventory().getItemInMainHand());
//						equipment2.set(EquipmentSlot.OFF_HAND, Bukkit.getPlayer(name).getInventory().getItemInOffHand());
					}
				}
				damage.clear();
				left--;
			}
			if(dragon.getName().equals("§c§lEnder Dragon")) {
				event.setDroppedExp(0);
				player.giveExp(12000);
				Bukkit.getPlayer(name).giveExp(12000 / 4);
				for (Map.Entry<Double, String> ent : getTop(1).entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("{player}", player.getName()).replace("{damager}", ent.getValue()).replace("{damage}", "" + ent.getKey()).replace("{dragon}", dragon.getName()));
					name = ent.getValue();
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						//player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						//Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
//						NPC npc1 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonKillerStatueID1"));
//						NPC npc2 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonDamagerStatueID1"));
//						
//						npc1.setName("§c" + player.getName());
//						npc2.setName("§c" + name);
//						Equipment equipment1 = npc1.getTrait(Equipment.class);
//						equipment1.set(EquipmentSlot.BOOTS, player.getInventory().getBoots());
//						equipment1.set(EquipmentSlot.LEGGINGS, player.getInventory().getLeggings());
//						equipment1.set(EquipmentSlot.CHESTPLATE, player.getInventory().getChestplate());
//						equipment1.set(EquipmentSlot.HELMET, player.getInventory().getHelmet());
//						equipment1.set(EquipmentSlot.HAND, player.getInventory().getItemInMainHand());
//						equipment1.set(EquipmentSlot.OFF_HAND, player.getInventory().getItemInOffHand());
//						
//						Equipment equipment2 = npc2.getTrait(Equipment.class);
//						equipment2.set(EquipmentSlot.BOOTS, Bukkit.getPlayer(name).getInventory().getBoots());
//						equipment2.set(EquipmentSlot.LEGGINGS, Bukkit.getPlayer(name).getInventory().getLeggings());
//						equipment2.set(EquipmentSlot.CHESTPLATE, Bukkit.getPlayer(name).getInventory().getChestplate());
//						equipment2.set(EquipmentSlot.HELMET, Bukkit.getPlayer(name).getInventory().getHelmet());
//						equipment2.set(EquipmentSlot.HAND, Bukkit.getPlayer(name).getInventory().getItemInMainHand());
//						equipment2.set(EquipmentSlot.OFF_HAND, Bukkit.getPlayer(name).getInventory().getItemInOffHand());
					}
				}
				damage1.clear();
				left--;
			}
			if(dragon.getName().equals("§9§lEnder Dragon")) {
				player.giveExp(12000);
				Bukkit.getPlayer(name).giveExp(12000 / 4);
				for (Map.Entry<Double, String> ent : getTop(2).entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("{player}", player.getName()).replace("{damager}", ent.getValue()).replace("{damage}", "" + ent.getKey()).replace("{dragon}", dragon.getName()));
					name = ent.getValue();
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						//player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						//Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
//						NPC npc1 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonKillerStatueID2"));
//						NPC npc2 = CitizensAPI.getNPCRegistry().getById(getConfig().getInt("DragonDamagerStatueID2"));
//						
//						npc1.setName("§9" + player.getName());
//						npc2.setName("§9" + name);
//						Equipment equipment1 = npc1.getTrait(Equipment.class);
//						equipment1.set(EquipmentSlot.BOOTS, player.getInventory().getBoots());
//						equipment1.set(EquipmentSlot.LEGGINGS, player.getInventory().getLeggings());
//						equipment1.set(EquipmentSlot.CHESTPLATE, player.getInventory().getChestplate());
//						equipment1.set(EquipmentSlot.HELMET, player.getInventory().getHelmet());
//						equipment1.set(EquipmentSlot.HAND, player.getInventory().getItemInMainHand());
//						equipment1.set(EquipmentSlot.OFF_HAND, player.getInventory().getItemInOffHand());
//						
//						Equipment equipment2 = npc2.getTrait(Equipment.class);
//						equipment2.set(EquipmentSlot.BOOTS, Bukkit.getPlayer(name).getInventory().getBoots());
//						equipment2.set(EquipmentSlot.LEGGINGS, Bukkit.getPlayer(name).getInventory().getLeggings());
//						equipment2.set(EquipmentSlot.CHESTPLATE, Bukkit.getPlayer(name).getInventory().getChestplate());
//						equipment2.set(EquipmentSlot.HELMET, Bukkit.getPlayer(name).getInventory().getHelmet());
//						equipment2.set(EquipmentSlot.HAND, Bukkit.getPlayer(name).getInventory().getItemInMainHand());
//						equipment2.set(EquipmentSlot.OFF_HAND, Bukkit.getPlayer(name).getInventory().getItemInOffHand());
						
					}
				}
				damage2.clear();
				left--;
			}
			if(left != 0)
				return;
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
			
			Location loc1 = new Location(Bukkit.getWorld("world_the_end"), 99, 51, 10);
			Location loc2 = new Location(Bukkit.getWorld("world_the_end"), 108, 51, 1);
			Location loc3 = new Location(Bukkit.getWorld("world_the_end"), 99, 51, -8);
			loc1.getBlock().setType(Material.END_GATEWAY);
			loc2.getBlock().setType(Material.END_GATEWAY);
			loc3.getBlock().setType(Material.END_GATEWAY);
		}
	}
	public TreeMap<Double, String> getTop(int damageNum) {
	    TreeMap<Double, String> scoreMap = new TreeMap<Double, String>();
	    if(damageNum == 0) {
	    	for (String name : damage.keySet()) {
		    	double wins = damage.get(name);
		    	scoreMap.put(Double.valueOf(wins), name);
		    }	
	    }
	    if(damageNum == 1) {
		    for (String name : damage1.keySet()) {
		    	double wins = damage1.get(name);
		    	scoreMap.put(Double.valueOf(wins), name);
		    }		
	    } else if(damageNum == 2) {
		    for (String name : damage2.keySet()) {
		    	double wins = damage2.get(name);
		    	scoreMap.put(Double.valueOf(wins), name);
		    }		
	    }
		return scoreMap;
	}
	
	/*@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player.getWorld().getName().equalsIgnoreCase("world_the_end")) {
			if(player.getWorld().getEntities().contains(dragon)) {
				//BossBar bar = Bukkit.createBossBar("Ender Dragon", BarColor.BLUE, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
				bar.addPlayer(player);
				player.sendMessage("Join test");
			}
		}
	}
	
	@EventHandler
	public void onSwitchWorld(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		Location from = event.getFrom();
		Location to = event.getTo();
		if(!from.getWorld().getName().equalsIgnoreCase("world_the_end")) {
			if(to.getWorld().getName().equalsIgnoreCase("world_the_end")) {
				boolean value = false;
				if(to.getWorld().getEntities().contains(dragon)) {
					//BossBar bar = Bukkit.createBossBar("EnderDragon", BarColor.BLUE, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
					bar.addPlayer(player);
					player.sendMessage("Switch world Test");
				}
			}
		}
	}*/
}
