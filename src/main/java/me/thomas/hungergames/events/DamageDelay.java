package me.thomas.hungergames.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageDelay implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        LivingEntity livingEntity = (LivingEntity) event.getEntity();
        livingEntity.setMaximumNoDamageTicks(15);
        livingEntity.setNoDamageTicks(15);
    }
}
