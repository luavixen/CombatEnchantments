package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.ForgeRegistries;

public class BarrageEnchantment extends Enchantment {
    public BarrageEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.BARRAGE)
            CombatEnchants.register(ForgeRegistries.Keys.ENCHANTMENTS, new Identifier("cenchants", "barrage"), this);
    }

    @Override
    public int getMinPower(int level) {
        return 25;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof CrossbowItem)
                return;

            StatusEffectInstance barrageStackInstance = user.getStatusEffect(CombatEnchants.BARRAGE_STACK_EFFECT);
            int barrageStacks = 0;
            if(!user.hasStatusEffect(CombatEnchants.BARRAGE_EFFECT)) {
                if(barrageStackInstance != null) {
                    barrageStacks = barrageStackInstance.getAmplifier();
                    if (!(barrageStacks >= 10))
                        user.addStatusEffect(new StatusEffectInstance(CombatEnchants.BARRAGE_STACK_EFFECT, 3, ++barrageStacks + 10));
                }
                else
                    user.addStatusEffect(new StatusEffectInstance(CombatEnchants.BARRAGE_STACK_EFFECT, 3, 10));
            }
            if(barrageStacks == 2)
            {
                user.addStatusEffect(new StatusEffectInstance(CombatEnchants.BARRAGE_STACK_EFFECT, 3, 20));
            }
        }
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
