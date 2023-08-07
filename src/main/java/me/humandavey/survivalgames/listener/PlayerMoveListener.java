package me.humandavey.survivalgames.listener;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.manager.GameState;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (SurvivalGames.getGameManager().getState() == GameState.WAITING || SurvivalGames.getGameManager().getState() == GameState.COUNTDOWN) {
			if (event.getPlayer().getLocation().getX() != event.getTo().getX() || event.getPlayer().getLocation().getZ() != event.getTo().getZ()) {
				if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
				event.setCancelled(true);
			}
		}
	}
}
