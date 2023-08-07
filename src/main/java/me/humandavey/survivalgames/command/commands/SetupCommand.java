package me.humandavey.survivalgames.command.commands;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.command.Command;
import me.humandavey.survivalgames.util.MessageUtil;
import org.bukkit.entity.Player;

public class SetupCommand extends Command {

	public SetupCommand() {
		super("setup", null, "Set up spawnpoints and chest locations");
	}

	@Override
	public void execute(Player player, String[] args) {
		if (player.hasPermission("survivalgames.admin") || player.isOp() || player.hasPermission("*")) {
			if (args.length > 0) {
				if (args[0].equals("addspawn")) {
					SurvivalGames.getConfiguration().addSpawnpoint(player.getLocation());
					player.sendMessage(MessageUtil.SUCCESS.getMessage());
				}
				if (args[0].equals("removespawn")) {
					SurvivalGames.getConfiguration().removeSpawnpoint(player.getLocation());
					player.sendMessage(MessageUtil.SUCCESS.getMessage());
				}
				if (args[0].equals("addchest") && args.length > 1) {
					SurvivalGames.getConfiguration().addChest(player.getTargetBlock(null, 5).getLocation(), Integer.parseInt(args[1]));
					player.sendMessage(MessageUtil.SUCCESS.getMessage());
				}
				if (args[0].equals("removechest") && args.length > 1) {
					SurvivalGames.getConfiguration().removeChest(player.getTargetBlock(null, 5).getLocation(), Integer.parseInt(args[1]));
					player.sendMessage(MessageUtil.SUCCESS.getMessage());
				}
			}
		} else {
			player.sendMessage(MessageUtil.NO_PERMISSION.getMessage());
		}
	}
}
