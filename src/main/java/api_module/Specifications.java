package api_module;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static common_module.logs.LoggerHelper.info;

public class Specifications {

    public static Response httpGetRequestWithQueryParameters(String URI, String paramName, List<String> queryParams) {
        info("Sending GET request to resource {%s} with parameter name: {%s} and query parameters: " + queryParams,
                URI,
                paramName);
        return RestAssured.given()
                .when()
                .queryParam(paramName, queryParams.toArray())
                .get(URI);
    }
}
