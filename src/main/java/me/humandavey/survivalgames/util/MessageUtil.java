package me.humandavey.survivalgames.util;

import me.humandavey.survivalgames.SurvivalGames;

public enum MessageUtil {

	NO_PERMISSION("no-permission"),
	PLAYER_JOINED("player-joined"),
	ALREADY_BEGUN("already-begun");

	private final String message;

	MessageUtil(String message) {
		this.message = message;
	}

	public String getMessage() {
		return Util.colorize(SurvivalGames.getConfiguration().getMessage(message));
	}
}
