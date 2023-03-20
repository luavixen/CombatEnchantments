package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class BiteEnchantment extends Enchantment {
    public BiteEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
        if(ModConfigs.BITE)
            CombatEnchants.register(ForgeRegistries.Keys.ENCHANTMENTS, new Identifier("cenchants", "bite"), this);
    }

    @Override
    public int getMinPower(int level) {
        return 0;
    }

    @Override
    public int getMaxPower(int level) {
        return 15;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof BowItem)
                return;
        }
        if(target.world instanceof ServerWorld && target instanceof LivingEntity && user instanceof PlayerEntity) {
            World world = target.world;
            WolfEntity wolf = EntityType.WOLF.create(world);
            double posX = Math.random() * 2 - 1;
            double posY = Math.random() * 2 - 1;
            assert wolf != null;
            wolf.setPos(target.getX() + posX, target.getY() + 1, target.getZ() + posY);
            wolf.setTarget((LivingEntity) target);
            wolf.setHealth(4 * level);
            wolf.addStatusEffect(new StatusEffectInstance(CombatEnchants.DELAYED_DEATH_EFFECT, 60 * level + 10, 100));
            world.spawnEntity(wolf);
        }
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
