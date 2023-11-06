package studies.springboot.five.springbootfive;

public class DevProfile implements ru.netology.springbootfive.SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
