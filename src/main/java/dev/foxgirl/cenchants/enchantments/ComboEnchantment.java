package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;


public class ComboEnchantment extends BaseEnchantment {
    public ComboEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "combo";
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.COMBO, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(target instanceof LivingEntity && user.world instanceof ServerWorld)
        {
            StatusEffectInstance mark = ((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT);
            if(mark != null && mark.getAmplifier() > 8 - level)
            {
                user.addStatusEffect(new StatusEffectInstance(CombatEnchants.LIGHTNING_IMMUNE, 30, 1, true, false));
                ((LivingEntity) target).removeStatusEffect(CombatEnchants.LIGHTNING_IMMUNE);
                World world = target.world;
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                assert lightning != null;
                lightning.setPos(target.getX(), target.getY(), target.getZ());
                world.spawnEntity(lightning);
                ((LivingEntity) target).removeStatusEffect(CombatEnchants.MARK_EFFECT);
                ((ServerWorld) user.world).spawnParticles(ParticleTypes.END_ROD, target.getX(), target.getBodyY(0.5D), target.getZ(), 20, 0.3, 0.5, 0.3, 0.5D);

            }
        }

    }

    @Override
    public int getDefaultMaxLevel() {
        return 5;
    }
}
