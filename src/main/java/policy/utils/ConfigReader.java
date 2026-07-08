package policy.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static final Properties properties;

    static {
        try {
            String path = "src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(path);

            properties = new Properties();
            properties.load(fis);
            fis.close();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}