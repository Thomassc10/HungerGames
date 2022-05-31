package me.thomas.hungergames.commands;

import me.thomas.hungergames.chests.ChestsForm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnChests implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("chests")){
            new ChestsForm();
            return true;
        }
        return false;
    }
}
