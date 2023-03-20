package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;
import java.util.Set;

public class InfernoEnchantment extends BaseEnchantment {
    public InfernoEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "inferno";
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.INFERNO, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        List<LivingEntity> list = target.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox()
                .expand(1.0D + level * 2, 0.25D, 1.0D + level * 2));

        for (LivingEntity e : list) {
            if (!e.equals(user)) {
                e.setFireTicks((level + 1) * 20);
            }
        }

        if (target.world instanceof ServerWorld && list.size() > 2) {
            ((ServerWorld) target.world).spawnParticles(ParticleTypes.FLAME, target.getX(), target.getBodyY(0.5D), target.getZ(), 7, 0.0D, 0.5D, 0.0D, 0.4D);

        }
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("fire_aspect");
    }
}
