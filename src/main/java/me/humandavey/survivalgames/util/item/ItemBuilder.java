package me.humandavey.survivalgames.util.item;

import me.humandavey.survivalgames.util.Util;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

	private final ItemStack item;
	private final ItemMeta meta;

	public ItemBuilder(Material material) {
		item = new ItemStack(material);
		meta = item.getItemMeta();
	}

	public ItemBuilder(FileConfiguration fc, String path) {
		item = new ItemStack(Material.getMaterial(fc.getString(path + ".material")));
		meta = item.getItemMeta();

		if (fc.getString(path + ".name") != null) {
			meta.setDisplayName(Util.colorize(fc.getString(path + ".name")));
		}
		if (fc.getStringList(path + ".lore").size() != 0) {
			meta.setLore(Util.colorize(fc.getStringList(path + ".lore")));
		}
		item.setAmount(fc.getInt(path + ".amount", 1));
		setUnbreakable(fc.getBoolean(path + ".unbreakable", false));

		if (fc.getConfigurationSection(path + ".enchantments") != null) {
			for (String ench : fc.getConfigurationSection(path + ".enchantments").getKeys(false)) {
				int level = fc.getInt(path + ".enchantments." + ench);
				addEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(ench)), level);
			}
		}
	}

	public ItemStack build() {
		item.setItemMeta(meta);
		return item;
	}

	public ItemBuilder setAmount(int amount) {
		item.setAmount(amount);
		return this;
	}

	public ItemBuilder addItemFlags(ItemFlag... flags) {
		meta.addItemFlags(flags);
		return this;
	}

	public ItemBuilder addItemFlag(ItemFlag flag) {
		meta.addItemFlags(flag);
		return this;
	}

	public ItemBuilder setMaterial(Material material) {
		item.setType(material);
		return this;
	}

	public ItemBuilder setItemName(String name) {
		meta.setDisplayName(name);
		return this;
	}

	public ItemBuilder setUnbreakable(boolean unbreakable) {
		meta.setUnbreakable(unbreakable);
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		meta.addEnchant(enchantment, level, true);
		return this;
	}

	public ItemBuilder setLocalizedName(String localizedName) {
		meta.setLocalizedName(localizedName);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		meta.setLore(lore);
		return this;
	}

	public ItemBuilder setLore(String... lore) {
		List<String> l = new ArrayList<>(Arrays.asList(lore));
		meta.setLore(l);
		return this;
	}
}