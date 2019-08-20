package com.Jackalantern29.QCRewards.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.Jackalantern29.QCRewards.QubeMC;
import com.Jackalantern29.QCRewards.Stats;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.util.player.UserManager;

public class SignInteractListener implements Listener {
	
	@EventHandler
	public void onSignInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				if(!QubeMC.plugin.mysql.hasConnection())
					return;
				if (sign.getLine(0).equalsIgnoreCase(QubeMC.plugin.getConfig().getString("SignTitle").replace("&", "§"))) {
					for(String string : QubeMC.plugin.getConfig().getStringList("Command")) {
						String str = string.split(":")[0];
						String cmd = string.split(":")[1];
						if(sign.getLine(1).equalsIgnoreCase(str)) {
							if (Stats.getCoins(player.getUniqueId()) >= Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", ""))) {
								Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("/", "").replace("%player%", player.getName()).replace("%amount%", sign.getLine(2)));
								player.sendMessage(QubeMC.plugin.getConfig().getString("PurchaseMessage").replace("&", "§"));
								Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId()) - Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", "")));
							} else {
								player.sendMessage(QubeMC.plugin.getConfig().getString("NotEnoughCoins").replace("&", "§"));
							}
							return;
						}
					}
					if(QubeMC.plugin.mcmmo != null) {
						for (SkillType skills : SkillType.values()) {
							if (sign.getLine(1).equalsIgnoreCase("mcMMO " + skills.getName())) {
								if (UserManager.getPlayer(player).getSkillLevel(skills) >= 1000) {
									player.sendMessage(QubeMC.plugin.getConfig().getString("Skill1000").replace("&", "§")
											.replace("%skill%", skills.getName().replace("_", " ").replace("&", "§")));
									return;
								}
								if (Stats.getCoins(player.getUniqueId()) >= Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", ""))) {
									UserManager.getPlayer(player)
											.addLevels(SkillType
													.valueOf(sign.getLine(1).replace("mcMMO ", "").toUpperCase()),
											Integer.parseInt(sign.getLine(2).replace(" levels", "").replace(" level", "")));
									player.sendMessage(QubeMC.plugin.getConfig().getString("PurchaseMessage").replace("&", "§"));
									Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId())
											- Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", "")));
								} else {
									player.sendMessage(QubeMC.plugin.getConfig().getString("NotEnoughCoins").replace("&", "§"));
								}
								return;
							} else if (sign.getLine(1).equalsIgnoreCase("mcMMO Woodcuttin")) {
								if (UserManager.getPlayer(player).getSkillLevel(SkillType.WOODCUTTING) >= 1000) {
									player.sendMessage(QubeMC.plugin.getConfig().getString("Skill1000").replace("&", "§")
											.replace("%skill%", skills.getName().replace("_", " ").replace("&", "§")));
									return;
								}
								if (Stats.getCoins(player.getUniqueId()) >= Integer
										.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", ""))) {
									UserManager.getPlayer(player).addLevels(SkillType.WOODCUTTING,
											Integer.parseInt(sign.getLine(2).replace(" levels", "").replace(" level", "")));
									player.sendMessage(QubeMC.plugin.getConfig().getString("PurchaseMessage").replace("&", "§"));
									Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId())
											- Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", "")));
								} else {
									player.sendMessage(QubeMC.plugin.getConfig().getString("NotEnoughCoins").replace("&", "§"));
								}
								return;
							}
						}	
					}
					if (sign.getLine(1).equals("In-Game Money")) {
						if (Stats.getCoins(player.getUniqueId()) >= Integer
								.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", ""))) {
							QubeMC.econ.depositPlayer(player, Double.parseDouble(sign.getLine(2).replace("$", "")));
							player.sendMessage(QubeMC.plugin.getConfig().getString("PurchaseMessage").replace("&", "§"));
							Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId())
									- Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", "")));
						} else {
							player.sendMessage(QubeMC.plugin.getConfig().getString("NotEnoughCoins").replace("&", "§"));
						}
						return;
					} else if (sign.getLine(1).equals("Experience")) {
						if (Stats.getCoins(player.getUniqueId()) >= Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", ""))) {
							player.giveExpLevels(
									Integer.parseInt(sign.getLine(2).replace("s", "").replace(" level", "")));
							player.sendMessage(QubeMC.plugin.getConfig().getString("PurchaseMessage").replace("&", "§"));
							Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId())
									- Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", "")));
						} else {
							player.sendMessage(QubeMC.plugin.getConfig().getString("NotEnoughCoins").replace("&", "§"));
						}
						return;
					} else if (Stats.getCoins(player.getUniqueId()) >= Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", ""))) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage(QubeMC.plugin.getConfig().getString("InventoryFull").replace("&", "§"));
							return;
						}
						Material material = Material.valueOf(sign.getLine(1).split(":")[0]);
						Integer damageValue = Integer.valueOf(Integer.parseInt(sign.getLine(1).split(":")[1]));
						player.getInventory()
								.addItem(new ItemStack(material,
										Integer.parseInt(sign.getLine(2).replace("s", "").replace(" amount", "")),
										damageValue.shortValue()));
						player.sendMessage(QubeMC.plugin.getConfig().getString("PurchaseMessage").replace("&", "§"));
						Stats.setCoins(player.getUniqueId(), Stats.getCoins(player.getUniqueId())
								- Integer.parseInt(sign.getLine(3).replace(" QC", "").replace("§2", "")));
					} else {
						player.sendMessage(QubeMC.plugin.getConfig().getString("NotEnoughCoins").replace("&", "§"));
					}
				}
			}
		}
	}

}
