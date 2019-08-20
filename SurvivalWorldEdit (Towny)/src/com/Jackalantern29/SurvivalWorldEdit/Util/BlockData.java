package com.Jackalantern29.SurvivalWorldEdit.Util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;

public class BlockData {
	Location location;
	Material material;
	byte damage;
	MaterialData data;
	BlockState state = null;
	
	@SuppressWarnings("deprecation")
	public BlockData(Location location, Material material, byte damage) {
		this.location = location;
		this.material = material;
		this.damage = damage;
		this.data = new MaterialData(material, damage);
	}
	@SuppressWarnings("deprecation")
	public BlockData(Location location, MaterialData data) {
		this.location = location;
		this.material = data.getItemType();
		this.damage = data.getData();
		this.data = data;
	}
	@SuppressWarnings("deprecation")
	public BlockData(Material material, byte damage) {
		this.location = null;
		this.material = material;
		this.damage = damage;
		this.data = new MaterialData(material, damage);
	}
	@SuppressWarnings("deprecation")
	public BlockData(MaterialData data) {
		this.location = null;
		this.material = data.getItemType();
		this.damage = data.getData();
		this.data = data;
	}
	@SuppressWarnings("deprecation")
	public BlockData(Block block) {
		this.location = block.getLocation();
		this.material = block.getType();
		this.damage = block.getData();
		this.data = new MaterialData(block.getType(), block.getData());
		this.state = block.getState();
	}
	public BlockData(BlockData data) {
		this.location = data.getLocation();
		this.material = data.getMaterial();
		this.damage = data.getDamage();
		this.data = data.getMaterialData();
	}
	
	public Location getLocation() {
		return this.location;
	}
	public Material getMaterial() {
		return this.material;
	}
	public byte getDamage() {
		return this.damage;
	}
	public Block getBlock() {
		return this.location.getBlock();
	}
	public MaterialData getMaterialData() {
		return this.data;
	}
	public BlockState getBlockState() {
		return state;
	}
}
