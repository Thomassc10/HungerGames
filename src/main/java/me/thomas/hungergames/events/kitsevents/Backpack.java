package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Backpack extends Kit implements Listener {

    private final Map<UUID, ItemStack[]> map = new HashMap<>();
    public Backpack() {
        super(new ItemStack(Material.CHEST), "Backpack", Arrays.asList("&7Receives a backpack to store", "&7items from your inventory."));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;

        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Backpack")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Inventory inv = Bukkit.createInventory(null, 27, "Backpack");
                if (map.containsKey(player.getUniqueId()))
                    inv.setContents(map.get(player.getUniqueId()));
                player.openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains("Backpack"))
            map.put(event.getPlayer().getUniqueId(), event.getInventory().getContents());
    }
}
