package me.humandavey.survivalgames;

import me.humandavey.survivalgames.command.commands.ExampleCommand;
import me.humandavey.survivalgames.nametag.NametagManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalGames extends JavaPlugin {

	private static SurvivalGames instance;

	@Override
	public void onEnable() {
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

	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new NametagManager(), this);
	}

	private void registerCommands() {
		new ExampleCommand();
	}

	public static SurvivalGames getInstance() {
		return instance;
	}
}
