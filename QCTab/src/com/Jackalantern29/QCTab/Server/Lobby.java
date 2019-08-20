package com.Jackalantern29.QCTab.Server;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.Jackalantern29.QCTab.PlayerData;
import com.Jackalantern29.QCTab.QubeMC;

public class Lobby {
	static HashMap<UUID, String> hm1 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm2 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm3 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm4 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm5 = new HashMap<UUID, String>();
	static HashMap<UUID, String> hm6 = new HashMap<UUID, String>();
	
	static int factionsc = 0;
	static int skyblockc = 0;
	static int gtac = 0;
	static int creativec= 0;
	static int townyc = 0;
	static int allc = 0;
	public static void loadLobbyScoreboard(Player player) {

		Scoreboard fakescoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = fakescoreboard.registerNewObjective("Scoreboard", "bbb");

		Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				obj.setDisplayName("§b§lLobby §b(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
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
		}, 0, 20 * 3);
		factionsc = QubeMC.factionsc;
		skyblockc = QubeMC.skyblockc;
		gtac = QubeMC.gtac;
		creativec= QubeMC.creativec;
		townyc = QubeMC.townyc;
		allc = QubeMC.count;
    	Bukkit.getScheduler().scheduleSyncRepeatingTask(QubeMC.getInstance(), new Runnable() {
    		public final void run() {
    			if(player.getScoreboard() == fakescoreboard) {
        			Score factions = obj.getScore("§aFactions: §f" + QubeMC.factionsc);
        			Score skyblock = obj.getScore("§aSkyblock: §f" + QubeMC.skyblockc);
        			Score gta = obj.getScore("§aGTA: §f" + QubeMC.gtac);
        			Score creative = obj.getScore("§aCreative: §f" + QubeMC.creativec);
        			Score towny = obj.getScore("§aTowny: §f" + QubeMC.townyc);
        			Score all = obj.getScore("§eOnline: §f" + QubeMC.count);
        			Score website = obj.getScore("§f§lWebsite§7§l:");
        			Score websiteurl = obj.getScore("§6qubemc.net");
        			if(hm1.containsKey(player.getUniqueId())) {
        				if(factionsc != QubeMC.factionsc) {
            				factions.getScoreboard().resetScores(hm1.get(player.getUniqueId()));
                			hm1.replace(player.getUniqueId(), "§aFactions: §f" + QubeMC.factionsc);
                			factions = obj.getScore(hm1.get(player.getUniqueId()));
                			factions.setScore(10);	
        				}
        			} else
            			hm1.put(player.getUniqueId(), "§aFactions: §f" + QubeMC.factionsc);
        			if(hm2.containsKey(player.getUniqueId())) {
            			if(skyblockc != QubeMC.skyblockc) {
            				skyblock.getScoreboard().resetScores(hm2.get(player.getUniqueId()));
                			hm2.replace(player.getUniqueId(), "§aSkyblock: §f" + QubeMC.skyblockc);
                			skyblock = obj.getScore(hm2.get(player.getUniqueId()));
                			skyblock.setScore(9);
            			}  
        			} else
            			hm2.put(player.getUniqueId(), "§aSkyblock: §f" + QubeMC.skyblockc);
        			if(hm3.containsKey(player.getUniqueId())) {
            			if(gtac != QubeMC.gtac) {
            				gta.getScoreboard().resetScores(hm3.get(player.getUniqueId()));
                			hm3.replace(player.getUniqueId(), "§aGTA: §f" + QubeMC.gtac);
                			gta = obj.getScore(hm3.get(player.getUniqueId()));
                			gta.setScore(8);
            			}
        			} else
            			hm3.put(player.getUniqueId(), "§aGTA: §f" + QubeMC.gtac);
        			if(hm4.containsKey(player.getUniqueId())) {
            			if(creativec != QubeMC.creativec) {
            				creative.getScoreboard().resetScores(hm4.get(player.getUniqueId()));
                			hm4.replace(player.getUniqueId(), "§aCreative: §f" + QubeMC.creativec);
                			creative = obj.getScore(hm4.get(player.getUniqueId()));
                			creative.setScore(7);
            			}
        			} else
            			hm4.put(player.getUniqueId(), "§aCreative: §f" + QubeMC.creativec);
        			if(hm5.containsKey(player.getUniqueId())) {
            			if(townyc != QubeMC.townyc) {
            				towny.getScoreboard().resetScores(hm5.get(player.getUniqueId()));
                			hm5.replace(player.getUniqueId(), "§aTowny: §f" + QubeMC.townyc);
                			towny = obj.getScore(hm5.get(player.getUniqueId()));
                			towny.setScore(6);
            			}
        			} else
            			hm5.put(player.getUniqueId(), "§aTowny: §f" + QubeMC.townyc);	
        			if(hm6.containsKey(player.getUniqueId())) {
            			if(allc != QubeMC.count) {
            				all.getScoreboard().resetScores(hm6.get(player.getUniqueId()));
                			hm6.replace(player.getUniqueId(), "§eOnline: §f" + QubeMC.count);
                			all = obj.getScore(hm6.get(player.getUniqueId()));
                			all.setScore(4);
            			}
        			} else
            			hm6.put(player.getUniqueId(), "§eOnline: §f" + QubeMC.count);
//        			if(!TGPlayer.getSettings().isPvPEnabled())
//        				name = obj.getScore("§a§l" + p.getName() + "§7§l:");
//        			else
//        				name = obj.getScore("§c§l" + p.getName() + "§7§l:");
        			
        			factions.setScore(10);
        			skyblock.setScore(9);
        			gta.setScore(8);
        			creative.setScore(7);
        			towny.setScore(6);
        			obj.getScore(" ").setScore(5);
        			all.setScore(4);
        			obj.getScore("  ").setScore(3);
        			website.setScore(2);
        			websiteurl.setScore(1);
        			
        			factionsc = QubeMC.factionsc;
        			skyblockc = QubeMC.skyblockc;
        			gtac = QubeMC.gtac;
        			creativec= QubeMC.creativec;
        			townyc = QubeMC.townyc;
        			allc = QubeMC.count;
        		}
    		}
    	}, 20 * 5L, 1);
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		player.setScoreboard(fakescoreboard);
	}
}
