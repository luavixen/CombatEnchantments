package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.ForgeRegistries;

public class VisionEnchantment extends Enchantment {
    public VisionEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});
        if(ModConfigs.VISION)
            CombatEnchants.register(ForgeRegistries.Keys.ENCHANTMENTS, new Identifier("cenchants", "vision"), this);
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
        if(attacker instanceof LivingEntity)
            ((LivingEntity)(attacker)).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, level * 30 + 20, 0));
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
