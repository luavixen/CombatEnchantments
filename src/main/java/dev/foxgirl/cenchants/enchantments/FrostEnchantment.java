package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BowItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class FrostEnchantment extends Enchantment {
    public FrostEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
        if(ModConfigs.FROST)
            CombatEnchants.register(Registries.ENCHANTMENT, new Identifier("cenchants", "frost"), this);
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
            target.setFrozenTicks(155 + level * (81));
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.FROST_PARTICLE_EFFECT, level * (40), 0, false, false));
        }
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.TRANQUILIZE))
            return false;
        return super.canAccept(other);
    }
}
