package me.humandavey.survivalgames.manager;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.instance.Countdown;
import me.humandavey.survivalgames.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class GameManager {

	private final ArrayList<UUID> players = new ArrayList<>();
	private GameState state = GameState.WAITING;
	private Countdown countdown;

	public void onStart() {
		fillChests();
	}

	private void fillChest(Chest chest, int tier) {
		if (SurvivalGames.getConfiguration().getItems(tier).size() < 1) return;
		int numToPick = new Random().nextInt(5, 9);
		for (int i = 0; i < numToPick; i++) {
			int index = new Random().nextInt(0, SurvivalGames.getConfiguration().getItems(tier).size());
			int slot = new Random().nextInt(0, 27);
			chest.getBlockInventory().setItem(slot, SurvivalGames.getConfiguration().getItems(tier).get(index));
		}
	}

	public void fillChests() {
		for (Chest chest : SurvivalGames.getConfiguration().getChests(1)) {
			fillChest(chest, 1);
		}
		for (Chest chest : SurvivalGames.getConfiguration().getChests(2)) {
			fillChest(chest, 2);
		}
	}

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
			if (!countdown.isRunning()) return;
			cancelCountdown();
		}
	}

	public void startCountdown() {
		if (countdown != null) {
			cancelCountdown();
		}

		countdown = new Countdown();
		countdown.start();
	}

	public void cancelCountdown() {
		countdown.setCancel(true);
		countdown.cancel();
		countdown = null;

		for (Player player : SurvivalGames.getGameManager().getPlayers()) {
			player.setLevel(0);
			player.setExp(0);
		}

		SurvivalGames.getGameManager().broadcast(MessageUtil.COUNTDOWN_CANCELLED.getMessage());
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
