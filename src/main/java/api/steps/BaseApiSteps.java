package api.steps;

import api.dto.auth_dto.UserDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import listeners.RestCustomFilter;

import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getUrlProperty;

public class BaseApiSteps {
    static {
        RestAssured.filters(new RestCustomFilter());
    }

    protected static final String LOGIN_ENDPOINT = "auth/login";
    protected static final String REGISTER_ENDPOINT = "auth/register";
    public static final String CONFIRM_ENDPOINT = "auth/confirm";
    protected static final String RESTORE_PASSWORD_ENDPOINT = "password/restore/";
    public static final String RESTORE_PASSWORD_CONFIRM_ENDPOINT = "password/restore/confirm/";
    protected static final String CHANGE_PASSWORD_ENDPOINT = "password/change/";


    public static Response login(UserDTO user) {
        return given()
                .spec(authReqSpec(LOGIN_ENDPOINT))
                .body(user)
                .when()
                .post()
                .then()
                .extract().response();
    }

    protected static RequestSpecification authReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getUrlProperty("auth.frontend.host") + "/api/")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification appReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getUrlProperty("main.backend.host") + "/api/")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected static RequestSpecification adminReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getUrlProperty("admin.backend.host") + "/api/")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }
}
