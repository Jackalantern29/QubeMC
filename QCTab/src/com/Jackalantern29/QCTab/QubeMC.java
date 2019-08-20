package com.Jackalantern29.QCTab;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.Jackalantern29.QCTab.Listeners.PlayerJoinListener;
import com.Jackalantern29.QCTab.Packet.TabHeaderFooter;
import com.Jackalantern29.QCTab.Packet.TabHeaderFooter_1_10_R1;
import com.Jackalantern29.QCTab.Packet.TabHeaderFooter_1_8_R1;
import com.Jackalantern29.QCTab.Packet.TabHeaderFooter_1_9_R2;
import com.Jackalantern29.QCTab.Server.Development;
import com.Jackalantern29.QCTab.Server.GTA;
import com.Jackalantern29.QCTab.Server.Lobby;
import com.Jackalantern29.QCTab.Server.Skyblock;
import com.Jackalantern29.QCTab.Server.Towny;
import com.earth2me.essentials.Essentials;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.massivecraft.factions.Factions;

import net.milkbowl.vault.economy.Economy;

public class QubeMC extends JavaPlugin implements PluginMessageListener {
	public static YamlConfiguration config;
	private static QubeMC plugin;
	Player pp;
	public static int count = 0;
	public static int skyblockc = 0;
	public static int gtac = 0;
	public static int factionsc = 0;
	public static int townyc = 0;
	public static int creativec = 0;
	static boolean status = false;
	static Factions factions;
	public static Economy econ;
	public static Essentials ess;
	private TabHeaderFooter tabheaderfooter;
	public void onEnable() {

		String version = Bukkit.getServer().getClass().getPackage()
                .getName().substring(23);
		if(version.equals("v1_8_R1")) {
			tabheaderfooter = new TabHeaderFooter_1_8_R1();
		} else if(version.equals("v1_9_R2")) {
			tabheaderfooter = new TabHeaderFooter_1_9_R2();
		} else if(version.equals("v1_10_R1")) {
			tabheaderfooter = new TabHeaderFooter_1_10_R1();
		} else {
			Bukkit.getConsoleSender().sendMessage("Server is running 1.8 or 1.10. Plugin will now disable.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		Plugin p = getServer().getPluginManager().getPlugin("Factions");
	    if ((p instanceof Factions)) {
	      factions = ((Factions)p);
	    }
	    p = getServer().getPluginManager().getPlugin("Essentials");
	    if ((p instanceof Essentials)) {
	      ess = ((Essentials)p);
	    }
	    if (!setupEconomy()) {
	    	econ = null;
		}
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		getCommand("tab").setExecutor(new CommandTab());
		plugin = this;
		File data = new File("../QubeMC - Data/Tab");
		data.mkdir();
		File players = new File("../QubeMC - Data/Tab/Players");
		players.mkdir();
		File groups = new File("../QubeMC - Data/Tab/Groups");
		groups.mkdir();
		File configFile = new File(data, "config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!config.contains("Server." + Bukkit.getServerName().replace("QubeMC - ", ""))) {
			config.set("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".TabHeader", "This is a header");
			config.set("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".TabFooter", "This is a footer");
			config.set("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".DefaultRank", "Guest");
			try {
				config.save(configFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(Player player : Bukkit.getOnlinePlayers()) {
			//PlayerData pData = new PlayerData(player);
			//pData.createProfile();
			Scoreboard scoreboard = player.getScoreboard();
			for(Team teams : scoreboard.getTeams()) {
				teams.unregister();
			}
		}
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(Bukkit.getServerName().replace("QubeMC - ", "").equals("Factions"))
				com.Jackalantern29.QCTab.Server.Factions.loadFactionScoreboard(player);
			if(Bukkit.getServerName().replace("QubeMC - ", "").equals("Skyblock"))
				Skyblock.loadSkyblockScoreboard(player);
			if(Bukkit.getServerName().replace("QubeMC - ", "").equals("GTA"))
				GTA.loadGTAScoreboard(player);
			if(Bukkit.getServerName().replace("QubeMC - ", "").equals("Towny"))
				Towny.loadTownyScoreboard(player);
			if(Bukkit.getServerName().replace("QubeMC - ", "").equals("Lobby"))
				Lobby.loadLobbyScoreboard(player);
			if(Bukkit.getServerName().replace("QubeMC - ", "").equals("Development"))
				Development.loadDevelopmentScoreboard(player);
		}
//		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
//			
//			@Override
//			public void run() {
//				for(Player player : Bukkit.getOnlinePlayers()) {
//					//Tab.loadTab(player);
//				}
//			}
//		}, 0, 40);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("PlayerCount");
					out.writeUTF("ALL");
					
					p.sendPluginMessage(QubeMC.this, "BungeeCord", out.toByteArray());
					pp = p;
					if(status) {
						getTabHeaderFooter().sendTabHeaderFooter(p, format(config.getString("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".TabHeader1")), format(config.getString("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".TabFooter1")));
						status = false;
					} else {
						getTabHeaderFooter().sendTabHeaderFooter(p, format(config.getString("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".TabHeader2")), format(config.getString("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".TabFooter2")));
						status = true;
					}
				}
			}
		}, 0, 10);
	}
	
	public String format(String message) {
		return message.replace("&", "§")
				.replace("{playercount}", Bukkit.getOnlinePlayers().size() + "")
				.replace("{bungeecount}", count + "")
				.replace("{ping}", Tab.pingPlayer(pp) + "");
	}

	public void onDisable() {
		
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
	    }
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("PlayerCount")) {
			String server = in.readUTF(); // Name of server, as given in the arguments
	    	int playercount = in.readInt();
	    	if(server.equals("ALL"))
	    		count = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    	if(server.equals("Factions"))
	    		factionsc = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    	if(server.equals("GTA"))
	    		gtac = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    	if(server.equals("Skyblock"))
	    		skyblockc = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    	if(server.equals("Creative"))
	    		creativec = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    	if(server.equals("Towny"))
	    		townyc = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    }
	}
	
	public static QubeMC getInstance() {
		return plugin;
	}
	public TabHeaderFooter getTabHeaderFooter() {
	    return tabheaderfooter;
	}
	public static Factions getFactions() {
		return factions;
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
}
