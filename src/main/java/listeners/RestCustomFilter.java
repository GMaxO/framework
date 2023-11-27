package listeners;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestCustomFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        Allure.addAttachment("REQUEST", getRequestForAllure(requestSpec));
        Allure.addAttachment("RESPONSE", getResponseForAllure(response));
        return response;
    }

    private String getRequestForAllure(FilterableRequestSpecification requestSpec) {
        String method = requestSpec.getMethod();
        String uri = requestSpec.getURI();
        String body = requestSpec.getBody() == null ? "" : "Body: \n" + requestSpec.getBody();
        Headers headers = requestSpec.getHeaders();
        Cookies cookies = requestSpec.getCookies();
        return method + " " + uri + "\n\n" + body + "\n\nHeaders:\n" + headers.asList() + "\n\nCookies:\n" + cookies;
    }

    private String getResponseForAllure(Response response) {
        String statusLine = response.getStatusLine();
        String body = "Body:\n" + response.getBody().asPrettyString();
        Headers headers = response.getHeaders();
        String timing = "Timing: " + response.getTime() + "ms";
        return timing + "\n\n" + statusLine + "\n\n" + body + "\n\nHeaders:\n" + headers.asList() + "\n\nCookies:\n"
                + response.getCookies();
    }
}
