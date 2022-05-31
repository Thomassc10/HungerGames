package me.thomas.hungergames.scoreboard;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager {

    public String[] scores = new String[] { ChatColor.UNDERLINE + "" + ChatColor.RESET, ChatColor.ITALIC + "" + ChatColor.RESET, ChatColor.BOLD + "" + ChatColor.RESET, ChatColor.RESET + "" + ChatColor.RESET, ChatColor.GREEN + "" + ChatColor.RESET, ChatColor.DARK_GRAY + "" + ChatColor.RESET, ChatColor.GOLD + "" + ChatColor.RESET, ChatColor.RED + "" + ChatColor.RESET, ChatColor.YELLOW + "" + ChatColor.RESET, ChatColor.WHITE + "" + ChatColor.RESET, ChatColor.DARK_GREEN + "" + ChatColor.RESET, ChatColor.BLUE + "" + ChatColor.RESET, ChatColor.STRIKETHROUGH + "" + ChatColor.RESET, ChatColor.MAGIC + "" + ChatColor.RESET, ChatColor.DARK_RED + "" + ChatColor.RESET };

    public void setScoreboard(Player player){
        GameManager gameManager = GameManager.getGameManager();
        if (gameManager.getGameState() == GameState.WAITING){
            player.setScoreboard(waitingScoreboard());
        }
        if (gameManager.getGameState() == GameState.PLAYING){
            player.setScoreboard(playinScoreboard());
        }
    }

    public void updateScoreboard(Player player, String team, String suffix){
        Scoreboard board = player.getScoreboard();
        board.getTeam(team).setSuffix(suffix);
    }

    public Scoreboard waitingScoreboard(){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("waitingscore", "dummy", ChatColor.YELLOW + "HungerGames");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team waiting = board.registerNewTeam("waiting");
        waiting.addEntry(scores[3]);
        waiting.setSuffix("Waiting for players...");
        obj.getScore(scores[3]).setScore(5);
        Team online = board.registerNewTeam("online-players");
        online.addEntry(scores[0]);
        online.setPrefix("Players: ");
        online.setSuffix(ChatColor.GOLD + "" + Bukkit.getOnlinePlayers().size() + "/20");
        obj.getScore(scores[0]).setScore(4);
        Team kits = board.registerNewTeam("kits");
        kits.addEntry(scores[1]);
        kits.setPrefix("Kit: ");
        kits.setSuffix(ChatColor.RED + "None");
        obj.getScore(scores[1]).setScore(2);
        Team ip = board.registerNewTeam("ip");
        ip.addEntry(scores[2]);
        ip.setPrefix("www.spigotmc.com");
        obj.getScore(scores[2]).setScore(0);

        obj.getScore(" ").setScore(1);
        obj.getScore("").setScore(3);
        obj.getScore("  ").setScore(6);
        return board;
    }

    public Scoreboard playinScoreboard(){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("playingscore", "dummy", ChatColor.GOLD + "HungerGames");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team time = board.registerNewTeam("time");
        time.addEntry(scores[0]);
        time.setPrefix("Game time: ");
        time.setSuffix("");
        obj.getScore(scores[0]).setScore(5);
        Team alive = board.registerNewTeam("alive");
        alive.addEntry(scores[1]);
        alive.setPrefix("Alive Players: ");
        alive.setSuffix("" + GameManager.getGameManager().getPlayerManager().getHgPlayers().size());
        obj.getScore(scores[1]).setScore(3);
        Team kills = board.registerNewTeam("kills");
        kills.addEntry(scores[2]);
        kills.setPrefix("Kills: ");
        kills.setSuffix("" + 0);
        obj.getScore(scores[2]).setScore(1);
        
        obj.getScore("").setScore(2);
        obj.getScore(" ").setScore(4);
        obj.getScore("  ").setScore(6);
        return board;
    }

}
