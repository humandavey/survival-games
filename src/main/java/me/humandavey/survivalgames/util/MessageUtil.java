package me.humandavey.survivalgames.util;

import me.humandavey.survivalgames.SurvivalGames;

public enum MessageUtil {

	NO_PERMISSION("no-permission"),
	PLAYER_JOINED("player-joined"),
	GAME_STARTED("game-started"),
	COUNTDOWN_CANCELLED("countdown-cancelled"),
	PLAYER_LEFT("player-left"),
	GAME_FORCE_STARTED("game-force-started"),
	ALREADY_BEGUN("already-begun");

	private final String message;

	MessageUtil(String message) {
		this.message = message;
	}

	public String getMessage() {
		return Util.colorize(SurvivalGames.getConfiguration().getMessage(message));
	}
}
