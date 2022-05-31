package me.thomas.hungergames.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {

    private List<HgPlayer> hgPlayers;
    public PlayerManager(){
        hgPlayers = new ArrayList<>();
    }

    public List<HgPlayer> getHgPlayers() {
        return hgPlayers;
    }

    public HgPlayer getHgPlayer(Player player) {
        for (HgPlayer hgPlayer : hgPlayers){
            if (hgPlayer.getUuid().equals(player.getUniqueId()))
                return hgPlayer;
        }
        return null;
    }

    public void addHgPlayer(HgPlayer hgPlayer){
        hgPlayers.add(hgPlayer);
    }

    public void pointCompassToNearestPlayer(Player player) {
        //new BukkitRunnable() {
            List<Entity> allent = player.getNearbyEntities(500, 150, 500);
            List<Player> allplayers = new ArrayList<>();
            //Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            //@Override
            //public void run() {
                for (Entity a : allent)
                    if (a instanceof Player)
                        allplayers.add((Player) a);
                Player nearestPlayer = allplayers.get(0);
                player.setCompassTarget(nearestPlayer.getLocation());
            //}
        //}.runTaskTimer(HungerGames.getInstance(), 0, 10);
    }

    public void randomTeleport() {
        for (int i = 0; i < hgPlayers.size(); i++) {
            Random r = new Random();
            int x = r.nextInt(200) - r.nextInt(200);
            int z = r.nextInt(200) - r.nextInt(200);
            Bukkit.getOnlinePlayers().forEach(player -> {
                Location loc = player.getLocation();
                player.teleport(new Location(player.getWorld(), x, 150, z));
                player.teleport(new Location(player.getWorld(), loc.getX(), player.getWorld().getHighestBlockYAt(loc), loc.getZ()));
            });
        }
    }

    public void newSpawn(Player player) {
        Random r = new Random();
        int x = r.nextInt(1000) - r.nextInt(1000);
        int z = r.nextInt(1000) - r.nextInt(1000);
        Location loc = player.getLocation();
        player.teleport(new Location(player.getWorld(), x, 150, z));
        player.teleport(new Location(player.getWorld(), loc.getX(), player.getWorld().getHighestBlockYAt(loc), loc.getZ()));
    }

    public List<HgPlayer> getAlivePlayers() {
        List<HgPlayer> alive = new ArrayList<>();
        for (HgPlayer hgPlayer : hgPlayers) {
            if (hgPlayer.getPlayerState() == PlayerState.PLAYING)
                alive.add(hgPlayer);
        }
        return alive;
    }

    public List<HgPlayer> getDeadPlayers() {
        List<HgPlayer> dead = new ArrayList<>();
        for (HgPlayer hgPlayer : hgPlayers) {
            if (hgPlayer.getPlayerState() == PlayerState.DEAD)
                dead.add(hgPlayer);
        }
        return dead;
    }
}
