package com.Jackalantern29.QCRewards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jackalantern29.QCRewards.Stats;

public class CommandQubeCoins implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("qubecoins")) {
			if(!QubeMC.plugin.mysql.hasConnection())
				return false;
			if (!(sender instanceof Player)) {
				sender.sendMessage("You must be a player to view your QubeCoins.");
				return true;
			}
			if (args.length == 0) {
				Player player = (Player) sender;
				player.sendMessage(QubeMC.plugin.getConfig().getString("QCAmounts").replace("&", "§").replace("%coins%", "" + Stats.getCoins(player.getUniqueId())));
				
				return true;
			} else if (args.length == 1) {
				Player player = (Player) sender;
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				if (target == null) {
					player.sendMessage(QubeMC.plugin.getConfig().getString("PlayerIsNotOnline").replace("&", "§"));
					return true;
				}
				player.sendMessage(QubeMC.plugin.getConfig().getString("QCPlayerAmounts").replace("&", "§")
						.replace("%coins%", "" + Stats.getCoins(target.getUniqueId())).replace("%player%", target.getName()));
				return true;
			} else {
				sender.sendMessage("§cToo many args.");
				return true;
			}
		} else if (command.getName().equalsIgnoreCase("givequbecoins")) {
			if(!QubeMC.plugin.mysql.hasConnection())
				return false;
			if (!sender.hasPermission("qubecraft.admin")) {
				sender.sendMessage("§cYou do not have permission to use this command.");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("§cUsage: /" + label + " [player] [amount]");
				return true;
			} else if (args.length == 1) {
				sender.sendMessage("§cUsage: /" + label + " [player] [amount]");
				return true;
			} else if (args.length == 2) {
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
				Stats.setCoins(target.getUniqueId(), Stats.getCoins(target.getUniqueId()) + Integer.parseInt(args[1]));
				sender.sendMessage("§bYou have givin " + target.getName() + " " + args[1] + ", " + target.getName()
						+ " now has " + Stats.getCoins(target.getUniqueId()));
				return true;
			}
		} else if(command.getName().equalsIgnoreCase("qubecoinstop")) {
			if(!QubeMC.plugin.mysql.hasConnection())
				return false;
			Map<String, BigDecimal> balances = new HashMap<String, BigDecimal>();
			for(int i = 1;i <= Stats.getMAXID();i++) {
				BigDecimal amount = BigDecimal.valueOf(Stats.getCoins(i));
				String name = Stats.getLastUsername(i);
				balances.put(name, amount);
			}
			List<Map.Entry<String, BigDecimal>> sortedEntries = new ArrayList<Map.Entry<String, BigDecimal>>(balances.entrySet());
			Collections.sort(sortedEntries, new Comparator<Map.Entry<String, BigDecimal>>() {
				@Override
				public int compare(Entry<String, BigDecimal> entry1, Entry<String, BigDecimal> entry2) {
					return ((BigDecimal)entry2.getValue()).compareTo((BigDecimal)entry1.getValue());
				}
			});
			List<String> lines = new ArrayList<String>();
			lines.add("§9Top 10 QubeCoins Leaderboard");

			int pos = 1;
			for (Map.Entry<String, BigDecimal> entry : sortedEntries) {
				if(pos != 11) {
					lines.add(pos + ". " + entry.getKey() + " - §2" + (BigDecimal)entry.getValue() + " QC");
					pos++;
				}
			}
			for(String messages : lines)
				sender.sendMessage(messages.replace(".0", ""));
			return true;
			}
			return false;
	}

}
