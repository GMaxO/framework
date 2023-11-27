package api.steps;

import org.openqa.selenium.remote.SessionId;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static settings.SettingStorage.getStringProperty;

public class LabmdaApiSteps {
    private static final String LAMBDA_URL = "https://automation.lambdatest.com";

    public static String getVideoUrl(SessionId sessionId) {
        return LAMBDA_URL + "/public/video?testID=" + sessionId + "&auth=" + getAuthToken();
    }

    private static String getAuthToken() {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String s = getStringProperty("lambda.username") + ":" + getStringProperty("lambda.key");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
