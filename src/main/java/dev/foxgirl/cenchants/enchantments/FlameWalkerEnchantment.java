package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FlameWalkerEnchantment extends BaseEnchantment {
    public FlameWalkerEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_FEET, EquipmentSlot.FEET);
    }

    @Override
    public String getName() {
        return "flame_walker";
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        user.addStatusEffect(new StatusEffectInstance(CombatEnchants.FIRE_WALK_EFFECT, 50 * level, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 50 * level, 0));
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }
}
