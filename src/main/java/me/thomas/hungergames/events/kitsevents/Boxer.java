package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Boxer extends Kit implements Listener {

	public Boxer() {
		super(new ItemStack(Material.STONE_SWORD), "Boxer", Arrays.asList("&7Your fist deals the same", "&7amount of damage as a stone", "&7sword."));
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getDamager();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;

		if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
			event.setDamage(6);
		}
	}

}
