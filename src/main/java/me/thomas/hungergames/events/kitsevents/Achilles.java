package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Achilles extends Kit implements Listener {

    public Achilles() {
        super(new ItemStack(Material.ACACIA_BOAT), "Achilles", Arrays.asList("&7Takes less damage from swords,", "&7but takes a lot more damage",
                "&7from a wooden sword."));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        Player damager = (Player) event.getDamager();
        if (damager.getInventory().getItemInMainHand().getType().name().contains("SWORD")) {
            if (damager.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
                event.setDamage(event.getDamage() * 2.5);
            } else event.setDamage(event.getDamage() / 2);
        }
    }
}
