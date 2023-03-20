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


public class PerceptionEnchantment extends BaseEnchantment {
    public PerceptionEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "perception";
    }

    @Override
    public int getMinPower(int level) {
        return 50;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.PERCEPTION, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(user instanceof PlayerEntity && target instanceof LivingEntity)
        {

            float damage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());
            int exp = ((PlayerEntity) user).experienceLevel;
            if(exp < 3)
                exp = 3;
            target.damage(DamageSource.player((PlayerEntity) user), damage + (int)(Math.log(exp)) - 1);
        }
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getDefaultMaxLevel() {
        return 1;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("lethality");
    }
}
