package me.humandavey.survivalgames.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class GameManager {

	private GameState state = GameState.WAITING;
	private final ArrayList<UUID> players = new ArrayList<>();

	public void broadcast(String message) {
		for (UUID uuid : players) {
			Player player = Bukkit.getPlayer(uuid);
			player.sendMessage(message);
		}
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

	public void addPlayer(Player player) {
		players.add(player.getUniqueId());
	}
}
