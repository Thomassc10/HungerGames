package me.thomas.hungergames.recipes;

import me.thomas.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Soup {

    public static void register(){
        Bukkit.getServer().addRecipe(cactussoup());
        Bukkit.getServer().addRecipe(applesoup());
        Bukkit.getServer().addRecipe(cocoasoup());
        Bukkit.getServer().addRecipe(seedsoup());
    }

    public static ShapedRecipe cactussoup(){
        ItemStack item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(HungerGames.getInstance(), "cactus_stew");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CC", " B");
        recipe.setIngredient('C', Material.CACTUS);
        recipe.setIngredient('B', Material.BOWL);
        return recipe;
    }

    public static ShapedRecipe applesoup(){
        ItemStack item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(HungerGames.getInstance(), "apple_stew");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("AA"," B");
        recipe.setIngredient('A', Material.APPLE);
        recipe.setIngredient('B', Material.BOWL);
        return recipe;
    }

    public static ShapedRecipe cocoasoup(){
        ItemStack item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(HungerGames.getInstance(), "cocoa_beans_stew");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CC"," B");
        recipe.setIngredient('C', Material.COCOA_BEANS);
        recipe.setIngredient('B', Material.BOWL);
        return recipe;
    }

    public static ShapedRecipe seedsoup(){
        ItemStack item = new ItemStack(Material.MUSHROOM_STEW);
        NamespacedKey key = new NamespacedKey(HungerGames.getInstance(), "seed_stew");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("SS", " B");
        recipe.setIngredient('S', Material.WHEAT_SEEDS);
        recipe.setIngredient('B', Material.BOWL);
        return recipe;
    }
}
