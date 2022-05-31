package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class Fisherman extends Kit implements Listener {

    public Fisherman() {
        super(new ItemStack(Material.FISHING_ROD), "Fisherman", Arrays.asList("&7Fish a player to launch it", "&7to your direction.", "", "&c(change description)"));
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
    	if (event.getCaught() == null) return;
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;

        Location l = player.getLocation();
        Location l2 = event.getHook().getLocation();
        //Vector v = new Vector((l2.getX() - l.getX()), (l2.getY() - l.getY()), (l2.getZ() - l.getZ()));
        event.getCaught().setVelocity(l.toVector().subtract(l2.toVector()));
        //event.getCaught().setVelocity(l.toVector().normalize().multiply(1));
    }

}
