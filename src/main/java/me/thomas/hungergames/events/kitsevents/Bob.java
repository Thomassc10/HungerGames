package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Bob extends Kit implements Listener {

    private Map<String, Long> cooldown = new HashMap<>();
    public Bob() {
        super(new ItemStack(Material.IRON_INGOT), "B.O.B", Arrays.asList("&7Spawns a golem to help you", "&7defeat your enemies."));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        if (!player.getInventory().getItemInMainHand().hasItemMeta()) return;

        if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Iron Golem Spawn Egg")) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (cooldown.containsKey(player.getName())) {
                    if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
                        player.sendMessage(ChatColor.RED + "This ability will be ready in " + ChatColor.GOLD + (cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000);
                        return;
                    }
                }
                cooldown.put(player.getName(), System.currentTimeMillis() + 25000);
                player.getWorld().spawn(event.getClickedBlock().getLocation().add(0, 1, 0), IronGolem.class);
                event.setCancelled(true);
            }
        }
    }
}
