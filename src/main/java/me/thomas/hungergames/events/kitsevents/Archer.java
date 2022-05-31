package me.thomas.hungergames.events.kitsevents;

import me.thomas.hungergames.game.GameManager;
import me.thomas.hungergames.kits.Kit;
import me.thomas.hungergames.kits.KitsManager;
import me.thomas.hungergames.player.HgPlayer;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class Archer extends Kit implements Listener {

	public Archer() {
		super(new ItemStack(Material.BOW), "Archer", Arrays.asList("&7Receives an enchanted bow and", "&7a few arrow on start.", "" +
				"&7Also makes it easier to", "&7track players when they are hit.", "&7(only with arrows)"));
	}

	@EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
    	Player player = (Player) event.getEntity().getShooter();
		HgPlayer hgPlayer = GameManager.getGameManager().getPlayerManager().getHgPlayer(player);
		if (hgPlayer.getKit() == null) return;
		if (!hgPlayer.getKit().getName().contains(getName())) return;
    	if (event.getEntityType() != EntityType.ARROW) return;
    	if (event.getHitEntity()== null) return;
    	
    	LivingEntity e = (LivingEntity) event.getHitEntity();
    	e.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1));
    }
}
