package com.Jackalantern29.QCRewards;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jackalantern29.QCRewards.Listeners.Listeners;
import com.gmail.nossr50.mcMMO;

public class QubeMC extends JavaPlugin implements Listener {
	public static QubeMC plugin;
	public static Economy econ;
	public final Logger logger = Logger.getLogger("Minecraft");
	public MySQL mysql;
	public mcMMO mcmmo;
	private static Connection connection;
	public static Map<UUID, Player> timer = new HashMap<UUID, Player>();
	int id = 0;
	public void onEnable() {
		if (!setupEconomy()) {
			logger.severe(
					String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		plugin = this;
		saveDefaultConfig();
		for(Listener listen : Listeners.getListeners())
			getServer().getPluginManager().registerEvents(listen, this);
		getCommand("qubecoins").setExecutor(new CommandQubeCoins());
		getCommand("givequbecoins").setExecutor(new CommandQubeCoins());
		getCommand("qubecoinstop").setExecutor(new CommandQubeCoins());

		if((mcMMO) getServer().getPluginManager().getPlugin("mcMMO") != null)
			mcmmo = (mcMMO) getServer().getPluginManager().getPlugin("mcMMO");
		if(isUsingMySQL())
			ConnectMySQL();
		
		File playerfile = new File(getDataFolder() + File.separator + "Players");
		if (!playerfile.exists()) {
			try {
				getLogger().info("Players folder created!");
				playerfile.mkdir();
			} catch (SecurityException e) {
				getLogger().info("Players folder can not created!");
				e.printStackTrace();
			}
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(!mysql.hasConnection()) {
					ConnectMySQL();
					return;
				}
				for(Player player : Bukkit.getOnlinePlayers()) {
						Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId()) + 10);
				}
				/*for (Player player : Bukkit.getOnlinePlayers()) {
					if(Stats.getStatus(player.getUniqueId()).equals("true")) {
						Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId()) + 10);
						Stats.setStatus(player.getUniqueId(), "false");
						Stats.setOnlineTime(player.getUniqueId(), QubeMC.this.getConfig().getInt("OnlineTime"));
					} else {
						Stats.setOnlineTime(player.getUniqueId(), Stats.getOnlineTime(player.getUniqueId()) - 1);
						if(Stats.getOnlineTime(player.getUniqueId()) == 0) {
							Stats.setStatus(player.getUniqueId(), "true");
						}
					}
				} */
			}
		}, 0L, 20 * QubeMC.plugin.getConfig().getInt("OnlineTime"));
	}

	public void onDisable() {
		try {
			if(connection != null && connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean isUsingMySQL() {
		return getConfig().getBoolean("MySQL.UseMySQL");
	}

	public void ConnectMySQL() {
		mysql = new MySQL(getConfig().getString("MySQL.Host"), getConfig().getString("MySQL.Database"),
				getConfig().getString("MySQL.User"), getConfig().getString("MySQL.Password"));
		//mysql.update("CREATE TABLE IF NOT EXISTS Coins (UUID varchar(64),COINS int)");
		mysql.update("CREATE TABLE IF NOT EXISTS Coins (`ID` INT NOT NULL AUTO_INCREMENT ,`UUID` VARCHAR( 64 ) NOT NULL ,`COINS` INT NOT NULL ,`LASTUSER` VARCHAR( 16 ) NOT NULL ,`STATUS` VARCHAR(5) NOT NULL DEFAULT 'true',`ONLINETIME` INT NOT NULL DEFAULT '0', PRIMARY KEY (  `ID` ))");
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

	public MySQL getMySQL(){
		return mysql;
	}
	
}