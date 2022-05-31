package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.HungerGames;
import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Endermage extends Kit implements Listener {
	
	public Map<String, Long> cooldown = new HashMap<>();

	public Endermage() {
		super(new ItemStack(Material.END_PORTAL_FRAME), "Endermage", Arrays.asList("&7Place an End Portal Frame on", "&7the ground to teleport players.", "", "&c(change this description later)"));
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand() == null) return;
		if (!player.getInventory().getItemInMainHand().getType().equals(Material.END_PORTAL_FRAME)) return;
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;
		
		Material type = event.getClickedBlock().getType();
		Location loc = event.getClickedBlock().getLocation().getBlock().getLocation().add(0.5, 1, 0.5);
		LivingEntity entity = getNearbyEntities(player);
		if (cooldown.containsKey(player.getName())) {
			if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
				long timeleft = (cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
				player.sendMessage(ChatColor.RED + "Ability will be ready in " + ChatColor.GOLD + timeleft + ChatColor.RED + " second(s).");
				return;
			}
		}
		cooldown.put(player.getName(), System.currentTimeMillis() + (10 * 1000));
		event.getClickedBlock().setType(Material.END_PORTAL_FRAME);
		//this.spawnParticles(player, loc, type);
		Bukkit.getScheduler().runTaskLater(HungerGames.getInstance(), () -> {
				event.getClickedBlock().setType(type);
				if (entity != null) {
					entity.teleport(loc);
					player.teleport(loc);
			}
		}, 40);
	}
	
	/*public void spawnParticles(Player player, Location loc, Material type) {
		new BukkitRunnable() {
			double var = 0;
			@Override
			public void run() {
				if (type == Material.END_PORTAL_FRAME) {
					var += Math.PI / 16;
					player.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc.clone().add(Math.cos(var), Math.sin(var) + 1, Math.sin(var)), 0);
					player.getWorld().spawnParticle(Particle.DRAGON_BREATH, loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) + 1, Math.sin(var + Math.PI)), 0);
				}
			}
			
		}.runTaskTimer(HungerGames.getInstance(), 0, 1);
	}*/
	
	@EventHandler 
	public void onDrop(PlayerDropItemEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.END_PORTAL_FRAME))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType().equals(Material.END_PORTAL_FRAME))
			event.setCancelled(true);
	}
	
	public static LivingEntity getNearbyEntities(Player player){
		List<Entity> nearAll = player.getNearbyEntities(3, 150, 3);
		List<Entity> nearPlayer = new ArrayList<>();
		for (Entity a : nearAll)
			if (a instanceof LivingEntity)
				nearPlayer.add(a);
		if (nearPlayer.size() == 0) return null;
		LivingEntity nearestPlayer = (LivingEntity) nearPlayer.get(0);
		for (Entity a : nearPlayer) {
			if (player.getLocation().distance(a.getLocation()) < player.getLocation().distance(nearestPlayer.getLocation())) {
				nearestPlayer = (LivingEntity) a;
			}
		}
		return nearestPlayer;
	}
}
