package me.humandavey.survivalgames.manager;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.instance.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class GameManager {

	private final ArrayList<UUID> players = new ArrayList<>();
	private GameState state = GameState.WAITING;
	private Countdown countdown;

	public void broadcast(String message) {
		for (UUID uuid : players) {
			Player player = Bukkit.getPlayer(uuid);
			player.sendMessage(message);
		}
	}

	public void addPlayer(Player player) {
		players.add(player.getUniqueId());

		if (SurvivalGames.getConfiguration().getMinPlayers() <= players.size() && countdown == null) {
			countdown = new Countdown();
			countdown.start();
		}
	}

	public void removePlayer(Player player) {
		players.remove(player.getUniqueId());

		if (SurvivalGames.getConfiguration().getMinPlayers() > players.size() && countdown != null) {
			cancelCountdown();
		}
	}

	public void startCountdown() {
		cancelCountdown();

		countdown = new Countdown();
		countdown.start();
	}

	public void cancelCountdown() {
		countdown.setCancel(true);
		countdown.cancel();
		countdown = null;
	}

	public ArrayList<Player> getPlayers() {
		ArrayList<Player> p = new ArrayList<>();
		players.forEach(uuid -> p.add(Bukkit.getPlayer(uuid)));
		return p;
	}

	public void setState(GameState gameState) {
		state = gameState;
	}

	public GameState getState() {
		return state;
	}
}
