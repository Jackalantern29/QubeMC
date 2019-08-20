package com.Jackalantern29.SurvivalWorldEdit.Commands;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jackalantern29.SurvivalWorldEdit.Util.Blocks;

public class CommandCount implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("count")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("survivalworldedit.command.count")) {
				player.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			if(Blocks.isPosNull(player.getUniqueId(), 0)) {
				player.sendMessage("§cMake a region selection first.");
				return true;
			}
			if(Blocks.isPosNull(player.getUniqueId(), 1)) {
				player.sendMessage("§cMake a region selection first.");
				return true;
			}
			int total = 0;
			HashMap<String, Integer> map = new HashMap<>();
			for(Block block : Blocks.getBlocks(player.getUniqueId())) {
				total++;
				if(!map.containsKey(block.getType() + ":" + block.getData())) {
					map.put(block.getType() + ":" + block.getData(), 1);
				} else {
					map.replace(block.getType() + ":" + block.getData(), map.get(block.getType() + ":" + block.getData()) + 1);
				}
			}
            StringBuilder str = new StringBuilder();
			for (Map.Entry<String, Integer> ent : getTop(map).entrySet()) {
				Material material = Material.getMaterial(ent.getKey().split(":")[0]);
				String name = StringUtils.capitalise(material.name().toLowerCase());
				byte data = Byte.valueOf(ent.getKey().split(":")[1]);
				int amount = ent.getValue();
                if(str.toString().length() > 0)
                	str.append("§f, ");
                if(data == 0)
                	str.append(name + " x §a" + NumberFormat.getInstance().format(amount));
                else
                	str.append(name + ":" + data + " x §a" + NumberFormat.getInstance().format(amount));
			}	
			player.sendMessage("§dTotal: " + NumberFormat.getInstance().format(total));
			player.sendMessage(str.toString());
		}
		return false;
	}
	
	public TreeMap<String, Integer> getTop(HashMap<String, Integer> map) {
	    TreeMap<String, Integer> scoreMap = new TreeMap<String, Integer>();
	    for (String name : map.keySet()) {
	    	Integer wins = map.get(name);
	    	scoreMap.put(name, wins);
	    }	
		return scoreMap;
	}
	

}
