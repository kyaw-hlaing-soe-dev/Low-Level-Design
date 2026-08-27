package org.example.designprinciple.DRY;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

interface ConfigSource {
    String loadValue(String key);
}

class FileConfigSource implements ConfigSource {
    private final Map<String, String> config;

    public FileConfigSource(Map<String, String> config) {
        this.config = config;
    }

    public String loadValue(String key) {
        return config.get(key);
    }
}

class EnvConfigSource implements ConfigSource {
    public String loadValue(String key) {
        return System.getenv(key.replace(".", "_").toUpperCase());
    }
}

class DefaultConfigSource implements ConfigSource {
    private final Map<String, String> defaults;

    public DefaultConfigSource(Map<String, String> defaults) {
        this.defaults = defaults;
    }

    public String loadValue(String key) {
        return defaults.get(key);
    }
}

class ConfigsLoader {
    private final List<ConfigSource> sources;

    public ConfigsLoader(ConfigSource... sources) {
        this.sources = List.of(sources);
    }

    public String get(String key) {
        for (ConfigSource source : sources) {
            String value = source.loadValue(key);
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return null;
    }
}

public class ConfigLoader{
    public static void main(String[] args) {
        Map<String, String> fileConfig = new HashMap<>();
        fileConfig.put("db.host", "localhost");
        fileConfig.put("db.port", "5432");

        Map<String, String> defaults = new HashMap<>();
        defaults.put("db.host", "127.0.0.1");
        defaults.put("db.port", "3306");
        defaults.put("db.timeout", "30");

        ConfigsLoader loader = new ConfigsLoader(
                new FileConfigSource(fileConfig),
                new EnvConfigSource(),
                new DefaultConfigSource(defaults)
        );

        System.out.println("db.host = " + loader.get("db.host"));
        System.out.println("db.port = " + loader.get("db.port"));
        System.out.println("db.timeout = " + loader.get("db.timeout"));
    }
}