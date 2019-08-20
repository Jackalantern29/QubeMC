package com.Jackalantern29.QCBounty;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeCraft extends JavaPlugin implements Listener {
	public static Economy econ;
	public final Logger logger = Logger.getLogger("Minecraft");
	public static QubeCraft plugin;
	private final transient List<String> lines = new ArrayList<String>();
	Map<String, BigDecimal> balances = new HashMap<String, BigDecimal>();
	public void onEnable() {
		if (!setupEconomy()) {
			logger.severe(
					String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		saveDefaultConfig();
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("bounty")) {
			if(args.length == 0) {
				if(!sender.hasPermission("bounty.command.help")) {
					sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
					return true;
				}
				for(String string : getConfig().getStringList("BountyHelpMsg")) {
					sender.sendMessage(string.replace("&", "§"));
				}
				return true;
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					if(!sender.hasPermission("bounty.command.help")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					for(String string : getConfig().getStringList("BountyHelpMsg")) {
						sender.sendMessage(string.replace("&", "§"));
					}
					return true;
				} else if(args[0].equalsIgnoreCase("add")) {
					if(!sender.hasPermission("bounty.command.add")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					sender.sendMessage("§cUsage: /bounty add <player> <amount>");
					return true;
				} else if(args[0].equalsIgnoreCase("set")) {
					if(!sender.hasPermission("bounty.command.set")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					sender.sendMessage("§cUsage: /bounty set <player> <amount>");
					return true;
				} else if(args[0].equalsIgnoreCase("get")) {
					if(!sender.hasPermission("bounty.command.get")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					sender.sendMessage("§cUsage: /bounty get <player>");
					return true;
				} else if(args[0].equalsIgnoreCase("clear")) {
					if(!sender.hasPermission("bounty.command.clear")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					sender.sendMessage("§cUsage: /bounty clear <player>");
					return true;
				} else if(args[0].equalsIgnoreCase("top")) {
					if(!sender.hasPermission("bounty.command.list")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					lines.clear();
					balances.clear();
					for(Player player : Bukkit.getOnlinePlayers()) {
						if(Stats.getPlayerBounty(player.getUniqueId()) != 0.0) {
							BigDecimal amount = BigDecimal.valueOf(Stats.getPlayerBounty(player.getUniqueId()));
							String name = player.getName();
							balances.put(name, amount);
						}
					}
					List<Map.Entry<String, BigDecimal>> sortedEntries = new ArrayList<Map.Entry<String, BigDecimal>>(balances.entrySet());
					Collections.sort(sortedEntries, new Comparator<Map.Entry<String, BigDecimal>>() {
						@Override
						public int compare(Entry<String, BigDecimal> entry1, Entry<String, BigDecimal> entry2) {
							return ((BigDecimal)entry2.getValue()).compareTo((BigDecimal)entry1.getValue());
						}
					});
					sender.sendMessage(getConfig().getString("BountyListHeader").replace("&", "§").replace("{page}", "1"));

					for (Map.Entry<String, BigDecimal> entry : sortedEntries) {
						lines.add(getConfig().getString("BountyListData").replace("&", "§").replace("%player%", entry.getKey()).replace("%amount%", "" + (BigDecimal)entry.getValue()));
					}
					for (int x = 0; x <= 9; x++) {
						if(lines.size() <= x) {
							return true;
						}
						if(lines.get(x) != null)
							sender.sendMessage(lines.get(x).replace("%pos%", "" + x + 1));
					}
					return true;
				} else {
					sender.sendMessage(getConfig().getString("UnknownCmd").replace("&", "§"));
					return true;
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("add")) {
					if(!sender.hasPermission("bounty.command.add")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					sender.sendMessage("§cUsage: /bounty add <player> <amount>");
					return true;
				} else if(args[0].equalsIgnoreCase("top")) {
					if(!sender.hasPermission("bounty.command.list")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					lines.clear();
					balances.clear();
					for(Player player : Bukkit.getOnlinePlayers()) {
							if(Stats.getPlayerBounty(player.getUniqueId()) != 0.0) {
								BigDecimal amount = BigDecimal.valueOf(Stats.getPlayerBounty(player.getUniqueId()));
								String name = player.getName();
								balances.put(name, amount);
							}
					}
					List<Map.Entry<String, BigDecimal>> sortedEntries = new ArrayList<Map.Entry<String, BigDecimal>>(balances.entrySet());
					Collections.sort(sortedEntries, new Comparator<Map.Entry<String, BigDecimal>>() {
						@Override
						public int compare(Entry<String, BigDecimal> entry1, Entry<String, BigDecimal> entry2) {
							return ((BigDecimal)entry2.getValue()).compareTo((BigDecimal)entry1.getValue());
						}
					});
					sender.sendMessage(getConfig().getString("BountyListHeader").replace("&", "§").replace("{page}", args[1]));

					for (Map.Entry<String, BigDecimal> entry : sortedEntries) {
						lines.add(getConfig().getString("BountyListData").replace("&", "§").replace("%player%", entry.getKey()).replace("%amount%", "" + (BigDecimal)entry.getValue()));
					}
					for (int x = (10 * Integer.parseInt(args[1]) - 10); x <= (10 * Integer.parseInt(args[1]) -1); x++) {
						if(lines.size() <= x) {
							return true;
						}
						if(lines.get(x) != null)
							sender.sendMessage(lines.get(x).replace("%pos%", "" + x + 1));
					}
					return true;
				} else if(args[0].equalsIgnoreCase("set")) {
					if(!sender.hasPermission("bounty.command.set")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					sender.sendMessage("§cUsage: /bounty set <player> <amount>");
					return true;
				} else if(args[0].equalsIgnoreCase("clear")) {
					if(!sender.hasPermission("bounty.command.clear")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if(target == null) {
						sender.sendMessage(getConfig().getString("PlayerNotOnline").replace("&", "§"));
						return true;
					}
					for(Player player : Bukkit.getOnlinePlayers()) {
						player.sendMessage(getConfig().getString("PlayerBountyCleared").replace("&", "§").replace("%player%", sender.getName()).replace("%target%", target.getName()));
					}
					Stats.clearPlayerBounty(target.getUniqueId());
					return true;
				} else if(args[0].equalsIgnoreCase("get")) {
					if(!sender.hasPermission("bounty.command.get")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					@SuppressWarnings("deprecation")
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
					if(target == null) {
						sender.sendMessage(getConfig().getString("PlayerNotOnline").replace("&", "§"));
						return true;
					}
					sender.sendMessage(getConfig().getString("GetPlayerBounty").replace("&", "§").replace("%player%", target.getName()).replace("%bounty%", "" + Stats.getPlayerBounty(target.getUniqueId())));
					return true;
				} else {
					sender.sendMessage(getConfig().getString("UnknownCmd").replace("&", "§"));
					return true;
				}
			} else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("add")) {
					if(!sender.hasPermission("bounty.command.add")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if(target == null) {
						sender.sendMessage(getConfig().getString("PlayerNotOnline").replace("&", "§"));
						return true;
					}
					/*if(target.hasPermission("bounty.ignore")) {
						sender.sendMessage(getConfig().getString("CannotSetBountyPlayer").replace("&", "§"));
						return true;
					}*/
					if(sender instanceof Player) {
						if(econ.getBalance((Player)sender) < Double.parseDouble(args[2])) {
							sender.sendMessage(getConfig().getString("InvalidAmount").replace("&", "§"));
							return true;
						}
						if(Double.parseDouble(args[2]) < 500) {
							sender.sendMessage(getConfig().getString("NotEnoughBounty").replace("&", "§"));
							return true;
						}
						econ.withdrawPlayer((Player)sender, Double.parseDouble(args[2]));
					}
					Stats.addPlayerBounty(target.getUniqueId(), Double.parseDouble(args[2]));
					for(Player player : Bukkit.getOnlinePlayers()) {
						player.sendMessage(getConfig().getString("PlayerBountyAdded").replace("&", "§").replace("{prefix}", getConfig().getString("Prefix").replace("&", "§")).replace("{player}", sender.getName()).replace("{target}", target.getName()).replace("{amount}", "" + Math.round(Double.parseDouble(args[2]))).replace("{bounty}", "" + Math.round(Stats.getPlayerBounty(target.getUniqueId()))));
					}
					return true;
				} else if(args[0].equalsIgnoreCase("set")) {
					if(!sender.hasPermission("bounty.command.set")) {
						sender.sendMessage(getConfig().getString("NoPerm").replace("&", "§"));
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if(target == null) {
						sender.sendMessage(getConfig().getString("PlayerNotOnline").replace("&", "§"));
						return true;
					}
					Stats.setPlayerBounty(target.getUniqueId(), Double.parseDouble(args[2]));
					for(Player player : Bukkit.getOnlinePlayers()) {
						player.sendMessage(getConfig().getString("PlayerBountySet").replace("&", "§").replace("%player%", sender.getName()).replace("%target%", target.getName()).replace("%amount%", "" + Double.parseDouble(args[2])).replace("%bounty%", "" + Stats.getPlayerBounty(target.getUniqueId())));
					}
					return true;
				} else {
					sender.sendMessage(getConfig().getString("UnknownCmd").replace("&", "§"));
					return true;
				}
			}
		}
		return false;
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void onDeath(PlayerDeathEvent event) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				if(event.getEntity().getKiller() instanceof Player) {
					Player player = event.getEntity();
					Player target = event.getEntity().getKiller();
					
					if(Stats.getPlayerBounty(player.getUniqueId()) >= 500) {
						for(Player players : Bukkit.getOnlinePlayers()) {
							players.sendMessage(getConfig().getString("PlayerRetrievedBounty").replace("&", "§").replace("%player%", target.getName()).replace("%target%", player.getName()).replace("%bounty%", "" + Stats.getPlayerBounty(player.getUniqueId())).replace("%killerbounty%", "" + Stats.getPlayerBounty(target.getUniqueId())));
						}
						econ.depositPlayer(target, Stats.getPlayerBounty(player.getUniqueId()) - 100);
						Stats.setPlayerBounty(player.getUniqueId(), 0);
					}
					if(econ.getBalance(player) >= 500) {
						for(Player players : Bukkit.getOnlinePlayers()) {
							players.sendMessage(getConfig().getString("PlayerBountyChanged").replace("&", "§").replace("%player%", target.getName()).replace("%target%", player.getName()).replace("%bounty%", "" + (Stats.getPlayerBounty(player.getUniqueId()))).replace("%killerbounty%", "" + (Stats.getPlayerBounty(target.getUniqueId()) + 100)));
						}
						Stats.addPlayerBounty(target.getUniqueId(), 100);
						econ.withdrawPlayer(player, 100);
					}
				}
			}
		}, 3);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(Stats.getPlayerBounty(player.getUniqueId()) > 0) {
			for(Player players : Bukkit.getOnlinePlayers()) {
				players.sendMessage(getConfig().getString("PlayerJoinedWithBounty").replace("&", "§").replace("%player%", player.getName()).replace("%bounty%", "" + Stats.getPlayerBounty(player.getUniqueId())));
			}
		}
	}
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	public static class Stats {
		public static void createStatFile(UUID uuid) {
			File stats = new File(plugin.getDataFolder() + File.separator + "Players", uuid.toString() + ".yml");
			YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
			if (!stats.exists()) {
				try {
					stats.createNewFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
				SC.set("Bounty", 0);
				try {
					SC.save(stats);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		public static double getPlayerBounty(UUID uuid) {
			File stats = new File(plugin.getDataFolder() + File.separator + "Players", uuid.toString() + ".yml");
			YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
			return SC.getDouble("Bounty");
		}
		public static void setPlayerBounty(UUID uuid, double amount) {
			File stats = new File(plugin.getDataFolder() + File.separator + "Players", uuid.toString() + ".yml");
			YamlConfiguration SC = YamlConfiguration.loadConfiguration(stats);
			SC.set("Bounty", amount);
			try {
				SC.save(stats);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		public static void addPlayerBounty(UUID uuid, double amount) {
			setPlayerBounty(uuid, getPlayerBounty(uuid) + amount);
		}
		public static void clearPlayerBounty(UUID uuid) {
			setPlayerBounty(uuid, 0);
		}
	} 
}
