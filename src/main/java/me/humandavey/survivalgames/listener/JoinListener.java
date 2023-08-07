package me.humandavey.survivalgames.listener;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.config.Configuration;
import me.humandavey.survivalgames.manager.GameManager;
import me.humandavey.survivalgames.manager.GameState;
import me.humandavey.survivalgames.nametag.NametagManager;
import me.humandavey.survivalgames.util.MessageUtil;
import me.humandavey.survivalgames.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	private final GameManager gameManager = SurvivalGames.getGameManager();
	private final Configuration configuration = SurvivalGames.getConfiguration();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);

		GameState state = gameManager.getState();
		Player player = event.getPlayer();
		if (state == GameState.WAITING || (state == GameState.COUNTDOWN && gameManager.getPlayers().size() < configuration.getMaxPlayers())) {
			gameManager.addPlayer(player);
			Util.resetPlayer(player, true);
			player.teleport(configuration.getSpawnpoints().get(gameManager.getPlayers().size() - 1));
			player.teleport(Util.faceLocation(player.getLocation(), configuration.getFacingLocation()));
			gameManager.broadcast(MessageUtil.PLAYER_JOINED.getMessage()
					.replaceAll("%player%", player.getName())
					.replaceAll("%num%", gameManager.getPlayers().size() + "")
					.replaceAll("%max%", configuration.getMaxPlayers() + "")
			);

			NametagManager.setColor(player, ChatColor.GRAY);
		} else {
			event.getPlayer().kickPlayer(MessageUtil.ALREADY_BEGUN.getMessage());
		}
	}
}
