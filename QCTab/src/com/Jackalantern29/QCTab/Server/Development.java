package com.Jackalantern29.QCTab.Server;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.Jackalantern29.QCRewards.QubeCraft;
import com.Jackalantern29.QCTab.QubeMC;

public class Development {
	public static HashMap<UUID, String> hm1 = new HashMap<UUID, String>();
	public static HashMap<UUID, String> hm2 = new HashMap<UUID, String>();
	public static HashMap<UUID, String> hm3 = new HashMap<UUID, String>();
	public static HashMap<UUID, String> hm4 = new HashMap<UUID, String>();
	
	static HashMap<UUID, String> hmt = new HashMap<>();
	public static void loadDevelopmentScoreboard(Player player) {

		Scoreboard fakescoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = fakescoreboard.registerNewObjective("Scoreboard", "bbb");
		if(hm1.containsKey(player.getUniqueId())) {
			hm1.remove(player.getUniqueId());
		}
		if(hm2.containsKey(player.getUniqueId())) {
			hm2.remove(player.getUniqueId());
		}
		if(hm3.containsKey(player.getUniqueId())) {
			hm3.remove(player.getUniqueId());
		}
		if(hm4.containsKey(player.getUniqueId())) {
			hm4.remove(player.getUniqueId());
		}
    	//Scroller TGScoreboardDisplay = new Scroller("          §f§lWelcome §e§l" + player.getName() + " §f§lto TitanGaming!                    Visit §b§lhttp://TitanGaming-Mc.enjin.com §f§lfor §e§lnews §f§land §e§lupdates!          ", 22, 1, '&');
//    	Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanGaming.getInstance(), new Runnable() {
//    		public final void run() {
//    			obj.setDisplayName(TGScoreboardDisplay.next());
//    		}
//    	}
//    	, 0L, 2L);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
    				//PlayerData pData = new PlayerData(p);
    				//String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    				//String letter = abc.substring(pData.getGroup().getTabPriority(), pData.getGroup().getTabPriority() + 1);
    				Team playerTeam = null;
    				if(fakescoreboard.getTeam(player.getName()) == null) {
    					playerTeam = fakescoreboard.registerNewTeam(player.getName());
    				} else {
    					playerTeam = fakescoreboard.getTeam(player.getName());

    				playerTeam.addEntry(p.getName());
    				if(!QubeMC.ess.getUser(p.getUniqueId()).isAfk())
    					playerTeam.setPrefix("§7");
    				else
    					playerTeam.setPrefix("§7[AFK] " + "§7");
    				if(p.getName().equals(player.getName()))
    					playerTeam.setSuffix(" §7[YOU]");
    				playerTeam.setAllowFriendlyFire(true);
    				playerTeam.setCanSeeFriendlyInvisibles(false);
    				}
    			}
			}
		}, 0, 20 * 3);
    	Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
    		public final void run() {
    			if(player.getScoreboard() == fakescoreboard) {
    				obj.setDisplayName("§f§lDevelopment §7(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
    				String usernames = "§f" + player.getDisplayName();
    				String locations = "§f" + player.getWorld().getName() + "/" + player.getLocation().getBlockX() + "/" + player.getLocation().getBlockY() + "/" + player.getLocation().getBlockZ();
        			String gamemodes = "";
        			if(player.getGameMode().equals(GameMode.ADVENTURE)) {
        				gamemodes = "§4" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
        			if(player.getGameMode().equals(GameMode.CREATIVE)) {
        				gamemodes = "§e" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
        			if(player.getGameMode().equals(GameMode.SPECTATOR)) {
        				gamemodes = "§7" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
        			if(player.getGameMode().equals(GameMode.SURVIVAL)) {
        				gamemodes = "§c" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
    				String coinss = "§a" + NumberFormat.getInstance().format(QubeCraft.Stats.getCoins(player.getUniqueId())) + " QC";
    						
    				Score username = obj.getScore(usernames);
        			Score location = obj.getScore(locations);
        			Score gamemode = obj.getScore(gamemodes);
        			Score coins = obj.getScore(coinss);
        			Score website = obj.getScore("§f§lWebsite§7§l:");
        			Score websiteurl = obj.getScore("§6qubemc.net");
        			if(!hm1.containsKey(player.getUniqueId())) {
        				hm1.put(player.getUniqueId(), usernames);
            			username.getScoreboard().resetScores(hm1.get(player.getUniqueId()));
            			username = obj.getScore(hm1.get(player.getUniqueId()));
            			username.setScore(13);
        			}
        			if(!hm2.containsKey(player.getUniqueId())) {
        				hm2.put(player.getUniqueId(), locations);
            			location.getScoreboard().resetScores(hm2.get(player.getUniqueId()));
            			location = obj.getScore(hm2.get(player.getUniqueId()));
            			location.setScore(10); 
        			}
        			if(!hm3.containsKey(player.getUniqueId())) {
        				hm3.put(player.getUniqueId(), gamemodes);
            			gamemode.getScoreboard().resetScores(hm3.get(player.getUniqueId()));
            			gamemode = obj.getScore(hm3.get(player.getUniqueId()));
            			gamemode.setScore(7);
        			}
        			if(!hm4.containsKey(player.getUniqueId())) {
        				hm4.put(player.getUniqueId(), coinss);
            			coins.getScoreboard().resetScores(hm4.get(player.getUniqueId()));
            			coins = obj.getScore(hm4.get(player.getUniqueId()));
            			coins.setScore(4);
        			}
        			
        			if(hm1.containsKey(player.getUniqueId())) {
        				if(!hm1.get(player.getUniqueId()).equals(usernames)) {
        					username.getScoreboard().resetScores(hm1.get(player.getUniqueId()));
                			hm1.replace(player.getUniqueId(), usernames);
                			username = obj.getScore(hm1.get(player.getUniqueId()));
                			username.setScore(13);	
        				}
        			}
        			if(hm2.containsKey(player.getUniqueId())) {
        				if(!hm2.get(player.getUniqueId()).equals(locations)) {
        					location.getScoreboard().resetScores(hm2.get(player.getUniqueId()));
                			hm2.replace(player.getUniqueId(), locations);
                			location = obj.getScore(hm2.get(player.getUniqueId()));
                			location.setScore(10); 
        				} 
        			}
        			if(hm3.containsKey(player.getUniqueId())) {
        				if(!hm3.get(player.getUniqueId()).equals(gamemodes)) {
        					gamemode.getScoreboard().resetScores(hm3.get(player.getUniqueId()));
                			hm3.replace(player.getUniqueId(), gamemodes);
                			gamemode = obj.getScore(hm3.get(player.getUniqueId()));
                			gamemode.setScore(7);
        				}
        			}
        			if(hm4.containsKey(player.getUniqueId())) {
        				if(!hm4.get(player.getUniqueId()).equals(coinss)) {
        					coins.getScoreboard().resetScores(hm4.get(player.getUniqueId()));
                			hm4.replace(player.getUniqueId(), coinss);
                			coins = obj.getScore(hm4.get(player.getUniqueId()));
                			coins.setScore(4);	
        				}
        			}
//        			if(!TGPlayer.getSettings().isPvPEnabled())
//        				name = obj.getScore("§a§l" + p.getName() + "§7§l:");
//        			else
//        				name = obj.getScore("§c§l" + p.getName() + "§7§l:");
        			
        			obj.getScore("§b§lUsername").setScore(14);;
        			obj.getScore(" ").setScore(12);
        			obj.getScore("§b§lLocation:").setScore(11);;
        			obj.getScore("  ").setScore(9);
        			obj.getScore("§b§lGameMode:").setScore(8);;
        			obj.getScore("   ").setScore(6);
        			obj.getScore("§b§lQubeCoins:").setScore(5);;
        			obj.getScore("    ").setScore(3);
        			website.setScore(2);
        			websiteurl.setScore(1);
        			
        			usernames = "§f" + player.getDisplayName();
    				locations = "§f" + player.getWorld().getName() + "/" + player.getLocation().getBlockX() + "/" + player.getLocation().getBlockY() + "/" + player.getLocation().getBlockZ();
        			if(player.getGameMode().equals(GameMode.ADVENTURE)) {
        				gamemodes = "§4" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
        			if(player.getGameMode().equals(GameMode.CREATIVE)) {
        				gamemodes = "§e" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
        			if(player.getGameMode().equals(GameMode.SPECTATOR)) {
        				gamemodes = "§7" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
        			if(player.getGameMode().equals(GameMode.SURVIVAL)) {
        				gamemodes = "§c" + StringUtils.capitalize(player.getGameMode().name().toLowerCase());
        			}
    				coinss = "§a" + NumberFormat.getInstance().format(QubeCraft.Stats.getCoins(player.getUniqueId())) + " QC";
        		}
    		}
    	}, 0, 1L);
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		player.setScoreboard(fakescoreboard);
	}
}
