package me.thomas.hungergames.events;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.player.HgPlayer;
import me.thomas.hungergames.player.PlayerState;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;

        GameManager gameManager = GameManager.getGameManager();
        Player killer = event.getEntity().getKiller();
        Player dead = event.getEntity();

        HgPlayer hgPlayer = gameManager.getPlayerManager().getHgPlayer(killer);
        HgPlayer hgPlayer1 = gameManager.getPlayerManager().getHgPlayer(dead);
        hgPlayer.addKills();
        hgPlayer1.setPlayerState(PlayerState.DEAD);
        dead.setGameMode(GameMode.SPECTATOR);
        gameManager.getScoreboardManager().updateScoreboard(killer, "kills", "" + hgPlayer.getKills());
        gameManager.getPlayerStats().addDeaths(dead);
        event.setDeathMessage(ChatColor.AQUA + dead.getName() + "(" + hgPlayer1.getKit().getName() + ") was killed by " + killer.getName() +
                "(" + hgPlayer.getKit().getName() + ") with a " + killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
    }
}
