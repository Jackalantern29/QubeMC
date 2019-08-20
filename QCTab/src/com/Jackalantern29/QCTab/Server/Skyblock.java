package com.Jackalantern29.QCTab.Server;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.Jackalantern29.QCRewards.QubeCraft;
import com.Jackalantern29.QCTab.PlayerData;
import com.Jackalantern29.QCTab.QubeMC;
import com.wasteofplastic.askyblock.ASkyBlockAPI;

public class Skyblock {
	static HashMap<UUID, String> hm1 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm2 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm3 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm4 = new HashMap<UUID, String>();	
	public static void loadSkyblockScoreboard(Player player) {
		Scoreboard fakescoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = fakescoreboard.registerNewObjective("Scoreboard", "bbb");

		Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				obj.setDisplayName("§b§lSkyblock §b(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
			}
		}, 0, 2);
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
    				PlayerData pData = new PlayerData(p);
    				String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    				String letter = abc.substring(pData.getGroup().getTabPriority(), pData.getGroup().getTabPriority() + 1);
    				Team playerTeam = null;
    				if(fakescoreboard.getTeam(letter + pData.getGroup().getName() + p.getUniqueId().toString().substring(0, 4)) == null) {
    					playerTeam = fakescoreboard.registerNewTeam(letter + pData.getGroup().getName() + p.getUniqueId().toString().substring(0, 4));
    				} else {
    					playerTeam = fakescoreboard.getTeam(letter + pData.getGroup().getName() + p.getUniqueId().toString().substring(0, 4));

    				playerTeam.addEntry(p.getName());
    				if(!QubeMC.ess.getUser(p.getUniqueId()).isAfk())
    					playerTeam.setPrefix(pData.getGroup().getTabPrefix().replace("&", "§"));
    				else
    					playerTeam.setPrefix("§7[AFK] " + pData.getGroup().getTabPrefix().replace("&", "§"));
    				if(p.getName().equals(player.getName()))
    					playerTeam.setSuffix(" §7[YOU]");
    				playerTeam.setAllowFriendlyFire(true);
    				playerTeam.setCanSeeFriendlyInvisibles(false);
    				}
    			}
			}
		}, 0, 60);
    	Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
    		public final void run() {
    			if(player.getScoreboard() == fakescoreboard) {
        			Score isLvl = obj.getScore("§f" + ASkyBlockAPI.getInstance().getIslandLevel(player.getUniqueId()));
        			Score balance = obj.getScore("§a$" + NumberFormat.getInstance().format(QubeMC.econ.getBalance(player)));
        			Score coins = obj.getScore("§a" + NumberFormat.getInstance().format(QubeCraft.Stats.getCoins(player.getUniqueId())) + " QC");
        			Score website = obj.getScore("§f§lWebsite§7§l:");
        			Score websiteurl = obj.getScore("§6qubemc.net");
        			if(hm1.containsKey(player.getUniqueId())) {
        				isLvl.getScoreboard().resetScores(hm1.get(player.getUniqueId()));
        				hm1.remove(player.getUniqueId());
        			}
        			if(hm3.containsKey(player.getUniqueId())) {
        				balance.getScoreboard().resetScores(hm3.get(player.getUniqueId()));
        				hm3.remove(player.getUniqueId());
        			}
        			if(hm4.containsKey(player.getUniqueId())) {
        				coins.getScoreboard().resetScores(hm4.get(player.getUniqueId()));
        				hm4.remove(player.getUniqueId());
        			}
        			hm1.put(player.getUniqueId(), "§f" + ASkyBlockAPI.getInstance().getIslandLevel(player.getUniqueId()));
        			hm3.put(player.getUniqueId(), "§a$" + NumberFormat.getInstance().format(QubeMC.econ.getBalance(player)));
        			hm4.put(player.getUniqueId(), "§a" + NumberFormat.getInstance().format(QubeCraft.Stats.getCoins(player.getUniqueId())) + " QC");
        			isLvl = obj.getScore(hm1.get(player.getUniqueId()));
        			balance = obj.getScore(hm3.get(player.getUniqueId()));
        			coins = obj.getScore(hm4.get(player.getUniqueId()));
//        			if(!TGPlayer.getSettings().isPvPEnabled())
//        				name = obj.getScore("§a§l" + p.getName() + "§7§l:");
//        			else
//        				name = obj.getScore("§c§l" + p.getName() + "§7§l:");
        			
        			obj.getScore("§e§lIsland Level:").setScore(11);;
        			isLvl.setScore(10);
        			obj.getScore("  ").setScore(9);
        			obj.getScore("§e§lBalance:").setScore(8);;
        			balance.setScore(7);
        			obj.getScore("   ").setScore(6);
        			obj.getScore("§e§lQubeCoins:").setScore(5);;
        			coins.setScore(4);
        			obj.getScore("    ").setScore(3);
        			website.setScore(2);
        			websiteurl.setScore(1);
        				}
    			}
    		}
    	, 0L, 20 * 8L);
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		player.setScoreboard(fakescoreboard);
	}
}
