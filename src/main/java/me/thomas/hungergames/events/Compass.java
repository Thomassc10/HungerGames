package me.thomas.hungergames.events;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Compass implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand() == null) return;
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) return;
        PlayerManager playerManager = GameManager.getGameManager().getPlayerManager();
        //playerManager.pointCompassToNearestPlayer(player);
    }

}
