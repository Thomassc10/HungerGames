package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Hulk extends Kit implements Listener {

    public Hulk() {
        super(new ItemStack(Material.LEATHER_HORSE_ARMOR), "Hulk", Arrays.asList("&7Right-Click while sneaking on an entity", "&7to carry them on your back."));
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        GameManager gameManager = GameManager.getGameManager();
        Player player = event.getPlayer();
        HgPlayer hgPlayer = gameManager.getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) return;

        Entity entity = event.getRightClicked();
        if (player.getPassengers().isEmpty() && player.isSneaking())
            player.addPassenger(entity);
    }
}
