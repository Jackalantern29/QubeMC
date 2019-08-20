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
import com.massivecraft.factions.FPlayers;

public class Factions {
	public static HashMap<UUID, String> hm1 = new HashMap<UUID, String>();
	public static HashMap<UUID, String> hm2 = new HashMap<UUID, String>();
	public static HashMap<UUID, String> hm3 = new HashMap<UUID, String>();
	public static HashMap<UUID, String> hm4 = new HashMap<UUID, String>();
	
	static HashMap<UUID, String> hmt = new HashMap<>();
	public static void loadFactionScoreboard(Player player) {

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
		}, 0, 20 * 3);
    	Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
    		public final void run() {
    			if(player.getScoreboard() == fakescoreboard) {
    				obj.setDisplayName("§b§lFactions §b(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
    				String tags = "§f" + FPlayers.getInstance().getByPlayer(player).getFaction().getTag();
    				String powers = "§f" + FPlayers.getInstance().getByPlayer(player).getPowerRounded() + "/" + FPlayers.getInstance().getByPlayer(player).getPowerMaxRounded();
        			String balances = "§a$" + NumberFormat.getInstance().format(QubeMC.econ.getBalance(player));
    				String coinss = "§a" + NumberFormat.getInstance().format(QubeCraft.Stats.getCoins(player.getUniqueId())) + " QC";
    						
    				Score tag = obj.getScore(tags);
        			Score power = obj.getScore(powers);
        			Score balance = obj.getScore(balances);
        			Score coins = obj.getScore(coinss);
        			Score website = obj.getScore("§f§lWebsite§7§l:");
        			Score websiteurl = obj.getScore("§6qubemc.net");
        			if(!hm1.containsKey(player.getUniqueId())) {
        				hm1.put(player.getUniqueId(), tags);
            			tag.getScoreboard().resetScores(hm1.get(player.getUniqueId()));
            			tag = obj.getScore(hm1.get(player.getUniqueId()));
            			tag.setScore(13);
        			}
        			if(!hm2.containsKey(player.getUniqueId())) {
        				hm2.put(player.getUniqueId(), powers);
            			power.getScoreboard().resetScores(hm2.get(player.getUniqueId()));
            			power = obj.getScore(hm2.get(player.getUniqueId()));
            			power.setScore(10); 
        			}
        			if(!hm3.containsKey(player.getUniqueId())) {
        				hm3.put(player.getUniqueId(), balances);
            			balance.getScoreboard().resetScores(hm3.get(player.getUniqueId()));
            			balance = obj.getScore(hm3.get(player.getUniqueId()));
            			balance.setScore(7);
        			}
        			if(!hm4.containsKey(player.getUniqueId())) {
        				hm4.put(player.getUniqueId(), coinss);
            			coins.getScoreboard().resetScores(hm4.get(player.getUniqueId()));
            			coins = obj.getScore(hm4.get(player.getUniqueId()));
            			coins.setScore(4);
        			}
        			
        			if(hm1.containsKey(player.getUniqueId())) {
        				if(!hm1.get(player.getUniqueId()).equals(tags)) {
        					tag.getScoreboard().resetScores(hm1.get(player.getUniqueId()));
                			hm1.replace(player.getUniqueId(), tags);
                			tag = obj.getScore(hm1.get(player.getUniqueId()));
                			tag.setScore(13);	
        				}
        			}
        			if(hm2.containsKey(player.getUniqueId())) {
        				if(!hm2.get(player.getUniqueId()).equals(powers)) {
        					power.getScoreboard().resetScores(hm2.get(player.getUniqueId()));
                			hm2.replace(player.getUniqueId(), powers);
                			power = obj.getScore(hm2.get(player.getUniqueId()));
                			power.setScore(10); 
        				} 
        			}
        			if(hm3.containsKey(player.getUniqueId())) {
        				if(!hm3.get(player.getUniqueId()).equals(balances)) {
        					balance.getScoreboard().resetScores(hm3.get(player.getUniqueId()));
                			hm3.replace(player.getUniqueId(), balances);
                			balance = obj.getScore(hm3.get(player.getUniqueId()));
                			balance.setScore(7);
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
        			
        			obj.getScore("§e§lYour Faction:").setScore(14);;
        			obj.getScore(" ").setScore(12);
        			obj.getScore("§e§lPower:").setScore(11);;
        			obj.getScore("  ").setScore(9);
        			obj.getScore("§e§lBalance:").setScore(8);;
        			obj.getScore("   ").setScore(6);
        			obj.getScore("§e§lQubeCoins:").setScore(5);;
        			obj.getScore("    ").setScore(3);
        			website.setScore(2);
        			websiteurl.setScore(1);
        			
        			tags = "§f" + FPlayers.getInstance().getByPlayer(player).getFaction().getTag();
    				powers = "§f" + FPlayers.getInstance().getByPlayer(player).getPowerRounded() + "/" + FPlayers.getInstance().getByPlayer(player).getPowerMaxRounded();
        			balances = "§a$" + NumberFormat.getInstance().format(QubeMC.econ.getBalance(player));
    				coinss = "§a" + NumberFormat.getInstance().format(QubeCraft.Stats.getCoins(player.getUniqueId())) + " QC";
        		}
    		}
    	}, 0, 1L);
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		player.setScoreboard(fakescoreboard);
	}
}
