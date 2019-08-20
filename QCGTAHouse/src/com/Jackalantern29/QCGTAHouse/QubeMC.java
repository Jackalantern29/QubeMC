package com.Jackalantern29.QCGTAHouse;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jackalantern29.QCGTAHouse.Listener.PlayerListeners;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class QubeMC extends JavaPlugin {
	public static Economy econ;
	public final Logger logger = Logger.getLogger("Minecraft");
	protected WorldGuardPlugin wgPlugin;
	protected static QubeMC plugin;
	public void onEnable() {
		plugin = this;
		if (!setupEconomy()) {
			logger.severe(
					String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
	    Plugin plug = getServer().getPluginManager().getPlugin("WorldGuard");

	    if ((plug == null) || (!(plug instanceof WorldGuardPlugin)) || (!plug.isEnabled())) {
	      getLogger().warning("Could not load WorldGuard Plugin, disabling");
	      getServer().getPluginManager().disablePlugin(this);
	      return;
	    }

	    this.wgPlugin = ((WorldGuardPlugin)plug);
	    
	    PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(new PlayerListeners(), this);
	}
	
	public void onDisable() {
		
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
	
	public static QubeMC getInstance() {
		return plugin;
	}
	
	public WorldGuardPlugin getWorldGuard() {
		return wgPlugin;
	}

}
