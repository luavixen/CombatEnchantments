package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.CrossbowItem;

import java.util.List;
import java.util.Set;

public class GrabEnchantment extends BaseEnchantment {
    public GrabEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "grab";
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
        System.out.println("test");
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof CrossbowItem)
                return;
        }
        System.out.println("test1");
        if(target instanceof LivingEntity) {
            List<LivingEntity> list = target.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox()
                    .expand(15 + level * 5, 10, 15 + level * 5));
            if (list.size() > 1) {
                LivingEntity closest = (LivingEntity) target;
                double closestdistance = 100;
                for (LivingEntity e : list) {
                    if (e.hasStatusEffect(CombatEnchants.GRAB_EFFECT) && e != target && e.distanceTo(target) <= closestdistance) {
                        closestdistance = e.distanceTo(target);
                        closest = e;
                    }
                }
                if (closest != target) {
                    double targetX = target.getX();
                    double targetZ = target.getZ();
                    double closestX = closest.getX();
                    double closestZ = closest.getZ();

                    double knockbackLevel = 1.2 * target.distanceTo(closest) / Math.sqrt(2 * Math.pow((5 + level * 2.5), 2));

//                    closest.setVelocity(0, 1, 0);
//                    target.setVelocity(0, 1, 0);
                    closest.takeKnockback(knockbackLevel, (closestX - targetX), (closestZ - targetZ));
                    ((LivingEntity) target).takeKnockback(knockbackLevel, targetX - closestX, targetZ - closestZ);

                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, level * 40, 1));
                    closest.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, level * 40, 1));
                    closest.removeStatusEffect(CombatEnchants.GRAB_EFFECT);
                    ((LivingEntity) target).removeStatusEffect(CombatEnchants.GRAB_EFFECT);
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.GRAB_EFFECT, 54 + level * 50, 10, false, false));
                } else
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.GRAB_EFFECT, 50 + level * 50, 0, false, false));
            }
        }
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("punch", "knockup");
    }
}
