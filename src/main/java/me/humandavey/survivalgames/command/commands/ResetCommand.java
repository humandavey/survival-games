package me.humandavey.survivalgames.command.commands;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.command.Command;
import me.humandavey.survivalgames.util.MessageUtil;
import org.bukkit.entity.Player;

public class ResetCommand extends Command {

	public ResetCommand() {
		super("reset", null, "Reset all chests");
	}

	@Override
	public void execute(Player player, String[] args) {
		if (player.hasPermission("survivalgames.admin") || player.isOp() || player.hasPermission("*")) {
			SurvivalGames.getGameManager().resetChests();
			player.sendMessage(MessageUtil.SUCCESS.getMessage());
		} else {
			player.sendMessage(MessageUtil.NO_PERMISSION.getMessage());
		}
	}
}
