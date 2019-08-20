package com.Jackalantern29.QCFlashlight.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandFlashlight implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			Player player = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("flashlight")) {
				if(args.length == 0) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You must be a player to use this command.");
						return true;
					}
					if(player.hasPermission("flashlight.use")) {
						if(!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0));
							player.sendMessage("§7[§eFlashlight§7] - §f[§7ON§f]");
							return true;
						} else {
							player.removePotionEffect(PotionEffectType.NIGHT_VISION);
							player.sendMessage("§7[§eFlashlight§7] - §f[§7OFF§f]");
							return true;
						}
					} else {
						player.sendMessage("§cYou do not have permission for this.");
						return true;
					}
				} else if(args.length == 1) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You must be a player to use this command.");
						return true;
					}
					if(player.hasPermission("flashlight.use")) {
						if(args[0].equalsIgnoreCase("on")) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0));
							player.sendMessage("§7[§eFlashlight§7] - §f[§7ON§f]");
							return true;
						} else if(args[0].equalsIgnoreCase("off")) {
							player.removePotionEffect(PotionEffectType.NIGHT_VISION);
							player.sendMessage("§7[§eFlashlight§7] - §f[§7OFF§f]");
							return true;
						}
					} else {
						player.sendMessage("§cYou do not have permission for this.");
						return true;
					}
				} else if(args.length == 2) {
					Player target = Bukkit.getPlayerExact(args[1]);
					if(player.hasPermission("flashlight.use.other")) {
						if(target == null) {
							sender.sendMessage("§cPlayer is not online.");
							return true;
						}
						if(args[0].equalsIgnoreCase("on")) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0));
							//target.sendMessage("§7[§eFlashlight§7] - §f[§7ON§f]");
							return true;
						} else if(args[0].equalsIgnoreCase("off")) {
							target.removePotionEffect(PotionEffectType.NIGHT_VISION);
							//target.sendMessage("§7[§eFlashlight§7] - §f[§7OFF§f]");
							return true;
						}
					} else {
						sender.sendMessage("§cYou do not have permission for this.");
						return true;
					}
				}
		}
		return false;
	}
	

}
