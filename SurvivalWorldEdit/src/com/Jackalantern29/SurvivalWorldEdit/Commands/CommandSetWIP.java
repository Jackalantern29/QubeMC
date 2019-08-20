package com.Jackalantern29.SurvivalWorldEdit.Commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.Jackalantern29.SurvivalWorldEdit.Util.Blocks;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.patterns.SingleBlockPattern;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.util.Countable;

@SuppressWarnings("deprecation")
public class CommandSetWIP implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("set")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only players can use this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("survivalworldedit.command.set")) {
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
			if(args.length == 0) {
				player.sendMessage("§cMissing value for <fillPattern>.");
				return true;
			} else if(args.length == 1) {
				Material material;
				byte damage;
				if(args[0].contains(":")) {
					if(StringUtils.isNumeric(args[0].split(":")[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0].split(":")[0])) != null) {
							material = Material.getMaterial(Integer.parseInt(args[0].split(":")[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0].split(":")[0] + "'");
							return true;
						}
					} else {
						try {
							material = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0].split(":")[0] + "' refers to");
							return true;
						}
					}
					damage = Byte.valueOf(args[0].split(":")[1]);
				} else {
					if(StringUtils.isNumeric(args[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0])) != null) {
							material = Material.getMaterial(Integer.parseInt(args[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0] + "'");
							return true;
						}
					} else {
						try {
							material = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0] + "' refers to");
							return true;
						}
					}
					damage = Byte.valueOf("0");
				}
				int amount = 0;
				if(player.getGameMode().equals(GameMode.SURVIVAL)) {
					for(ItemStack item : player.getInventory().getContents()) {
						if(item != null) {
							if(item.getType().equals(material)) {
								if(item.getData().getData() == damage) {
									amount = (amount + item.getAmount());
								}
							}
						}
					}
					EditSession session = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitUtil.getLocalWorld(player.getWorld()), amount);
					try {
						Vector v1 = new Vector(Blocks.getPos(player.getUniqueId(), 1).getBlockX(), Blocks.getPos(player.getUniqueId(), 1).getBlockY(), Blocks.getPos(player.getUniqueId(), 1).getBlockZ());
						Vector v2 = new Vector(Blocks.getPos(player.getUniqueId(), 2).getBlockX(), Blocks.getPos(player.getUniqueId(), 2).getBlockY(), Blocks.getPos(player.getUniqueId(), 2).getBlockZ());
						CuboidRegion region = new CuboidRegion(v1, v2);
						BaseBlock blocks = new BaseBlock(material.getId(), damage);
						SingleBlockPattern pattern = new SingleBlockPattern(blocks);
						session.setBlocks(region, pattern);
						for(Countable<BaseBlock> block : session.getBlockDistributionWithData(region)) {
							player.sendMessage("Changed: " + session.getBlockChangeCount() + " | Material: " + Material.getMaterial(block.getID().getId()) + ":" + block.getID().getData() + " | Amount: " + block.getAmount());
						}							
						//session.makeSphere(BukkitUtil.toVector(l), new SingleBlockPattern(new BaseBlock(Material.GLASS.getId())), getConfig().getInt("Options.DropZone-Area"), false);
					} catch (MaxChangedBlocksException e) {
					}
				}
			}
			
			/*else if(args.length == 1) {
				List<Block> blocks = Blocks.getBlocks(player.getUniqueId());
				Material material;
				byte damage;
				if(args[0].contains(":")) {
					if(StringUtils.isNumeric(args[0].split(":")[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0].split(":")[0])) != null) {
							material = Material.getMaterial(Integer.parseInt(args[0].split(":")[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0].split(":")[0] + "'");
							return true;
						}
					} else {
						try {
							material = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0].split(":")[0] + "' refers to");
							return true;
						}
					}
					damage = Byte.valueOf(args[0].split(":")[1]);
				} else {
					if(StringUtils.isNumeric(args[0])) {
						if(Material.getMaterial(Integer.parseInt(args[0])) != null) {
							material = Material.getMaterial(Integer.parseInt(args[0]));
						} else {
							player.sendMessage("§cDoes not match a valid block type: '" + args[0] + "'");
							return true;
						}
					} else {
						try {
							material = Material.valueOf(args[0].split(":")[0].toUpperCase());
						} catch(IllegalArgumentException e) {
							player.sendMessage("§cCan't figure out what block '" + args[0] + "' refers to");
							return true;
						}
					}
					damage = Byte.valueOf("0");
				}
				List<BlockData> list = new ArrayList<>();
				int amount = 0;
				for(Block block : blocks) {
					if(player.getGameMode().equals(GameMode.SURVIVAL)) {
						for(ItemStack item : player.getInventory().getContents()) {
							if(!material.equals(Material.AIR)) {
								if(item != null) {
									if(item.getType().equals(material)) {
										if(item.getData().getData() == damage) {
											if(item.getData().getData() != block.getData()) {
												ItemStack item2 = new ItemStack(block.getType(), 1, Short.valueOf(block.getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block));
												amount++;
												//block.breakNaturally();
												block.setTypeIdAndData(material.getId(), damage, true);
												if(item.getAmount() == 1) {
													player.getInventory().remove(item);
												} else {
													ItemStack item3 = item;
													item.setAmount(item.getAmount() - 1);
													item = item3;
												}
											} else if(!item.getType().equals(block.getType())) {
												ItemStack item2 = new ItemStack(block.getType(), 1, Short.valueOf(block.getData()));
												player.getInventory().addItem(item2);
												list.add(new BlockData(block));
												amount++;
												//block.breakNaturally();
												block.setTypeIdAndData(material.getId(), damage, true);
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
								if(item == null) {
									if(!block.getType().equals(Material.AIR)) {
										ItemStack item2 = new ItemStack(block.getType(), 1, Short.valueOf(block.getData()));
										player.getInventory().addItem(item2);
										list.add(new BlockData(block));
										amount++;
										//block.breakNaturally();
										block.setTypeIdAndData(material.getId(), damage, true);
									}
								}
							}
						}
					} else {
						list.add(new BlockData(block));
						amount++;
						block.setTypeIdAndData(material.getId(), damage, true);
					}
				}
				Blocks.addUndoBlocks(player.getUniqueId(), list);
				player.sendMessage("§dOperation completed (" + amount + " blocks affected).");
				return true;
			}*/
		}
		return false;
	}

}
