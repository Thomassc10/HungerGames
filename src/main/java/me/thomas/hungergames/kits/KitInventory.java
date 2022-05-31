package me.thomas.hungergames.kits;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitInventory {

    private static Inventory INV;

    public static void register(){
        GameManager gameManager = GameManager.getGameManager();
        Inventory inv = Bukkit.createInventory(null, 36, "Kits");
        for (Kit kit : gameManager.getKitsManager().getKits().values()){
            if (!kit.getName().contains("Wrong kit"))
                inv.addItem(kit.getItemStack());
        }
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Random Kit");
        item.setItemMeta(meta);
        inv.setItem(35, item);

        setInventory(inv);
    }

    public static Inventory getInventory(){
        return INV;
    }

    public static void setInventory(Inventory inv){
        INV = inv;
    }

    public static void openInventory(Player player){
        if (GameManager.getGameManager().getGameState() == GameState.WAITING || player.hasPermission("hg.kit.openinv"))
            player.openInventory(INV);
    }
}
