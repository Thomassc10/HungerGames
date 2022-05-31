package me.thomas.hungergames.commands;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.game.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NewSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("newspawn")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            GameManager gameManager = GameManager.getGameManager();
            if (gameManager.getGameState() == GameState.WAITING)
                gameManager.getPlayerManager().newSpawn(player);
            player.sendMessage(ChatColor.GREEN + "Teleporting to new location.");
            return true;
        }
        return false;
    }
}
