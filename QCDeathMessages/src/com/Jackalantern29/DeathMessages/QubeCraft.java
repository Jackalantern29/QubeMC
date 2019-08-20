package com.Jackalantern29.DeathMessages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeCraft extends JavaPlugin implements Listener {
	public static QubeCraft plugin;
	String prefix = getConfig().getString("Prefix").replace("&", "§");

	public void onEnable() {
		getCommand("deathmessage").setExecutor(new CommandDeathMessage());
		saveDefaultConfig();
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		if (getConfig().getString("Prefix").equals("none")) {
			prefix = "";
		}
	}

	public void onDisable() {

	}

	public static QubeCraft getInstance() {
		return plugin;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		final String message = event.getDeathMessage();
		event.setDeathMessage(null);
		if(!event.getEntity().getLocation().getWorld().getName().equals("world"))
			return;
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Stats.getShowDeathMessages(player)) {
				player.sendMessage(prefix + message);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Stats.createStatFile(player);
	}
}