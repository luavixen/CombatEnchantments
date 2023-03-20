package dev.foxgirl.cenchants.config;

import java.util.Collections;
import java.util.Set;

public final class Config {

    public boolean enabled;
    public int level;
    public Set<String> excludes;

    public Config() {
        enabled = true;
        level = 0;
        excludes = Collections.emptySet();
    }

}
