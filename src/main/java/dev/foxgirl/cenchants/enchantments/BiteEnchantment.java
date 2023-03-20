package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
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
import net.minecraft.world.World;

public class BiteEnchantment extends BaseEnchantment {
    public BiteEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.CROSSBOW, EquipmentSlot.MAINHAND);
    }

    @Override
    public String getName() {
        return "bite";
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
    public int getDefaultMaxLevel() {
        return 2;
    }
}
