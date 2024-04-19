package api_module;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static common_module.logs.LoggerHelper.info;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Specifications {

    public Response httpGetRequestWithQueryParameters(String URI, String paramName, List<String> queryParams) {
        info("Sending GET request to resource {%s} with parameter name: {%s} and query parameters: " + queryParams,
                URI,
                paramName);
        return RestAssured.given()
                .when()
                .queryParam(paramName, queryParams.toArray())
                .get(URI);
    }

    public void checkForStatusCodeEquivalence(Response response, int expectedStatusCode) {
        info("Check that {%s} code is equal to {%s}",
                String.valueOf(response.getStatusCode()),
                String.valueOf(expectedStatusCode));
        assertEquals(response.getStatusCode(), expectedStatusCode,
                String.format("Actual status code is %d and it's not equal to expected: %d", response.getStatusCode(), expectedStatusCode));
    }

    public void checkThatResponseBodyIsNotEmpty(Response response) {
        info("Check that {%s} response body is not empty",
                String.valueOf(ResponseBodyParser.getResponseBodyAsString(response).isEmpty()));
        assertFalse(ResponseBodyParser.getResponseBodyAsString(response).isEmpty(), "Response body is empty");
    }

    public void checkThatAllElementsContainsValue(List<List<String>> elements, String value) {
        info("Check that value {%s} presented in the collection", value);
        assertTrue(elements.stream().allMatch(element -> element.contains(value)), "Elements don't contain value: " + value);
    }
}
