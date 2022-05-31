package me.thomas.hungergames.events;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.game.GameState;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeave implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GameManager gameManager = GameManager.getGameManager();
        gameManager.getScoreboardManager().setScoreboard(event.getPlayer());
        HgPlayer hgPlayer = new HgPlayer(event.getPlayer().getUniqueId());
        if (!gameManager.getPlayerManager().getHgPlayers().contains(hgPlayer))
            gameManager.getPlayerManager().addHgPlayer(hgPlayer);

        int size = Bukkit.getOnlinePlayers().size();
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online == event.getPlayer())
                return;
            gameManager.getScoreboardManager().updateScoreboard(online, "online-player",
                    ChatColor.GOLD + "" + size + "/20");
        }
        event.setJoinMessage(ChatColor.YELLOW + hgPlayer.getPlayer().getName() + ChatColor.GREEN +  " joined the game. " + ChatColor.GOLD + "(" + size + "/20)");
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        GameManager gameManager = GameManager.getGameManager();
        if (gameManager.getGameState() == GameState.LOADING)
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "The world is currently being loaded. Please wait a minute before joining again.");
        else if (gameManager.getGameState() != GameState.WAITING)
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "There is ");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.DARK_GRAY + event.getPlayer().getName() + " disconnected.");
    }
}
