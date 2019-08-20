package com.Jackalantern29.QCWither;

import java.lang.reflect.Field;

import net.minecraft.server.v1_9_R1.BossBattleServer;
import net.minecraft.server.v1_9_R1.EntityWither;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Wither;

public class Packet {
	public static void spawnWitherNoBar(String name, Location location) {
		Wither w = (Wither)Bukkit.getWorld("Skyblock").spawn(location, Wither.class);
		w.setCustomName(name);
		w.setCustomNameVisible(true);
		net.minecraft.server.v1_9_R1.Entity nmsentity = ((CraftEntity) w).getHandle();
		EntityWither nmswither = (EntityWither) nmsentity;

		            Field bossbar = null;
		            BossBattleServer  bossbattleserver = null;
		            try {
		                bossbar = EntityWither.class.getDeclaredField("bE");
		            } catch (NoSuchFieldException | SecurityException e1) {
		                e1.printStackTrace();
		            }
		            bossbar.setAccessible(true);
		            if (bossbar != null) {
		                try {
		                    bossbattleserver = (BossBattleServer) bossbar.get(nmswither);
		                } catch (IllegalAccessException e) {
		                    e.printStackTrace();
		                }
		            }
		            bossbattleserver.setVisible(false);
	}
	public static void hideWitherBar(Wither wither) {
		net.minecraft.server.v1_9_R1.Entity nmsentity = ((CraftEntity) wither).getHandle();
		EntityWither nmswither = (EntityWither) nmsentity;

		            Field bossbar = null;
		            BossBattleServer  bossbattleserver = null;
		            try {
		                bossbar = EntityWither.class.getDeclaredField("bE");
		            } catch (NoSuchFieldException | SecurityException e1) {
		                e1.printStackTrace();
		            }
		            bossbar.setAccessible(true);
		            if (bossbar != null) {
		                try {
		                    bossbattleserver = (BossBattleServer) bossbar.get(nmswither);
		                } catch (IllegalAccessException e) {
		                    e.printStackTrace();
		                }
		            }
		            bossbattleserver.setVisible(false);
	}
}
