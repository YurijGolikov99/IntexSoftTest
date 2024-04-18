package api.rest_countries;

import api.BaseTest;
import api_module.constants.StatusCode;
import common_module.constants.PropertyPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static api_module.steps.CountriesCodeSteps.checkForStatusCodeEquivalence;
import static api_module.steps.CountriesCodeSteps.checkThatAllElementsContainsValue;
import static api_module.steps.CountriesCodeSteps.checkThatResponseBodyIsNotEmpty;
import static api_module.steps.CountriesCodeSteps.getBordersOfCountry;
import static api_module.steps.CountriesCodeSteps.getBordersOfMultipleCountries;
import static api_module.steps.CountriesCodeSteps.sendGetRequestForMultipleCountries;

public class CountriesCodeTests extends BaseTest {

    @DisplayName("Наличие верного статуса ответа и данных")
    @ParameterizedTest
    @CsvSource(value = {"codes:RUS", "codes:BLR", "codes:CHN"}, delimiter = ':')
    public void testResponseStatusAndDataPresence(String parameterName, String countryISOCode) {
        Response responseForOneCountry = sendGetRequest(parameterName, countryISOCode);
        Response responseForMultipleCountries = sendGetRequestForMultipleCountries(getBordersOfCountry(responseForOneCountry));
        checkForStatusCodeEquivalence(responseForOneCountry, StatusCode.OK.getStatusCode());
        checkForStatusCodeEquivalence(responseForMultipleCountries, StatusCode.OK.getStatusCode());
        checkThatResponseBodyIsNotEmpty(responseForOneCountry);
        checkThatResponseBodyIsNotEmpty(responseForMultipleCountries);
    }

    @DisplayName("Наличие взаимных границ между странами")
    @ParameterizedTest
    @CsvSource(value = {"codes:RUS", "codes:BLR", "codes:CHN"}, delimiter = ':')
    public void testBorderPresence(String parameterName, String countryISOCode) {
        Response responseForOneCountry = sendGetRequest(parameterName, countryISOCode);
        List<List<String>> bordersOfMultipleCountries = getBordersOfMultipleCountries(sendGetRequestForMultipleCountries(getBordersOfCountry(responseForOneCountry)));
        checkThatAllElementsContainsValue(bordersOfMultipleCountries, countryISOCode.toUpperCase());
    }
}
