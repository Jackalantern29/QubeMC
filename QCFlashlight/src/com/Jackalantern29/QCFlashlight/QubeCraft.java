package com.Jackalantern29.QCFlashlight;

import org.bukkit.plugin.java.JavaPlugin;

import com.Jackalantern29.QCFlashlight.Commands.CommandFlashlight;

public class QubeCraft extends JavaPlugin {
	
	public void onEnable() {
		
		getCommand("flashlight").setExecutor(new CommandFlashlight());
	}
	public void onDisable() {
		
	}

}
