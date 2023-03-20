package dev.foxgirl.cenchants.effects;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.Objects;

public class LifestealCooldownEffect extends StatusEffect {
    public LifestealCooldownEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(EnchantmentHelper.getLevel(CombatEnchants.LIFESTEAL, entity.getMainHandStack()) == 0) {
            int currentDuration = Objects.requireNonNull(entity.getStatusEffect(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT)).getDuration();
            entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT, currentDuration + 20));
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
