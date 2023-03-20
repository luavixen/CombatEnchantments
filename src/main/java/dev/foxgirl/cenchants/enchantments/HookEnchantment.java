package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BowItem;
import net.minecraft.util.math.MathHelper;

public class HookEnchantment extends BaseEnchantment {
    public HookEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.CROSSBOW, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "hook";
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
        }
        if(target instanceof LivingEntity) {
            target.setVelocity(0, 0, 0);
            ((LivingEntity)target).takeKnockback(level * 0.5, -MathHelper.sin(user.getYaw() * 0.017453292F),(MathHelper.cos(user.getYaw() * 0.017453292F)));
        }
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }
}
