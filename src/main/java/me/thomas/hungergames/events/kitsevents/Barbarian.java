package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Random;

public class Barbarian extends Kit implements Listener {

    public Barbarian() {
        super(new ItemStack(Material.WOODEN_SWORD), "Barbarian", Arrays.asList("&7There is a chance to upgrade your", "&7sword when killing other players."));
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();
        if (!player.getInventory().getItemInMainHand().getType().name().contains("SWORD")) return;
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        
        Random r = new Random();
        int i = r.nextInt(5) + 1;
        int o = r.nextInt(20) + 1;
        if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_SWORD)){
            player.getInventory().getItemInMainHand().setType(Material.STONE_SWORD);
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)){
            if (i == 5) {
                player.getInventory().getItemInMainHand().setType(Material.IRON_SWORD);
            }
        }else
        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)){
            if (o == 20) {
                player.getInventory().getItemInMainHand().setType(Material.DIAMOND_SWORD);
            }
        }

    }

}