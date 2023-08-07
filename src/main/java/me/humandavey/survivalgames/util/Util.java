package me.humandavey.survivalgames.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {

	public static void resetPlayer(Player player, boolean clearInv) {
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20);
		player.setMaxHealth(20);
		player.setFoodLevel(20);
		if (clearInv) {
			player.getInventory().clear();
			player.getInventory().setArmorContents(new ItemStack[4]);
		}
		for (PotionEffect pe : player.getActivePotionEffects()) {
			player.removePotionEffect(pe.getType());
		}
		player.setExp(0);
		player.setLevel(0);
		player.setTotalExperience(0);
		player.setAllowFlight(false);
	}

	public static List<String> colorizeList(List<String> list) {
		List<String> strings = new ArrayList<>();
		for (String s : list) {
			strings.add(colorize(s));
		}
		return strings;
	}

	public static String colorize(String m) {
		return ChatColor.translateAlternateColorCodes('&', m);
	}


	public static Location configToLocation(FileConfiguration fc, String path) {
		if (fc.get(path) != null) {
			if (fc.get(path + ".world") != null && fc.get(path + ".x") != null && fc.get(path + ".y") != null && fc.get(path + ".z") != null && fc.get(path + ".yaw") != null && fc.get(path + ".pitch") != null) {
				return new Location(Bukkit.getWorld(Objects.requireNonNull(fc.getString(path + ".world"))), fc.getDouble(path + ".x"), fc.getDouble(path + ".y"), fc.getDouble(path + ".z"), fc.getInt(path + ".yaw"), fc.getInt(path + ".pitch"));
			} else if (fc.get(path + ".world") != null && fc.get(path + ".x") != null && fc.get(path + ".y") != null && fc.get(path + ".z") != null) {
				return new Location(Bukkit.getWorld(Objects.requireNonNull(fc.getString(path + ".world"))), fc.getDouble(path + ".x"), fc.getDouble(path + ".y"), fc.getDouble(path + ".z"));
			}
		}
		return null;
	}

	public static Location stringToLocation(String location, String split) {
		String[] parts = location.split(split);
		if (parts.length == 3) {
			return new Location(Bukkit.getWorlds().get(0), Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
		} else if (parts.length == 4) {
			return new Location(Bukkit.getWorld(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
		} else if (parts.length == 6) {
			return new Location(Bukkit.getWorld(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
		}
		return null;
	}

	public static Location faceLocation(Location playerLocation, Location face) {
		return playerLocation.setDirection(face.subtract(playerLocation).toVector());
	}

	public enum DefaultFontInfo {
		A('A', 5),
		a('a', 5),
		B('B', 5),
		b('b', 5),
		C('C', 5),
		c('c', 5),
		D('D', 5),
		d('d', 5),
		E('E', 5),
		e('e', 5),
		F('F', 5),
		f('f', 4),
		G('G', 5),
		g('g', 5),
		H('H', 5),
		h('h', 5),
		I('I', 3),
		i('i', 1),
		J('J', 5),
		j('j', 5),
		K('K', 5),
		k('k', 4),
		L('L', 5),
		l('l', 1),
		M('M', 5),
		m('m', 5),
		N('N', 5),
		n('n', 5),
		O('O', 5),
		o('o', 5),
		P('P', 5),
		p('p', 5),
		Q('Q', 5),
		q('q', 5),
		R('R', 5),
		r('r', 5),
		S('S', 5),
		s('s', 5),
		T('T', 5),
		t('t', 4),
		U('U', 5),
		u('u', 5),
		V('V', 5),
		v('v', 5),
		W('W', 5),
		w('w', 5),
		X('X', 5),
		x('x', 5),
		Y('Y', 5),
		y('y', 5),
		Z('Z', 5),
		z('z', 5),
		NUM_1('1', 5),
		NUM_2('2', 5),
		NUM_3('3', 5),
		NUM_4('4', 5),
		NUM_5('5', 5),
		NUM_6('6', 5),
		NUM_7('7', 5),
		NUM_8('8', 5),
		NUM_9('9', 5),
		NUM_0('0', 5),
		EXCLAMATION_POINT('!', 1),
		AT_SYMBOL('@', 6),
		NUM_SIGN('#', 5),
		DOLLAR_SIGN('$', 5),
		PERCENT('%', 5),
		UP_ARROW('^', 5),
		AMPERSAND('&', 5),
		ASTERISK('*', 5),
		LEFT_PARENTHESIS('(', 4),
		RIGHT_PERENTHESIS(')', 4),
		MINUS('-', 5),
		UNDERSCORE('_', 5),
		PLUS_SIGN('+', 5),
		EQUALS_SIGN('=', 5),
		LEFT_CURL_BRACE('{', 4),
		RIGHT_CURL_BRACE('}', 4),
		LEFT_BRACKET('[', 3),
		RIGHT_BRACKET(']', 3),
		COLON(':', 1),
		SEMI_COLON(';', 1),
		DOUBLE_QUOTE('"', 3),
		SINGLE_QUOTE('\'', 1),
		LEFT_ARROW('<', 4),
		RIGHT_ARROW('>', 4),
		QUESTION_MARK('?', 5),
		SLASH('/', 5),
		BACK_SLASH('\\', 5),
		LINE('|', 1),
		TILDE('~', 5),
		TICK('`', 2),
		PERIOD('.', 1),
		COMMA(',', 1),
		SPACE(' ', 3),
		DEFAULT('a', 4);

		private final char character;
		private final int length;

		DefaultFontInfo(char character, int length) {
			this.character = character;
			this.length = length;
		}

		public char getCharacter() {
			return this.character;
		}

		public int getLength() {
			return this.length;
		}

		public int getBoldLength() {
			if (this == DefaultFontInfo.SPACE) return this.getLength();
			return this.length + 1;
		}

		public static DefaultFontInfo getDefaultFontInfo(char c) {
			for (DefaultFontInfo dFI : DefaultFontInfo.values()) {
				if (dFI.getCharacter() == c) return dFI;
			}
			return DefaultFontInfo.DEFAULT;
		}
	}

	public static String getCenteredMessage(Player player, String message){
		if (message == null || message.equals("")) player.sendMessage("");
		message = colorize(message);

		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;

		for(char c : message.toCharArray()){
			if (c == '§') {
				previousCode = true;
			} else if (previousCode) {
				previousCode = false;
				if (c == 'l' || c == 'L') {
					isBold = true;
				} else isBold = false;
			} else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
		}

		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = 154 - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while(compensated < toCompensate){
			sb.append(" ");
			compensated += spaceLength;
		}
		return sb + message;
	}
}