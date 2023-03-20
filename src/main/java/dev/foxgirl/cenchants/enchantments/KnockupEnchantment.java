package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.CrossbowItem;

import java.util.Set;

public class KnockupEnchantment extends BaseEnchantment {
    public KnockupEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "knockup";
    }

    @Override
    public int getMinPower(int level) {
        return 12 + (level - 1) * 20;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 25;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof CrossbowItem)
                return;
        }
        if(target instanceof LivingEntity)
        {
            target.addVelocity( 0, (level / 6.0f) * (1.0D - ((LivingEntity)(target)).getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE) * 0.5), 0);
        }
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("punch", "grab");
    }
}
