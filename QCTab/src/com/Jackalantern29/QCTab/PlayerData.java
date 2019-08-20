package com.Jackalantern29.QCTab;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerData {
	Player player;
	
	public PlayerData(Player player) {
		this.player = player;
	}
	
	public Group getGroup() {
		//File file = new File("../QubeMC - Data/Tab/Players", player.getUniqueId().toString() + ".yml");
		//YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		PermissionUser user = PermissionsEx.getUser(player);
		String group = user.getPrefix().replace("[", "").replace("]", "")
				.replace("&0", "").replace("&1", "").replace("&2", "").replace("&3", "").replace("&4", "").replace("&5", "")
				.replace("&6", "").replace("&7", "").replace("&8", "").replace("&9", "").replace("&a", "").replace("&b", "")
				.replace("&c", "").replace("&d", "").replace("&e", "").replace("&f", "").replace("&k", "").replace("&l", "")
				.replace("&m", "").replace("&n", "").replace("&o", "").replace("&r", "").replace(" ", "");
		return new Group(group);
		//return new Group(config.getString("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".Group"));
	}
	
//	public void setGroup(String server, String group) {
//		File file = new File("../QubeMC - Data/Tab/Players", player.getUniqueId().toString() + ".yml");
//		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//		if(!file.exists())
//			createProfile();
//		config.set("Server." + server + ".Group", group);
//		try {
//			config.save(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void createProfile() {
//		File file = new File("../QubeMC - Data/Tab/Players", player.getUniqueId().toString() + ".yml");
//		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
//		if(!file.exists()) {
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			config.set("Server." + Bukkit.getServerName().replace("QubeMC - ", "") + ".Group", "Guest");
//			try {
//				config.save(file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
