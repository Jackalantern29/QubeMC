package com.Jackalantern29.QCTab.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Jackalantern29.QCTab.Server.Development;
import com.Jackalantern29.QCTab.Server.Factions;
import com.Jackalantern29.QCTab.Server.GTA;
import com.Jackalantern29.QCTab.Server.Lobby;
import com.Jackalantern29.QCTab.Server.Skyblock;
import com.Jackalantern29.QCTab.Server.Towny;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(Bukkit.getServerName().replace("QubeMC - ", "").equals("Factions"))
			Factions.loadFactionScoreboard(player);
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
		//Player player = event.getPlayer();
		//PlayerData data = new PlayerData(player);
		//data.createProfile();
	}

}
