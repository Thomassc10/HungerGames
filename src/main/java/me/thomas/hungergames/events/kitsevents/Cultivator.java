package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Cultivator extends Kit implements Listener {

    public Cultivator() {
        super(new ItemStack(Material.WHEAT), "Cultivator", Arrays.asList("&7Instantly grows seeds when", "&7planting them."));
    }

    @EventHandler
    public void onPlant(BlockPlaceEvent event){
        Player player = event.getPlayer();
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        Block block = event.getBlockPlaced();
        BlockData data = block.getBlockData();
        if (data instanceof Ageable){
            Ageable ageable = (Ageable) data;
            ageable.setAge(ageable.getMaximumAge());
            block.setBlockData(data);
        }
    }
}
