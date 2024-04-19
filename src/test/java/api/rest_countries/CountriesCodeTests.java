package api.rest_countries;

import api.BaseTest;
import api_module.Specifications;
import api_module.constants.StatusCode;
import api_module.steps.CountriesCodeSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class CountriesCodeTests extends BaseTest {

    private final CountriesCodeSteps countriesCodeSteps = new CountriesCodeSteps();
    private final Specifications specifications = new Specifications();

    @DisplayName("Наличие верного статуса ответа")
    @ParameterizedTest
    @CsvSource(value = {"codes:RUS", "codes:BLR", "codes:CHN"}, delimiter = ':')
    public void testResponseStatus(String parameterName, String countryISOCode) {
        Response responseForOneCountry = sendGetRequest(parameterName, countryISOCode);
        Response responseForMultipleCountries = countriesCodeSteps.sendGetRequestForMultipleCountries(
                countriesCodeSteps.getBordersOfCountry(responseForOneCountry));
        specifications.checkForStatusCodeEquivalence(responseForOneCountry, StatusCode.OK.getStatusCode());
        specifications.checkForStatusCodeEquivalence(responseForMultipleCountries, StatusCode.OK.getStatusCode());
    }

    @DisplayName("Наличие данных в теле ответа")
    @ParameterizedTest
    @CsvSource(value = {"codes:RUS", "codes:BLR", "codes:CHN"}, delimiter = ':')
    public void testDataPresence(String parameterName, String countryISOCode) {
        Response responseForOneCountry = sendGetRequest(parameterName, countryISOCode);
        Response responseForMultipleCountries = countriesCodeSteps.sendGetRequestForMultipleCountries(
                countriesCodeSteps.getBordersOfCountry(responseForOneCountry));
        specifications.checkThatResponseBodyIsNotEmpty(responseForOneCountry);
        specifications.checkThatResponseBodyIsNotEmpty(responseForMultipleCountries);
    }

    @DisplayName("Наличие взаимных границ между странами")
    @ParameterizedTest
    @CsvSource(value = {"codes:RUS", "codes:BLR", "codes:CHN"}, delimiter = ':')
    public void testBorderPresence(String parameterName, String countryISOCode) {
        Response responseForOneCountry = sendGetRequest(parameterName, countryISOCode);
        List<List<String>> bordersOfMultipleCountries = countriesCodeSteps.getBordersOfMultipleCountries(
                countriesCodeSteps.sendGetRequestForMultipleCountries(
                        countriesCodeSteps.getBordersOfCountry(responseForOneCountry)));
        specifications.checkThatAllElementsContainsValue(bordersOfMultipleCountries, countryISOCode.toUpperCase());
    }
}
