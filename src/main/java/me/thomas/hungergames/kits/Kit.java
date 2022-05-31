package me.thomas.hungergames.kits;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Kit {

    private ItemStack itemStack;
    private String name;
    private List<String> lore;
    public Kit(ItemStack item, String name, List<String> lore) {
        this.itemStack = item;
        this.name = name;
        this.lore = lore;
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> l = new ArrayList<>();
        for (String s : lore)
            l.add(ChatColor.translateAlternateColorCodes('&', s));
        meta.setLore(l);
        item.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }
}
