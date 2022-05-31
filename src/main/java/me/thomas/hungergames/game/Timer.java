package me.thomas.hungergames.game;

import me.thomas.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private int lobbyTimeLeft;
    private int gameTime;
    //private int dtTimeLeft;
    private int endTimeLeft;
    public Timer(){
        lobbyTimeLeft = 60;
        gameTime = 0;
        //dtTimeLeft = 1200;
        endTimeLeft = 10;
        this.runTaskTimer(HungerGames.getInstance(), 1, 20);
    }

    @Override
    public void run() {
        GameManager game = GameManager.getGameManager();
        if (!game.isPause()) {
            switch (game.getGameState()) {
                case WAITING:
                    int players = Bukkit.getOnlinePlayers().size();
                    if (players >= 10 && players <= 20) {
                        if (lobbyTimeLeft != 0) {

                            Bukkit.getOnlinePlayers().forEach(player -> game.getScoreboardManager().updateScoreboard(player, "waiting", "Starting in: " + lobbyTimeLeft));
                            lobbyTimeLeft--;
                            if (lobbyTimeLeft <= 10)
                                game.broadcastMessage(ChatColor.RED + "Game starting in: " + ChatColor.GOLD + lobbyTimeLeft);
                        } else {
                            game.setGameState(GameState.STARTING);
                        }
                    }
                    break;
                case STARTING:
                    game.startGame();
                    break;
                case PLAYING:
                    if (gameTime == 90)
                        game.setInvinciblePeriod(false);
                    if (gameTime == 300)
                        Bukkit.getWorlds().forEach(w -> w.getWorldBorder().setSize(500, 600));

                    Bukkit.getOnlinePlayers().forEach(player -> game.getScoreboardManager().updateScoreboard(player, "time", gameTime + ""));
                    gameTime++;
                    if (game.getPlayerManager().getAlivePlayers().size() == 1) {
                        game.endGame();
                    }
                    /*if (gameTimeLeft != 0) {
                        if (gameTimeLeft == 120) {
                            game.setInvinciblePeriod(false);
                        }
                        Bukkit.getOnlinePlayers().forEach(player -> game.getScoreboardManager().updateScoreboard(player, "time", gameTimeLeft + ""));
                        gameTimeLeft--;
                    } else {
                       //game.deathMatch();
                    }
                    break;*/
                /*case DEATHMATCH:
                    if (dtTimeLeft != 0) {
                        Bukkit.getOnlinePlayers().forEach(player -> game.getScoreboardManager().updateScoreboard(player, "time", dtTimeLeft + ""));
                        dtTimeLeft--;
                        /*if (game.getPlayerManager().getHgPlayers().size() == 1) {
                            game.endGame();
                        }
                    } else {
                        game.endGame();
                    }
                    break;*/
                case ENDED:
                    if (endTimeLeft != 0) {
                        if (endTimeLeft == 10) {
                            game.broadcastMessage("A new game will be starting soon."); System.out.println("Starting a new game soon.");
                        }
                        endTimeLeft--;
                    } else {
                        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("Starting a new game. Please wait a minute before joining again."));
                        game.setGameState(GameState.LOADING);
                        //game.setPause(true);
                        //game.loadNewGame();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
