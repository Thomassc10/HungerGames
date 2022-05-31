package me.thomas.hungergames.events;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.game.GameState;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        GameManager gameManager = GameManager.getGameManager();
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        HgPlayer hgPlayer = gameManager.getPlayerManager().getHgPlayer(player);
        if (gameManager.getGameState() == GameState.WAITING || gameManager.isInvinciblePeriod() ||
                !gameManager.getPlayerManager().getHgPlayers().contains(hgPlayer))
            event.setCancelled(true);
    }
}
