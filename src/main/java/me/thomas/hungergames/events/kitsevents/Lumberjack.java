package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.HungerGames;
import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class Lumberjack extends Kit implements Listener {

    public Lumberjack() {
        super(new ItemStack(Material.WOODEN_AXE), "Lumberjack", Arrays.asList("&7Chop down entire trees while", "&7only breaking one block."));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(event.getPlayer());
        if (hgPlayer.getKit() == null) return;
        if (!hgPlayer.getKit().getName().contains(getName())) return;
        if (!event.getPlayer().getInventory().getItemInMainHand().getType().name().contains("AXE")) return;
        Block block = event.getBlock();
        if (!block.getType().name().contains("LOG")) return;
        doBlockEater(hgPlayer.getPlayer(), block, 20);
    }

    public void scheduleTask(final Runnable run, final int i) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(HungerGames.getInstance(), run, i);
    }

    public void doBlockEater(Player player, Block startingBlock, int amount) {
        if (startingBlock.getType() == Material.AIR) return;

        Material targetMaterial = startingBlock.getType();

        ArrayList<Block> blocksToCheck = new ArrayList<>();
        blocksToCheck.add(startingBlock);

        for (int i = 0; i <= amount; i++) {
            this.scheduleTask(() -> {
                ArrayList<Block> preClonedList = new ArrayList<>(blocksToCheck);
                for (Block block : preClonedList) {
                    blocksToCheck.remove(block);
                    Block upperBlock = block.getRelative(BlockFace.UP);
                    Block lowerBlock = block.getRelative(BlockFace.DOWN);
                    Block northBlock = block.getRelative(BlockFace.NORTH);
                    Block eastBlock = block.getRelative(BlockFace.EAST);
                    Block southBlock = block.getRelative(BlockFace.SOUTH);
                    Block westBlock = block.getRelative(BlockFace.WEST);
                    for (Block nearbyBlock : new ArrayList<Block>(Arrays.asList(upperBlock, lowerBlock, northBlock, eastBlock, southBlock, westBlock))) {
                        if (nearbyBlock.getType() == targetMaterial) {
                            if (nearbyBlock.getType().name().contains("LOG")) {
                                player.getWorld().dropItemNaturally(startingBlock.getLocation(), new ItemStack(targetMaterial));
                                nearbyBlock.getWorld().playSound(nearbyBlock.getLocation(), Sound.BLOCK_WOOD_BREAK, 0.3F, 2F);
                            }
                            nearbyBlock.setType(Material.AIR);
                            blocksToCheck.add(nearbyBlock);
                        }
                    }
                }
            }, i*2);
        }
    }

}
