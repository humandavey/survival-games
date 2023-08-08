package me.humandavey.survivalgames.instance;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.manager.GameState;
import me.humandavey.survivalgames.util.MessageUtil;
import me.humandavey.survivalgames.util.Util;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

	private int countdownSeconds;
	private boolean cancel;
	private boolean isRunning;

	public Countdown() {
		this.countdownSeconds = 20;
	}

	public void start() {
		SurvivalGames.getGameManager().setState(GameState.COUNTDOWN);
		runTaskTimer(SurvivalGames.getInstance(), 0, 20);
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public void run() {
		isRunning = true;
		if (cancel) {
			SurvivalGames.getGameManager().broadcast(MessageUtil.COUNTDOWN_CANCELLED.getMessage());
		}
		if (countdownSeconds == 0) {
			for (Player player : SurvivalGames.getGameManager().getPlayers()) {
				player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 0.3f, 1f);
				player.setLevel(0);
				player.setExp(0);
			}
			SurvivalGames.getGameManager().broadcast(MessageUtil.GAME_STARTED.getMessage());
			cancel();
			SurvivalGames.getGameManager().setState(GameState.LIVE);
			SurvivalGames.getGameManager().onStart();
			isRunning = false;
			return;
		}

		for (Player player : SurvivalGames.getGameManager().getPlayers()) {
			player.setLevel(countdownSeconds);
			player.setExp((float) countdownSeconds / 20);
		}

		if (countdownSeconds <= 5 || countdownSeconds % 5 == 0) {
			for (Player player : SurvivalGames.getGameManager().getPlayers()) {
				player.playSound(player.getLocation(), Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, 1f, 5f);
			}
			if (countdownSeconds == 10) {
				SurvivalGames.getGameManager().broadcast(Util.colorize("&eThe game will start in &610 &eseconds" + "!"));
			} else if (countdownSeconds <= 5) {
				SurvivalGames.getGameManager().broadcast(Util.colorize("&eThe game will start in &c" + countdownSeconds + " &esecond" + (countdownSeconds == 1 ? "" : "s") + "!"));
			} else {
				SurvivalGames.getGameManager().broadcast(Util.colorize("&eThe game will start in &a" + countdownSeconds + " &eseconds" + "!"));
			}
		}

		countdownSeconds--;
	}

	public boolean isRunning() {
		return isRunning;
	}
}