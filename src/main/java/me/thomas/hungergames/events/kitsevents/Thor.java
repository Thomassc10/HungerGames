package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Thor extends Kit implements Listener {

    private Map<String, Long> cooldown = new HashMap<>();
    public Thor() {
        super(new ItemStack(Material.STONE_AXE), "Thor", Arrays.asList("&7Right-Click with an axe to", "&7strike lightning on your enemies."));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;

        if (player.getInventory().getItemInMainHand().getType() == Material.WOODEN_AXE) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (cooldown.containsKey(player.getName())) {
                    if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
                        player.sendMessage(ChatColor.RED + "This ability will be ready in " + ChatColor.GOLD + ((cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000));
                        return;
                    }
                }
                cooldown.put(player.getName(), System.currentTimeMillis() + 3000);
                RayTraceResult rayTraceResult = player.rayTraceBlocks(20);
                if (rayTraceResult.getHitEntity() != null)
                    player.getWorld().strikeLightning(rayTraceResult.getHitEntity().getLocation());
                else if (rayTraceResult.getHitBlock() == null)
                    return;
                else player.getWorld().strikeLightning(rayTraceResult.getHitBlock().getLocation());
            }
        }
    }
}
