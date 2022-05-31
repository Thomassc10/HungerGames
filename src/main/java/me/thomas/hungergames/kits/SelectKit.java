package me.thomas.hungergames.kits;

import me.thomas.hungergames.game.GameManager;

import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SelectKit implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        GameManager gameManager = GameManager.getGameManager();
        KitsManager kits = gameManager.getKitsManager();
        HgPlayer hgPlayer = gameManager.getPlayerManager().getHgPlayer(player);
        if (event.getInventory() != KitInventory.getInventory()) return;
        if (event.getCurrentItem() == null) return;
        event.setCancelled(true);
        String name = event.getCurrentItem().getItemMeta().getDisplayName();
        if (!name.contains("Random Kit")) {
        	hgPlayer.setKit(kits.getKitByItem(event.getCurrentItem()));
        }else kits.randomKit(player);
        GameManager.getGameManager().getScoreboardManager().updateScoreboard(player, "kits", ChatColor.GREEN + name);
        player.sendMessage(ChatColor.GREEN + "Selected the kit: " + ChatColor.RED + name);
    }
}
