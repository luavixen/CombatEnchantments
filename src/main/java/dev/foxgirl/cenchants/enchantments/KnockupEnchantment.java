package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.CrossbowItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class KnockupEnchantment extends Enchantment {
    public KnockupEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.KNOCKUP)
            CombatEnchants.register(Registries.ENCHANTMENT, new Identifier("cenchants", "knockup"), this);
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
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.PUNCH) || other.equals(CombatEnchants.GRAB))
            return false;
        return super.canAccept(other);
    }
}
