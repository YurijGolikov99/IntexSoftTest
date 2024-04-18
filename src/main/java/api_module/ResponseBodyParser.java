package api_module;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.List;

import static common_module.logs.LoggerHelper.info;


public class ResponseBodyParser {

    public static ResponseBody getResponseBody(Response response) {
        return response.getBody();
    }

    public static String getResponseBodyAsString(Response response) {
        info("Getting response body as String %s", getResponseBody(response).asString());
        return getResponseBody(response).asString();
    }

    public static String getBodyValueWithJsonPath(Response response, String jsonPath) {
        info("Getting single value with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }

    public static List<String> getBodyValuesWithJsonPathSingle(Response response, String jsonPath) {
        info("Getting collection of values with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }

    public static List<List<String>> getBodyValuesWithJsonPathMultiple(Response response, String jsonPath) {
        info("Getting multiple collection of values with jsonPath %s", jsonPath);
        return JsonPath.read(getResponseBodyAsString(response), jsonPath);
    }
}
