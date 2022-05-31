package me.thomas.hungergames.commands;

import me.thomas.hungergames.kits.KitInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("kits")){
            if (!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            KitInventory.openInventory(player);
            return true;
        }
        return false;
    }
}
