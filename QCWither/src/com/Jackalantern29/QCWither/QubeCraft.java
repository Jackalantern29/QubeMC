package com.Jackalantern29.QCWither;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeCraft extends JavaPlugin implements Listener {
	static HashMap<String, Double> damage = new HashMap<String, Double>();
	BossBar bar1 = Bukkit.createBossBar("Wither", BarColor.WHITE, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	BossBar bar2 = Bukkit.createBossBar("§aWither", BarColor.GREEN, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	BossBar bar3 = Bukkit.createBossBar("§4Wither", BarColor.RED, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	BossBar bar4 = Bukkit.createBossBar("§eWither", BarColor.YELLOW, BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
	static HashMap<Location, Map<Material, Byte>> blocksmap = new HashMap<>();
	static List<ItemStack> items = new ArrayList<>();
	double health1 = 0.0;
	double maxhealth1 = 0.0;
	double health2 = 0.0;
	double maxhealth2 = 0.0;
	double health3 = 0.0;
	double maxhealth3 = 0.0;
	double health4 = 0.0; 
	double maxhealth4 = 0.0;
	boolean value1;
	boolean value2;
	boolean value3;
	boolean value4;
	//static boolean check = false;
	
	public void onEnable() {
		File configFile = new File(getDataFolder() + "/config.yml");
		if (!configFile.exists()) {
			saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				value1 = false;
				value2 = false;
				value3 = false;
				value4 = false;
				for(Entity entity : Bukkit.getWorld("Skyblock").getEntities()) {
					if(entity instanceof Item) {
						Item item = (Item)entity;
						if(item.getLocation().distance(new Location(entity.getWorld(), -602, 129, 1007)) <= 50) {
							if(!items.contains(item.getItemStack())) {
								item.remove();
							}
						}
					}
					if(entity instanceof Wither) {
						if(entity.getCustomName().equals("§f§lWither")) {
							if(entity.getLocation().distance(new Location(entity.getWorld(), -602, 129, 1007)) <= 50) {
								value1 = true;
								Wither wither = (Wither)entity;
								health1 = wither.getHealth();
								maxhealth1 = wither.getMaxHealth();
								Packet.hideWitherBar(wither);
							}
						}
						if(entity.getCustomName().equals("§a§lWither")) {
							if(entity.getLocation().distance(new Location(entity.getWorld(), -602, 129, 1007)) <= 50) {
								value2 = true;
								Wither wither = (Wither)entity;
								health2 = wither.getHealth();
								maxhealth2 = wither.getMaxHealth();
								Packet.hideWitherBar(wither);
							}
						}
						if(entity.getCustomName().equals("§4§lWither")) {
							if(entity.getLocation().distance(new Location(entity.getWorld(), -602, 129, 1007)) <= 50) {
								value3 = true;
								Wither wither = (Wither)entity;
								health3 = wither.getHealth();
								maxhealth3 = wither.getMaxHealth();
								Packet.hideWitherBar(wither);
							}
						}
						if(entity.getCustomName().equals("§e§lWither")) {
							if(entity.getLocation().distance(new Location(entity.getWorld(), -602, 129, 1007)) <= 50) {
								value4 = true;
								Wither wither = (Wither)entity;
								health4 = wither.getHealth();
								maxhealth4 = wither.getMaxHealth();
								Packet.hideWitherBar(wither);
							}
						}
					}
				}
				if(value1) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(player.getWorld().getName().equalsIgnoreCase("Skyblock")) {
							if(player.getLocation().distance(new Location(player.getWorld(), -602, 129, 1007)) <= 50) {
								bar1.addPlayer(player);
							} else {
								bar1.removePlayer(player);
							}
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
						if(player.getWorld().getName().equalsIgnoreCase("Skyblock")) {
							if(player.getLocation().distance(new Location(player.getWorld(), -602, 129, 1007)) <= 50) {
								bar2.addPlayer(player);
							} else {
								bar2.removePlayer(player);
							}
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
				if(value3) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(player.getWorld().getName().equalsIgnoreCase("Skyblock")) {
							if(player.getLocation().distance(new Location(player.getWorld(), -602, 129, 1007)) <= 50) {
								bar3.addPlayer(player);
							} else {
								bar3.removePlayer(player);
							}
						} else {
							bar3.removePlayer(player);;
						}
					}				
					bar3.setProgress(health3 / maxhealth3);
					if(bar3.getProgress() == 0.0) {
						bar3.removeAll();
					}
				} else {
					bar3.removeAll();
				}
				if(value4) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(player.getWorld().getName().equalsIgnoreCase("Skyblock")) {
							if(player.getLocation().distance(new Location(player.getWorld(), -602, 129, 1007)) <= 50) {
								bar4.addPlayer(player);
							} else {
								bar4.removePlayer(player);
							}
						} else {
							bar4.removePlayer(player);;
						}
					}				
					bar4.setProgress(health4 / maxhealth4);
					if(bar4.getProgress() == 0.0) {
						bar4.removeAll();
					}
				} else {
					bar4.removeAll();
				}
			}
		}, 0, 1);
	}
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onShoot(ProjectileLaunchEvent event) {
		if(event.getEntity() instanceof WitherSkull) {
			WitherSkull skull = (WitherSkull)event.getEntity();
			if(skull.getShooter() instanceof Wither) {
				Wither wither = (Wither)skull.getShooter();
				if(wither.getCustomName().equals("§e§lWither")) {
					event.setCancelled(true);
					/*Skeleton skeleton  = (Skeleton)skull.getWorld().spawnEntity(skull.getLocation(), EntityType.SKELETON);
					skeleton.setSkeletonType(SkeletonType.WITHER);
					skeleton.setVelocity(skull.getVelocity());
					skeleton.setFallDistance(skull.getFallDistance());*/
					LargeFireball fireball = wither.launchProjectile(LargeFireball.class);
					fireball.setIsIncendiary(true);
					fireball.setDirection(skull.getDirection());
					fireball.setFallDistance(skull.getFallDistance());
					fireball.setShooter(wither);
					fireball.setVelocity(skull.getVelocity());
					fireball.setYield(skull.getYield() * 2);
				}				
			}
		}
	}
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		/*if(event.getDamager() instanceof Wither) {
			if(event.getEntity().getCustomName().equals("§f§lWither")) {
				event.setDamage(event.getDamage() / 2);
			} else if(event.getEntity().getCustomName().equals("§a§lWither")) {
				event.setDamage(event.getDamage() / 2);
			} else if(event.getEntity().getCustomName().equals("§lWither")) {
				event.setDamage(event.getDamage() / 2);
			}
		}
		if(event.getDamager() instanceof WitherSkull) {
			WitherSkull skull = (WitherSkull)event.getDamager();
			Wither wither = (Wither) skull.getShooter();
			if(wither.getCustomName().equals("§f§lWither")) {
				event.setDamage(event.getDamage() / 2);
			} else if(wither.getCustomName().equals("§a§lWither")) {
				event.setDamage(event.getDamage() / 2);
			} else if(wither.getCustomName().equals("§lWither")) {
				event.setDamage(event.getDamage() / 2);
			}
		}*/
		if(event.getEntity() instanceof Wither) {
			Player player = (Player) event.getDamager();
			if(event.getEntity().getCustomName().equals("§f§lWither")) {
				if(!damage.containsKey(player.getName())) {
					damage.put(player.getName(), 0.0);
				}
				damage.replace(player.getName(), damage.get(player.getName()) + event.getDamage());
			} else if(event.getEntity().getCustomName().equals("§a§lWither")) {
				if(!damage.containsKey(player.getName())) {
					damage.put(player.getName(), 0.0);
				}
				damage.replace(player.getName(), damage.get(player.getName()) + event.getDamage());
			} else if(event.getEntity().getCustomName().equals("§4§lWither")) {
				if(!damage.containsKey(player.getName())) {
					damage.put(player.getName(), 0.0);
				}
				damage.replace(player.getName(), damage.get(player.getName()) + event.getDamage());
			} else if(event.getEntity().getCustomName().equals("§e§lWither")) {
				if(!damage.containsKey(player.getName())) {
					damage.put(player.getName(), 0.0);
				}
				damage.replace(player.getName(), damage.get(player.getName()) + event.getDamage());
			}
		}
	}
	@EventHandler
	public void onKill(EntityDeathEvent event) {
		if(event.getEntity() instanceof Wither) {
			Player player = (Player) event.getEntity().getKiller();
			if(event.getEntity().getCustomName().equals("§f§lWither")) {
				ArrayList<String> list = new ArrayList<String>();
				event.getDrops().clear();
				int tempnumber = 0;
				
				for (Map.Entry<Double, String> ent : getTop().entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("%player%", player.getName()).replace("%damager%", ent.getValue()).replace("%damage%", "" + ent.getKey()));
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						/*player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));*/
						
					}
				}
				//damage.clear();
			} else if(event.getEntity().getCustomName().equals("§a§lWither")) {
				ArrayList<String> list = new ArrayList<String>();
				event.getDrops().clear();
				int tempnumber = 0;
				
				for (Map.Entry<Double, String> ent : getTop().entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("%player%", player.getName()).replace("%damager%", ent.getValue()).replace("%damage%", "" + ent.getKey()));
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						/*player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));*/
						
					}
				}
				//damage.clear();
			} else if(event.getEntity().getCustomName().equals("§4§lWither")) {
				ArrayList<String> list = new ArrayList<String>();
				event.getDrops().clear();
				int tempnumber = 0;
				
				for (Map.Entry<Double, String> ent : getTop().entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("%player%", player.getName()).replace("%damager%", ent.getValue()).replace("%damage%", "" + ent.getKey()));
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						/*player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));*/
						
					}
				}
				//damage.clear();
			} else if(event.getEntity().getCustomName().equals("§e§lWither")) {
				ArrayList<String> list = new ArrayList<String>();
				event.getDrops().clear();
				int tempnumber = 0;
				
				for (Map.Entry<Double, String> ent : getTop().entrySet()) {
					list.add(getConfig().getString("BroadcastMessage").replace("&", "§").replace("%player%", player.getName()).replace("%damager%", ent.getValue()).replace("%damage%", "" + ent.getKey()));
				}			
				for (int i = list.size() - 1; i >= 0; i--) {
					tempnumber++;
					
					if (tempnumber <= 1) {
						Bukkit.broadcastMessage(list.get(i));
						/*player.getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						player.getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.SKULL_ITEM, 1, (short)5));
						Bukkit.getPlayer(name).getInventory().addItem(new ItemStack(Material.DRAGON_EGG, 1));*/
						
					}
				}
				//damage.clear();
			}
		}
	}
	
	@EventHandler(ignoreCancelled=true,priority=EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if(player.getLocation().distance(new Location(player.getWorld(), -602, 129, 1007)) <= 50) {
			event.setKeepInventory(true);
			event.setKeepLevel(true);
			
		}
	}
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(player.getLocation().distance(new Location(player.getWorld(), -602, 129, 1007)) <= 50) {
			items.add(event.getItemDrop().getItemStack());
		}
	}
	@EventHandler
	public void onMobSpawn(EntitySpawnEvent event) {
		EntityType entity = event.getEntityType();
		if(event.getEntity().getLocation().distance(new Location(event.getEntity().getWorld(), -602, 129, 1007)) <= 50) {
			if(entity.equals(EntityType.CREEPER)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.ZOMBIE)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.SPIDER)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.PIG)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.VILLAGER)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.WOLF)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.OCELOT)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.SLIME)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.BLAZE)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.COW)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.SHEEP)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.RABBIT)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.ENDERMAN)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.WITCH)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.ENDERMITE)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.CHICKEN)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.MUSHROOM_COW)) {
				event.setCancelled(true);
			}
			if(entity.equals(EntityType.HORSE)) {
				event.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWitherGrief(EntityExplodeEvent event) {
		if(event.getEntity() instanceof Projectile) {
			Projectile entity = (Projectile)event.getEntity();
			if(entity.getShooter() instanceof Wither) {
				Wither wither = (Wither) entity.getShooter();
				if(wither.getCustomName().equals("§f§lWither")) {
					for(Block blocks : event.blockList()) {
						if(!blocks.getType().equals(Material.AIR)) {
							Map<Material, Byte> map = new HashMap<>();
							map.put(blocks.getType(), blocks.getData());
							blocksmap.put(blocks.getLocation(), map);
						}
					}
				} else if(wither.getCustomName().equals("§a§lWither")) {
					for(Block blocks : event.blockList()) {
						if(!blocks.getType().equals(Material.AIR)) {
							Map<Material, Byte> map = new HashMap<>();
							map.put(blocks.getType(), blocks.getData());
							blocksmap.put(blocks.getLocation(), map);
						}
					}
				} else if(wither.getCustomName().equals("§4§lWither")) {
					for(Block blocks : event.blockList()) {
						if(!blocks.getType().equals(Material.AIR)) {
							Map<Material, Byte> map = new HashMap<>();
							map.put(blocks.getType(), blocks.getData());
							blocksmap.put(blocks.getLocation(), map);
						}
					}
				} else if(wither.getCustomName().equals("§e§lWither")) {
					for(Block blocks : event.blockList()) {
						if(!blocks.getType().equals(Material.AIR)) {
							Map<Material, Byte> map = new HashMap<>();
							map.put(blocks.getType(), blocks.getData());
							blocksmap.put(blocks.getLocation(), map);
						}
					}
				}
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWitherGrief(EntityChangeBlockEvent event) {
		if(event.getEntity() instanceof Wither) {
			Wither wither = (Wither)event.getEntity();
			if(wither.getCustomName().equals("§f§lWither")) {
				Map<Material, Byte> map = new HashMap<>();
				map.put(event.getBlock().getType(), event.getBlock().getData());
				blocksmap.put(event.getBlock().getLocation(), map);
			} else if(wither.getCustomName().equals("§a§lWither")) {
				Map<Material, Byte> map = new HashMap<>();
				map.put(event.getBlock().getType(), event.getBlock().getData());
				blocksmap.put(event.getBlock().getLocation(), map);
			} else if(wither.getCustomName().equals("§4§lWither")) {
				Map<Material, Byte> map = new HashMap<>();
				map.put(event.getBlock().getType(), event.getBlock().getData());
				blocksmap.put(event.getBlock().getLocation(), map);
			} else if(wither.getCustomName().equals("§e§lWither")) {
				Map<Material, Byte> map = new HashMap<>();
				map.put(event.getBlock().getType(), event.getBlock().getData());
				blocksmap.put(event.getBlock().getLocation(), map);
			}
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
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("wither")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("command.wither")) {
				player.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			if(args.length == 0) {
				player.sendMessage("§bCommands:");
				player.sendMessage("§b/wither spawn [1-3]");
				player.sendMessage("§b/wither killall");
				player.sendMessage("§b/wither regenblocks");
				player.sendMessage("§b/wither resetdamage");
				player.sendMessage("§b/wither topdamage");
				player.sendMessage("§b/wither skeleton");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("spawn")) {
					Packet.spawnWitherNoBar("§f§lWither", player.getLocation());
					player.sendMessage("§7[§8Wither§7] §aSuccessfully spawned a wither.");
					return true;
				} else if(args[0].equalsIgnoreCase("killall")) {
					for(Entity entity : player.getWorld().getEntities()) {
						if(entity instanceof Wither) {
							if(entity.getCustomName().equals("§f§lWither")) {
								entity.remove();
							}
							if(entity.getCustomName().equals("§a§lWither")) {
								entity.remove();
							} 
							if(entity.getCustomName().equals("§4§lWither")) {
								entity.remove();
							}
							if(entity.getCustomName().equals("§e§lWither")) {
								entity.remove();
							}
						}
					}
					player.sendMessage("§7[§8Wither§7] §aAll '/wither spawn' has been removed.");
				} else if(args[0].equalsIgnoreCase("regenblocks")) {
					player.sendMessage("§7[§8Wither§7] §aRegenerating blocks...");
					int count = 0;
					for(Location locations : blocksmap.keySet()) {
						Map<Material, Byte> map = blocksmap.get(locations);
						locations.getBlock().setTypeIdAndData(map.keySet().iterator().next().getId(), map.values().iterator().next(), true);
						count++;
					}
					blocksmap.clear();	
					player.sendMessage("§7[§8Wither§7] §e" + NumberFormat.getInstance().format(count) + "§a block(s) are now regenerated.");
				} else if(args[0].equalsIgnoreCase("resetdamage")) {
					damage.clear();
					player.sendMessage("§7[§8Wither§7] §aAll players current total damage were successfully reset.");
				} else if(args[0].equalsIgnoreCase("topdamage")) {
					ArrayList<String> list = new ArrayList<String>();
					
					for (Map.Entry<Double, String> ent : getTop().entrySet()) {
						list.add(ent.getValue() + ";" + ent.getKey());
					}
					StringBuilder str = new StringBuilder();
	                  for (String string: list) {
	                	  String pname = string.split(";")[0];
	                	  String pdamage = string.split(";")[1];
	                	  if(str.toString().length() > 0)
	                		  str.append("§7,§a");
	                	  str.append("§6" + pname + " §a'§e" + pdamage + "§a'");
	                  }
	                  player.sendMessage("§7[§8Wither§7] §a" + str.toString());
				}/* else if(args[0].equalsIgnoreCase("skeleton")) {
					if(check)
						check = false;
					else
						check = true;
					player.sendMessage("§7[§8Wither§7] §aWitherSkeleton spawning: §e" + check);
				}*/
			} else if(args.length == 2) {
				String code = "fa4e";
				if(args[0].equalsIgnoreCase("spawn")) {
					if(args[1].equalsIgnoreCase("fire")) {
						Packet.spawnWitherNoBar("§e§lWither", player.getLocation());

					}
					if(StringUtils.isNumeric(args[1])) {
						int number = Integer.parseInt(args[1]);
						if(number > 4) {
							player.sendMessage("§cNumber is bigger than 4.");
							return true;
						}
						for (int i = 0; i < number; i++) {
							Packet.spawnWitherNoBar("§" + code.substring(i, i + 1) + "§lWither", player.getLocation());
						}
						player.sendMessage("§7[§8Wither§7] §aSuccessfully spawned §e" + number + " §awither(s).");
					}
				}
			}
		}
		return false;
	}
}
