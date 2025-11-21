package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FetchProperties {

    public static Properties properties = new Properties();

    static {
        try (InputStream input = FetchProperties.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in classpath");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file: " + e.getMessage());
        }
    }

    // Get property as String
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Get property as boolean
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}
