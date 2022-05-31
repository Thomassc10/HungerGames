package me.thomas.hungergames.chests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChestsForm {

    public ChestsForm(){
        spawnChest(chestItems());
    }

    public void spawnChest(ItemStack[] items) {
        Random r = new Random();
        int x = r.nextInt(200) - r.nextInt(200);
        int z = r.nextInt(200) - r.nextInt(200);
        Location loc = new Location(Bukkit.getWorld("world"), x, 150, z);
        loc.setY(Bukkit.getWorld("world").getHighestBlockYAt(loc));
        Block block = loc.getBlock();
        Block northBlock = block.getRelative(BlockFace.NORTH);
        Block eastBlock = block.getRelative(BlockFace.EAST);
        Block westBlock = block.getRelative(BlockFace.WEST);
        Block southBlock = block.getRelative(BlockFace.SOUTH);
        List<Block> blocks = Arrays.asList(northBlock, eastBlock, westBlock, southBlock);
        block.setType(Material.ENCHANTING_TABLE);
        for (Block b : blocks) {
            b.setType(Material.CHEST);
            Chest chest = (Chest) b.getState();
            chest.getInventory().addItem(items);
        }
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(
        		ChatColor.RED + "Location: " + ChatColor.GOLD + loc.getX() + ", " + loc.getY() + ", " + loc.getZ()));
    }

    public ItemStack[] chestItems() {
    	Random r = new Random();
        ItemStack bow = new ItemStack(Material.BOW);
        bow.getItemMeta().addEnchant(Enchantment.ARROW_DAMAGE, r.nextInt(3) + 1, true);
        return new ItemStack[]{new ItemStack(Material.DIAMOND, 3),
                new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS),
                new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.COOKED_CHICKEN, 22), new ItemStack(Material.COOKED_BEEF, 22),
                new ItemStack(Material.ENDER_PEARL, 6), new ItemStack(Material.WATER_BUCKET),
                new ItemStack(Material.LAVA_BUCKET), new ItemStack(Material.ARROW, 12),
                bow};
    }
}
