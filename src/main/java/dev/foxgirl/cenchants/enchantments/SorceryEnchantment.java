package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import java.util.Set;

public class SorceryEnchantment extends BaseEnchantment {
    public SorceryEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
    }

    @Override
    public String getName() {
        return "sorcery";
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }

    @Override
    public int getDefaultMaxLevel() {
        return 4;
    }

    @Override
    public Set<String> getDefaultExcludes() {
        return Set.of("shielding");
    }
}
