package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class TremorEnchantment extends BaseEnchantment {
    public TremorEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_FEET, EquipmentSlot.FEET);
    }

    @Override
    public String getName() {
        return "tremor";
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
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }

    @Override
    public int getDefaultMaxLevel() {
        return 1;
    }
}
