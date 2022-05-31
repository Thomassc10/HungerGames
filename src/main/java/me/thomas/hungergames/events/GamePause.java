package me.thomas.hungergames.events;

import me.thomas.hungergames.game.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class GamePause implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        GameManager gameManager = GameManager.getGameManager();
        if (gameManager.isPause())
            event.setCancelled(true);
    }
}
