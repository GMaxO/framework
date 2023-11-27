package api.dto.auth_dto;

import io.restassured.http.Cookies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    private String accessToken;
    private String refreshToken;
    private Cookies cookies;


    public String getTokenForBrowser() {
        return "{\"access\":\"" + accessToken + "\",\"refresh\":\"" + refreshToken + "\"}";
    }
}
