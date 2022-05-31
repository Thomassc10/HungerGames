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

public class AntiKB extends Kit implements Listener {

	public AntiKB() {
		super(new ItemStack(Material.DIAMOND_CHESTPLATE), "Anti KB", Arrays.asList("&7You and your target do not", "&7receive any knock back damage."));
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		if (!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		HgPlayer hgPlayer1 = GameManager.getGameManager().getPlayerManager().getHgPlayer((Player) event.getDamager());
		if (hgPlayer.getKit() == null || hgPlayer1.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName()) || !hgPlayer1.getKit().getName().contains(getName())) return;
		player.damage(event.getDamage());
		event.setCancelled(true);
	}
}
