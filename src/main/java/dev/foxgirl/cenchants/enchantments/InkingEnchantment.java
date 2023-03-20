package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.ForgeRegistries;

public class InkingEnchantment extends Enchantment {
    public InkingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.INKING)
            CombatEnchants.register(ForgeRegistries.Keys.ENCHANTMENTS, new Identifier("cenchants", "inking"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}

