package com.Jackalantern29.QubeGems;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.Jackalantern29.QubeGems.Commands.CommandGiveQubeGems;
import com.Jackalantern29.QubeGems.Commands.CommandQubeGems;
import com.Jackalantern29.QubeGems.GUI.QubeGemsGUI;
import com.Jackalantern29.QubeGems.Listeners.PlayerJoinListener;
import com.Jackalantern29.QubeGems.Util.QubeGems;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

//import net.daboross.bukkitdev.skywars.SkyWarsPlugin;

public class QubeMC extends JavaPlugin implements PluginMessageListener {
	//public static SkyWarsPlugin skywars = null;
	public static int amount = 0;
	public void onEnable() {
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	    
	    File data = new File("../QubeMC - Data");
		if (!data.exists()) {
			try {
				getLogger().info("QubeMC Data folder created!");
				data.mkdir();
			} catch (SecurityException e) {
				getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File reward = new File("../QubeMC - Data/QubeGems");
		if (!reward.exists()) {
			try {
				getLogger().info("QubeMC Data folder created!");
				reward.mkdir();
			} catch (SecurityException e) {
				getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File playerfile = new File("../QubeMC - Data/QubeGems/Players");
		if (!playerfile.exists()) {
			try {
				getLogger().info("Players folder created!");
				playerfile.mkdir();
			} catch (SecurityException e) {
				getLogger().info("Players folder can not created!");
				e.printStackTrace();
			}
		}
		QubeGems.createConfig();
		if(!QubeGems.getConfig().contains("Server." + Bukkit.getServerName().replace("QubeMC - ", "").replace(" ", "") + ".Amount")) {
			QubeGems.getConfig().set("Server." + Bukkit.getServerName().replace("QubeMC - ", "").replace(" ", "") + ".Amount", 0);
			try {
				QubeGems.getConfig().save(QubeGems.getConfigFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		amount = QubeGems.getConfig().getInt("Server." + Bukkit.getServerName().replace("QubeMC - ", "").replace(" ", "") + ".Amount");
//		if (getServer().getPluginManager().getPlugin("SkyWars") != null) {
//			skywars = (SkyWarsPlugin)getServer().getPluginManager().getPlugin("SkyWars");
//			Bukkit.getPlayer("Jackalantern29").sendMessage("SkyWars is not null");
//		}
		getCommand("qubegems").setExecutor(new CommandQubeGems());
		getCommand("givequbegems").setExecutor(new CommandGiveQubeGems());
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new QubeGemsGUI(), this);
//		if(skywars != null) {
//			pm.registerEvents(new SkyWarsGameEndListener(), this);
//			Bukkit.getPlayer("Jackalantern29").sendMessage("SkyWarsListener enabled.");
//		}
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			QubeGems.createProfile(player.getUniqueId());
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers()) {
					QubeGems.addGems(player.getUniqueId(), amount);
				}
			}
		}, 0L, 20 * 600);
		
//	    Player player = Bukkit.getPlayer("Jackalantern29");
//		ByteArrayDataOutput out = ByteStreams.newDataOutput();
//		out.writeUTF("Connect");
//		out.writeUTF("Factions");
//		player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
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
	    }
	}
}
