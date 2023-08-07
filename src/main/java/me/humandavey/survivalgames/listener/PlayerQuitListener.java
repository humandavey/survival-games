package me.humandavey.survivalgames.listener;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.util.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);

		SurvivalGames.getGameManager().removePlayer(event.getPlayer());
		SurvivalGames.getGameManager().broadcast(MessageUtil.PLAYER_LEFT.getMessage()
				.replaceAll("%player%", event.getPlayer().getName())
				.replaceAll("%num%", SurvivalGames.getGameManager().getPlayers().size() + "")
				.replaceAll("%max%", SurvivalGames.getConfiguration().getMaxPlayers() + ""));
	}
}
