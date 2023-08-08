package me.humandavey.survivalgames.listener;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.manager.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (SurvivalGames.getGameManager().getState() == GameState.COUNTDOWN || SurvivalGames.getGameManager().getState() == GameState.WAITING) {
			event.setCancelled(true);
		}
	}
}
