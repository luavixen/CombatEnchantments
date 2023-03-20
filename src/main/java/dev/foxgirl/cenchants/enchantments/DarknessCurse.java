package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class DarknessCurse extends BaseEnchantment {
    public DarknessCurse() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "darkness";
    }

    @Override
    public int getMinPower(int level) {
        return level * 25;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getDefaultMaxLevel() {
        return 1;
    }
}
