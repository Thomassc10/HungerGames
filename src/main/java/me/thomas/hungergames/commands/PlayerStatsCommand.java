package me.thomas.hungergames.commands;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hgstats")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            PlayerStats playerStats = GameManager.getGameManager().getPlayerStats();
            int games = playerStats.getGamesPlayed(player);
            int kills = playerStats.getKills(player);
            int deaths = playerStats.getDeaths(player);
            int wins = playerStats.getWins(player);
            player.sendMessage(ChatColor.GREEN + player.getName() + "'s HungerGames stats: \n" +
                    "Games played: " + ChatColor.YELLOW + games + "\n" + ChatColor.GREEN +
                    "Wins: " + ChatColor.YELLOW + wins + "\n" + ChatColor.GREEN +
                    "Kills: " + ChatColor.YELLOW + kills + "\n" + ChatColor.GREEN +
                    "Deaths: " + ChatColor.YELLOW + deaths);
            return true;
        }
        return false;
    }
}
