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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Random;

public class Cannibal extends Kit implements Listener {

    public Cannibal() {
        super(new ItemStack(Material.COD), "Cannibal", Arrays.asList("&7There is a chance of giving", "&7hunger to you target when", "&7hitting them."));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;

        Random r = new Random();
        int i = r.nextInt(2);
        Player p = (Player) event.getEntity();
        if (i == 1) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 2));
        }
    }
}
