package api_module.steps;

import api_module.ResponseBodyParser;
import common_module.constants.PropertyPath;
import io.restassured.response.Response;

import java.util.List;

import static api_module.ResponseBodyParser.getBodyValuesWithJsonPathMultiple;
import static api_module.ResponseBodyParser.getBodyValuesWithJsonPathSingle;
import static api_module.Specifications.httpGetRequestWithQueryParameters;
import static common_module.logs.LoggerHelper.info;
import static common_module.property.PropertyHelper.getProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountriesCodeSteps {
    public static void checkForStatusCodeEquivalence(Response response, int expectedStatusCode) {
        info("Check that {%s} code is equal to {%s}",
                String.valueOf(response.getStatusCode()),
                String.valueOf(expectedStatusCode));
        assertEquals(response.getStatusCode(), expectedStatusCode, String.format("Actual status code is %d and it's not equal to expected: %d", response.getStatusCode(), expectedStatusCode));
    }

    public static void checkThatResponseBodyIsNotEmpty(Response response) {
        info("Check that {%s} response body is not empty",
                String.valueOf(ResponseBodyParser.getResponseBodyAsString(response).isEmpty()));
        assertFalse(ResponseBodyParser.getResponseBodyAsString(response).isEmpty(), "Response body is empty");
    }

    public static void checkThatAllElementsContainsValue(List<List<String>> elements, String value) {
        info("Check that value {%s} presented in the collection" + elements);
        assertTrue(elements.stream().allMatch(element -> element.contains(value)), "Elements do not contain value: " + value);
    }

    public static List<String> getBordersOfCountry(Response response) {
        return getBodyValuesWithJsonPathSingle(response, getProperty(PropertyPath.ALL_BORDERS_PATH.getPath()));
    }

    public static Response sendGetRequestForMultipleCountries(List<String> borders) {
        return httpGetRequestWithQueryParameters(
                getProperty(PropertyPath.BASE_URL.getPath()),
                getProperty(PropertyPath.CODES_PARAMETER_NAME.getPath()),
                borders
        );
    }

    public static List<List<String>> getBordersOfMultipleCountries(Response response) {
        return getBodyValuesWithJsonPathMultiple(response, getProperty(PropertyPath.BORDERS_COLLECTION_PATH.getPath()));
    }
}
