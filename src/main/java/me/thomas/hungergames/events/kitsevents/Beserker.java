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

public class Beserker extends Kit implements Listener {

	public Beserker() {
		super(new ItemStack(Material.STONE_AXE), "Beserker", Arrays.asList("&7The less health you have,", "&7the more damage you deal to others."));
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getDamager();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;

		event.setDamage((20 - player.getHealth()) / 20 * 5 * event.getDamage());
		/*if (player.getHealth() <= 9 && player.getHealth() > 5) {
			event.setDamage(event.getDamage() * 1.5);
		}
		if (player.getHealth() <= 5) {
			event.setDamage(event.getDamage() * 2);
			player.sendMessage("" + event.getDamage());
		}*/
	}

}
