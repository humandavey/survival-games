package me.humandavey.survivalgames.manager;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class BlockManager {

	public boolean canBreak(Block block) {
		return true;
	}

	public boolean canPlace(Block block) {
		return true;
	}

	private ArrayList<Material> getBreakableBlocks() {
		return new ArrayList<>();
	}

	private ArrayList<Material> getPlaceableBlocks() {
		return new ArrayList<>();
	}
}
