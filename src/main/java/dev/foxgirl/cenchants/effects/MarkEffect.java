package dev.foxgirl.cenchants.effects;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class MarkEffect extends StatusEffect {
    public MarkEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //onTargetDamaged ran twice, so this whole file is a workaround sort of
        entity.removeStatusEffect(CombatEnchants.MARK_EFFECT);
        entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, amplifier * 20, 0));
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 5 && amplifier != 0;
    }
}
