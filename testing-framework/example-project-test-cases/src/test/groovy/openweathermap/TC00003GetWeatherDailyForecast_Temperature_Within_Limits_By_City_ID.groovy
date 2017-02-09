package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase
import org.json.JSONArray
import org.json.JSONObject

class TC00003GetWeatherDailyForecast_Temperature_Within_Limits_By_City_ID extends OpenWeatherMapTestCase {
    def "User should be able to retrieve daily forecast"() {

        when: "I retrieve daily forecast for a city id"
        String response = dailyWeatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING: "id={location}&units={units}&cnt={cnt}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                location: locationIdValue,
                                units   : unitsValue,
                                cnt     : cntValue,
                                id      : idValue
                        ],
                REQUEST_METHOD : "GET"
        )

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("list");

        then: "Temperature in the daytime is within the limits of the city"
        for (int i = 0; i < arr.length(); i++)
        {
            assert arr.getJSONObject(i).get("temp").get("day") > -35.5
            assert arr.getJSONObject(i).get("temp").get("day") < 36.7
        }

        where:
        locationIdValue |unitsValue |cntValue
        "629634"        |"metric"   |"16"
    }
}