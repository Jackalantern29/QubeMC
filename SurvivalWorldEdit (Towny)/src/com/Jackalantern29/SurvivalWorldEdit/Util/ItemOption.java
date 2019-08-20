package com.Jackalantern29.SurvivalWorldEdit.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import com.Jackalantern29.SurvivalWorldEdit.QubeMC;

public class ItemOption {
	
	public static ItemStack getItemFromBlock(BlockData block) {
		ItemStack item = null;
		if(block.getMaterial().equals(Material.LOG)) {
			if(block.getDamage() == 0 || block.getDamage() == 4 || block.getDamage() == 8)
				item = new ItemStack(Material.LOG, 1, (short)0);
			else if(block.getDamage() == 1 || block.getDamage() == 5 || block.getDamage() == 9)
				item = new ItemStack(Material.LOG, 1, (short)1);
			else if(block.getDamage() == 2 || block.getDamage() == 6 || block.getDamage() == 10)
				item = new ItemStack(Material.LOG, 1, (short)2);
			else if(block.getDamage() == 3 || block.getDamage() == 7 || block.getDamage() == 11)
				item = new ItemStack(Material.LOG, 1, (short)3);
		} else if(block.getMaterial().equals(Material.LOG_2)) {
			if(block.getDamage() == 0 || block.getDamage() == 4 || block.getDamage() == 8)
				item = new ItemStack(Material.LOG_2, 1, (short)0);
			else if(block.getDamage() == 1 || block.getDamage() == 5 || block.getDamage() == 9)
				item = new ItemStack(Material.LOG_2, 1, (short)1);
		} else if(block.getMaterial().equals(Material.CHEST)) {
			item = new ItemStack(Material.CHEST, 1, (short)0);
			BlockStateMeta meta = (BlockStateMeta)item.getItemMeta();
			Chest chest = (Chest)meta.getBlockState();
			if(block.getBlockState() != null) {
				chest.getBlockInventory().setContents(((Chest)block.getBlockState()).getBlockInventory().getContents());
				meta.setBlockState(chest);
				List<String> list = new ArrayList<>();
				list.add("§7WorldEdit Contained Chest");
				meta.setLore(list);
				if(!((Chest)block.getBlockState()).getBlockInventory().getTitle().equals("container.chest"))
					meta.setDisplayName(((Chest)block.getBlockState()).getBlockInventory().getTitle());
				else
					meta.setDisplayName("§rChest");
				item.setItemMeta(meta);
			}
		} else if(block.getMaterial().equals(Material.HOPPER)) {
			item = new ItemStack(Material.HOPPER, 1, (short)0);
		} else if(block.getMaterial().equals(Material.SIGN_POST))
			item = new ItemStack(Material.SIGN, 1, (short)0);
		else if(block.getMaterial().equals(Material.WALL_SIGN))
			item = new ItemStack(Material.SIGN, 1, (short)0);
		else if(block.getMaterial().equals(Material.WOOD_STAIRS))
			item = new ItemStack(Material.WOOD_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.COBBLESTONE_STAIRS))
			item = new ItemStack(Material.COBBLESTONE_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.BRICK_STAIRS))
			item = new ItemStack(Material.BRICK_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.SMOOTH_STAIRS))
			item = new ItemStack(Material.SMOOTH_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.NETHER_BRICK_STAIRS))
			item = new ItemStack(Material.NETHER_BRICK_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.SANDSTONE_STAIRS))
			item = new ItemStack(Material.SANDSTONE_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.SPRUCE_WOOD_STAIRS))
			item = new ItemStack(Material.SPRUCE_WOOD_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.BIRCH_WOOD_STAIRS))
			item = new ItemStack(Material.BIRCH_WOOD_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.JUNGLE_WOOD_STAIRS))
			item = new ItemStack(Material.JUNGLE_WOOD_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.QUARTZ_STAIRS))
			item = new ItemStack(Material.QUARTZ_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.ACACIA_STAIRS))
			item = new ItemStack(Material.ACACIA_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.DARK_OAK_STAIRS))
			item = new ItemStack(Material.DARK_OAK_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.RED_SANDSTONE_STAIRS))
			item = new ItemStack(Material.RED_SANDSTONE_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.PURPUR_STAIRS))
			item = new ItemStack(Material.PURPUR_STAIRS, 1, (short)0);
		else if(block.getMaterial().equals(Material.WATER))
			item = new ItemStack(Material.WATER_BUCKET, 1, (short)0);
		else if(block.getMaterial().equals(Material.LAVA))
			item = new ItemStack(Material.LAVA_BUCKET, 1, (short)0);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 8)
			item = new ItemStack(Material.STEP, 1, (short)0);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 9)
			item = new ItemStack(Material.STEP, 1, (short)1);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 10)
			item = new ItemStack(Material.STEP, 1, (short)2);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 11)
			item = new ItemStack(Material.STEP, 1, (short)3);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 12)
			item = new ItemStack(Material.STEP, 1, (short)4);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 13)
			item = new ItemStack(Material.STEP, 1, (short)5);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 14)
			item = new ItemStack(Material.STEP, 1, (short)6);
		else if(block.getMaterial().equals(Material.STEP) && block.getDamage() == 15)
			item = new ItemStack(Material.STEP, 1, (short)7);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 0)
			item = new ItemStack(Material.STEP, 2, (short)0);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 1)
			item = new ItemStack(Material.STEP, 2, (short)1);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 2)
			item = new ItemStack(Material.STEP, 2, (short)2);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 3)
			item = new ItemStack(Material.STEP, 2, (short)3);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 4)
			item = new ItemStack(Material.STEP, 2, (short)4);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 5)
			item = new ItemStack(Material.STEP, 2, (short)5);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 6)
			item = new ItemStack(Material.STEP, 2, (short)6);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 7)
			item = new ItemStack(Material.STEP, 2, (short)7);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 8)
			item = new ItemStack(Material.STEP, 2, (short)0);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 9)
			item = new ItemStack(Material.STEP, 2, (short)1);
		else if(block.getMaterial().equals(Material.DOUBLE_STEP) && block.getDamage() == 15)
			item = new ItemStack(Material.STEP, 2, (short)7);
		else if(block.getMaterial().equals(Material.STONE_SLAB2) && block.getDamage() == 8)
			item = new ItemStack(Material.STONE_SLAB2, 1, (short)0);
		else if(block.getMaterial().equals(Material.DOUBLE_STONE_SLAB2) && block.getDamage() == 8)
			item = new ItemStack(Material.STONE_SLAB2, 2, (short)0);
		else if(block.getMaterial().equals(Material.WOOD_STEP) && block.getDamage() == 8)
			item = new ItemStack(Material.WOOD_STEP, 1, (short)0);
		else if(block.getMaterial().equals(Material.WOOD_STEP) && block.getDamage() == 9)
			item = new ItemStack(Material.WOOD_STEP, 1, (short)1);
		else if(block.getMaterial().equals(Material.WOOD_STEP) && block.getDamage() == 10)
			item = new ItemStack(Material.WOOD_STEP, 1, (short)2);
		else if(block.getMaterial().equals(Material.WOOD_STEP) && block.getDamage() == 11)
			item = new ItemStack(Material.WOOD_STEP, 1, (short)3);
		else if(block.getMaterial().equals(Material.WOOD_STEP) && block.getDamage() == 12)
			item = new ItemStack(Material.WOOD_STEP, 1, (short)4);
		else if(block.getMaterial().equals(Material.WOOD_STEP) && block.getDamage() == 13)
			item = new ItemStack(Material.WOOD_STEP, 1, (short)5);
		else if(block.getMaterial().equals(Material.WOOD_DOUBLE_STEP) && block.getDamage() == 0)
			item = new ItemStack(Material.WOOD_STEP, 2, (short)0);
		else if(block.getMaterial().equals(Material.WOOD_DOUBLE_STEP) && block.getDamage() == 1)
			item = new ItemStack(Material.WOOD_STEP, 2, (short)1);
		else if(block.getMaterial().equals(Material.WOOD_DOUBLE_STEP) && block.getDamage() == 2)
			item = new ItemStack(Material.WOOD_STEP, 2, (short)2);
		else if(block.getMaterial().equals(Material.WOOD_DOUBLE_STEP) && block.getDamage() == 3)
			item = new ItemStack(Material.WOOD_STEP, 2, (short)3);
		else if(block.getMaterial().equals(Material.WOOD_DOUBLE_STEP) && block.getDamage() == 4)
			item = new ItemStack(Material.WOOD_STEP, 2, (short)4);
		else if(block.getMaterial().equals(Material.WOOD_DOUBLE_STEP) && block.getDamage() == 5)
			item = new ItemStack(Material.WOOD_STEP, 2, (short)5);
		else if(block.getMaterial().equals(Material.PURPUR_DOUBLE_SLAB) && block.getDamage() == 0)
			item = new ItemStack(Material.PURPUR_SLAB, 2, (short)0);
		else if(block.getMaterial().equals(Material.PURPUR_SLAB) && block.getDamage() == 8)
			item = new ItemStack(Material.PURPUR_SLAB, 1, (short)0);
		else
			item = new ItemStack(block.getMaterial(), 1, block.getDamage());		
			
		return item;
	}
	
	public static ItemStack getItemFromBlock(Block block) {
		return getItemFromBlock(new BlockData(block));
	}
	
	public boolean isItemABlock(ItemStack item) {
		
		return true;
	}
	
	public boolean isItemABlock(Material material) {
		return true;
	}
	public static boolean isBlockOnList(BlockData block) {
		for(String string : QubeMC.getInstance().getConfig().getStringList("blocks")) {
			String materialData = string.split(", ")[0];
			Material material = Material.getMaterial(materialData.split(":")[0].toUpperCase());
			byte data = Byte.valueOf(materialData.split(":")[1]);
			if(block.getMaterial().equals(material) && block.getDamage() == data) {
				return true;
			}
		}
		return false;
	}
	public static String getRightToolType(BlockData block) {
		for(String string : QubeMC.getInstance().getConfig().getStringList("blocks")) {
			String materialData = string.split(" - ")[0];
			Material material = Material.getMaterial(materialData.split(":")[0].toUpperCase());
			byte data = Byte.valueOf(materialData.split(":")[1]);
			String toolType = string.split(" - ")[1].split(", ")[0];
			if(toolType.equals("PICKAXE"))
				toolType = toolType.replace("PICKAXE", "_PICKAXE");
			if(toolType.equals("AXE"))
				toolType = toolType.replace("AXE", "_AXE");
			if(toolType.equals("SHOVEL"))
				toolType = toolType.replace("SHOVEL", "_SPADE");
			if(toolType.equals("SPADE"))
				toolType = toolType.replace("SPADE", "_SPADE");
			if(toolType.equals("HAND"))
				toolType = toolType.replace("HAND", "AIR");
			if(block.getMaterial().equals(material) && block.getDamage() == data) {
				return toolType;
			}
		}
		return null;
	}
	public static boolean isRightToolType(BlockData block, String type) {
		for(String string : QubeMC.getInstance().getConfig().getStringList("blocks")) {
			String materialData = string.split(" - ")[0];
			Material material = Material.getMaterial(materialData.split(":")[0].toUpperCase());
			byte data = Byte.valueOf(materialData.split(":")[1]);
			String toolType = string.split(" - ")[1].split(", ")[0];
			if(toolType.equals("PICKAXE"))
				toolType = toolType.replace("PICKAXE", "_PICKAXE");
			if(toolType.equals("AXE"))
				toolType = toolType.replace("AXE", "_AXE");
			if(toolType.equals("SHOVEL"))
				toolType = toolType.replace("SHOVEL", "_SPADE");
			if(toolType.equals("SPADE"))
				toolType = toolType.replace("SPADE", "_SPADE");
			if(toolType.equals("HAND"))
				toolType = toolType.replace("HAND", "AIR");
			if(block.getMaterial().equals(material) && block.getDamage() == data && toolType.equals(type)) {
				return true;
			}
		}
		return false;
	}
	public static ItemStack getRightTool(UUID uuid, BlockData block) {
		for(String string : QubeMC.getInstance().getConfig().getStringList("blocks")) {
			String materialData = string.split(" - ")[0];
			Material material = Material.getMaterial(materialData.split(":")[0].toUpperCase());
			byte data = Byte.valueOf(materialData.split(":")[1]);
			String toolType = string.split(" - ")[1].split(", ")[0];
			if(toolType.equals("PICKAXE"))
				toolType = toolType.replace("PICKAXE", "_PICKAXE");
			if(toolType.equals("AXE"))
				toolType = toolType.replace("AXE", "_AXE");
			if(toolType.equals("SHOVEL"))
				toolType = toolType.replace("SHOVEL", "_SPADE");
			if(toolType.equals("SPADE"))
				toolType = toolType.replace("SPADE", "_SPADE");
			if(toolType.equals("HAND"))
				toolType = toolType.replace("HAND", "AIR");
			if(block.getMaterial().equals(material) && block.getDamage() == data) {
				for(ItemStack item : Bukkit.getPlayer(uuid).getInventory().getContents())
					if(toolType.equals("AIR")) {
						return null;
					} else {
						if(item != null)
							if(item.getType().name().contains(toolType)) {
								return item;
							}
					}
			}
		}
		return null;
	}
	public static boolean hasItem(ItemStack[] items, Material material, short damage) {
		if(material.equals(Material.AIR))
			return true;
		for(ItemStack item : items) {
			if(item != null) {
				if(item.getType().equals(material) && item.getDurability() == damage)
					return true;
			}
		}
		return false;
	}
	public static ItemStack getReturnItem(BlockData block) {
		if(block.getMaterial().equals(Material.CHEST)) {
			return new ItemStack(Material.CHEST, 1, (short)0);
		}
//		for(String string : QubeMC.getInstance().getConfig().getStringList("blocks")) {
//			String materialData1 = string.split(", ")[0];
//			String materialData2 = string.split(", ")[1];
//			Material material1 = Material.getMaterial(materialData1.split(":")[0]);
//			byte data1 = Byte.valueOf(materialData1.split(":")[1]);
//			
//			Material material2 = Material.getMaterial(materialData2.split(":")[0]);
//			byte data2 = Byte.valueOf(materialData2.split(":")[1]);
//			
//			if(block.getMaterial().equals(material1) && block.getDamage() == data1)
//				return new ItemStack(material2, 1, data2);
//		}
		return null;
	}
	public static ItemStack getReturnItem(ItemStack item, BlockData block) {
		for(String string : QubeMC.getInstance().getConfig().getStringList("blocks")) {
			String materialData1 = string.split(" - ")[0];
			String materialData2 = string.split(", ")[1];
			if(!materialData2.contains("[") && !materialData2.contains("]")) {
				Material material1 = Material.getMaterial(materialData1.split(":")[0]);
				byte data1 = Byte.valueOf(materialData1.split(":")[1]);
				
				Material material2 = Material.getMaterial(materialData2.split(":")[0]);
				byte data2 = Byte.valueOf(materialData2.split(":")[1]);
				
				String toolType = string.split(" - ")[1].split(", ")[0];
				if(item != null) {
					if(item.containsEnchantment(Enchantment.SILK_TOUCH)) {
						if(block.getMaterial().equals(material1) && block.getDamage() == data1) {
							if(item.getType().name().contains(toolType))
							return new ItemStack(material1, 1, data1);
						}
					} else {
						if(block.getMaterial().equals(material1) && block.getDamage() == data1)
							if(item.getType().name().contains(toolType))
								return new ItemStack(material2, 1, data2);
					}
				} else {
					if(block.getMaterial().equals(material1) && block.getDamage() == data1)
						if(toolType.equals("AIR"))
							return new ItemStack(material2, 1, data2);
				}
			} else {
				String itemInfo = materialData2.split("\\[")[1].replace("]", "");
				Material mainMat = Material.getMaterial(materialData2.split("\\[")[0].split(":")[0]);
				byte mainData = Byte.valueOf(materialData2.split("\\[")[0].split(":")[1]);
				
				Material material1 = Material.getMaterial(materialData1.split(":")[0]);
				int amountOfValue = StringUtils.countMatches(itemInfo, "=");
				int amountOfInstance = 0;
				byte data1 = Byte.valueOf(materialData1.split(":")[1]);
				String type = "";
				String value = "";
				List<String> instanceList = new ArrayList<>();
				if(block.getMaterial().equals(material1) && block.getDamage() == data1) {
					for (int i = 0; i < amountOfValue; i++) {
						if(i % 2 == 0) {
							type = itemInfo.split("=")[i];
							value = itemInfo.split("=")[i + 1];
							amountOfInstance = StringUtils.countMatches(value, ";");
						}
					}
					for (int i = 0; i < amountOfInstance + 1; i++) {
						instanceList.add(value.split(";")[i]);
					}
					if(type.equals("PCT")) {
						for (int i = 0; i < amountOfInstance + 1; i++) {
							instanceList.add(value.split(";")[i]);
						}
						int c = 0;
						for(String strings : instanceList) {
							Material valMat = Material.getMaterial(strings.split("\\?")[0].split(":")[0]);
							byte valData = Byte.valueOf(strings.split("\\?")[0].split(":")[1]);
							String math = strings.split("\\?")[1];
							int x = Integer.valueOf(math.split("/")[0]);
							int y = Integer.valueOf(math.split("/")[1]);
							Random random = new Random();
							int r = random.nextInt(y);
							if((r - 1) < (x - 1)) {
								return new ItemStack(valMat, 1, Short.valueOf(valData));
							} else {
								if(c == amountOfInstance)
									return new ItemStack(mainMat, 1, mainData);
							}
							c++;
						}
					}
				}
			}
		}
		return null;
	}
	public static void modifyTool(UUID uuid, ItemStack item, int modify) {
		if(item.containsEnchantment(Enchantment.DURABILITY)) {
			double random = Math.random() * 100;
			if(random > (100/(item.getEnchantmentLevel(Enchantment.DURABILITY) + 1))) {
				item.setDurability((short) (item.getDurability() + 1));
				if(item.getType().name().contains("WOOD_")) {
					if(item.getDurability() >= 60)
						Bukkit.getPlayer(uuid).getInventory().remove(item);
				} else if(item.getType().name().contains("STONE_")) {
					if(item.getDurability() >= 132)
						Bukkit.getPlayer(uuid).getInventory().remove(item);
				} else if(item.getType().name().contains("IRON_")) {
					if(item.getDurability() >= 251)
						Bukkit.getPlayer(uuid).getInventory().remove(item);
				} else if(item.getType().name().contains("DIAMOND_")) {
					if(item.getDurability() >= 1562)
						Bukkit.getPlayer(uuid).getInventory().remove(item);
				} else if(item.getType().name().contains("GOLD_")) {
					if(item.getDurability() >= 33)
						Bukkit.getPlayer(uuid).getInventory().remove(item);
				} else if(item.getType().name().contains("SHEARS")) {
					if(item.getDurability() >= 239)
						Bukkit.getPlayer(uuid).getInventory().remove(item);
				}
			}
		} else {
			item.setDurability((short) (item.getDurability() + 1));
			if(item.getType().name().contains("WOOD_")) {
				if(item.getDurability() >= 60)
					Bukkit.getPlayer(uuid).getInventory().remove(item);
			} else if(item.getType().name().contains("STONE_")) {
				if(item.getDurability() >= 132)
					Bukkit.getPlayer(uuid).getInventory().remove(item);
			} else if(item.getType().name().contains("IRON_")) {
				if(item.getDurability() >= 251)
					Bukkit.getPlayer(uuid).getInventory().remove(item);
			} else if(item.getType().name().contains("DIAMOND_")) {
				if(item.getDurability() >= 1562)
					Bukkit.getPlayer(uuid).getInventory().remove(item);
			} else if(item.getType().name().contains("GOLD_")) {
				if(item.getDurability() >= 33)
					Bukkit.getPlayer(uuid).getInventory().remove(item);
			} else if(item.getType().name().contains("SHEARS")) {
				if(item.getDurability() >= 239)
					Bukkit.getPlayer(uuid).getInventory().remove(item);
			}
		}
	}
	
	public static int countEmpty(Player player) {
		int x = 0;
		for (int i = 0; i <= 35; i++) {
			if(player.getInventory().getItem(i) == null)
				x++;
		}
		return x;
	}
}
