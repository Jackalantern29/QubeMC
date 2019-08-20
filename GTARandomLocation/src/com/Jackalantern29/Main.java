package com.Jackalantern29;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public static ArrayList<UUID> protection = new ArrayList<UUID>();
	public static HashMap<UUID, Integer> cooldown = new HashMap<UUID, Integer>();
	
	int MallDelay = 0;
	int MallRepeat = 0;
	public void onEnable() {
		saveDefaultConfig();
		File log = new File(getDataFolder(), "log.log");
		if(!log.exists()) {
			try {
				log.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(player.getGameMode() != GameMode.CREATIVE) {
						if(player.getLocation().getY() <= 5) {
							final Location location = player.getLocation();
							Location spawn = new Location(Bukkit.getWorld("world"), 2842, 72, 1607);
							player.teleport(spawn);
							FileOutputStream fstream = null;
							try {
								fstream = new FileOutputStream(new File(getDataFolder(), "log.log"));
							} catch (FileNotFoundException e) {
								player.sendMessage(e.getMessage());
							}
							BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fstream));
							Date date = new Date();
							try {
								writer.write("[" + date.toString() + "] Player: " + player.getName() + " Location: " + location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ());
							} catch (IOException e) {
								player.sendMessage(e.getMessage());
							}
							try {
								writer.flush();
							} catch (IOException e) {
								player.sendMessage(e.getMessage());
							}
							try {
								writer.close();
							} catch (IOException e) {
								player.sendMessage(e.getMessage());
							}
						}
					}
				}
			}
		}, 0, 1);
		File Locations = new File(getDataFolder() + "/Locations.yml");
		if (!Locations.exists()) {
			new File(getDataFolder(), "/Locations.yml");
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(cooldown.containsKey(player.getUniqueId())) {
						if(cooldown.get(player.getUniqueId()) != 0) {
							cooldown.replace(player.getUniqueId(), cooldown.get(player.getUniqueId()) - 1);
						}
					}
				}
			}
		}, 0, 20);
	}
	
	@EventHandler
	public void onPvP(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			if(protection.contains(event.getEntity().getUniqueId()))
				event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		protection.remove(event.getPlayer());
	}
	@EventHandler
	public void onPlayerDeath(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		File locationFile = new File(getDataFolder() + File.separator + "Locations.yml");
		YamlConfiguration SC = YamlConfiguration.loadConfiguration(locationFile);
		if (SC.contains("Locations")) {
			Random rnd = new Random();
			int spawn;
			if (SC.getInt("Locations") >= 2) {
				spawn = rnd.nextInt(SC.getInt("Locations"));
				spawn = spawn + 1;
			} else {
				spawn = +1;
			}
			World world = Bukkit.getWorld(SC.getString(spawn + ".World"));
			double x = SC.getDouble(spawn + ".X");
			double y = SC.getDouble(spawn + ".Y");
			double z = SC.getDouble(spawn + ".Z");
			float yaw = (float) SC.getDouble(spawn + ".Yaw");
			float pitch = (float) SC.getDouble(spawn + ".Pitch");
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				
				@Override
				public void run() {
					player.teleport(new Location(world, x, y, z, yaw, pitch));
				}
			}, 4);
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					protection.remove(player.getUniqueId());
					player.sendMessage(getConfig().getString("SpawnProtectionOver").replace("&", "§"));
				}
			}, 20L * getConfig().getInt("ProtectionTime"));
		}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("play")) {
			if (sender instanceof Player) {
				File locationFile = new File(getDataFolder() + File.separator + "Locations.yml");
				YamlConfiguration SC = YamlConfiguration.loadConfiguration(locationFile);
				Player player = (Player) sender;
				//if(isInPlay.contains(player.getUniqueId())) {
					//player.sendMessage(getConfig().getString("AlreadyPlaying").replace("&", "§"));
					//return true;
				//}
				if(cooldown.containsKey(player.getUniqueId())) {
					if(cooldown.get(player.getUniqueId()) != 0) {
						player.sendMessage(getConfig().getString("CooldownMsg").replace("&", "§").replace("%time%", "" + cooldown.get(player.getUniqueId())));
						return true;
					} else {
						cooldown.replace(player.getUniqueId(), 10);
					}
				} else {
					cooldown.put(player.getUniqueId(), 10);
				}
				
				Location loc = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
				Bukkit.getScheduler().cancelTask(MallDelay);
				Bukkit.getScheduler().cancelTask(MallRepeat);

				player.sendMessage(getConfig().getString("Teleport").replace("&", "§"));
				player.sendMessage(getConfig().getString("PreTeleport").replace("&", "§"));

				MallDelay = Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						if (SC.contains("Locations")) {
							Random rnd = new Random();
							int spawn;
							if (SC.getInt("Locations") >= 2) {
								spawn = rnd.nextInt(SC.getInt("Locations"));
								spawn = spawn + 1;
							} else {
								spawn = +1;
							}
							World world = Bukkit.getWorld(SC.getString(spawn + ".World"));
							double x = SC.getDouble(spawn + ".X");
							double y = SC.getDouble(spawn + ".Y");
							double z = SC.getDouble(spawn + ".Z");
							float yaw = (float) SC.getDouble(spawn + ".Yaw");
							float pitch = (float) SC.getDouble(spawn + ".Pitch");
							player.teleport(new Location(world, x, y, z, yaw, pitch));
							if(!protection.contains(player.getUniqueId()))
								protection.add(player.getUniqueId());
							//if(!isInPlay.contains(player.getUniqueId())) {
							//	isInPlay.add(player.getUniqueId());
							//}
						
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.this, new Runnable() {
								@Override
								public void run() {
									protection.remove(player.getUniqueId());
									player.sendMessage(getConfig().getString("SpawnProtectionOver").replace("&", "§"));
								}
							}, 20L * getConfig().getInt("ProtectionTime"));
						} else {
							player.sendMessage(getConfig().getString("NoSpawn").replace("&", "§"));
						}
						player.sendMessage(getConfig().getString("NowPlayingGame").replace("&", "§"));
						Bukkit.getScheduler().cancelTask(MallDelay);
						Bukkit.getScheduler().cancelTask(MallRepeat);

					}
				}, 20 * 4);
				MallRepeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
					@Override
					public void run() {
						if ((loc.getBlockX() != player.getLocation().getBlockX()) || (loc.getBlockY() != player.getLocation().getBlockY())
								|| (loc.getBlockZ() != player.getLocation().getBlockZ())) {
							player.sendMessage(getConfig().getString("TeleportCancelled").replace("&", "§"));
							Bukkit.getScheduler().cancelTask(MallRepeat);
							Bukkit.getScheduler().cancelTask(MallDelay);
						}
					}
				}, 0, 1);
			} else {
				sender.sendMessage("You need to be a player to perform this command!");
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("addspawn")) {
			Player player = (Player) sender;
			File locationFile = new File(getDataFolder() + File.separator + "Locations.yml");
			YamlConfiguration SC = YamlConfiguration.loadConfiguration(locationFile);
			if (!SC.contains("Locations")) {
				SC.set("Locations", 0);
				try {
					SC.save(locationFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int spawn = SC.getInt("Locations");
			spawn++;
			SC.set(spawn + ".World", player.getLocation().getWorld().getName());
			SC.set(spawn + ".X", player.getLocation().getX());
			SC.set(spawn + ".Y", player.getLocation().getY());
			SC.set(spawn + ".Z", player.getLocation().getZ());
			SC.set(spawn + ".Yaw", player.getLocation().getYaw());
			SC.set(spawn + ".Pitch", player.getLocation().getPitch());
			SC.set("Locations", spawn);
			try {
				SC.save(locationFile);
				player.sendMessage(getConfig().getString("SetSpawn").replace("&", "§"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}