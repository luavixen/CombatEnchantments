package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Set;

public class LethalityEnchantment extends BaseEnchantment {
    public LethalityEnchantment() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "lethality";
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 4;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.LETHALITY, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if (user instanceof PlayerEntity && target instanceof LivingEntity) {
            float bDamage = ((((LivingEntity) target).getArmor() - user.getArmor()) / (6.0f - level));
            float damage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());

            if(bDamage > 0)
                target.damage(DamageSource.player((PlayerEntity) user), bDamage + damage);
        }
    }

    @Override
    public int getDefaultMaxLevel() {
        return 3;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("perception");
    }
}
