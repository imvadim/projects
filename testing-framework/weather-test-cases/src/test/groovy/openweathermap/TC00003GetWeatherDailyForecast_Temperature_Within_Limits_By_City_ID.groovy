package openweathermap

import com.google.gson.JsonParser
import com.ihg.middleware.test.OpenWeatherMapTestCase

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

        def obj = new JsonParser().parse(response)
        def jarray = obj.getAsJsonArray("list")

        then: "Temperature in the daytime is within the limits of the city"
        jarray.each {assert it.getAsJsonObject("temp").get("day").asBigDecimal > -35.5}
        jarray.each {assert it.getAsJsonObject("temp").get("day").asBigDecimal < 36.7}

        where:
        locationIdValue |unitsValue |cntValue
        629634          |"metric"   |16
    }
}