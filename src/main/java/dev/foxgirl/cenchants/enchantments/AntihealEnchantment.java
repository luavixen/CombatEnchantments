package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BowItem;

public class AntihealEnchantment extends BaseEnchantment {
    public AntihealEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.CROSSBOW, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "antiheal";
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof BowItem)
                return;
        }
        if(target instanceof LivingEntity)
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.ANTIHEAL_EFFECT, 160, level));
    }

    @Override
    public int getDefaultMaxLevel() {
        return 1;
    }
}
