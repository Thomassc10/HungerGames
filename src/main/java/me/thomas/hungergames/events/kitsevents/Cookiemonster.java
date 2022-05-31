package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Random;

public class Cookiemonster extends Kit implements Listener {

    public Cookiemonster() {
        super(new ItemStack(Material.COOKIE), "Cookiemonster", Arrays.asList("&7There is a chance of dropping a cookie", "&7when breaking grass.",
                "&7Eat cookies to gain speed", "&7and health regeneration."));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;

        if (event.getBlock().getType() == Material.GRASS || event.getBlock().getType() == Material.TALL_GRASS) {
            Random r = new Random();
            int i = r.nextInt(5);
            if (i == 4) {
                player.getWorld().dropItemNaturally(event.getBlock().getLocation().add(0, 1, 0), new ItemStack(Material.COOKIE));
            }
        }
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;

        if (event.getItem().getType() == Material.COOKIE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
        }
    }
}
