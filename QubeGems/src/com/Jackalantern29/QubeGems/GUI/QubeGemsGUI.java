package com.Jackalantern29.QubeGems.GUI;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Jackalantern29.QubeGems.Util.ItemOptions;
import com.Jackalantern29.QubeGems.Util.PlayerParticles;
import com.Jackalantern29.QubeGems.Util.QubeGems;

public class QubeGemsGUI implements Listener {
	static Inventory inventory;
	Inventory inv = inventory;
	public static void openMain(Player player) {
		//Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "         §b§m-§f§m[§r§m-§r QubeGems §2§l" + QubeGems.getGems(player.getUniqueId()) + "§r §m-§f§m]§b§m-");
		inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "QubeGems §2§l" + QubeGems.getGems(player.getUniqueId()) + " §r- Menu");
		inventory.setItem(1, ItemOptions.createItem(Material.REDSTONE, 1, 0, "§9Coming Soon", " ", ""/*"§6Select or Purchase a particle."*/, " ", "§7Unlocked: §c?§7/§6?"));
		inventory.setItem(3, ItemOptions.createItem(Material.INK_SACK, 1, 4, "§9Items", " ", "§6Buy items."));
		player.openInventory(inventory);
	}
	
	public static void openParticlesMenu(Player player) {
		inventory = Bukkit.createInventory(null, 9 * 3, "QubeGems §2§l" + QubeGems.getGems(player.getUniqueId()) + " §r- Particles");
		if(QubeGems.hasParticle(player.getUniqueId(), "Reddust", "Halo", "Head"))
			inventory.setItem(0, ItemOptions.createItem(Material.REDSTONE, 1, 0, "§aRedstone Dust", "§7Type: §9Halo, with Head", "", "§7Click to use select this."));
		else
			inventory.setItem(0, ItemOptions.createItem(Material.REDSTONE, 1, 0, "§aRedstone Dust", "§7Type: §9Halo, with Head", "", "§7Price: §a" + QubeGems.getParticlePrice("Reddust", "Halo", "Head")));
		
		if(QubeGems.hasParticle(player.getUniqueId(), "Flame", "Halo", "Feet"))
			inventory.setItem(1, ItemOptions.createItem(Material.FURNACE, 1, 0, "§aFlame", "§7Type: §9Halo, with Feet", "", "§7Click to use select this."));
		else
			inventory.setItem(1, ItemOptions.createItem(Material.FURNACE, 1, 0, "§aFlame", "§7Type: §9Halo, with Feet", "", "§7Price: §a" + QubeGems.getParticlePrice("Flame", "Halo", "Feet")));
		
		if(QubeGems.hasParticle(player.getUniqueId(), "DripLava", "Halo", "Waist"))
			inventory.setItem(2, ItemOptions.createItem(Material.LAVA_BUCKET, 1, 0, "§aDrip Lava", "§7Type: §9Halo, with Waist", "", "§7Click to use select this."));
		else
			inventory.setItem(2, ItemOptions.createItem(Material.LAVA_BUCKET, 1, 0, "§aDrip Lava", "§7Type: §9Halo, with Waist", "", "§7Price: §a" + QubeGems.getParticlePrice("DripLava", "Halo", "Waist")));
		
		if(QubeGems.hasParticle(player.getUniqueId(), "DragonBreath", "Cape", ""))
			inventory.setItem(3, ItemOptions.createItem(Material.DRAGONS_BREATH, 1, 0, "§aDragon Breath", "§7Type: §9Cape", "", "§7Click to use select this."));
		else
			inventory.setItem(3, ItemOptions.createItem(Material.DRAGONS_BREATH, 1, 0, "§aDragon Breath", "§7Type: §9Cape", "", "§7Price: §a" + QubeGems.getParticlePrice("DragonBreath", "Cape", "")));
		player.openInventory(inventory);
	}
	
	public static void openItemsMenu(Player player) {
		inventory = Bukkit.createInventory(null, 9 * 5, "QubeGems §2§l" + QubeGems.getGems(player.getUniqueId()) + " §r- Items");
		int count = 0;
		for(ItemStack items : QubeGems.getItems()) {
			ItemStack item = items;
			ItemMeta meta = item.getItemMeta();
			if(!meta.hasDisplayName())
				meta.setDisplayName("§a" + StringUtils.capitalize(item.getType().name().toLowerCase()).replace("_", " "));
			if(meta.hasLore()) {
				List<String> list = meta.getLore();
				list.add(" ");
				list.add("§7Price: §a" + QubeGems.getItemPrice(item));
				meta.setLore(list);
			} else {
				List<String> list = new ArrayList<>();
				list.add(" ");
				list.add("§7Price: §a" + QubeGems.getItemPrice(item));
				meta.setLore(list);
			}
			item.setItemMeta(meta);
			inventory.setItem(count, item);
			count++;
		}
		player.openInventory(inventory);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inventory = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		int slot = event.getSlot();
		if(inventory.getHolder() == null) {
			if(inventory.getTitle().contains("QubeGems ")) {
				if(inventory.getTitle().contains("- Menu")) {
					event.setCancelled(true);
					if(slot == 1) {
						//openParticlesMenu(player);
						player.sendMessage("§7[§aQubeGems§7] §cComing Soon.");
					}
					if(slot == 3) {
						openItemsMenu(player);
					}
				} else if(inventory.getTitle().contains("- Particles")) {
					event.setCancelled(true);
					if(slot == 0) {
						if(QubeGems.hasParticle(player.getUniqueId(), "Reddust", "Halo", "Head"))
							PlayerParticles.playRedstoneHalo(player);
						else {
							if(QubeGems.getGems(player.getUniqueId()) >= QubeGems.getParticlePrice("Reddust", "Halo", "Head")) {
								QubeGems.setParticle(player.getUniqueId(), "Reddust", "Halo", "Head", true);
								PlayerParticles.playRedstoneHalo(player);
								QubeGems.removeGems(player.getUniqueId(), QubeGems.getParticlePrice("Reddust", "Halo", "Head"));
								player.sendMessage("§7[§aParticles§7] §ePlaying the Reddust Halo Particle.");
							} else {
								player.sendMessage("§cYou do not have enough to get this item.");
							}
						}	
					} else if(slot == 1) {
						if(QubeGems.hasParticle(player.getUniqueId(), "Flame", "Halo", "Feet"))
							PlayerParticles.playFlameHalo(player);
						else {
							if(QubeGems.getGems(player.getUniqueId()) >= QubeGems.getParticlePrice("Flame", "Halo", "Feet")) {
								QubeGems.setParticle(player.getUniqueId(), "Flame", "Halo", "Feet", true);
								PlayerParticles.playFlameHalo(player);
								QubeGems.removeGems(player.getUniqueId(), QubeGems.getParticlePrice("Flame", "Halo", "Feet"));
								player.sendMessage("§7[§aParticles§7] §ePlaying the Flame Halo Particle.");
							} else {
								player.sendMessage("§cYou do not have enough to get this item.");
							}
						}	
					} else if(slot == 2) {
						if(QubeGems.hasParticle(player.getUniqueId(), "DripLava", "Halo", "Waist"))
							PlayerParticles.playLavaDripHalo(player);
						else {
							if(QubeGems.getGems(player.getUniqueId()) >= QubeGems.getParticlePrice("DripLava", "Halo", "Waist")) {
								QubeGems.setParticle(player.getUniqueId(), "DripLava", "Halo", "Waist", true);
								PlayerParticles.playLavaDripHalo(player);
								QubeGems.removeGems(player.getUniqueId(), QubeGems.getParticlePrice("DripLava", "Halo", "Waist"));
								player.sendMessage("§7[§aParticles§7] §ePlaying the DripLava Halo Particle.");
							} else {
								player.sendMessage("§cYou do not have enough to get this item.");
							}
						}	
					} else if(slot == 3) {
						if(QubeGems.hasParticle(player.getUniqueId(), "DragonBreath", "Cape", ""))
							PlayerParticles.playDragonBreathCape(player);
						else {
							if(QubeGems.getGems(player.getUniqueId()) >= QubeGems.getParticlePrice("DragonBreath", "Cape", "")) {
								QubeGems.setParticle(player.getUniqueId(), "DragonBreath", "Cape", "", true);
								PlayerParticles.playDragonBreathCape(player);
								QubeGems.removeGems(player.getUniqueId(), QubeGems.getParticlePrice("DragonBreath", "Cape", ""));
								player.sendMessage("§7[§aParticles§7] §ePlaying the DragonBreath Cape Particle.");
							} else {
								player.sendMessage("§cYou do not have enough to get this item.");
							}
						}	
					}
				} else if(inventory.getTitle().contains("- Items")) {
					event.setCancelled(true);
					if(event.getCurrentItem() != null) {
						if(event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore() && event.getCurrentItem().getItemMeta().getLore().get(event.getCurrentItem().getItemMeta().getLore().size() - 1).contains("§7Price: ")) {
							if(QubeGems.getGems(player.getUniqueId()) >= QubeGems.getItemPrice(event.getCurrentItem())) {
								if(player.getInventory().firstEmpty() != -1) {
									ItemStack item = event.getCurrentItem();
									ItemMeta meta = item.getItemMeta();
									List<String> list = meta.getLore();
									list.remove(list.size() - 1);
									list.remove(list.size() - 1);
									meta.setLore(list);
									if(meta.getDisplayName().contains("§a"))
										meta.setDisplayName(null);
									item.setItemMeta(meta);
									player.getInventory().addItem(item);
									QubeGems.removeGems(player.getUniqueId(), QubeGems.getItemPrice(event.getCurrentItem()));
									player.sendMessage("§7[§aQubeGems§7] §eYou bought " + event.getCurrentItem().getAmount() + " " + StringUtils.capitalize(event.getCurrentItem().getType().name().toLowerCase()).replace("_", " ") + " for " + QubeGems.getItemPrice(event.getCurrentItem()) + " QubeGems.");
									openItemsMenu(player);
								} else {
									player.sendMessage("§7[§aQubeGems§7] §cYour inventory is full. Make an available slot to purchase items.");
								}
							} else {
								player.sendMessage("§cYou do not have enough to get this item.");
							}
						}
					}
				}
			}
		}
	}
	
	

}
