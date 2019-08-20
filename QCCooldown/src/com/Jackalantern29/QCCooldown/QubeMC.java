package com.Jackalantern29.QCCooldown;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class QubeMC extends JavaPlugin implements Listener {
	int MallDelay = 0;
	int MallRepeat = 0;
	int WildDelay = 0;
	int WildRepeat = 0;
	
	public void onEnable() {
		saveDefaultConfig();
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		Objective wildObj = scoreboard.getObjective("wildcooldown");
		if(scoreboard.getObjective("wildcooldown") == null) {
			scoreboard.registerNewObjective("wildcooldown", "dummy");
		}
		getServer().getPluginManager().registerEvents(this, this);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
					if(wildObj.getScore(player.getUniqueId().toString()) == null)
						wildObj.getScore(player.getUniqueId().toString()).setScore(0);
					if(wildObj.getScore(player.getUniqueId().toString()).getScore() != 0) {
						wildObj.getScore(player.getUniqueId().toString()).setScore(wildObj.getScore(player.getUniqueId().toString()).getScore() - 1);
					}
				}
			}
		}, 0, 20);
			
	}
	
	public void onDisable() {
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void precommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if (event.getMessage().equalsIgnoreCase("/mall")) {
			event.setCancelled(true);
			Location loc = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
			if (player.hasPermission("mallcooldown.bypass")) {
				player.performCommand("rspawn mall");
				player.sendMessage(getConfig().getString("Commencing").replace("&", "§"));
			} else {
				Bukkit.getScheduler().cancelTask(MallDelay);
				Bukkit.getScheduler().cancelTask(MallRepeat);

				player.sendMessage(getConfig().getString("Teleport").replace("&", "§"));
				player.sendMessage(getConfig().getString("PreTeleport").replace("&", "§"));

				MallDelay = Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						player.performCommand("rspawn mall");
						player.sendMessage(getConfig().getString("Commencing").replace("&", "§"));
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
			}
		} else if ((event.getMessage().equalsIgnoreCase("/wild"))
				|| (event.getMessage().equalsIgnoreCase("/rspawn wild"))) {
			event.setCancelled(true);
			Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
			Objective wildObj = scoreboard.getObjective("wildcooldown");
			if (wildObj.getScore(player.getUniqueId().toString()).getScore() != 0) {
				player.sendMessage(getConfig().getString("WildCooldownMessage").replace("&", "§").replace("%time%", "" + calculateTime(wildObj.getScore(player.getUniqueId().toString()).getScore())));
				return;
			}
			Location loc = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
			if (player.hasPermission("wildcooldown.cooldown")) {
				if(player.hasPermission("wildcooldown.bypass")) {
					player.performCommand("rspawn wild");
					player.sendMessage(getConfig().getString("WildCommencing").replace("&", "§"));
					wildObj.getScore(player.getUniqueId().toString()).setScore(getConfig().getInt("WildCooldown"));
				} else {
					Bukkit.getScheduler().cancelTask(WildDelay);
					Bukkit.getScheduler().cancelTask(WildRepeat);

					player.sendMessage(getConfig().getString("WildTeleport").replace("&", "§"));
					player.sendMessage(getConfig().getString("PreWildTeleport").replace("&", "§"));

					WildDelay = Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

						@Override
						public void run() {
							player.performCommand("rspawn wild");
							player.sendMessage(getConfig().getString("WildCommencing").replace("&", "§"));
							wildObj.getScore(player.getUniqueId().toString()).setScore(getConfig().getInt("WildCooldown"));
							Bukkit.getScheduler().cancelTask(WildDelay);
							Bukkit.getScheduler().cancelTask(WildRepeat);

						}
					}, 20 * 4);
					WildRepeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
						@Override
						public void run() {
							if ((loc.getBlockX() != player.getLocation().getBlockX()) || (loc.getBlockY() != player.getLocation().getBlockY())
									|| (loc.getBlockZ() != player.getLocation().getBlockZ())) {
								player.sendMessage(getConfig().getString("WildTeleportCancelled").replace("&", "§"));
								Bukkit.getScheduler().cancelTask(WildRepeat);
								Bukkit.getScheduler().cancelTask(WildDelay);
							}
						}
					}, 0, 1);
				}
			} else if (player.hasPermission("wildcooldown.cooldown.extend")) {
				if(player.hasPermission("wildcooldown.bypass")) {
					player.performCommand("rspawn wild");
					player.sendMessage(getConfig().getString("WildCommencing").replace("&", "§"));
					wildObj.getScore(player.getUniqueId().toString()).setScore(getConfig().getInt("WildCooldown"));
					} else {
					Bukkit.getScheduler().cancelTask(WildDelay);
					Bukkit.getScheduler().cancelTask(WildRepeat);

					player.sendMessage(getConfig().getString("WildTeleport").replace("&", "§"));
					player.sendMessage(getConfig().getString("PreWildTeleport").replace("&", "§"));

					WildDelay = Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

						@Override
						public void run() {
							player.performCommand("rspawn wild");
							player.sendMessage(getConfig().getString("WildCommencing").replace("&", "§"));
							wildObj.getScore(player.getUniqueId().toString()).setScore(getConfig().getInt("WildExtendedCooldown"));
							Bukkit.getScheduler().cancelTask(WildDelay);
							Bukkit.getScheduler().cancelTask(WildRepeat);

						}
					}, 20 * 5);
					WildRepeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
						@Override
						public void run() {
							if ((loc.getBlockX() != player.getLocation().getBlockX()) || (loc.getBlockY() != player.getLocation().getBlockY())
									|| (loc.getBlockZ() != player.getLocation().getBlockZ())) {
								player.sendMessage(getConfig().getString("WildTeleportCancelled").replace("&", "§"));
								Bukkit.getScheduler().cancelTask(WildRepeat);
								Bukkit.getScheduler().cancelTask(WildDelay);
							}
						}
					}, 0, 1);
				}
			}
		}
	}
	public static String calculateTime(long seconds) {
		int day = (int)TimeUnit.SECONDS.toDays(seconds);        
	    long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
		long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
		long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
			
		return (day + " days, " + hours + " hours, " + minute + " minutes, " + second + " seconds").replace("0 days, ", "").replace("0 hours, ", "").replace("0 minutes, ", "");

    }
}
