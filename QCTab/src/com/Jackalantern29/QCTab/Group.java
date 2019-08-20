package com.Jackalantern29.QCTab;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Group {
	String name;
	File file;
	YamlConfiguration config;
	public Group(String name) {
		this.name = name;
		file = new File("../QubeMC - Data/Tab/Group", name + ".yml");
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public String getName() {
		return config.getString("Name");
	}
	
	public int getTabPriority() {
		return config.getInt("Priority");
	}
	
	public String getTabPrefix() {
		return config.getString("Prefix");
	}
	
	public void createGroup(String name, int priority, String prefix) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			config.set("Name", name);
			config.set("Priority", priority);
			config.set("Prefix", prefix);
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
