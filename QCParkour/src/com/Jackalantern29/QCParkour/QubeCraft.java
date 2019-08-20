package com.Jackalantern29.QCParkour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import com.Jackalantern29.QCParkour.Commands.CommandParkour;
import com.Jackalantern29.QCParkour.Commands.CommandSetparkour;
import com.Jackalantern29.QCParkour.Listeners.PlayerFellListener;

public class QubeCraft extends JavaPlugin {
	private static QubeCraft plugin;
	private static Location parkourLobby;
	private static Location parkourEasy;
	private static Location parkourMedium;
	private static Location parkourHard;
	private static Location parkourMediumCheck1;
	private static Location parkourHardCheck1;
	private static Location parkourHardCheck2;
	private static List<Player> parkourplayers = new ArrayList<Player>();
	private static HashMap<Player, ParkourStage> stage = new HashMap<Player, ParkourStage>();
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		getCommand("parkour").setExecutor(new CommandParkour());
		getCommand("setparkour").setExecutor(new CommandSetparkour());
		getServer().getPluginManager().registerEvents(new PlayerFellListener(), this);
		parkourLobby = new Location(Bukkit.getWorld(getConfig().getString("Lobby.World")), getConfig().getDouble("Lobby.X"), getConfig().getDouble("Lobby.Y"), getConfig().getDouble("Lobby.Z"), Float.valueOf(getConfig().getString("Lobby.Yaw")), Float.valueOf(getConfig().getString("Lobby.Pitch")));
		parkourEasy = new Location(Bukkit.getWorld(getConfig().getString("Easy.World")), getConfig().getDouble("Easy.X"), getConfig().getDouble("Easy.Y"), getConfig().getDouble("Easy.Z"), Float.valueOf(getConfig().getString("Easy.Yaw")), Float.valueOf(getConfig().getString("Easy.Pitch")));
		parkourMedium = new Location(Bukkit.getWorld(getConfig().getString("Medium.World")), getConfig().getDouble("Medium.X"), getConfig().getDouble("Medium.Y"), getConfig().getDouble("Medium.Z"), Float.valueOf(getConfig().getString("Medium.Yaw")), Float.valueOf(getConfig().getString("Medium.Pitch")));
		parkourHard = new Location(Bukkit.getWorld(getConfig().getString("Hard.World")), getConfig().getDouble("Hard.X"), getConfig().getDouble("Hard.Y"), getConfig().getDouble("Hard.Z"), Float.valueOf(getConfig().getString("Hard.Yaw")), Float.valueOf(getConfig().getString("Hard.Pitch")));
		
		parkourMediumCheck1 = new Location(Bukkit.getWorld(getConfig().getString("Medium.Checkpoint1.World")), getConfig().getDouble("Medium.Checkpoint1.X"), getConfig().getDouble("Medium.Checkpoint1.Y"), getConfig().getDouble("Medium.Checkpoint1.Z"), Float.valueOf(getConfig().getString("Medium.Checkpoint1.Yaw")), Float.valueOf(getConfig().getString("Medium.Checkpoint1.Pitch")));
		parkourHardCheck1 = new Location(Bukkit.getWorld(getConfig().getString("Hard.Checkpoint1.World")), getConfig().getDouble("Hard.Checkpoint1.X"), getConfig().getDouble("Hard.Checkpoint1.Y"), getConfig().getDouble("Hard.Checkpoint1.Z"), Float.valueOf(getConfig().getString("Hard.Checkpoint1.Yaw")), Float.valueOf(getConfig().getString("Hard.Checkpoint1.Pitch")));
		parkourHardCheck2 = new Location(Bukkit.getWorld(getConfig().getString("Hard.Checkpoint2.World")), getConfig().getDouble("Hard.Checkpoint2.X"), getConfig().getDouble("Hard.Checkpoint2.Y"), getConfig().getDouble("Hard.Checkpoint2.Z"), Float.valueOf(getConfig().getString("Hard.Checkpoint2.Yaw")), Float.valueOf(getConfig().getString("Hard.Checkpoint2.Pitch")));

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : QubeCraft.getParkourPlayers()) {
					player.removePotionEffect(PotionEffectType.SPEED);
					player.removePotionEffect(PotionEffectType.JUMP);
				}
			}
		}, 0, 1);
	}
	public void onDisable() {
		
	}
	
	public static QubeCraft getInstance() {
		return plugin;
	}
	
	public static Location getParkourLobby() {
		return parkourLobby;
	}
	public static Location getParkourEasy() {
		return parkourEasy;
	}
	public static Location getParkourMedium() {
		return parkourMedium;
	}
	public static Location getParkourHard() {
		return parkourHard;
	}
	public static Location getParkourMediumCheckpoint1() {
		return parkourMediumCheck1;
	}
	public static Location getParkourHardCheckpoint1() {
		return parkourHardCheck1;
	}
	public static Location getParkourHardCheckpoint2() {
		return parkourHardCheck2;
	}
	public static List<Player> getParkourPlayers() {
		return parkourplayers;
	}
	public static String getPlayerDifficulty(Player player) {
		return stage.get(player).getDifficulty();
	}
	public static int getPlayerCheckpoint(Player player) {
		return stage.get(player).getCheckpoint();
	}
	public static void setPlayerParkour(Player player, String difficulty, int checkpoint) {
		stage.remove(player);
		stage.put(player, new ParkourStage(difficulty, checkpoint));
	}
	public static void removePlayerParkour(Player player) {
		stage.remove(player);
	}
	public static void setParkourLobby(Location location) {
		parkourLobby = location;
		getInstance().getConfig().set("Lobby.World", location.getWorld().getName());
		getInstance().getConfig().set("Lobby.X", location.getX());
		getInstance().getConfig().set("Lobby.Y", location.getY());
		getInstance().getConfig().set("Lobby.Z", location.getZ());
		getInstance().getConfig().set("Lobby.Yaw", location.getYaw());
		getInstance().getConfig().set("Lobby.Pitch", location.getPitch());
		getInstance().saveConfig();
	}
	public static void setParkourEasy(Location location) {
		parkourEasy = location;
		getInstance().getConfig().set("Easy.World", location.getWorld().getName());
		getInstance().getConfig().set("Easy.X", location.getX());
		getInstance().getConfig().set("Easy.Y", location.getY());
		getInstance().getConfig().set("Easy.Z", location.getZ());
		getInstance().getConfig().set("Easy.Yaw", location.getYaw());
		getInstance().getConfig().set("Easy.Pitch", location.getPitch());
		getInstance().saveConfig();
	}	
	public static void setParkourMedium(Location location) {
		parkourMedium = location;
		getInstance().getConfig().set("Medium.World", location.getWorld().getName());
		getInstance().getConfig().set("Medium.X", location.getX());
		getInstance().getConfig().set("Medium.Y", location.getY());
		getInstance().getConfig().set("Medium.Z", location.getZ());
		getInstance().getConfig().set("Medium.Yaw", location.getYaw());
		getInstance().getConfig().set("Medium.Pitch", location.getPitch());
		getInstance().saveConfig();
	}
	public static void setParkourHard(Location location) {
		parkourHard = location;
		getInstance().getConfig().set("Hard.World", location.getWorld().getName());
		getInstance().getConfig().set("Hard.X", location.getX());
		getInstance().getConfig().set("Hard.Y", location.getY());
		getInstance().getConfig().set("Hard.Z", location.getZ());
		getInstance().getConfig().set("Hard.Yaw", location.getYaw());
		getInstance().getConfig().set("Hard.Pitch", location.getPitch());
		getInstance().saveConfig();
	}
	public static void setParkourMediumCheck1(Location location) {
		parkourMediumCheck1 = location;
		getInstance().getConfig().set("Medium.Checkpoint1.World", location.getWorld().getName());
		getInstance().getConfig().set("Medium.Checkpoint1.X", location.getX());
		getInstance().getConfig().set("Medium.Checkpoint1.Y", location.getY());
		getInstance().getConfig().set("Medium.Checkpoint1.Z", location.getZ());
		getInstance().getConfig().set("Medium.Checkpoint1.Yaw", location.getYaw());
		getInstance().getConfig().set("Medium.Checkpoint1.Pitch", location.getPitch());
		getInstance().saveConfig();
	}
	public static void setParkourHardCheck1(Location location) {
		parkourHardCheck1 = location;
		getInstance().getConfig().set("Hard.Checkpoint1.World", location.getWorld().getName());
		getInstance().getConfig().set("Hard.Checkpoint1.X", location.getX());
		getInstance().getConfig().set("Hard.Checkpoint1.Y", location.getY());
		getInstance().getConfig().set("Hard.Checkpoint1.Z", location.getZ());
		getInstance().getConfig().set("Hard.Checkpoint1.Yaw", location.getYaw());
		getInstance().getConfig().set("Hard.Checkpoint1.Pitch", location.getPitch());
		getInstance().saveConfig();
	}
	public static void setParkourHardCheck2(Location location) {
		parkourHardCheck1 = location;
		getInstance().getConfig().set("Hard.Checkpoint2.World", location.getWorld().getName());
		getInstance().getConfig().set("Hard.Checkpoint2.X", location.getX());
		getInstance().getConfig().set("Hard.Checkpoint2.Y", location.getY());
		getInstance().getConfig().set("Hard.Checkpoint2.Z", location.getZ());
		getInstance().getConfig().set("Hard.Checkpoint2.Yaw", location.getYaw());
		getInstance().getConfig().set("Hard.Checkpoint2.Pitch", location.getPitch());
		getInstance().saveConfig();
	}
}