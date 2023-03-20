package dev.foxgirl.cenchants;

import dev.foxgirl.cenchants.config.Configs;
import dev.foxgirl.cenchants.effects.*;
import dev.foxgirl.cenchants.enchantments.*;
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
    public static BaseEnchantment DUELING;
    public static BaseEnchantment LETHALITY;
    public static BaseEnchantment TRIUMPH;
    public static BaseEnchantment RAMPAGE;
    public static BaseEnchantment INFERNO;
    public static BaseEnchantment SORCERY;
    public static BaseEnchantment LIFESTEAL;
    public static BaseEnchantment ZAP;
    public static BaseEnchantment VOLLEY;
    public static BaseEnchantment HUNTER;
    public static BaseEnchantment TRANQUILIZE;
    public static BaseEnchantment FROST;
    public static BaseEnchantment SHIELDING;
    public static BaseEnchantment SELFDESTRUCT;
    public static BaseEnchantment FLAME_WALKER;
    public static BaseEnchantment VISION;
    public static BaseEnchantment DARKNESS;
    public static BaseEnchantment ANTIHEAL;
    public static BaseEnchantment INSPIRE;
    public static BaseEnchantment REJUVENATE;
    public static BaseEnchantment BITE;
    public static BaseEnchantment HOOK;
    public static BaseEnchantment PERCEPTION;
    public static BaseEnchantment COMBO;
    public static BaseEnchantment KNOCKUP;
    public static BaseEnchantment FERVOR;
    public static BaseEnchantment BARRAGE;
    public static BaseEnchantment DEFLECT;
    public static BaseEnchantment SNAP;
    public static BaseEnchantment STEADFAST;
    public static BaseEnchantment LIGHTWEIGHT;
    public static BaseEnchantment LIFELINE;
    public static BaseEnchantment INKING;
    public static BaseEnchantment GRAB;
    public static BaseEnchantment TREMOR;
    public static BaseEnchantment AFTERSHOCK;

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
        var configState = Configs.register();

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

        configState.commit();

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

    private static final List<Object[]> REGISTRY_ENTRIES = new ArrayList<>(64);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void onRegister(RegisterEvent event) {
        for (Object[] tuple : REGISTRY_ENTRIES) {
            event.register((RegistryKey) tuple[0], (Identifier) tuple[1], () -> tuple[2]);
        }
    }

    public static <T> void register(RegistryKey<? extends Registry<T>> key, Identifier id, T entry) {
        REGISTRY_ENTRIES.add(new Object[] { key, id, entry });
    }
}
