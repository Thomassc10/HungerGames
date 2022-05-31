package me.thomas.hungergames.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class WeaponAttackSpeed implements Listener {

    // 1024
    @EventHandler
    public void onItemCraft(CraftItemEvent event) {
        ItemStack item = event.getRecipe().getResult();
        ItemMeta meta = item.getItemMeta();

        //Collection<AttributeModifier> attributeModifiers = item.getType().getDefaultAttributeModifiers(EquipmentSlot.HAND).get(Attribute.GENERIC_ATTACK_DAMAGE);
        //double damage = attributeModifiers.stream().findFirst().get().getAmount();

        AttributeModifier attackSpeed = new AttributeModifier(UUID.randomUUID(), "attack_speed",
                16, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

        AttributeModifier attackDamage = new AttributeModifier(UUID.randomUUID(), "attack_damage",
                0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeed);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamage);



        /*Collection<AttributeModifier> attributeModifiers = meta.getAttributeModifiers(EquipmentSlot.HAND).get(Attribute.GENERIC_ATTACK_DAMAGE);
        attributeModifiers.forEach(attributeModifier -> {
            AttributeModifier attackDamage = new AttributeModifier(UUID.randomUUID(), "attackdamage",
                    attributeModifier.getAmount(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamage);
        });*/

        item.setItemMeta(meta);
        event.setCurrentItem(item);
    }

    /*public double getAttackDamage(ItemStack itemStack) {
        double attackDamage = 1.0;
        UUID uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
        net.minecraft.world.item.ItemStack craftItemStack = CraftItemStack.asNMSCopy(itemStack);
        Item item = craftItemStack.getItem();
        if(item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemHoe) {
            Multimap<String, AttributeModifier> map = item.a(EnumItemSlot.d);
            Collection<AttributeModifier> attributes = map.get(GenericAttributes.a.getName());
            if(!attributes.isEmpty()) {
                Bukkit.getLogger().info("Found one or more attribute modifiers:");
                for(AttributeModifier am: attributes) {
                    Bukkit.getLogger().info(String.format("  (%s, %s, %f, %d)",am.a().toString(), am.b(), am.d(), am.c()));
                }
                for(AttributeModifier am: attributes) {
                    if(am.a().toString().equalsIgnoreCase(uuid.toString()) && am.c() == 0) attackDamage += am.d();
                }
                double y = 1;
                for(AttributeModifier am: attributes) {
                    if(am.a().toString().equalsIgnoreCase(uuid.toString()) && am.c() == 1) y += am.d();
                }
                attackDamage *= y;
                for(AttributeModifier am: attributes) {
                    if(am.a().toString().equalsIgnoreCase(uuid.toString()) && am.c() == 2) attackDamage *= (1 + am.d());
                }
            }
        }
        return attackDamage;
    }*/
}
