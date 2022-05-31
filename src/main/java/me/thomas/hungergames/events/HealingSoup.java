package me.thomas.hungergames.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HealingSoup implements Listener {
	
	@EventHandler
	public void onEat(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand() == null) return;
		if (!player.getInventory().getItemInMainHand().getType().equals(Material.MUSHROOM_STEW)) return;
		if (player.getHealth() == 20) return;

		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (player.getHealth() >= 14) {
				player.setHealth(20);
			}else player.setHealth(player.getHealth() + 6);
			player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.BOWL));
		}
	}

}

















