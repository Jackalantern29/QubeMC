package com.Jackalantern29.SurvivalWorldEdit.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Blocks {
	private static Map<UUID, Location> pos1 = new HashMap<>();
	private static Map<UUID, Location> pos2 = new HashMap<>();
	private static Map<UUID, Map<Integer, List<BlockData>>> undoBlocks = new HashMap<>();
	private static Map<UUID, Map<Integer, List<BlockData>>> redoBlocks = new HashMap<>();
	
	public static void setPos(UUID uuid, int pos, Location location) {
		if(pos == 1) {
			if(!pos1.containsKey(uuid)) {
				pos1.put(uuid, location);
			} else {
				pos1.replace(uuid, location);
			}
		} else if(pos == 2) {
			if(!pos2.containsKey(uuid)) {
				pos2.put(uuid, location);
			} else {
				pos2.replace(uuid, location);
			}
		}
	}
	public static List<Block> getBlocks(UUID uuid) {
		List<Block> blocks = new ArrayList<>();
		Location loc1 = pos1.get(uuid);
		Location loc2 = pos2.get(uuid);
	    int topBlockX = loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX();
	    int bottomBlockX = loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX();

	    int topBlockY = loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY();
	    int bottomBlockY = loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY();

	    int topBlockZ = loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ();
	    int bottomBlockZ = loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ();

	    for (int x = bottomBlockX; x <= topBlockX; x++) {
	      for (int z = bottomBlockZ; z <= topBlockZ; z++) {
	        for (int y = bottomBlockY; y <= topBlockY; y++) {
	          Block block = loc1.getWorld().getBlockAt(x, y, z);

	          blocks.add(block);
	        }
	      }
	    }

	    return blocks;
	}
	
	@SuppressWarnings("deprecation")
	public static void replaceBlocks(Player player, BlockData block1, BlockData block2) {
		if(block2 == null) {
			setBlocks(player, block1);
			return;
		}
		int amount = 0;
		Material mat1 = block1.getMaterial();
		Material mat2 = block2.getMaterial();
		
		byte data1 = block1.getDamage();
		byte data2 = block2.getDamage();
		if(!ItemOption.hasItem(player.getInventory().getContents(), mat2, data2)) {
			player.sendMessage("§cYou are required to have those blocks to perform that action.");
			return;
		}
		if(player.getLevel() <= 3) {
			player.sendMessage("§c§lERROR: §cYou do not have enough Experience Levels. Minimum required is 3.");
			return;
		}
		player.setLevel(player.getLevel() - 3);
		for(Block blocks : getBlocks(player.getUniqueId())) {
			if(player.getGameMode().equals(GameMode.SURVIVAL)) {
				if(!blocks.getType().equals(Material.BEDROCK)) {
					for(ItemStack item : player.getInventory().getContents()) {
						if(!mat2.equals(Material.AIR)) {
							if(item != null) {
								if(item.getType().equals(mat2)) {
									if(item.getData().getData() == data2) {
										if(item.getData().getData() != blocks.getData() || !item.getType().equals(blocks.getType())) {
											if(blocks.getType().equals(mat1) && blocks.getData() == data1) {
												if(ItemOption.countEmpty(player) <= 1) {
													player.sendMessage("§cThere are not enough empty slots in your inventory.");
													player.sendMessage("§cYou must make some more slots to continue. Current Operation will now cancel.");
													return;
												} else {
													player.getInventory().addItem(ItemOption.getItemFromBlock(new BlockData(blocks)));	
													if(blocks.getType().equals(Material.CHEST))
														((Chest)blocks.getState()).getBlockInventory().clear();
													blocks.setTypeIdAndData(mat2.getId(), data2, false);
													amount++;
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
							}
						} else {
							if(item == null) {
								if(!blocks.getType().equals(Material.AIR)) {
									if(blocks.getType().equals(mat1) && blocks.getData() == data1) {
										if(ItemOption.countEmpty(player) <= 1) {
											player.sendMessage("§cThere are not enough empty slots in your inventory.");
											player.sendMessage("§cYou must make some more slots to continue. Current Operation will now cancel.");
											return;
										} else {
											player.getInventory().addItem(ItemOption.getItemFromBlock(new BlockData(blocks)));
											if(blocks.getType().equals(Material.CHEST))
												((Chest)blocks.getState()).getBlockInventory().clear();
											blocks.setTypeIdAndData(mat2.getId(), data2, true);
											amount++;
										}
									}
								}
							}
						}
					}
				}
			} else if(player.getGameMode().equals(GameMode.CREATIVE)) {
				amount++;
				if(blocks.getType().equals(mat1) && blocks.getData() == data1)
					blocks.setTypeIdAndData(mat2.getId(), data2, true);
			}
		}
		if(amount != 0)
			player.sendMessage("§d" + amount + " block(s) have been replaced.");
		else
			player.sendMessage("§cYou will need the right tools to change some blocks.");
	}
	@SuppressWarnings("deprecation")
	public static void setBlocks(Player player, BlockData block) {
		//List<BlockData> list = new ArrayList<>();
		int amount = 0;
		Material material = block.getMaterial();
		byte data = block.getDamage();
		if(player.getLevel() <= 3) {
			player.sendMessage("§c§lERROR: §cYou do not have enough Experience Levels. Minimum required is 3.");
			return;
		}
		player.setLevel(player.getLevel() - 3);
		for(Block blocks : getBlocks(player.getUniqueId())) {
			if(player.getGameMode().equals(GameMode.SURVIVAL)) {
				if(!blocks.getType().equals(Material.BEDROCK)) {
					for(ItemStack item : player.getInventory().getContents()) {
						if(!material.equals(Material.AIR)) {
							if(item != null) {
								if(item.getType().equals(material)) {
									if(item.getData().getData() == data) {
										if(item.getData().getData() != blocks.getData() || !item.getType().equals(blocks.getType())) {
											if(ItemOption.countEmpty(player) <= 1) {
												player.sendMessage("§cThere are not enough empty slots in your inventory.");
												player.sendMessage("§cYou must make some more slots to continue. Current Operation will now cancel.");
												return;
											} else {
												player.getInventory().addItem(ItemOption.getItemFromBlock(new BlockData(blocks)));		
												if(blocks.getType().equals(Material.CHEST))
													((Chest)blocks.getState()).getBlockInventory().clear();
												blocks.setTypeIdAndData(material.getId(), data, true);
												amount++;
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
							if(item == null) {
								if(!blocks.getType().equals(Material.AIR)) {
									if(ItemOption.countEmpty(player) <= 1) {
										player.sendMessage("§cThere are not enough empty slots in your inventory.");
										player.sendMessage("§cYou must make some more slots to continue. Current Operation will now cancel.");
										return;
									} else {
										player.getInventory().addItem(ItemOption.getItemFromBlock(new BlockData(blocks)));	
										if(blocks.getType().equals(Material.CHEST))
											((Chest)blocks.getState()).getBlockInventory().clear();;
										blocks.setTypeIdAndData(material.getId(), data, true);
										amount++;
									}
								}
							}
						}
					}
				}
			} else if(player.getGameMode().equals(GameMode.CREATIVE)) {
				amount++;
				blocks.setTypeIdAndData(material.getId(), data, true);
			}
		}
		player.sendMessage("§dOperation completed (" + amount + " blocks affected).");
	}
	
	public static boolean isPosNull(UUID uuid, int pos) {
		if(pos == 1) {
			if(!pos1.containsKey(uuid))
				return true;
			if(pos1.get(uuid) == null)
				return true;
			return false;
		} else if(pos == 2) {
			if(!pos2.containsKey(uuid))
				return true;
			if(pos2.get(uuid) == null)
				return true;
			return false;
		}
		return false;
	}
	
	public static Location getPos(UUID uuid, int pos) {
		if(pos == 1) {
			if(!isPosNull(uuid, pos))
				return pos1.get(uuid);
			else
				return null;
		} else if(pos == 2) {
			if(!isPosNull(uuid, pos))
				return pos2.get(uuid);
			else
				return null;
		} else
			return null;
	}
	
	public static void addUndoBlocks(UUID uuid, List<BlockData> blocks) {
		if(!undoBlocks.containsKey(uuid)) {
			Map<Integer, List<BlockData>> map = new HashMap<>();
			map.put(1, blocks);
			undoBlocks.put(uuid, map);
		} else {
			Map<Integer, List<BlockData>> map = undoBlocks.get(uuid);
			map.put(undoBlocks.get(uuid).values().size() + 1, blocks);
			undoBlocks.replace(uuid, map);
		}
	}
	
	public static List<BlockData> getUndoBlocks(UUID uuid, int slot) {
		if(!undoBlocks.containsKey(uuid)) {
			return null;
		} else {
			final List<BlockData> blocks = undoBlocks.get(uuid).get(slot);
			return blocks;
		}
	}
	public static List<BlockData> getLatestUndoBlocks(UUID uuid) {
		if(!undoBlocks.containsKey(uuid)) {
			return null;
		} else {
			final List<BlockData> blocks = undoBlocks.get(uuid).get(undoBlocks.get(uuid).values().size());
			return blocks;
		}
	}
	public static void removeUndoBlocks(UUID uuid, int slot) {
		if(!undoBlocks.containsKey(uuid)) {
			return;
		} else {
			Map<Integer, List<BlockData>> map = undoBlocks.get(uuid);
			map.remove(slot);
			undoBlocks.replace(uuid, map);
		}
	}
	public static int getUndoLatestCount(UUID uuid) {
		if(!undoBlocks.containsKey(uuid)) {
			return 0;
		} else {
			return undoBlocks.get(uuid).values().size();
		}
	}
	
	//REDO
	
	public static void addRedoBlocks(UUID uuid, List<BlockData> blocks) {
		if(!redoBlocks.containsKey(uuid)) {
			Map<Integer, List<BlockData>> map = new HashMap<>();
			map.put(1, blocks);
			redoBlocks.put(uuid, map);
		} else {
			Map<Integer, List<BlockData>> map = redoBlocks.get(uuid);
			map.put(redoBlocks.get(uuid).values().size() + 1, blocks);
			redoBlocks.replace(uuid, map);
		}
	}
	
	public static List<BlockData> getRedoBlocks(UUID uuid, int slot) {
		if(!redoBlocks.containsKey(uuid)) {
			return null;
		} else {
			final List<BlockData> blocks = redoBlocks.get(uuid).get(slot);
			return blocks;
		}
	}
	public static List<BlockData> getLatestRedoBlocks(UUID uuid) {
		if(!redoBlocks.containsKey(uuid)) {
			return null;
		} else {
			final List<BlockData> blocks = redoBlocks.get(uuid).get(redoBlocks.get(uuid).values().size());
			return blocks;
		}
	}
	public static void removeRedoBlocks(UUID uuid, int slot) {
		if(!redoBlocks.containsKey(uuid)) {
			return;
		} else {
			Map<Integer, List<BlockData>> map = redoBlocks.get(uuid);
			map.remove(slot);
			redoBlocks.replace(uuid, map);
		}
	}
	public static int getRedoLatestCount(UUID uuid) {
		if(!redoBlocks.containsKey(uuid)) {
			return 0;
		} else {
			return redoBlocks.get(uuid).values().size();
		}
	}
}
