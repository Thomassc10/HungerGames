package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class Assassin extends Kit implements Listener {

    public Assassin() {
        super(new ItemStack(Material.IRON_SWORD), "Assassin", Arrays.asList("&7Deals more damage when hitting", "&7on the targets back."));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;

        Vector pDir = player.getLocation().getDirection();
        Vector eDir = event.getEntity().getLocation().getDirection();
        double xv = pDir.getX() * eDir.getZ() - pDir.getZ() * eDir.getX();
        double zv = pDir.getX() * eDir.getX() + pDir.getZ() * eDir.getZ();
        double angle = Math.atan2(xv, zv);
        double angleInDegrees = (angle * 180) / Math.PI;

        if (angleInDegrees <= 60 && angleInDegrees >= -32) {
            event.setDamage(event.getDamage() * 1.5);
        }
    }
}
