package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class LightweightEnchantment extends BaseEnchantment {
    public LightweightEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    }

    @Override
    public String getName() {
        return "lightweight";
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
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }

    @Override
    public int getDefaultMaxLevel() {
        return 5;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
