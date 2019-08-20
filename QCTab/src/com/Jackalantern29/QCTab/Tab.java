package com.Jackalantern29.QCTab;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Tab {
	
	
	
	
	
	
	public static void loadTabss(Player player) {
		Scoreboard fakescoreboard = player.getScoreboard();
		//PlayerData playerData = new PlayerData(player);
		for(Player p : Bukkit.getOnlinePlayers()) {
			PlayerData pData = new PlayerData(p);
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			String letter = abc.substring(pData.getGroup().getTabPriority(), pData.getGroup().getTabPriority() + 1);
			Team playerTeam = null;
			if(fakescoreboard.getTeam(letter + pData.getGroup().getName()) == null) {
				playerTeam = fakescoreboard.registerNewTeam(letter + pData.getGroup().getName());
			} else {
				playerTeam = fakescoreboard.getTeam(letter + pData.getGroup().getName());

			playerTeam.addEntry(p.getName());
			playerTeam.setPrefix(pData.getGroup().getTabPrefix().replace("&", "§"));
			playerTeam.setAllowFriendlyFire(true);
			playerTeam.setCanSeeFriendlyInvisibles(false);
			}
		}
		player.setScoreboard(fakescoreboard);
	}
	public static int pingPlayer(Player who) {
        try {
            // Building the version of the server in such a form we can use it
            // in NMS code.
            String bukkitversion = Bukkit.getServer().getClass().getPackage()
                    .getName().substring(23);
            // Getting craftplayer
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
                    + bukkitversion + ".entity.CraftPlayer");
            // Invoking method getHandle() for the player
            Object handle = craftPlayer.getMethod("getHandle").invoke(who);
            // Getting field "ping" that holds player's ping obviously
            Integer ping = (Integer) handle.getClass().getDeclaredField("ping")
                    .get(handle);
            // Returning the ping
            return ping.intValue();
        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            // Handle exceptions however you like, i chose to return value of
            // -1; since player's ping can't be -1.
            return -1;
        }
    }
}
