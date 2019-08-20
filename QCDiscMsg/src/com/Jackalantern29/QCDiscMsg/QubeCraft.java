package com.Jackalantern29.QCDiscMsg;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class QubeCraft extends Plugin implements Listener {
	
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, this);
	}
	
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onLeave(ServerSwitchEvent event) {
		ProxiedPlayer player = event.getPlayer();
		for(ProxiedPlayer players : player.getServer().getInfo().getPlayers()) {
			players.sendMessage(new TextComponent("§e" + player.getName() + " has left and joined the " + player.getReconnectServer().getName() + " server."));
		}
	}
	
	@EventHandler
	public void onLeave(ServerDisconnectEvent event) {
		ProxiedPlayer player = event.getPlayer();
		for(ProxiedPlayer players : player.getReconnectServer().getPlayers()) {
			players.sendMessage(new TextComponent("§e" + player.getName() + " has left the server."));
		}
	}
	
	@EventHandler
	public void onJoin(ServerConnectedEvent event) {
		ProxiedPlayer player = event.getPlayer();
		for(ProxiedPlayer players : player.getReconnectServer().getPlayers()) {
			players.sendMessage(new TextComponent("§e" + player.getName() + " has joined the server."));
		}
	}
}
