package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase
import groovy.json.JsonSlurper

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

        def result = new JsonSlurper().parseText(response)

        then: "Temperature in the daytime is within the limits of the city"
        assert result.list.temp[0].day > -35.5
        assert result.list.temp[0].day < 36.7

        where:
        locationIdValue |unitsValue |cntValue
        "629634"        |"metric"   |"16"
    }
}