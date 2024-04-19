package api_module.steps;

import api_module.Specifications;
import common_module.constants.PropertyPath;
import io.restassured.response.Response;

import java.util.List;

import static api_module.ResponseBodyParser.getBodyValuesWithJsonPathMultiple;
import static api_module.ResponseBodyParser.getBodyValuesWithJsonPathSingle;
import static common_module.property.PropertyHelper.getProperty;

public class CountriesCodeSteps {

    private final Specifications specifications = new Specifications();

    public List<String> getBordersOfCountry(Response response) {
        return getBodyValuesWithJsonPathSingle(response, getProperty(PropertyPath.ALL_BORDERS_PATH.getPath()));
    }

    public Response sendGetRequestForMultipleCountries(List<String> borders) {
        return specifications.httpGetRequestWithQueryParameters(
                getProperty(PropertyPath.BASE_URL.getPath()),
                getProperty(PropertyPath.CODES_PARAMETER_NAME.getPath()),
                borders
        );
    }

    public List<List<String>> getBordersOfMultipleCountries(Response response) {
        return getBodyValuesWithJsonPathMultiple(response, getProperty(PropertyPath.BORDERS_COLLECTION_PATH.getPath()));
    }
}
