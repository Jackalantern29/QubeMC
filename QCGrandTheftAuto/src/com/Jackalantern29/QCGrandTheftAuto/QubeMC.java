package com.Jackalantern29.QCGrandTheftAuto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.Jackalantern29.QCGrandTheftAuto.Listeners.PlayerDeathListener;
import com.Jackalantern29.QCGrandTheftAuto.Listeners.PlayerInteractVillagerListener;
import com.Jackalantern29.QCGrandTheftAuto.Listeners.PlayerJoinListener;
import com.Jackalantern29.QCGrandTheftAuto.Listeners.PlayerKillListener;

public class QubeMC extends JavaPlugin implements Listener {
	public static HashMap<UUID, Integer> wantedLevel = new HashMap<UUID, Integer>();
	public static HashMap<Player, Integer> timer = new HashMap<Player, Integer>();
	public static HashMap<Player, Boolean> isTarget = new HashMap<Player, Boolean>();
	public final Logger logger = Logger.getLogger("Minecraft");
	public static HashMap<UUID, Integer> spawnDelay = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> amount = new HashMap<UUID, Integer>();
	public static List<UUID> shouldBeInvis = new ArrayList<UUID>();
	public static List<UUID> isInHouse = new ArrayList<UUID>();
	public static HashMap<UUID, Integer> tries = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Double> rent = new HashMap<UUID, Double>();
	//public static final BooleanFlag PAYNSPRAY_FLAG = new BooleanFlag("paynspray");
	//public static final BooleanFlag HOUSE_FLAG = new BooleanFlag("house");
	public static List<UUID> msgCooldown = new ArrayList<UUID>();
	//private WGCustomFlagsPlugin custPlugin;
	//public WorldGuardPlugin wgPlugin;
	protected static QubeMC plugin;
	public static final BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
	public static final BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };
	public void onEnable() {
		plugin = this;
	  //  Plugin plug = getServer().getPluginManager().getPlugin("WGCustomFlags");

	    //if ((plug == null) || (!(plug instanceof WGCustomFlagsPlugin)) || (!plug.isEnabled())) {
	    //  getLogger().warning("Could not load WorldGuard Custom Flags Plugin, disabling");
	   //   getServer().getPluginManager().disablePlugin(this);
	   //   return;
	 //  }

	    //this.custPlugin = ((WGCustomFlagsPlugin)plug);

	    //plug = getServer().getPluginManager().getPlugin("WorldGuard");

	    //if ((plug == null) || (!(plug instanceof WorldGuardPlugin)) || (!plug.isEnabled())) {
	      //getLogger().warning("Could not load WorldGuard Plugin, disabling");
	      //getServer().getPluginManager().disablePlugin(this);
	     // return;
	   // }

	   // this.wgPlugin = ((WorldGuardPlugin)plug);

	    //this.custPlugin.addCustomFlag(PAYNSPRAY_FLAG);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerKillListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new PlayerInteractVillagerListener(), this);
		pm.registerEvents(this, this);
		saveDefaultConfig();
		for(Player player : Bukkit.getOnlinePlayers()) {
			timer.put(player, 0);
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(OfflinePlayer players : Bukkit.getOfflinePlayers()) {
					if(tries.containsKey(players.getUniqueId())) {
						tries.replace(players.getUniqueId(), tries.get(players.getUniqueId()) - 1);
						if(tries.get(players.getUniqueId()) == -2) {
							tries.remove(players.getUniqueId());
						}
					}
					if(msgCooldown.contains(players.getUniqueId())) {
						msgCooldown.remove(players.getUniqueId());
					}
				}
			}
		}, 0, 20 * 5);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(msgCooldown.contains(player.getUniqueId())) {
						player.setFallDistance(0);
					}
					//player.sendMessage("False: " + yawToFace(player.getLocation().getYaw(), false).name());
					//player.sendMessage("True: " + yawToFace(player.getLocation().getYaw(), true).name());
				}
			}
		}, 0, 1);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(!timer.containsKey(player)) {
						timer.put(player, 0);
					}
					if(player.getOpenInventory().getType().equals(InventoryType.MERCHANT)) {
						player.closeInventory();
					}
					if(shouldBeInvis.contains(player.getUniqueId())) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 9999, 0, false, false));
						return;
					}
					if((player.getInventory().getHelmet() != null) && (player.getInventory().getChestplate() != null) && (player.getInventory().getLeggings() != null) && (player.getInventory().getBoots() != null)) {
						
						if((player.getInventory().getHelmet().getType().equals(Material.GOLD_HELMET)) && (player.getInventory().getChestplate().getType().equals(Material.GOLD_CHESTPLATE)) && (player.getInventory().getLeggings().getType().equals(Material.GOLD_LEGGINGS)) && (player.getInventory().getBoots().getType().equals(Material.GOLD_BOOTS))) { 
							player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 9999, 0, false, false));
						} else {
							player.removePotionEffect(PotionEffectType.INVISIBILITY);
							}
					} else {
						player.removePotionEffect(PotionEffectType.INVISIBILITY);
					}
				}
			}
		}, 0, 1);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(!wantedLevel.containsKey(player.getUniqueId()))
						wantedLevel.put(player.getUniqueId(), 0);
					if(!amount.containsKey(player.getUniqueId()))
						amount.put(player.getUniqueId(), 0);
					if(!spawnDelay.containsKey(player.getUniqueId()))
						spawnDelay.put(player.getUniqueId(), 0);
					if(wantedLevel.get(player.getUniqueId()) == 0) {
						Packets.sendActionBar(player, "§f✩✩✩✩✩✩");
					} else if(wantedLevel.get(player.getUniqueId()) == 1) {
						Packets.sendActionBar(player, "§f✮✩✩✩✩✩");
					} else if(wantedLevel.get(player.getUniqueId()) == 2) {
						Packets.sendActionBar(player, "§f✮✮✩✩✩✩");
					} else if(wantedLevel.get(player.getUniqueId()) == 3) {
						Packets.sendActionBar(player, "§f✮✮✮✩✩✩");
					} else if(wantedLevel.get(player.getUniqueId()) == 4) {
						Packets.sendActionBar(player, "§f✮✮✮✮✩✩");
					} else if(wantedLevel.get(player.getUniqueId()) == 5) {
						Packets.sendActionBar(player, "§f✮✮✮✮✮✩");
					} else if(wantedLevel.get(player.getUniqueId()) == 6) {
						Packets.sendActionBar(player, "§f✮✮✮✮✮✮");
					}
				}
			}
		}, 0, 10);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(wantedLevel.containsKey(player.getUniqueId())) {
						if((wantedLevel.get(player.getUniqueId()) >= 1) && (wantedLevel.get(player.getUniqueId()) <= 3)) {
							boolean check = false;
							for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
								if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
									Creature pigzombie = (Creature)entity;
									if(pigzombie.getTarget() == player)
										check = true;
								}
							}
							if(check == true) {
								Random r = new Random();
								int randomX = r.nextInt(20 * 2) - 20;
								int randomZ = r.nextInt(20 * 2) - 20;
								Location location = player.getLocation();
								location.add(randomX, 0, randomZ);
								location.setY(player.getWorld().getHighestBlockYAt(location));
								PigZombie pigzombie = null;
								if(spawnDelay.get(player.getUniqueId()) != 0)
									return;
								if(wantedLevel.get(player.getUniqueId()) == 1) {
									if(amount.get(player.getUniqueId()) == 12)
										return;
									amount.replace(player.getUniqueId(), amount.get(player.getUniqueId()) + 1);
									spawnDelay.replace(player.getUniqueId(), 12);
									pigzombie = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie.setCustomName("§9Police Officer");
									pigzombie.setHealth(2);
								} else if(wantedLevel.get(player.getUniqueId()) == 2) {
									if(amount.get(player.getUniqueId()) == 16)
										return;
									amount.replace(player.getUniqueId(), amount.get(player.getUniqueId()) + 1);
									spawnDelay.replace(player.getUniqueId(), 8);
									pigzombie = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie.setCustomName("§9Police Officer");
									pigzombie.setHealth(2);
								} else if(wantedLevel.get(player.getUniqueId()) == 3) {
									if(amount.get(player.getUniqueId()) == 24)
										return;
									amount.replace(player.getUniqueId(), amount.get(player.getUniqueId()) + 1);
									spawnDelay.replace(player.getUniqueId(), 10);
									pigzombie = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
									pigzombie.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
									pigzombie.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
									pigzombie.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
									pigzombie.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_SWORD));
									
									pigzombie.setCustomName("§9Armored Police");
									pigzombie.setHealth(2);

								}
								pigzombie.setTarget(player);
							}
						}
					}
				}
			}
		}, 0, 20);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(wantedLevel.containsKey(player.getUniqueId())) {
						if(wantedLevel.get(player.getUniqueId()) >= 4) {
							boolean check = false;
							for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
								if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
									Creature pigzombie = (Creature)entity;
									if(pigzombie.getTarget() == player)
										check = true;
								}
							}
							if(check == true) {
								Random r = new Random();
								int randomX = r.nextInt(20 * 2) - 20;
								int randomZ = r.nextInt(20 * 2) - 20;
								Location location = player.getLocation();
								location.add(randomX, 0, randomZ);
								location.setY(player.getWorld().getHighestBlockYAt(location));
								PigZombie pigzombie = null;
								PigZombie pigzombie2 = null;
								if(spawnDelay.get(player.getUniqueId()) != 0)
									return;
								if(wantedLevel.get(player.getUniqueId()) == 4) {
									if(amount.get(player.getUniqueId()) == 32)
										return;
									amount.replace(player.getUniqueId(), amount.get(player.getUniqueId()) + 2);
									spawnDelay.replace(player.getUniqueId(), 6);
									pigzombie = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
									pigzombie.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
									pigzombie.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
									pigzombie.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
									pigzombie.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_SWORD));
									
									pigzombie2 = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie2.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
									pigzombie2.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
									pigzombie2.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
									pigzombie2.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
									pigzombie2.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_SWORD));
									
									pigzombie.setCustomName("§9Armored Police");
									pigzombie.setHealth(2);
									
									pigzombie2.setCustomName("§9Armored Police");
									pigzombie2.setHealth(2);
								} else if(wantedLevel.get(player.getUniqueId()) == 5) {
									if(amount.get(player.getUniqueId()) == 40)
										return;
									amount.replace(player.getUniqueId(), amount.get(player.getUniqueId()) + 2);
									spawnDelay.replace(player.getUniqueId(), 8);
									pigzombie = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
									pigzombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
									pigzombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
									pigzombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
									pigzombie.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
									
									pigzombie2 = (PigZombie)player.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
									pigzombie2.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
									pigzombie2.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
									pigzombie2.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
									pigzombie2.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
									pigzombie2.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
									
									pigzombie.setCustomName("§9SWAT Member");
									pigzombie.setHealth(2);
									
									pigzombie2.setCustomName("§9SWAT Member");
									pigzombie2.setHealth(2);
								} else if(wantedLevel.get(player.getUniqueId()) == 6) {
									if(amount.get(player.getUniqueId()) == 48)
										return;
									amount.replace(player.getUniqueId(), amount.get(player.getUniqueId()) + 2);
									spawnDelay.replace(player.getUniqueId(), 4);
									Skeleton skeleton = (Skeleton)player.getWorld().spawnEntity(location, EntityType.SKELETON);
									skeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
									skeleton.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
									skeleton.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
									skeleton.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
									ItemStack item = new ItemStack(Material.BOW);
									item.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
									skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
									
									Skeleton skeleton2 = (Skeleton)player.getWorld().spawnEntity(location, EntityType.SKELETON);
									skeleton2.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
									skeleton2.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
									skeleton2.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
									skeleton2.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
									skeleton2.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
									
									skeleton.setCustomName("§9Army Soldier");
									skeleton.setHealth(2);
									
									skeleton2.setCustomName("§9Army Soldier");
									skeleton2.setHealth(2);
								}
								pigzombie.setTarget(player);
								pigzombie2.setTarget(player);
							}
						}
					}
				}
			}
		}, 0, 20);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(spawnDelay.containsKey(player.getUniqueId())) {
						if(spawnDelay.get(player.getUniqueId()) != 0) {
							spawnDelay.replace(player.getUniqueId(), spawnDelay.get(player.getUniqueId()) - 1);
						}
					}
					if(wantedLevel.containsKey(player.getUniqueId())) {
						if(wantedLevel.get(player.getUniqueId()) >= 1) {
							boolean check = true;
							for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
								if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
									Creature pigzombie = (Creature)entity;
									if(pigzombie.getTarget() == null) {
										pigzombie.setTarget(player);
									}
									if(pigzombie.getTarget() == player) {
										check = false;
									}
								}
							}
							if(check == true) {
								timer.replace(player, timer.get(player) + 1);
								if((timer.get(player) >= 110) && (timer.get(player) <= 120)) {
									Packets.sendActionBar(player, "");
								}
								if(timer.get(player) == 120) {
									wantedLevel.replace(player.getUniqueId(), wantedLevel.get(player.getUniqueId()) - 1);
									timer.replace(player, 0);
								}
							}
						} else {
							for(Entity entity : player.getNearbyEntities(10, 10, 10)) {
								if((entity instanceof PigZombie) || (entity instanceof IronGolem)) {
									Creature pigzombie = (Creature)entity;
									if(pigzombie.getTarget() == player)
										pigzombie.setTarget(null);
								}
							}
						}
					}
				}
			}
		}, 0, 20);
	}
	
	public void onDisable() {
		timer.clear();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().performCommand("spawn");
	}
	
	public static QubeMC getInstance() {
		return plugin;
	}
	
	/**
	    * Gets the horizontal Block Face from a given yaw angle<br>
	    * This includes the NORTH_WEST faces
	    *
	    * @param yaw angle
	    * @return The Block Face of the angle
	    */
	    public static BlockFace yawToFace(float yaw) {
	        return yawToFace(yaw, true);
	    }
	 
	    /**
	    * Gets the horizontal Block Face from a given yaw angle
	    *
	    * @param yaw angle
	    * @param useSubCardinalDirections setting, True to allow NORTH_WEST to be returned
	    * @return The Block Face of the angle
	    */
	    public static BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
	        if (useSubCardinalDirections) {
	            return radial[Math.round(yaw / 45f) & 0x7];
	        } else {
	            return axis[Math.round(yaw / 90f) & 0x3];
	        }
	    }
}