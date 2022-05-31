package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stomper extends Kit implements Listener {

	public Stomper() {
		super(new ItemStack(Material.IRON_BOOTS), "Stomper", Arrays.asList("&7Takes less fall damage and", "&7make others receive your damage.", "", "&c(change description)"));
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		Player player = (Player) event.getEntity();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;

		if (event.getCause() == DamageCause.FALL) {
			Entity entity = getNearbyEntities(player);
			//Player p = (Player) entity;
			if (entity == null) {
				event.setDamage(event.getDamage() / 4);
			} else {
					((Damageable) entity).damage(event.getDamage() / 1.5);
					event.setCancelled(true);
				}
		}
	}

	public Entity getNearbyEntities(Player player) {
		List<Entity> nearAll = player.getNearbyEntities(4, 2, 4);
		List<Entity> nearPlayer = new ArrayList<>();
		for (Entity a : nearAll)
			if (a instanceof Player)
				nearPlayer.add(a);
		if (nearPlayer.size() == 0) return null;
		Entity nearestPlayer = nearPlayer.get(0);
		for (Entity a : nearPlayer) {
			if (player.getLocation().distance(a.getLocation()) < player.getLocation().distance(nearestPlayer.getLocation())) {
				nearestPlayer = a;
			}
		}
		return nearestPlayer;
	}

}
