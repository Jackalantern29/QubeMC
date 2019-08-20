package com.Jackalantern29.QCLogger;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;

public class QCFileLoader {
	public static void createFiles() {
		File data = new File("../QubeMC - Data");
		if (!data.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Data folder created!");
				data.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File qclogger = new File("../QubeMC - Data/QCLogger");
		if (!qclogger.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Data folder created!");
				qclogger.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File servers = new File("../QubeMC - Data/QCLogger/Servers");
		if (!servers.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Data folder created!");
				servers.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File skyblock = new File("../QubeMC - Data/QCLogger/Servers/Skyblock");
		if(!skyblock.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Skyblock Data folder created!");
				skyblock.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File skyblockFile = new File("../QubeMC - Data/QCLogger/Servers/Skyblock", "log.txt");
		if(!skyblockFile.exists()) {
			try {
				skyblockFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File factions = new File("../QubeMC - Data/QCLogger/Servers/Factions");
		if(!factions.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Factions Data folder created!");
				factions.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File factionsFile = new File("../QubeMC - Data/QCLogger/Servers/Factions", "log.txt");
		if(!factionsFile.exists()) {
			try {
				factionsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File gta = new File("../QubeMC - Data/QCLogger/Servers/GTA");
		if(!gta.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC GTA Data folder created!");
				gta.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File gtaFile = new File("../QubeMC - Data/QCLogger/Servers/GTA", "log.txt");
		if(!gtaFile.exists()) {
			try {
				gtaFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File towny = new File("../QubeMC - Data/QCLogger/Servers/Towny");
		if(!towny.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Towny Data folder created!");
				towny.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File townyFile = new File("../QubeMC - Data/QCLogger/Servers/Towny", "log.txt");
		if(!townyFile.exists()) {
			try {
				townyFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File creative = new File("../QubeMC - Data/QCLogger/Servers/Creative");
		if(!creative.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Creative Data folder created!");
				creative.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File creativeFile = new File("../QubeMC - Data/QCLogger/Servers/Creative", "log.txt");
		if(!creativeFile.exists()) {
			try {
				creativeFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File lobby = new File("../QubeMC - Data/QCLogger/Servers/Lobby");
		if(!lobby.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Lobby Data folder created!");
				lobby.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File lobbyFile = new File("../QubeMC - Data/QCLogger/Servers/Lobby", "log.txt");
		if(!lobbyFile.exists()) {
			try {
				lobbyFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File dev = new File("../QubeMC - Data/QCLogger/Servers/Development");
		if(!dev.exists()) {
			try {
				Bukkit.getLogger().info("QubeMC Development Data folder created!");
				dev.mkdir();
			} catch (SecurityException e) {
				Bukkit.getLogger().info("QubeMC Data folder can not created!");
				e.printStackTrace();
			}
		}
		File devFile = new File("../QubeMC - Data/QCLogger/Servers/Development", "log.txt");
		if(!devFile.exists()) {
			try {
				devFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
