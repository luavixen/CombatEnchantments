package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ShieldingEnchantment extends Enchantment {
    public ShieldingEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, CombatEnchants.ALL_ARMOR);
        if(ModConfigs.SHIELDING)
            CombatEnchants.register(Registries.ENCHANTMENT, new Identifier("cenchants", "shielding"), this);
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
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.SORCERY))
            return false;
        return super.canAccept(other);
    }
}
