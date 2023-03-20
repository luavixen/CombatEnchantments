package dev.foxgirl.cenchants.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class ShieldBreakEnchantment extends Enchantment {
    public ShieldBreakEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
//        if(ModConfigs.SHIELDBREAK)
//            CombatEnchants.register(ForgeRegistries.Keys.ENCHANTMENTS, new Identifier("cenchants", "shield_break"), this);
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

    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
