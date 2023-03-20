package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class SteadfastEnchantment extends BaseEnchantment {
    public SteadfastEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BREAKABLE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    }

    @Override
    public String getName() {
        return "steadfast";
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
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }

    @Override
    public boolean isTreasure() {
        return super.isTreasure();
    }

    @Override
    public int getDefaultMaxLevel() {
        return 2;
    }
}
