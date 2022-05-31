package me.thomas.hungergames.player;

import me.thomas.hungergames.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HgPlayer {

    private UUID uuid;
    private Kit kit;
    private PlayerState playerState;
    private int wins;
    private int kills;
    private int deaths;
    private int gamesPlayed;
    public HgPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public Kit getKit() {
        return kit;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamesPlayed() {
        gamesPlayed++;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeaths() {
        deaths++;
    }

    public int getKills() {
        return kills;
    }

    public void addKills() {
        kills++;
    }

    public int getWins() {
        return wins;
    }

    public void addWins() {
        wins++;
    }
}
