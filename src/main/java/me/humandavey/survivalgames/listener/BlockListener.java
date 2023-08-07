package me.humandavey.survivalgames.listener;

import me.humandavey.survivalgames.SurvivalGames;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (!SurvivalGames.getBlockManager().canBreak(event.getBlock())) {
			if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (!SurvivalGames.getBlockManager().canPlace(event.getBlock())) {
			if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
			event.setCancelled(true);
		}
	}
}
