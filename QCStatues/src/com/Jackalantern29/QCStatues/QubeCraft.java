package com.Jackalantern29.QCStatues;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRemoveEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;
import net.citizensnpcs.trait.LookClose;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class QubeCraft extends JavaPlugin implements Listener {
	public static Economy econ;
	public final Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		saveDefaultConfig();
		File playerFile = new File(getDataFolder() + "/players.yml");
		if (!playerFile.exists()) {
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getServer().getPluginManager().registerEvents(this, this);
		if (!setupEconomy()) {
			logger.severe(String.format(
					"[%s] - Disabled due to no Vault dependency found!",
					getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (getServer().getPluginManager().getPlugin("Citizens") == null
				|| getServer().getPluginManager().getPlugin("Citizens")
						.isEnabled() == false) {
			getLogger().log(Level.SEVERE,
					"Citizens 2.0 not found or not enabled");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
	}

	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("statue")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can execute this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("qubecraft.command.statue")) {
				player.sendMessage(getConfig().getString("NoPermission").replace("&", "§"));
				return true;
			}
			if(args.length == 0) {
				player.sendMessage(getConfig().getString("TypeHelp").replace("&", "§"));
				return true;
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					for(String string : getConfig().getStringList("HelpMessage")) {
						player.sendMessage(string.replace("&", "§"));
					}
				} else if(args[0].equalsIgnoreCase("create")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					if (config.getBoolean(player.getUniqueId().toString() + ".obbyHasStatue")) {
						player.sendMessage(getConfig().getString("AlreadyOwnStatue").replace("&", "§"));
					} else if (econ.getBalance(player) >= getConfig().getDouble("StatueCreatePrice")) {
						econ.withdrawPlayer(player, getConfig().getDouble("StatueCreatePrice"));
						NPCRegistry registry = CitizensAPI.getNPCRegistry();
						NPC npc = registry.createNPC(EntityType.PLAYER, player.getName());
						LookClose lookclose = new LookClose();
						lookclose.lookClose(true);
						npc.spawn(player.getLocation());
						npc.setName("§d" + player.getName());
						npc.addTrait(lookclose);
						config.set(player.getUniqueId().toString() + ".obbyNpcID", npc.getId());
						config.set(player.getUniqueId().toString() + ".obbyHasStatue", true);
						try {
							config.save(playerFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						player.sendMessage(getConfig().getString("StatueCreated").replace("&", "§"));
					} else {
						player.sendMessage(getConfig().getString("NotEnoughMoney").replace("&", "§"));
					}
				} else if(args[0].equalsIgnoreCase("equip")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					if (econ.getBalance(player) >= getConfig().getDouble("EquipCommandCost")) {
						if (config.getBoolean(player.getUniqueId().toString() + ".obbyHasStatue")) {
							NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString()+ ".obbyNpcID"));
							if(npc == null) {
								player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
								return true;
							}
							Equipment equipment = npc.getTrait(Equipment.class);
							if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_HELMET))) {
								final ItemStack item = player.getInventory().getItemInMainHand();
								Repairable meta = (Repairable)item.getItemMeta();
								config.set(player.getUniqueId().toString() + ".obbyHelmetRepairCost", meta.getRepairCost());
								try {
									config.save(playerFile);
								} catch (IOException e) {
									e.printStackTrace();
								}
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HELMET));
								equipment.set(EquipmentSlot.HELMET, item);
								equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
								equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
								equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
								equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
							} else if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_CHESTPLATE))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_CHESTPLATE))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_CHESTPLATE))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_CHESTPLATE))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_CHESTPLATE))) {
								final ItemStack item = player.getInventory().getItemInMainHand();
								Repairable meta = (Repairable)item.getItemMeta();
								config.set(player.getUniqueId().toString() + ".obbyChestplateRepairCost", meta.getRepairCost());
								try {
									config.save(playerFile);
								} catch (IOException e) {
									e.printStackTrace();
								}
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.CHESTPLATE));
								equipment.set(EquipmentSlot.CHESTPLATE, item);
								equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
								equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
								equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
								equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
							} else if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_LEGGINGS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_LEGGINGS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_LEGGINGS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_LEGGINGS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_LEGGINGS))) {
								final ItemStack item = player.getInventory().getItemInMainHand();
								Repairable meta = (Repairable)item.getItemMeta();
								config.set(player.getUniqueId().toString() + ".obbyLeggingsRepairCost", meta.getRepairCost());
								try {
									config.save(playerFile);
								} catch (IOException e) {
									e.printStackTrace();
								}
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.LEGGINGS));
								equipment.set(EquipmentSlot.LEGGINGS, item);
								equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
								equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
								equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
								equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
							} else if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_BOOTS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_BOOTS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BOOTS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_BOOTS))
									|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_BOOTS))) {
								final ItemStack item = player.getInventory().getItemInMainHand();
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.BOOTS));
								Repairable meta = (Repairable)item.getItemMeta();
								config.set(player.getUniqueId().toString() + ".obbyBootRepairCost", meta.getRepairCost());
								try {
									config.save(playerFile);
								} catch (IOException e) {
									e.printStackTrace();
								}
								equipment.set(EquipmentSlot.BOOTS, item);
								equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
								equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
								equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
								equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
							} else {
								if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
									player.sendMessage(getConfig().getString("CannotEquipAir").replace("&", "§"));
									return true;
								} else {
									final ItemStack item = player.getInventory().getItemInMainHand();
									Repairable meta = (Repairable)item.getItemMeta();
									config.set(player.getUniqueId().toString() + ".obbyHandRepairCost", meta.getRepairCost());
									try {
										config.save(playerFile);
									} catch (IOException e) {
										e.printStackTrace();
									}
									player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
									equipment.set(EquipmentSlot.HAND, item);
									equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
									equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
									equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
									equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
								}
							}
							econ.withdrawPlayer(player, getConfig().getDouble("EquipCommandCost"));
							if(getConfig().getString("EquipedMessage").contains("::")) {
								player.sendMessage(getConfig().getString("EquipedMessage").split("::")[0].replace("&", "§"));
								player.sendMessage(getConfig().getString("EquipedMessage").split("::")[1].replace("&", "§"));
							} else
								player.sendMessage(getConfig().getString("EquipedMessage").replace("&", "§"));
							return true;
						} else {
							player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
						}
					}
				} else if(args[0].equalsIgnoreCase("unequip")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					if (econ.getBalance(player) >= getConfig().getDouble("UnequipCommandCost")) {
						if (config.getBoolean(player.getUniqueId().toString() + ".obbyHasStatue")) {
							NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString() + ".obbyNpcID"));
							if(npc == null) {
								player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
								return true;
							}
							Equipment equipment = npc.getTrait(Equipment.class);
							if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
								if(equipment.get(EquipmentSlot.HAND) == null) {
									player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
									return true;
								}
								if(getConfig().getString("UnequipedMessage").contains("::")) {
									player.sendMessage(getConfig().getString("UnequipedMessage").split("::")[0].replace("&", "§"));
									player.sendMessage(getConfig().getString("UnequipedMessage").split("::")[1].replace("&", "§"));
								} else
									player.sendMessage(getConfig().getString("UnequipedMessage").replace("&", "§"));
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
								equipment.set(EquipmentSlot.HAND, null);
							} else {
								player.sendMessage(getConfig().getString("NeedHoldAir").replace("&", "§"));
								return true;
							}
							econ.withdrawPlayer(player, getConfig().getDouble("UnequipCommandCost"));
							return true;
						} else {
							player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
						}
					} else {
						player.sendMessage(getConfig().getString("NotEnoughMoney").replace("&", "§"));
					}
				} else if(args[0].equalsIgnoreCase("lookclose")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					if (config.getBoolean(player.getUniqueId().toString() + ".obbyHasStatue")) {
						NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString() + ".obbyNpcID"));
						if(npc == null) {
							player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
							return true;
						}
						LookClose lookclose = npc.getTrait(LookClose.class);
						if(lookclose.toggle() == true) {
							player.sendMessage(getConfig().getString("EnabledLookClose").replace("&", "§"));
						} else {
							player.sendMessage(getConfig().getString("DisabledLookClose").replace("&", "§"));
						}
					} else {
						player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
					}
				} else if(args[0].equalsIgnoreCase("tphere")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					if (config.getBoolean(player.getUniqueId().toString() + ".obbyHasStatue")) {
						NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString() + ".obbyNpcID"));
						if(npc == null) {
							player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
							return true;
						}
						npc.teleport(player.getLocation(), TeleportCause.COMMAND);
						player.sendMessage(getConfig().getString("TpHere").replace("&", "§"));
					} else {
						player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
					}
				} else if(args[0].equalsIgnoreCase("remove")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					if (config.getBoolean(player.getUniqueId().toString() + ".obbyHasStatue")) {
						NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString() + ".obbyNpcID"));
						if(npc == null) {
							player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
							return true;
						}
						Equipment equipment = npc.getTrait(Equipment.class);
						if(equipment.get(EquipmentSlot.BOOTS) != null)
							npc.getEntity().getWorld().dropItem(npc.getEntity().getLocation(), equipment.get(EquipmentSlot.BOOTS));
						if(equipment.get(EquipmentSlot.CHESTPLATE) != null)
							npc.getEntity().getWorld().dropItem(npc.getEntity().getLocation(), equipment.get(EquipmentSlot.CHESTPLATE));
						if(equipment.get(EquipmentSlot.HAND) != null)
							npc.getEntity().getWorld().dropItem(npc.getEntity().getLocation(), equipment.get(EquipmentSlot.HAND));
						if(equipment.get(EquipmentSlot.HELMET) != null)
							npc.getEntity().getWorld().dropItem(npc.getEntity().getLocation(), equipment.get(EquipmentSlot.HELMET));
						if(equipment.get(EquipmentSlot.LEGGINGS) != null)
							npc.getEntity().getWorld().dropItem(npc.getEntity().getLocation(), equipment.get(EquipmentSlot.LEGGINGS));
						npc.destroy();
						player.sendMessage(getConfig().getString("StatueRemoved").replace("&", "§"));
					} else {
						player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
					}
				} else {
					player.sendMessage(getConfig().getString("TypeHelp").replace("&", "§"));
				}
				return true;
			} else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("unequip")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
					NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString() + ".obbyNpcID"));
					if(npc == null) {
						player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
						return true;
					}
					Equipment equipment = npc.getTrait(Equipment.class);
						if (args[1].equalsIgnoreCase("helmet")) {
							if(equipment.get(EquipmentSlot.HELMET) == null) {
								player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
								return true;
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HELMET));
							equipment.set(EquipmentSlot.HELMET, null);
						} else if (args[1].equalsIgnoreCase("chestplate")) {
							if(equipment.get(EquipmentSlot.CHESTPLATE) == null) {
								player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
								return true;
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.CHESTPLATE));
							equipment.set(EquipmentSlot.CHESTPLATE, null);
						} else if (args[1].equalsIgnoreCase("leggings")) {
							if(equipment.get(EquipmentSlot.LEGGINGS) == null) {
								player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
								return true;
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.LEGGINGS));
							equipment.set(EquipmentSlot.LEGGINGS, null);
						} else if (args[1].equalsIgnoreCase("boots")) {
							if(equipment.get(EquipmentSlot.BOOTS) == null) {
								player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
								return true;
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.BOOTS));
							equipment.set(EquipmentSlot.BOOTS, null);
						} else {
							if(equipment.get(EquipmentSlot.HAND) == null) {
								player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
								return true;
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
							equipment.set(EquipmentSlot.HAND, null);
						}
						econ.withdrawPlayer(player, getConfig().getDouble("UnequipCommandCost"));
						return true;
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("unequip")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Only players can execute this command.");
				return true;
			} else {
				Player player = (Player) sender;
				File playerFile = new File(getDataFolder() + "/players.yml");
				YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
				if (econ.getBalance(player) >= getConfig().getDouble("UnequipCommandCost")) {
					if (config.getBoolean(player.getUniqueId().toString() + ".hasStatue")) {
						NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString() + ".npcID"));
						if(npc == null) {
							player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
							return true;
						}
						Equipment equipment = npc.getTrait(Equipment.class);
						if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
							if(equipment.get(EquipmentSlot.HAND) == null) {
								player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
								return true;
							}
							if(getConfig().getString("UnequipedMessage").contains("::")) {
								player.sendMessage(getConfig().getString("UnequipedMessage").split("::")[0].replace("&", "§"));
								player.sendMessage(getConfig().getString("UnequipedMessage").split("::")[1].replace("&", "§"));
							} else
								player.sendMessage(getConfig().getString("UnequipedMessage").replace("&", "§"));
							if (args.length == 0) {
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
								equipment.set(EquipmentSlot.HAND, null);
							} else if (args.length == 1) {
								if (args[0].equalsIgnoreCase("helmet")) {
									if(equipment.get(EquipmentSlot.HELMET) == null) {
										player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
										return true;
									}
									player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HELMET));
									equipment.set(EquipmentSlot.HELMET, null);
								} else if (args[0].equalsIgnoreCase("chestplate")) {
									if(equipment.get(EquipmentSlot.CHESTPLATE) == null) {
										player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
										return true;
									}
									player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.CHESTPLATE));
									equipment.set(EquipmentSlot.CHESTPLATE, null);
								} else if (args[0].equalsIgnoreCase("leggings")) {
									if(equipment.get(EquipmentSlot.LEGGINGS) == null) {
										player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
										return true;
									}
									player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.LEGGINGS));
									equipment.set(EquipmentSlot.LEGGINGS, null);
								} else if (args[0].equalsIgnoreCase("boots")) {
									if(equipment.get(EquipmentSlot.BOOTS) == null) {
										player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
										return true;
									}
									player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.BOOTS));
									equipment.set(EquipmentSlot.BOOTS, null);
								} else {
									player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
									equipment.set(EquipmentSlot.HAND, null);
								}
							} else {
								if(equipment.get(EquipmentSlot.HAND) == null) {
									player.sendMessage(getConfig().getString("NPCCantUnequipAir").replace("&", "§"));
									return true;
								}
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
								equipment.set(EquipmentSlot.HAND, null);
							}
							econ.withdrawPlayer(player, getConfig().getDouble("UnequipCommandCost"));
							return true;
						} else {
							player.sendMessage(getConfig().getString("NeedHoldAir").replace("&", "§"));
							return true;
						}
					} else {
						player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
					}
				} else {
					player.sendMessage(getConfig().getString("NotEnoughMoney").replace("&", "§"));
				}
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("equip")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Only players can execute this command.");
				return true;
			} else {
				Player player = (Player) sender;
				File playerFile = new File(getDataFolder() + "/players.yml");
				YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
				if (econ.getBalance(player) >= getConfig().getDouble("EquipCommandCost")) {
					if (config.getBoolean(player.getUniqueId().toString() + ".hasStatue")) {
						NPC npc = CitizensAPI.getNPCRegistry().getById(config.getInt(player.getUniqueId().toString()+ ".npcID"));
						if(npc == null) {
							player.sendMessage(getConfig().getString("StatueDoesNotExist").replace("&", "§"));
							return true;
						}
						Equipment equipment = npc.getTrait(Equipment.class);					
						if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HELMET))|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_HELMET))) {
							final ItemStack item = player.getInventory().getItemInMainHand();
							Repairable meta = (Repairable)item.getItemMeta();
							config.set(player.getUniqueId().toString() + ".helmetRepairCost", meta.getRepairCost());
							try {
								config.save(playerFile);
							} catch (IOException e) {
								e.printStackTrace();
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HELMET));
							equipment.set(EquipmentSlot.HELMET, item);
							equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
							equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
							equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
							equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
						} else if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_CHESTPLATE))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_CHESTPLATE))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_CHESTPLATE))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_CHESTPLATE))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_CHESTPLATE))) {
							final ItemStack item = player.getInventory().getItemInMainHand();
							Repairable meta = (Repairable)item.getItemMeta();
							config.set(player.getUniqueId().toString() + ".chestplateRepairCost", meta.getRepairCost());
							try {
								config.save(playerFile);
							} catch (IOException e) {
								e.printStackTrace();
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.CHESTPLATE));
							equipment.set(EquipmentSlot.CHESTPLATE, item);
							equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
							equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
							equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
							equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
						} else if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_LEGGINGS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_LEGGINGS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_LEGGINGS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_LEGGINGS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_LEGGINGS))) {
							final ItemStack item = player.getInventory().getItemInMainHand();
							Repairable meta = (Repairable)item.getItemMeta();
							config.set(player.getUniqueId().toString() + ".leggingsRepairCost", meta.getRepairCost());
							try {
								config.save(playerFile);
							} catch (IOException e) {
								e.printStackTrace();
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.LEGGINGS));
							equipment.set(EquipmentSlot.LEGGINGS, item);
							equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
							equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
							equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
							equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
						} else if ((player.getInventory().getItemInMainHand().getType().equals(Material.LEATHER_BOOTS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_BOOTS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BOOTS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_BOOTS))
								|| (player.getInventory().getItemInMainHand().getType().equals(Material.CHAINMAIL_BOOTS))) {
							final ItemStack item = player.getInventory().getItemInMainHand();
							Repairable meta = (Repairable)item.getItemMeta();
							config.set(player.getUniqueId().toString() + ".bootsRepairCost", meta.getRepairCost());
							try {
								config.save(playerFile);
							} catch (IOException e) {
								e.printStackTrace();
							}
							player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.BOOTS));
							equipment.set(EquipmentSlot.BOOTS, item);
							equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
							equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
							equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
							equipment.set(EquipmentSlot.HAND, equipment.get(EquipmentSlot.HAND));
						} else {
							if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
								player.sendMessage(getConfig().getString("CannotEquipAir").replace("&", "§"));
								return true;
							} else {
								final ItemStack item = player.getInventory().getItemInMainHand();
								Repairable meta = (Repairable)item.getItemMeta();
								config.set(player.getUniqueId().toString() + ".handRepairCost", meta.getRepairCost());
								try {
									config.save(playerFile);
								} catch (IOException e) {
									e.printStackTrace();
								}
								player.getInventory().setItemInMainHand(equipment.get(EquipmentSlot.HAND));
								equipment.set(EquipmentSlot.HAND, item);
								equipment.set(EquipmentSlot.HELMET, equipment.get(EquipmentSlot.HELMET));
								equipment.set(EquipmentSlot.CHESTPLATE, equipment.get(EquipmentSlot.CHESTPLATE));
								equipment.set(EquipmentSlot.LEGGINGS, equipment.get(EquipmentSlot.LEGGINGS));
								equipment.set(EquipmentSlot.BOOTS, equipment.get(EquipmentSlot.BOOTS));
							}
						}
						econ.withdrawPlayer(player, getConfig().getDouble("EquipCommandCost"));
						if(getConfig().getString("EquipedMessage").contains("::")) {
							player.sendMessage(getConfig().getString("EquipedMessage").split("::")[0].replace("&", "§"));
							player.sendMessage(getConfig().getString("EquipedMessage").split("::")[1].replace("&", "§"));
						} else
							player.sendMessage(getConfig().getString("EquipedMessage").replace("&", "§"));	
						return true;
					} else {
						player.sendMessage(getConfig().getString("NoStatuesPurchased").replace("&", "§"));
					}
				} else {
					player.sendMessage(getConfig().getString("NotEnoughMoney").replace("&", "§"));
				}
			}
			return true;
		}
		return false;

	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public void spawnNPC(Player player, Location loc) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.createNPC(EntityType.PLAYER, player.getName());
		LookClose lookclose = new LookClose();
		lookclose.lookClose(true);
		Location location = new Location(loc.getWorld(), loc.getX() + 0.5, loc.getY(), loc.getZ() + 0.5);
		npc.spawn(location);
		npc.setName(player.getName());
		npc.addTrait(lookclose);
		File playerFile = new File(getDataFolder() + "/players.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
		config.set(player.getUniqueId().toString() + ".npcID", npc.getId());
		config.set(player.getUniqueId().toString() + ".hasStatue", true);
		try {
			config.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (sign.getLine(0).equals("[§1Statue§0]")) {
					File playerFile = new File(getDataFolder() + "/players.yml");
					YamlConfiguration config = YamlConfiguration
							.loadConfiguration(playerFile);
					if (config.getBoolean(player.getUniqueId().toString() + ".hasStatue")) {
						player.sendMessage(getConfig().getString("AlreadyOwnStatue").replace("&", "§"));
					} else if (econ.getBalance(player) >= Integer.parseInt(sign.getLine(1).replace("$", ""))) {
						econ.withdrawPlayer(player,Integer.parseInt(sign.getLine(1).replace("$","")));
						sign.getBlock().setType(org.bukkit.Material.AIR);
						spawnNPC(player, sign.getLocation());
						player.sendMessage(getConfig().getString("PurchasedStatue").replace("&", "§"));
						Bukkit.broadcastMessage(getConfig().getString("BroadcastMessage").replace("&", "§").replace("[username]", player.getName()));
					} else {
						player.sendMessage(getConfig().getString("NotEnoughMoney").replace("&", "§"));
					}
				}

			}
		}
	}

	@EventHandler
	public void onSign(SignChangeEvent event) {
		Player player = event.getPlayer();
		if (event.getLine(0).equalsIgnoreCase("[statue]")) {
			if (player.hasPermission("qubecraft.admin")) {
				event.setLine(0, "[§1Statue§0]");
				if (event.getLine(1) == "") {
					event.setLine(1, "$200000");
				} else if (!event.getLine(1).contains("$")) {
					event.setLine(1, "$" + event.getLine(1));
				}
				event.setLine(2, "Right click the");
				event.setLine(3, "sign to purchase!");
			} else {
				player.sendMessage("§cYou do not have permission to perform this action!");
				event.getBlock().breakNaturally();
			}
		}
		if(event.getLine(0).replace("&0", "").replace("§0", "").contains("[Statue]")) {
			if(!player.hasPermission("qubecraft.admin")) {
				player.sendMessage("§cYou do not have permission to perform this action!");
				event.getBlock().breakNaturally();
			}
		}
	}
	
	@EventHandler
	public void onNPCRemove(NPCRemoveEvent event) {
		Player player = Bukkit.getPlayerExact(event.getNPC().getFullName());
		NPC npc = event.getNPC();
		File playerFile = new File(getDataFolder() + "/players.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
		if(config.contains(player.getUniqueId().toString() + ".npcID")) {
			if(config.getInt(player.getUniqueId().toString() + ".npcID") == npc.getId()) {
				config.set(player.getUniqueId().toString() + ".npcID", null);
				config.set(player.getUniqueId().toString() + ".hasStatue", false);
				try {
					config.save(playerFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if(config.contains(player.getUniqueId().toString() + ".obbyNpcID")) {
			if(config.getInt(player.getUniqueId().toString() + ".obbyNpcID") == npc.getId()) {
				config.set(player.getUniqueId().toString() + ".obbyNpcID", null);
				config.set(player.getUniqueId().toString() + ".obbyHasStatue", false);
				try {
					config.save(playerFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@EventHandler
	public void onNPCRespawn(NPCSpawnEvent event) {
		@SuppressWarnings("deprecation")
		OfflinePlayer player = Bukkit.getOfflinePlayer(event.getNPC().getFullName());
		NPC npc = event.getNPC();
		File playerFile = new File(getDataFolder() + "/players.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
		Equipment equipment = npc.getTrait(Equipment.class);
		if(config.contains(player.getUniqueId().toString() + ".npcID")) {
			if(config.getInt(player.getUniqueId().toString() + ".npcID") == npc.getId()) {
				if(config.contains(player.getUniqueId().toString() + ".handRepairCost"))
					if(equipment.get(EquipmentSlot.HAND) != null)
						equipment.set(EquipmentSlot.HAND, setItem(equipment.get(EquipmentSlot.HAND), config.getInt(player.getUniqueId().toString() + ".handRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".helmetRepairCost"))
					if(equipment.get(EquipmentSlot.HELMET) != null)
						equipment.set(EquipmentSlot.HELMET, setItem(equipment.get(EquipmentSlot.HELMET), config.getInt(player.getUniqueId().toString() + ".helmetRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".chestplateRepairCost"))
					if(equipment.get(EquipmentSlot.CHESTPLATE) != null)
						equipment.set(EquipmentSlot.CHESTPLATE, setItem(equipment.get(EquipmentSlot.CHESTPLATE), config.getInt(player.getUniqueId().toString() + ".chestplateRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".leggingsRepairCost"))
					if(equipment.get(EquipmentSlot.LEGGINGS) != null)
						equipment.set(EquipmentSlot.LEGGINGS, setItem(equipment.get(EquipmentSlot.LEGGINGS), config.getInt(player.getUniqueId().toString() + ".leggingsRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".bootsRepairCost"))
					if(equipment.get(EquipmentSlot.BOOTS) != null)
						equipment.set(EquipmentSlot.BOOTS, setItem(equipment.get(EquipmentSlot.BOOTS), config.getInt(player.getUniqueId().toString() + ".bootsRepairCost")));

			} 
		}
		if(config.contains(player.getUniqueId().toString() + ".obbyNpcID")) {
			npc.setName("§d" + player.getName());
			if(config.getInt(player.getUniqueId().toString() + ".obbyNpcID") == npc.getId()) {
				if(config.contains(player.getUniqueId().toString() + ".obbyHandRepairCost"))
					if(equipment.get(EquipmentSlot.HAND) != null)
						equipment.set(EquipmentSlot.HAND, setItem(equipment.get(EquipmentSlot.HAND), config.getInt(player.getUniqueId().toString() + ".obbyHandRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".obbyHelmetRepairCost"))
					if(equipment.get(EquipmentSlot.HELMET) != null)
						equipment.set(EquipmentSlot.HELMET, setItem(equipment.get(EquipmentSlot.HELMET), config.getInt(player.getUniqueId().toString() + ".obbyHelmetRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".obbyChestplateRepairCost"))
					if(equipment.get(EquipmentSlot.CHESTPLATE) != null)
						equipment.set(EquipmentSlot.CHESTPLATE, setItem(equipment.get(EquipmentSlot.CHESTPLATE), config.getInt(player.getUniqueId().toString() + ".obbyChestplateRepairCost")));
				if(config.contains(player.getUniqueId().toString() + ".obbyLeggingsRepairCost")) {
					if(equipment.get(EquipmentSlot.LEGGINGS) != null)
						equipment.set(EquipmentSlot.LEGGINGS, setItem(equipment.get(EquipmentSlot.LEGGINGS), config.getInt(player.getUniqueId().toString() + ".obbyLeggingsRepairCost")));
				} else if(config.contains(player.getUniqueId().toString() + ".obbyBootsRepairCost"))
					if(equipment.get(EquipmentSlot.BOOTS) != null)
						equipment.set(EquipmentSlot.BOOTS, setItem(equipment.get(EquipmentSlot.BOOTS), config.getInt(player.getUniqueId().toString() + ".obbyBootsRepairCost")));
			}
		}
	}
	
	public ItemStack setItem(ItemStack item, int repaircost) {
		ItemMeta repair = item.getItemMeta();
		((Repairable) repair).setRepairCost(repaircost);
		item.setItemMeta(repair);
		return item;
	}

}