package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Random;

public class Vampire extends Kit implements Listener {

	public Vampire() {
		super(new ItemStack(Material.SPIDER_EYE), "Vampire", Arrays.asList("&7There is a chance of stealing life", "&7off your victims.", "&7Receives a healing potion when", "&7killing a player."));
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getDamager();
		if (player.getHealth() == 20) return;
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;

		Random r = new Random();
		int i = r.nextInt(20) + 1;
		if (i >= 5 && i < 20) {
			player.setHealth(player.getHealth() + 1);
		}else
		if (i == 20) {
			player.setHealth(player.getHealth() + 3);
		}
	}

	@EventHandler
	public void onKill(PlayerDeathEvent event) {
		if (event.getEntity().getKiller() == null) return;
		Player player = event.getEntity().getKiller();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (!hgPlayer.getKit().getName().contains(getName())) return;
		ItemStack item = new ItemStack(Material.SPLASH_POTION);
		PotionMeta meta = (PotionMeta) item.getItemMeta();
		meta.setColor(Color.RED);
		meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
		item.setItemMeta(meta);
		if (player.getInventory().firstEmpty() == -1) {
			player.getWorld().dropItemNaturally(player.getLocation(), item);
		} else player.getInventory().addItem(item);
	}
}
