package me.thomas.hungergames.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {

	private Map<String, Integer> wins, kills, deaths, gamesPlayed;
	public PlayerManager playerManager;
	public PlayerStats() {
		playerManager = new PlayerManager();
		wins = new HashMap<>();
		kills = new HashMap<>();
		deaths = new HashMap<>();
		gamesPlayed = new HashMap<>();
	}

	public int getWins(Player player) {
		if (wins.containsKey(player.getUniqueId().toString()))
			return wins.get(player.getUniqueId().toString());
		return 0;
	}

	public int getKills(Player player) {
		if (kills.containsKey(player.getUniqueId().toString()))
			return kills.get(player.getUniqueId().toString());
		return 0;
	}

	public int getDeaths(Player player) {
		if (deaths.containsKey(player.getUniqueId().toString()))
			return deaths.get(player.getUniqueId().toString());
		return 0;
	}

	public int getGamesPlayed(Player player) {
		if (gamesPlayed.containsKey(player.getUniqueId().toString()))
			return gamesPlayed.get(player.getUniqueId().toString());
		return 0;
	}

	public void addWins(Player player) {
		wins.put(player.getUniqueId().toString(), wins.get(player.getUniqueId().toString()) + 1);
	}

	public void addKills(Player player) {
		kills.put(player.getUniqueId().toString(), kills.get(player.getUniqueId().toString()) + 1);
	}

	public void addDeaths(Player player) {
		deaths.put(player.getUniqueId().toString(), deaths.get(player.getUniqueId().toString()) + 1);
	}

	public void addGamesPlayed(Player player) {
		gamesPlayed.put(player.getUniqueId().toString(), gamesPlayed.get(player.getUniqueId().toString()) + 1);
	}

}
