package settings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFileManager {
    private static volatile PropertiesFileManager instance;
    private static Properties prop;

    public static PropertiesFileManager getInstance(String appEnv) {
        if (instance == null) {
            synchronized (PropertiesFileManager.class) {
                instance = new PropertiesFileManager(appEnv);
            }
        }
        return instance;
    }

    public String load(String key) {
        return prop.getProperty(key);
    }

    private PropertiesFileManager(String appEnv) {
        String propertiesFilePath = getPropertiesFilePath(appEnv);
        try (InputStream input = Files.newInputStream(Paths.get(propertiesFilePath))) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getPropertiesFilePath(String appEnv) {
        String path;
        if (appEnv.equals("prod")) {
            path = "src/main/resources/prod.properties";
        } else {
            path = "src/main/resources/pre.properties";
        }
        File file = new File(path);
        if (!file.isFile()) {
            path = ".properties";
        }
        return path;
    }
}
