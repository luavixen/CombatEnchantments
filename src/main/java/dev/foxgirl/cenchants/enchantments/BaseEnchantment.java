package dev.foxgirl.cenchants.enchantments;

import dev.foxgirl.cenchants.CombatEnchants;
import dev.foxgirl.cenchants.config.Config;
import dev.foxgirl.cenchants.config.Configs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public abstract class BaseEnchantment extends Enchantment {
    public abstract String getName();

    public int getDefaultMaxLevel() {
        return 1;
    }
    public Set<String> getDefaultExcludes() {
        return Collections.emptySet();
    }

    private final Config config;

    protected BaseEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slots) {
        super(weight, type, slots);

        config = Configs.get(this);

        if (config.enabled) {
            CombatEnchants.register(ForgeRegistries.Keys.ENCHANTMENTS, new Identifier("cenchants", getName()), this);
        }
    }

    @Override
    public final int getMaxLevel() {
        if (config.level > 0 && config.level < 65536) return config.level;
        return getDefaultMaxLevel();
    }

    @Override
    protected final boolean canAccept(Enchantment other) {
        Set<String> excludes;
        if (config.excludes != null && !config.excludes.isEmpty()) {
            excludes = config.excludes;
        } else {
            excludes = getDefaultExcludes();
        }
        if (!excludes.isEmpty()) {
            String name;
            if (other instanceof BaseEnchantment) {
                name = ((BaseEnchantment) other).getName();
            } else {
                name = Objects.requireNonNull(ForgeRegistries.ENCHANTMENTS.getKey(other)).getPath();
            }
            if (excludes.contains(name)) return false;
        }
        return super.canAccept(other);
    }
}
