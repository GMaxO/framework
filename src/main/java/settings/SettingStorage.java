package settings;

import exceptions.PropertyNotFoundException;

public class SettingStorage {
    public static final boolean runUiLocal = true;
    public static final boolean runWithRetries = false;
//    System.getenv("ONLY_PAYMENTS_TEST") == null || System.getenv("ONLY_PAYMENTS_TEST").isEmpty();

    private static final PropertiesFileManager storage = PropertiesFileManager.getInstance(getEnvName());

    public static String getEnvName() {
        String envName = System.getenv("APP_DOMAIN_NAME_PREFIX");
        if (envName == null || envName.isEmpty()) {
            envName = "prod";
            System.setProperty("TARGET_ENV_NAME", envName);
        }
        return envName;
    }

    public static String getStringProperty(String key) {
        String property = storage.load(key);
        if (property == null) throw new PropertyNotFoundException("Can`t load `" + key + "` from properties file");
        return property;
    }

    public static int getIntProperty(String key) {
        String property = storage.load(key);
        if (property == null) throw new PropertyNotFoundException("Can`t load `" + key + "` from properties file");
        return Integer.parseInt(property);
    }

    public static String getUrlProperty(String host) {
        String url;
        if (getEnvName().equals("prod")) {
            url = storage.load(host);
        } else {
            String urlWithoutDomainName = storage.load(host);
            System.out.println("urlWithoutDomainName " + urlWithoutDomainName);
            String domainName = System.getenv("APP_DOMAIN_NAME_PREFIX");
            System.out.println("DOMAIN_NAME " + domainName);
            url = urlWithoutDomainName.replace("(PREFIX)", domainName);
        }
        if (url == null) {
            throw new PropertyNotFoundException("Can`t load `host URL` from properties file");
        }
        return url;
    }

    public static String getDbUrlProperty(String host) {
        String url;
        if (getEnvName().equals("prod")) {
            url = storage.load(host);
        } else {
            String urlWithoutDomainName = storage.load(host);
            String domainName = System.getenv("APP_DOMAIN_NAME_PREFIX");
            domainName = domainName.replace("pre-", "");
            url = urlWithoutDomainName + domainName;
        }
        if (url == null) {
            throw new PropertyNotFoundException("Can`t load `host URL` from properties file");
        }
        return url;
    }


}
