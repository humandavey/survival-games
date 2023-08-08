package me.humandavey.survivalgames.config;

import me.humandavey.survivalgames.SurvivalGames;
import me.humandavey.survivalgames.util.Util;
import me.humandavey.survivalgames.util.item.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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

	public ArrayList<Chest> getChests(int tier) {
		List<String> locs = config.getStringList("chest-locations.tier-" + tier);
		ArrayList<Chest> chests = new ArrayList<>();

		for (String loc : locs) {
			Location newLoc = Util.stringToLocation(loc, ";");
			if (newLoc.getBlock().getType() == Material.CHEST) {
				Chest chest = (Chest) newLoc.getBlock().getState();
				chests.add(chest);
			}
		}
		return chests;
	}

	public ArrayList<ItemStack> getItems(int tier) {
		ArrayList<ItemStack> items = new ArrayList<>();

		for (String i : config.getConfigurationSection("chest-items.tier-" + tier).getKeys(false)) {
			items.add(new ItemBuilder(config, "chest-items.tier-" + tier + "." + i).build());
		}

		return items;
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

	public void addSpawnpoint(Location location) {
		if (!getSpawnpoints().contains(location)) {
			List<String> locs = config.getStringList("spawnpoints");
			locs.add(location.getWorld().getName() + ";" + location.getBlockX() + ".5;" + location.getBlockY() + ";" + location.getBlockZ() + ".5");

			config.set("spawnpoints", locs);
			SurvivalGames.getInstance().saveDefaultConfig();
		}
	}

	public void removeSpawnpoint(Location location) {
		if (getSpawnpoints().contains(location)) {
			List<String> locs = config.getStringList("spawnpoints");
			locs.remove(location.getWorld().getName() + ";" + location.getBlockX() + ".5;" + location.getBlockY() + ";" + location.getBlockZ() + ".5");

			config.set("spawnpoints", locs);
			SurvivalGames.getInstance().saveDefaultConfig();
		}
	}

	public void addChest(Location location, int tier) {
		if (location.getBlock().getType() != Material.CHEST) return;

		Chest chest = (Chest) location.getBlock().getState();
		if (!getChests(tier).contains(chest)) {
			List<String> locs = config.getStringList("chest-locations.tier-" + tier);
			locs.add(location.getWorld().getName() + ";" + location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ());

			config.set("chest-locations.tier-" + tier, locs);
			SurvivalGames.getInstance().saveConfig();
		}
	}

	public void removeChest(Location location, int tier) {
		if (location.getBlock().getType() != Material.CHEST) return;

		Chest chest = (Chest) location.getBlock().getState();
		if (getChests(tier).contains(chest)) {
			List<String> locs = config.getStringList("chest-locations.tier-" + tier);
			locs.remove(location.getWorld().getName() + ";" + location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ());

			config.set("chest-locations.tier-" + tier, locs);
			SurvivalGames.getInstance().saveConfig();
		}
	}
}
