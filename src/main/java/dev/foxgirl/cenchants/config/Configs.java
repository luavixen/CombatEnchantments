package dev.foxgirl.cenchants.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import dev.foxgirl.cenchants.enchantments.BaseEnchantment;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public final class Configs {

    private static final Gson GSON = new GsonBuilder()
        .setLenient()
        .setPrettyPrinting()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

    private static final Map<String, Config> CONFIGS = new HashMap<>();

    static {
        var names = new String[] {
            "aftershock", "antiheal", "barrage", "bite", "combo",
            "darkness", "deflect", "dueling", "fervor", "flamewalker",
            "frost", "grab", "hook", "hunter", "inferno", "inking",
            "inspire", "knockup", "lethality", "lifeline", "lifesteal",
            "lightweight", "perception", "rampage", "rejuvenate",
            "shielding", "shieldbreak", "snap", "sorcery", "steadfast",
            "selfdestruct", "tranquilize", "tremor", "triumph", "vision",
            "volley", "zap",
        };
        for (var name : names) CONFIGS.put(name, null);
    }

    public static final class State {
        private final Path path;
        private boolean success;

        private State() {
            path = FMLPaths.CONFIGDIR.get().resolve("combat-enchants.json");
            try {
                CONFIGS.putAll(GSON.fromJson(Files.newBufferedReader(path), new TypeToken<Map<String, Config>>() {}.getType()));
                success = true;
            } catch (NullPointerException | IOException | JsonParseException ignored) {
                success = false;
            }
        }

        public void commit() {
            if (success && Files.isReadable(path)) return;
            try {
                Files.writeString(path, GSON.toJson(CONFIGS), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException cause) {
                throw new RuntimeException(cause);
            }
        }
    }

    public static State register() {
        return new State();
    }

    public static Config get(BaseEnchantment enchantment) {
        var config = CONFIGS.get(enchantment.getName());
        if (config == null) {
            config = new Config();
            config.level = enchantment.getDefaultMaxLevel();
            config.excludes = enchantment.getDefaultExcludes();
            CONFIGS.put(enchantment.getName(), config);
        }
        return config;
    }

}
