package com.Jackalantern29.SurvivalWorldEdit;

import org.bukkit.plugin.java.JavaPlugin;

import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandCount;
import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandRedo;
import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandReplace;
import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandSet;
import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandTree;
import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandUndo;
import com.Jackalantern29.SurvivalWorldEdit.Commands.CommandWand;
import com.Jackalantern29.SurvivalWorldEdit.Listeners.PlayerInteractListener;

public class QubeMC extends JavaPlugin {
	protected static QubeMC plugin;
	public void onEnable() {
		saveDefaultConfig();
		plugin = this;
		getCommand("set").setExecutor(new CommandSet());
		//getCommand("set").setExecutor(new CommandSetWIP());
		getCommand("wand").setExecutor(new CommandWand());
		getCommand("undo").setExecutor(new CommandUndo());
		getCommand("redo").setExecutor(new CommandRedo());
		getCommand("tree").setExecutor(new CommandTree());
		getCommand("replace").setExecutor(new CommandReplace());
		getCommand("count").setExecutor(new CommandCount());
		//getCommand("addblock").setExecutor(new CommandAddblock());
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
	}
	
	public void onDisable() {
		
	}
	
	public static QubeMC getInstance() {
		return plugin;
	}
}
