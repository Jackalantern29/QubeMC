package com.Jackalantern29.SurvivalWorldEdit.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.Jackalantern29.SurvivalWorldEdit.Util.BlockData;
import com.Jackalantern29.SurvivalWorldEdit.Util.Blocks;

public class CommandUndo implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("undo")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(player.hasPermission("survivalworldedit.command.undo")) {
				boolean t = true;
				if(t) {
					player.sendMessage("§c§lThis command is currently disabled.");
					return true;
				}
				if(Blocks.getLatestUndoBlocks(player.getUniqueId()) != null) {
					List<BlockData> list = new ArrayList<>();
					for(BlockData block : Blocks.getLatestUndoBlocks(player.getUniqueId())) {
						Material material = block.getMaterial();
						byte damage = block.getDamage();
						if(player.getGameMode().equals(GameMode.SURVIVAL)) {
							if(!material.equals(Material.AIR)) {
								for(ItemStack item : player.getInventory().getContents()) {
									if(item != null) {
										if(material.equals(Material.CHEST)) {
											if(item.getType().equals(material)) {
												ItemStack item2 = null;
												if(block.getBlock().getType().equals(Material.LOG)) {
													if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
													else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
													else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
												} else if(block.getBlock().getType().equals(Material.LOG_2)) {
													if(block.getBlock().getData() == 0 || block.getBlock().getData() == 4 || block.getBlock().getData() == 8)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
												} else
													item2 = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getBlock().getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block.getBlock()));
												
												//block.getBlock().breakNaturally();
												block.getBlock().setTypeIdAndData(material.getId(), damage, true);
												if(item.getAmount() == 1) {
													player.getInventory().remove(item);
												} else {
													ItemStack item3 = item;
													item.setAmount(item.getAmount() - 1);
													item = item3;
												}
											}
										} else if(material.equals(Material.LOG)) {
											if(item.getType().equals(Material.LOG)) {
												ItemStack item2 = null;
												if(block.getBlock().getType().equals(Material.LOG)) {
													if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
													else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
													else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
												} else if(block.getBlock().getType().equals(Material.LOG_2)) {
													if(block.getBlock().getData() == 0 || block.getBlock().getData() == 4 || block.getBlock().getData() == 8)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
												} else
													item2 = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getBlock().getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block.getBlock()));
												block.getBlock().setTypeIdAndData(material.getId(), damage, true);
												if(block.getBlock().getType() != Material.AIR) {
													if(item.getAmount() == 1) {
														player.getInventory().remove(item);
													} else {
														ItemStack item3 = item;
														item.setAmount(item.getAmount() - 1);
														item = item3;
													}
												}
											}
										} else if(material.equals(Material.LOG_2)) {
											if(item.getType().equals(Material.LOG_2)) {
												ItemStack item2 = null;
												if(block.getBlock().getType().equals(Material.LOG)) {
													if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
													else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
													else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
												} else if(block.getBlock().getType().equals(Material.LOG_2)) {
													if(block.getBlock().getData() == 0 || block.getBlock().getData() == 4 || block.getBlock().getData() == 8)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
												} else
													item2 = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getBlock().getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block.getBlock()));
												block.getBlock().setTypeIdAndData(material.getId(), damage, true);
												if(block.getBlock().getType() != Material.AIR) {
													if(item.getAmount() == 1) {
														player.getInventory().remove(item);
													} else {
														ItemStack item3 = item;
														item.setAmount(item.getAmount() - 1);
														item = item3;
													}
												}
											}
										} else if(material.equals(Material.SIGN_POST)) {
											if(item.getType().equals(Material.SIGN)) {
												ItemStack item2 = null;
												if(block.getBlock().getType().equals(Material.LOG)) {
													if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
													else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
													else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
												} else if(block.getBlock().getType().equals(Material.LOG_2)) {
													if(block.getBlock().getData() == 0 || block.getBlock().getData() == 4 || block.getBlock().getData() == 8)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
												} else
													item2 = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getBlock().getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block.getBlock()));
												//block.getBlock().breakNaturally();
												block.getBlock().setTypeIdAndData(material.getId(), damage, true);
												if(item.getAmount() == 1) {
													player.getInventory().remove(item);
												} else {
													ItemStack item3 = item;
													item.setAmount(item.getAmount() - 1);
													item = item3;
												}
											}
										} else if(item.getType().equals(material)) {
											if(item.getData().getData() == damage) {
												ItemStack item2 = null;
												if(block.getBlock().getType().equals(Material.LOG)) {
													if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
													else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
													else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
												} else if(block.getBlock().getType().equals(Material.LOG_2)) {
													if(block.getBlock().getData() == 0 || block.getBlock().getData() == 4 || block.getBlock().getData() == 8)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
												} else
													item2 = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getBlock().getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block.getBlock()));
												
												//block.getBlock().breakNaturally();
												block.getBlock().setTypeIdAndData(material.getId(), damage, true);
												if(item.getAmount() == 1) {
													player.getInventory().remove(item);
												} else {
													ItemStack item3 = item;
													item.setAmount(item.getAmount() - 1);
													item = item3;
												}
											}
										}
									}
								}
							} else {
								for(ItemStack item : player.getInventory().getContents()) {
									if(item != null) {
										if(block.getBlock().getType().equals(Material.SIGN_POST)) {
											ItemStack item2 = new ItemStack(Material.SIGN, 1, (short)0);
											player.getInventory().addItem(item2);
											list.add(new BlockData(block.getBlock()));
											//block.getBlock().breakNaturally();
											block.getBlock().setTypeIdAndData(material.getId(), damage, true);
											if(block.getBlock().getType() != Material.AIR) {
												if(item.getType().equals(block.getBlock().getType())) {
													if(item.getAmount() == 1) {
														player.getInventory().remove(item);
													} else {
														ItemStack item3 = item;
														item.setAmount(item.getAmount() - 1);
														item = item3;
													}
												}
											}
										} else if(block.getBlock().getType().equals(Material.LOG)) {
											ItemStack item2 = null;
											if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
												item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
											else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
												item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
											else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
												item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
											else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
												item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
											player.getInventory().addItem(item2);
											list.add(new BlockData(block.getBlock()));
											block.getBlock().setTypeIdAndData(material.getId(), damage, true);
											if(block.getBlock().getType() != Material.AIR) {
												if(item.getType().equals(block.getBlock().getType())) {
													if(item.getAmount() == 1) {
														player.getInventory().remove(item);
													} else {
														ItemStack item3 = item;
														item.setAmount(item.getAmount() - 1);
														item = item3;
													}
												}
											}
										} else if(block.getBlock().getType().equals(Material.LOG_2)) {
											ItemStack item2 = null;
											if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
												item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
											else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
												item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
											player.getInventory().addItem(item2);
											list.add(new BlockData(block.getBlock()));
											block.getBlock().setTypeIdAndData(material.getId(), damage, true);
											if(block.getBlock().getType() != Material.AIR) {
												if(item.getType().equals(block.getBlock().getType())) {
													if(item.getAmount() == 1) {
														player.getInventory().remove(item);
													} else {
														ItemStack item3 = item;
														item.setAmount(item.getAmount() - 1);
														item = item3;
													}
												}
											}
										} else if(item.getType().equals(block.getBlock().getType())) {
											if(item.getData().getData() == block.getBlock().getData()) {
												ItemStack item2 = null;
												if(block.getBlock().getType().equals(Material.LOG)) {
													if((block.getBlock().getData() == 0) || (block.getBlock().getData() == 4) || (block.getBlock().getData() == 8))
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
													else if(block.getBlock().getData() == 2 || block.getBlock().getData() == 6 || block.getBlock().getData() == 10)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)2);
													else if(block.getBlock().getData() == 3 || block.getBlock().getData() == 7 || block.getBlock().getData() == 11)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)3);
												} else if(block.getBlock().getType().equals(Material.LOG_2)) {
													if(block.getBlock().getData() == 0 || block.getBlock().getData() == 4 || block.getBlock().getData() == 8)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)0);
													else if(block.getBlock().getData() == 1 || block.getBlock().getData() == 5 || block.getBlock().getData() == 9)
														item2 = new ItemStack(block.getBlock().getType(), 1, (short)1);
												} else
													item2 = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getBlock().getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block.getBlock()));
												
												//block.getBlock().breakNaturally();
												block.getBlock().setTypeIdAndData(material.getId(), damage, true);
												if(block.getBlock().getType() != Material.AIR) {
													if(item.getAmount() == 1) {
														player.getInventory().remove(item);
													} else {
														ItemStack item3 = item;
														item.setAmount(item.getAmount() - 1);
														item = item3;
													}
												}
											}
										} else if(block.getBlock().getType().equals(Material.AIR)) {
											list.add(new BlockData(block.getBlock()));
											
											//block.getBlock().breakNaturally();
											block.getBlock().setTypeIdAndData(material.getId(), damage, true);
											if(block.getBlock().getType() != Material.AIR) {
												if(item.getAmount() == 1) {
													player.getInventory().remove(item);
												} else {
													ItemStack item3 = item;
													item.setAmount(item.getAmount() - 1);
													item = item3;
												}
											}
										}
									}
								}
							}
						} else {
							list.add(new BlockData(block.getBlock()));
							block.getBlock().setTypeIdAndData(material.getId(), damage, true);
						}
						/*ItemStack item = new ItemStack(block.getBlock().getType(), 1, Short.valueOf(block.getDamage()));
						player.getInventory().addItem(item);
						block.getBlock().setTypeIdAndData(block.getMaterial().getId(), block.getDamage(), true);
						ItemStack item2 = new ItemStack(block.getMaterial(), 1, Short.valueOf(block.getDamage()));
						player.getInventory().remove(item2);*/
					}
					Blocks.removeUndoBlocks(player.getUniqueId(), Blocks.getUndoLatestCount(player.getUniqueId()));
					Blocks.addRedoBlocks(player.getUniqueId(), list);
					player.sendMessage("§dUndo Successful.");
				}
			}
		}
		return false;
	}
}
