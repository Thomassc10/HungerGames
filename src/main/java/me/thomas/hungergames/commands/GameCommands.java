package me.thomas.hungergames.commands;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.game.GameState;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("game")){
        	GameManager gameManager = GameManager.getGameManager();
            if (args.length == 0){

                return true;
            }
            if (args[0].equalsIgnoreCase("state")) {
                if (args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "Game state: " + ChatColor.GOLD + gameManager.getGameState());
                    return true;
                }
                gameManager.setGameState(GameState.valueOf(args[1].toUpperCase()));
                sender.sendMessage(ChatColor.RED + "Set game state to " + ChatColor.GOLD + gameManager.getGameState());
                return true;
            }
            if (args[0].equalsIgnoreCase("pause")) {
                if (gameManager.isPause()) {
                    gameManager.setPause(false);
                    sender.sendMessage(ChatColor.RED + "Resumed the game.");
                } else {
                    gameManager.setPause(true);
                    sender.sendMessage(ChatColor.RED + "Paused the game.");
                }
                return true;
            }
        }
        return false;
    }
}
