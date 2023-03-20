package dev.foxgirl.cenchants;

import dev.foxgirl.cenchants.config.ModConfigs;
import dev.foxgirl.cenchants.effects.*;
import dev.foxgirl.cenchants.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.List;

@Mod("cenchants")
public final class CombatEnchants {
    public static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public static final String MOD_ID = "cenchants";

    public static Enchantment DUELING;
    public static Enchantment LETHALITY;
    public static Enchantment TRIUMPH;
    public static Enchantment RAMPAGE;
    public static Enchantment INFERNO;
    public static Enchantment SORCERY;
    public static Enchantment LIFESTEAL;
    public static Enchantment ZAP;
    public static Enchantment VOLLEY;
    public static Enchantment HUNTER;
    public static Enchantment TRANQUILIZE;
    public static Enchantment FROST;
    public static Enchantment SHIELDING;
    public static Enchantment SELFDESTRUCT;
    public static Enchantment FLAME_WALKER;
    public static Enchantment VISION;
    public static Enchantment DARKNESS;
    public static Enchantment ANTIHEAL;
    public static Enchantment INSPIRE;
    public static Enchantment REJUVENATE;
    public static Enchantment BITE;
    public static Enchantment HOOK;
    public static Enchantment PERCEPTION;
    public static Enchantment COMBO;
    public static Enchantment KNOCKUP;
    public static Enchantment FERVOR;
    public static Enchantment BARRAGE;
    public static Enchantment DEFLECT;
    public static Enchantment SNAP;
    public static Enchantment STEADFAST;
    public static Enchantment LIGHTWEIGHT;
    public static Enchantment LIFELINE;
    public static Enchantment INKING;
    public static Enchantment GRAB;
    public static Enchantment TREMOR;
    public static Enchantment AFTERSHOCK;
    public static Enchantment SHIELDBREAK;

    public static final StatusEffect RAMPAGE_EFFECT = new RampageEffect();
    public static final StatusEffect LIFESTEAL_COOLDOWN_EFFECT = new LifestealCooldownEffect();
    public static final StatusEffect MARK_EFFECT = new MarkEffect();
    public static final StatusEffect SLEEPY_EFFECT = new SleepyEffect();
    public static final StatusEffect SHIELDING_COOLDOWN_EFFECT = new ShieldingCooldown();
    public static final StatusEffect FIRE_WALK_EFFECT = new FireWalkEffect();
    public static final StatusEffect ANTIHEAL_EFFECT = new AntihealEffect();
    public static final StatusEffect DELAYED_DEATH_EFFECT = new DelayedDeathEffect();
    public static final StatusEffect FERVOR_EFFECT = new FervorEffect();
    public static final StatusEffect BARRAGE_EFFECT = new BarrageEffect();
    public static final StatusEffect BARRAGE_STACK_EFFECT = new BarrageStackEffect();
    public static final StatusEffect LIFELINE_COOLDOWN_EFFECT = new LifelineCooldownEffect();
    public static final StatusEffect FROST_PARTICLE_EFFECT = new FrostParticleEffect();
    public static final StatusEffect SLEEPY_PARTICLE_EFFECT = new SleepyParticleEffect();
    public static final StatusEffect GRAB_EFFECT = new GrabEffect();
    public static final StatusEffect AFTERSHOCK_EFFECT = new AftershockEffect();
    public static final StatusEffect LIGHTNING_IMMUNE = new LightningImmuneEffect();

    // public static final DefaultParticleType SHIELD_PARTICLE = FabricParticleTypes.simple();
    // public static final DefaultParticleType SLEEPY_PARTICLE = FabricParticleTypes.simple();

    public CombatEnchants() {
        ModConfigs.registerConfigs();

        ANTIHEAL = new AntihealEnchantment();
        BARRAGE = new BarrageEnchantment();
        BITE = new BiteEnchantment();
        COMBO = new ComboEnchantment();
        DARKNESS = new DarknessCurse();
        DEFLECT = new DeflectEnchantment();
        DUELING = new DuelingEnchantment();
        FERVOR = new FervorEnchantment();
        FLAME_WALKER = new FlameWalkerEnchantment();
        FROST = new FrostEnchantment();
        GRAB = new GrabEnchantment();
        HOOK = new HookEnchantment();
        HUNTER = new HunterEnchantment();
        INFERNO = new InfernoEnchantment();
        INKING = new InkingEnchantment();
        INSPIRE = new InspireEnchantment();
        KNOCKUP = new KnockupEnchantment();
        LETHALITY = new LethalityEnchantment();
        LIFELINE = new LifelineEnchantment();
        LIFESTEAL = new LifestealEnchantment();
        LIGHTWEIGHT = new LightweightEnchantment();
        PERCEPTION = new PerceptionEnchantment();
        RAMPAGE = new RampageEnchantment();
        REJUVENATE = new RejuvenateEnchantment();
        SHIELDING = new ShieldingEnchantment();
        AFTERSHOCK = new AftershockEnchantment();
        SNAP = new SnapEnchantment();
        SORCERY = new SorceryEnchantment();
        STEADFAST = new SteadfastEnchantment();
        SELFDESTRUCT = new SelfDestructEnchantment();
        TRANQUILIZE = new TranquilizeEnchantment();
        TREMOR = new TremorEnchantment();
        TRIUMPH = new TriumphEnchantment();
        VISION = new VisionEnchantment();
        VOLLEY = new VolleyEnchantment();
        ZAP = new ZapEnchantment();
        SHIELDBREAK = new ShieldBreakEnchantment();

        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "rampage"), RAMPAGE_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "lifesteal_cooldown"), LIFESTEAL_COOLDOWN_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "mark"), MARK_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "sleepy"), SLEEPY_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "shielding_cooldown"), SHIELDING_COOLDOWN_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "fire_walk"), FIRE_WALK_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "antiheal"), ANTIHEAL_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "delayed_death"), DELAYED_DEATH_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "fervor"), FERVOR_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "barrage"), BARRAGE_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "barrage_stack"), BARRAGE_STACK_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "lifeline_cooldown"), LIFELINE_COOLDOWN_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "frost_particle"), FROST_PARTICLE_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "sleepy_particle"), SLEEPY_PARTICLE_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "grab"), GRAB_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "aftershock"), AFTERSHOCK_EFFECT);
        register(ForgeRegistries.Keys.MOB_EFFECTS, new Identifier("cenchants", "lightning_immune"), LIGHTNING_IMMUNE);
        // register(Registries.PARTICLE_TYPE.getKey(), new Identifier("cenchants", "shield"), SHIELD_PARTICLE);
        // register(Registries.PARTICLE_TYPE.getKey(), new Identifier("cenchants", "sleepy"), SLEEPY_PARTICLE);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegister);
    }

    public static <T> void register(RegistryKey<? extends Registry<T>> key, Identifier id, T entry) {
        registryEntries.add(new Object[] { key, id, entry });
    }

    private static final List<Object[]> registryEntries = new ArrayList<>(64);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void onRegister(RegisterEvent event) {
        for (Object[] tuple : registryEntries) {
            event.register((RegistryKey) tuple[0], (Identifier) tuple[1], () -> tuple[2]);
        }
    }

}
