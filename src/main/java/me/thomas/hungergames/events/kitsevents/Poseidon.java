package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Poseidon extends Kit implements Listener {

	public Poseidon() {
		super(new ItemStack(Material.WATER_BUCKET), "Poseidon", Arrays.asList("&7Becomes stronger when inside the water.", "&7Starts with depth strider 4 boots."));
	}

	@EventHandler
	public void onPlace(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;
		if (player.isInWater()) {
			player.setRemainingAir(20);
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 1));
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getCurrentItem() == null) return;
		Player player = (Player) event.getWhoClicked();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;
		if (!event.getCurrentItem().hasItemMeta()) return;
		if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Poseidon's Boots")) {
			event.setCancelled(true);
			if (event.getCursor().getType() == Material.IRON_INGOT || event.getCursor().getType() == Material.GOLD_INGOT ||
					event.getCursor().getType() == Material.DIAMOND) {
				if (event.getCursor().getAmount() >= 4) {
					Material material = event.getCursor().getType();
					String name = material.name().contains("INGOT") ? material.name().replace("_INGOT", "") : material.name();
					player.getInventory().getBoots().setType(Material.matchMaterial(name + "_BOOTS"));
					player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() - 4);
				}
			} else if (event.getCursor().getType().name().contains("BOOTS")) {
				player.getInventory().getBoots().setType(event.getCursor().getType());
				event.setCursor(new ItemStack(Material.AIR));
			}
		}
	}

}
