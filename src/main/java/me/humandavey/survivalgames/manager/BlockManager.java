package me.humandavey.survivalgames.manager;

import me.humandavey.survivalgames.SurvivalGames;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class BlockManager {

	public boolean canBreak(Block block) {
		return getBreakableBlocks().contains(block.getType());
	}

	public boolean canPlace(Block block) {
		return getPlaceableBlocks().contains(block.getType());
	}

	private ArrayList<Material> getBreakableBlocks() {
		return SurvivalGames.getConfiguration().getBreakableBlocks();
	}

	private ArrayList<Material> getPlaceableBlocks() {
		return SurvivalGames.getConfiguration().getPlaceableBlocks();
	}
}
