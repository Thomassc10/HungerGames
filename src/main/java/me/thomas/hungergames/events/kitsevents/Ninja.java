package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ninja extends Kit implements Listener {

	private Map<String, Long> cooldown = new HashMap<>();

	public Ninja() {
		super(new ItemStack(Material.LEATHER_BOOTS), "Ninja", Arrays.asList("&7Hit an entity while sneaking to", "&7teleport behind them."));
		LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) getItemStack().getItemMeta();
		leatherArmorMeta.setColor(Color.BLACK);
		getItemStack().setItemMeta(leatherArmorMeta);
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player player = (Player) event.getDamager();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;
		LivingEntity entity = (LivingEntity) event.getEntity();
		if (player.isSneaking()) {
			if (cooldown.containsKey(player.getName())) {
				if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
					player.sendMessage(ChatColor.RED + "This ability will be ready in " + ChatColor.GOLD + ((cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000));
					return;
				}
			}
			cooldown.put(player.getName(), System.currentTimeMillis() + 3000);
			player.teleport(entity.getLocation().add(entity.getLocation().getDirection().multiply(-1)));
		}
	}

}
