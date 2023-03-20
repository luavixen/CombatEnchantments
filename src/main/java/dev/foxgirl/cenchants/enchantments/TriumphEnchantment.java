package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.Set;

public class TriumphEnchantment extends BaseEnchantment {
    public TriumphEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "triumph";
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.TRIUMPH, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(target instanceof PlayerEntity && ((LivingEntity)target).isDead())
        {
            user.heal(level);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1, (level * 3) - 1 ));
            if (target.world instanceof ServerWorld) {
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.SOUL, target.getX(), target.getBodyY(0.5D), target.getZ(), 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.HEART, user.getX(), user.getBodyY(0.5D), user.getZ(), 0, 0.2, 0.5, 0.2, 0.0D);
            }
        }
        else if(target instanceof LivingEntity && ((LivingEntity)target).isDead())
        {
            user.heal(level / 2.0f);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1, 0));
            if (target.world instanceof ServerWorld) {
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.SOUL, target.getX(), target.getBodyY(0.5D), target.getZ(), 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.HEART, user.getX(), user.getBodyY(0.5D), user.getZ(), 0, 0.4, 0.5, 0.4, 0.0D);
            }
        }
    }

    @Override
    public int getDefaultMaxLevel() {
        return 3;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("rampage");
    }
}
