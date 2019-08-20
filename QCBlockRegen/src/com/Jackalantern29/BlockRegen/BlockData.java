package com.Jackalantern29.BlockRegen;

import org.bukkit.Material;

public class BlockData {
	private final Material material;
	private final byte data;

	public BlockData(Material material, byte data) {
		this.material = material;
		this.data = data;
	}

	public Material getMaterial() {
		return this.material;
	}

	public byte getData() {
		return this.data;
	}
}
