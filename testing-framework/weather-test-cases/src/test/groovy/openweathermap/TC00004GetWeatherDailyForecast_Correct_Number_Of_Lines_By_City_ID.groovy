package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase
import groovy.json.JsonSlurper

class TC00004GetWeatherDailyForecast_Correct_Number_Of_Lines_By_City_ID extends OpenWeatherMapTestCase {
    def "User should be able to retrieve daily forecast"() {

        when: "I retrieve daily forecast for a city id"
        def response = dailyWeatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING: "id={location}&units={units}&cnt={cnt}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                location: locationIdValue,
                                units   : unitsValue,
                                cnt     : cntValue,
                                id      : idValue
                        ],
                REQUEST_METHOD: "GET"
        )

        def result = new JsonSlurper().parseText(response)

        then: "Number of lines returned by this API call is displayed in response"
        assert result.cnt == cntValue

        where:
        locationIdValue | unitsValue | cntValue
        629634          | "metric"   | 5
    }
}