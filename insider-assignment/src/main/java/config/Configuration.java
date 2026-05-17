package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads layered configuration: base {@code application.properties} plus
 * environment-specific overrides from {@code application-{env}.properties}.
 */
public final class Configuration {

    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);
    private static final String BASE_CONFIG = "config/application.properties";

    private static volatile Configuration instance;

    private final Properties properties = new Properties();

    private Configuration() {
        load(BASE_CONFIG);

        String env = resolveEnvironment();
        String envConfig = "config/application-" + env + ".properties";
        load(envConfig);

        properties.stringPropertyNames().forEach(key ->
                System.getenv().entrySet().stream()
                        .filter(entry -> toPropertyKey(entry.getKey()).equals(key))
                        .findFirst()
                        .ifPresent(entry -> properties.setProperty(key, entry.getValue())));

        properties.stringPropertyNames().forEach(key -> {
            String systemValue = System.getProperty(key);
            if (systemValue != null && !systemValue.isBlank()) {
                properties.setProperty(key, systemValue);
            }
        });

        LOG.info("Active environment: {}", env);
    }

    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }

    public int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    private void load(String resourcePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                LOG.debug("Config resource not found (skipped): {}", resourcePath);
                return;
            }
            properties.load(input);
            LOG.debug("Loaded config: {}", resourcePath);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config: " + resourcePath, e);
        }
    }

    private String resolveEnvironment() {
        String env = System.getProperty("env");
        if (env == null || env.isBlank()) {
            env = System.getenv("TEST_ENV");
        }
        if (env == null || env.isBlank()) {
            env = "local";
        }
        return env.toLowerCase();
    }

    private static String toPropertyKey(String envKey) {
        return envKey.toLowerCase().replace('_', '.');
    }

    public static void reset() {
        instance = null;
    }
}
