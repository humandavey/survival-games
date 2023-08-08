package me.humandavey.survivalgames;

import me.humandavey.survivalgames.command.commands.SetupCommand;
import me.humandavey.survivalgames.command.commands.StartCommand;
import me.humandavey.survivalgames.config.Configuration;
import me.humandavey.survivalgames.listener.BlockListener;
import me.humandavey.survivalgames.listener.JoinListener;
import me.humandavey.survivalgames.listener.PlayerMoveListener;
import me.humandavey.survivalgames.listener.PlayerQuitListener;
import me.humandavey.survivalgames.manager.BlockManager;
import me.humandavey.survivalgames.manager.GameManager;
import me.humandavey.survivalgames.nametag.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalGames extends JavaPlugin {

	private static SurvivalGames instance;
	private static GameManager gameManager;
	private static BlockManager blockManager;
	private static Configuration configuration;

	@Override
	public void onEnable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.kickPlayer("Survival Games is restarting and requires all players offline.");
		}

		instance = this;

		setupConfigs();
		setupManagers();
		registerListeners();
		registerCommands();
	}

	@Override
	public void onDisable() {

	}

	private void setupConfigs() {
		getConfig().options().copyDefaults();
		saveDefaultConfig();
	}

	private void setupManagers() {
		gameManager = new GameManager();
		blockManager = new BlockManager();
		configuration = new Configuration(getConfig());
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new NametagManager(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
	}

	private void registerCommands() {
		new StartCommand();
		new SetupCommand();
	}

	public static GameManager getGameManager() {
		return gameManager;
	}

	public static BlockManager getBlockManager() {
		return blockManager;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}

	public static SurvivalGames getInstance() {
		return instance;
	}
}
