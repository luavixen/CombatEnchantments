package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class InkingEnchantment extends BaseEnchantment {
    public InkingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.TRIDENT, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "inking";
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }
}
