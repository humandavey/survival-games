package me.humandavey.survivalgames.command.commands;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.command.Command;
import me.humandavey.survivalgames.manager.GameState;
import me.humandavey.survivalgames.util.MessageUtil;
import org.bukkit.entity.Player;

public class StartCommand extends Command {

	public StartCommand() {
		super("start", null, "Start the countdown if not already started!");
	}

	@Override
	public void execute(Player player, String[] args) {
		if (player.hasPermission("survivalgames.admin") || player.isOp() || player.hasPermission("*")) {
			if (SurvivalGames.getGameManager().getState() == GameState.WAITING) {
				SurvivalGames.getGameManager().broadcast(MessageUtil.GAME_FORCE_STARTED.getMessage().replaceAll("%player%", player.getName()));
				SurvivalGames.getGameManager().startCountdown();
			} else {
				player.sendMessage(MessageUtil.ALREADY_BEGUN.getMessage());
			}
		} else {
			player.sendMessage(MessageUtil.NO_PERMISSION.getMessage());
		}
	}
}
