package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BowItem;

import java.util.Set;

public class TranquilizeEnchantment extends BaseEnchantment {
    public TranquilizeEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "tranquilize";
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
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof BowItem)
                return;
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.SLEEPY_EFFECT, 200, level + 1));
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.SLEEPY_PARTICLE_EFFECT, 200, 0, false, false));

        }

    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("frost");
    }
}
