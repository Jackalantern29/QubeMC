package com.Jackalantern29.QCHolograms.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.Jackalantern29.QCHolograms.QubeCraft;
import com.Jackalantern29.QCHolograms.Util.HoloConfig;

public class CommandHologram implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("hologram")) {
			if(!sender.hasPermission("qchologram.admin")) {
				sender.sendMessage("§4§lYou do not have permission to use this command!");
				return true;
			}
			if(args.length == 0) {
				sender.sendMessage("§b§lQubeCraft: §e§lHologram Commands");
				sender.sendMessage("§a/" + label + " create <name> <message>");
				sender.sendMessage("§a/" + label + " delete <name>");
				sender.sendMessage("§a/" + label + " list");
				sender.sendMessage("§a/" + label + " edit <name> [message|tp|name]");
				sender.sendMessage("§a/" + label + " addline <name> <message>");
				sender.sendMessage("§a/" + label + " delline <name>");
				sender.sendMessage("§a/" + label + " forceremove");
				sender.sendMessage("§4§lNOTE: §cWhen using /" + label + " forceremove, you need to be next to a hologram and make sure that the hologram's file does not exists!");
				sender.sendMessage("§a/" + label);
				return true;
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You need to be a player to use this command!");
						return true;
					}
					sender.sendMessage("§cUsage: /" + label + " create <name> <message>");
					return true;
				} else if(args[0].equalsIgnoreCase("delete")) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You need to be a player to use this command!");
						return true;
					}
					sender.sendMessage("§cUsage: /" + label + " delete <name>");
					return true;
				} else if(args[0].equalsIgnoreCase("list")) {
					List<String> names = new ArrayList<String>();
					StringBuilder str = new StringBuilder();
					for(String name : new File(QubeCraft.getInstance().getDataFolder() + File.separator + "Holograms").list()) {
						names.add(name);
						if(str.length() > 0)
							str.append("§8, §a");
						str.append("§a" + name.replace(".yml", ""));
					}
					if(names.size() == 0) {
						sender.sendMessage("§e§lList of Holograms:");
						sender.sendMessage("§cThere are currently no holograms online!");
						return true;
					}
					sender.sendMessage("§e§lList of Holograms:");
					sender.sendMessage(str.toString());
					return true;
				} else if(args[0].equalsIgnoreCase("edit")) {
					sender.sendMessage("§cUsage: /" + label + " edit <name> [message|teleport|name]");
					return true;
				} else if(args[0].equalsIgnoreCase("addline")) {
					sender.sendMessage("§cUsage: /" + label + " addline <name> <message>");
					return true;
				} else if(args[0].equalsIgnoreCase("delline")) {
					sender.sendMessage("§cUsage: /" + label + " deline <name>");
					return true;
				} else if(args[0].equalsIgnoreCase("forceremove")) {
					if(sender instanceof Player) {
						Player player = (Player)sender;
						for(Entity entity : player.getNearbyEntities(1, 1, 1)) {
							if(entity instanceof ArmorStand) {
								ArmorStand armorstand = (ArmorStand)entity;
								if((armorstand.isMarker()) && (!armorstand.isVisible())) {
									armorstand.remove();
								}
							}
						}
					} else {
						sender.sendMessage("You need to be a player to perform this command.");
					}
					return true;
				}
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You need to be a player to use this command!");
						return true;
					}
					sender.sendMessage("§cUsage: /" + label + " create <name> <message>");
					return true;
				} else if(args[0].equalsIgnoreCase("addline")) {
					sender.sendMessage("§cUsage: /" + label + " addline <name> <message>");
					return true;
				} else if(args[0].equalsIgnoreCase("delete")) {
					if(HoloConfig.deleteHologram(args[1])) {
						sender.sendMessage("§aSuccessfully removed hologram!");
					} else {
						sender.sendMessage("§cHologram does not exists!");
					}
					return true;
				} else if(args[0].equalsIgnoreCase("delline")) {
					if(HoloConfig.delHologramLine(args[1]))
						sender.sendMessage("§aSuccessfully removed line!");
					else
						sender.sendMessage("§cFailed to remove line!");
					return true;
				} else if(args[0].equalsIgnoreCase("edit")) {
					if(HoloConfig.doesHologramExsits(args[1])) {
						sender.sendMessage("§cUsage: /" + label + " edit " + args[1] + " [message|tp|name]");
					} else
						sender.sendMessage("§cHologram does not exists!");
					return true;
				}
			} else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("edit")) {
					if(HoloConfig.doesHologramExsits(args[1])) {
						if(args[2].equalsIgnoreCase("message")) {
							sender.sendMessage("§cUsage: /" + label + " edit " + args[1] + " message <message>");
						} else if(args[2].equalsIgnoreCase("tp")) {
							sender.sendMessage("§cUsage: /" + label + " edit " + args[1] + " tp <X,Y,Z|~,~,~>");
						} else if(args[2].equalsIgnoreCase("name")) {
							sender.sendMessage("§cUsage: /" + label + " edit " + args[1] + " name <name>");
						}
					} else
						sender.sendMessage("§cHologram does not exists!");
					return true;
				} else if(args[0].equalsIgnoreCase("create")) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You need to be a player to use this command!");
						return true;
					}
					Player player = (Player)sender;
					Location location = player.getLocation();
                    
					if(HoloConfig.createHologram(location, args[1], args[2].replace("_", " ")))
						player.sendMessage("§aSuccessfully created hologram!");
					else
						player.sendMessage("§cHologram already exists!");
					return true;
				} else if(args[0].equalsIgnoreCase("addline")) {
					if(HoloConfig.addHologramMessage(args[1], args[2].replace("_", " ")))
						sender.sendMessage("§aSuccessfully created hologram!");
					return true;
				}
			} else if(args.length >= 3) {
				if(args[0].equalsIgnoreCase("edit")) {
					if(args[2].equalsIgnoreCase("message")) {
	                    StringBuilder sb = new StringBuilder();

	                    for (int i = 3; i < args.length; i++) 
	                         sb.append(args[i]+" ");
	                    if(HoloConfig.changeHologramMessage(args[1], sb.toString().substring(0, sb.toString().length() - 1))) {
	                    	sender.sendMessage("§aSuccessfully has changed hologram message!");
	                    } else
	                    	sender.sendMessage("§cHologram message could not be changed!");
	                    return true;
					} else if(args[2].equalsIgnoreCase("name")) {
	                    StringBuilder sb = new StringBuilder();

	                    for (int i = 3; i < args.length; i++) 
	                         sb.append(args[i]+" ");
	                    if(HoloConfig.changeHologramName(args[1], sb.toString().replace(" ", ""))) {
	                    	sender.sendMessage("§aSuccessfully changed hologram file name to " + sb.toString().replace(" ", "") + "!");
	                    } else
	                    	sender.sendMessage("§cFailed to change hologram file name!");
	                    return true;
					} else if(args.length == 4) {
						if(args[2].equalsIgnoreCase("tp")) {
							World world;
							double x = 0;
							double y = 0;
							double z = 0;
							if(sender instanceof Player) {
								world = ((Player)sender).getWorld();
								if(args[3].split(",")[0].equals("~"))
									x = ((Player)sender).getLocation().getX();
								else
									x = Double.valueOf(args[3].split(",")[0]);
								if(args[3].split(",")[1].equals("~"))
									y = ((Player)sender).getLocation().getY();
								else
									y = Double.valueOf(args[3].split(",")[1]);
								if(args[3].split(",")[2].equals("~"))
									z = ((Player)sender).getLocation().getZ();
								else
									z = Double.valueOf(args[3].split(",")[2]);
							} else {
								world = HoloConfig.getHologramLocation(args[1]).getWorld();
								if(args[3].split(",")[0].equals("~")) {
									sender.sendMessage("Only players can use '~'!");
									return true;
								}
								if(args[3].split(",")[1].equals("~")) {
									sender.sendMessage("Only players can use '~'!");
									return true;
								}
								if(args[3].split(",")[2].equals("~")) {
									sender.sendMessage("Only players can use '~'!");
									return true;
								}
							}
							Location loc = new Location(world, x, y, z);
							if(HoloConfig.teleportHologram(args[1], loc)) {
								sender.sendMessage("§aSuccessfully teleported hologram!");
							} else {
								sender.sendMessage("§cFailed to teleport hologram!");
							}
							return true;
						}
					}
				} else if(args[0].equalsIgnoreCase("create")) {
					if(!(sender instanceof Player)) {
						sender.sendMessage("You need to be a player to use this command!");
						return true;
					}
					Player player = (Player)sender;
					Location location = player.getLocation();
					
                    StringBuilder sb = new StringBuilder();

                    for (int i = 2; i < args.length; i++) 
                         sb.append(args[i]+" ");
                    
					if(HoloConfig.createHologram(location, args[1], sb.toString().substring(0, sb.toString().length() - 1)))
						player.sendMessage("§aSuccessfully created hologram!");
					else
						player.sendMessage("§cHologram already exists!");
					return true;
				} else if(args[0].equalsIgnoreCase("addline")) {
                    StringBuilder sb = new StringBuilder();

                    for (int i = 2; i < args.length; i++) 
                         sb.append(args[i]+" ");
                    
					if(HoloConfig.addHologramMessage(args[1], sb.toString().substring(0, sb.toString().length() - 1)))
						sender.sendMessage("§aSuccessfully created hologram!");
					return true;
				}
			}
		}
			
		return false;
	}
	
}