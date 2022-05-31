package me.thomas.hungergames.kits;

import me.thomas.hungergames.events.kitsevents.*;
import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class KitsManager {

    private static Map<String, Kit> kits;
    public KitsManager() {
        kits = new HashMap<>();
    }

    public Map<String, Kit> getKits() {
        return kits;
    }

    public Kit getKitByKey(String key) {
        if (kits.containsKey(key))
            return kits.get(key);
        return kits.get("default");
    }

    public Kit getKitByItem(ItemStack item) {
        for (Kit k : kits.values()) {
            if (k.getItemStack().isSimilar(item))
                return k;
        }
        return kits.get("default");
    }
    
    public void randomKit(Player player) {
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        List<Kit> kit = new ArrayList<>(kits.values());
        Random r = new Random();
        int random = r.nextInt(kit.size());
        hgPlayer.setKit(kit.get(random));
    }

    public void giveKitItems(Player player) {
        HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
        switch (hgPlayer.getKit().getName()){
            case "Endermage":
                ItemStack portal = new ItemStack(Material.END_PORTAL_FRAME);
                ItemMeta portalmeta = portal.getItemMeta();
                portalmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
                portalmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                portal.setItemMeta(portalmeta);
                player.getInventory().addItem(portal);
                break;
            case "Fisherman":
                ItemStack fish = new ItemStack(Material.FISHING_ROD);
                ItemMeta fishmeta = fish.getItemMeta();
                fishmeta.setUnbreakable(true);
                fish.setItemMeta(fishmeta);
                player.getInventory().addItem(fish);
                break;
            case "Lumberjack":
                ItemStack axe = new ItemStack(Material.WOODEN_AXE);
                ItemMeta axemeta = axe.getItemMeta();
                axemeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                axe.setItemMeta(axemeta);
                player.getInventory().addItem(axe);
                break;
            case "Archer":
                ItemStack bow = new ItemStack(Material.BOW);
                ItemMeta bowmeta = bow.getItemMeta();
                bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                bow.setItemMeta(bowmeta);
                player.getInventory().addItem(bow);
                break;
            case "Scout":
                ItemStack potion = new ItemStack(Material.SPLASH_POTION, 3);
                PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
                potionMeta.setColor(Color.BLUE);
                potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 4), true);
                potion.setItemMeta(potionMeta);
                player.getInventory().addItem(potion);
                break;
            case "Poseidon":
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta meta = (LeatherArmorMeta) boots.getItemMeta();
                meta.addEnchant(Enchantment.DEPTH_STRIDER, 5, true);
                meta.setDisplayName(ChatColor.GOLD + "Poseidon's Boots");
                meta.setUnbreakable(true);
                meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                boots.setItemMeta(meta);
                player.getInventory().setBoots(boots);
                break;
            case "Backpack":
                ItemStack bp = new ItemStack(Material.LEATHER);
                ItemMeta bpMeta = bp.getItemMeta();
                bpMeta.setDisplayName(ChatColor.GREEN + "Backpack");
                bp.setItemMeta(bpMeta);
                player.getInventory().addItem(bp);
                break;
            case "BeastMaster":
                player.getInventory().addItem(new ItemStack(Material.WOLF_SPAWN_EGG, 2));
                player.getInventory().addItem(new ItemStack(Material.BONE, 6));
                break;
            case "B.O.B":
                ItemStack golem = new ItemStack(Material.WOLF_SPAWN_EGG);
                ItemMeta golemMeta = golem.getItemMeta();
                golemMeta.setDisplayName(ChatColor.GREEN + "Iron Golem Spawn Egg");
                golem.setItemMeta(golemMeta);
                player.getInventory().addItem(golem);
                break;
            case "Thor":
                player.getInventory().addItem(new ItemStack(Material.WOODEN_AXE));
                break;
            default:
                break;
        }
    }

    public static void registerKits() {
        kits.put("default", new Kit(new ItemStack(Material.BARRIER), "Wrong kit", Arrays.asList("Something wrong occurred!")));
        kits.put("achilles", new Achilles());
        kits.put("anti_kb", new AntiKB());
        kits.put("archer", new Archer());
        kits.put("assassin", new Assassin());
        kits.put("backpack", new Backpack());
        kits.put("barbarian", new Barbarian());
        kits.put("beastmaster", new Kit(new ItemStack(Material.WOLF_SPAWN_EGG), "BeastMaster", Arrays.asList("BeastMaster")));
        kits.put("beserker", new Beserker());
        kits.put("bob", new Bob());
        kits.put("boxer", new Boxer());
        kits.put("cannibal", new Cannibal());
        kits.put("cookiemonster", new Cookiemonster());
        kits.put("cultivator", new Cultivator());
        kits.put("endermage", new Endermage());
        kits.put("fisherman", new Fisherman());
        kits.put("hulk", new Hulk());
        kits.put("lumberjack", new Lumberjack());
        kits.put("ninja", new Ninja());
        kits.put("poseidon", new Poseidon());
        kits.put("scout", new Kit(new ItemStack(Material.SPLASH_POTION), "Scout", Arrays.asList("Scout")));
        kits.put("stomper", new Stomper());
        kits.put("thor", new Thor());
        kits.put("vampire", new Vampire());
        kits.put("viking", new Viking());
        kits.put("viper", new Viper());
    }
}
