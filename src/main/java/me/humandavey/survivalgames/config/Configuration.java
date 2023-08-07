package me.humandavey.survivalgames.config;

import me.humandavey.survivalgames.util.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class Configuration {

	private final FileConfiguration config;

	public Configuration(FileConfiguration config) {
		this.config = config;
	}

	public String getMessage(String message) {
		return config.getString("messages." + message);
	}

	public int getMaxPlayers() {
		return config.getInt("max-players");
	}

	public int getMinPlayers() {
		return config.getInt("min-players");
	}

	public Location getFacingLocation() {
		return Util.stringToLocation(config.getString("facing"), ";");
	}

	public ArrayList<Location> getSpawnpoints() {
		ArrayList<Location> locations = new ArrayList<>();
		config.getStringList("spawnpoints").forEach(spawnpoint -> {
			locations.add(Util.stringToLocation(spawnpoint, ";"));
		});
		return locations;
	}

	public ArrayList<Material> getBreakableBlocks() {
		ArrayList<Material> mats = new ArrayList<>();
		config.getStringList("breakable-blocks").forEach(mat -> {
			mats.add(Material.getMaterial(mat));
		});
		return mats;
	}

	public ArrayList<Material> getPlaceableBlocks() {
		ArrayList<Material> mats = new ArrayList<>();
		config.getStringList("placeable-blocks").forEach(mat -> {
			mats.add(Material.getMaterial(mat));
		});
		return mats;
	}
}
