package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class GetWeatherDailyForecastTestCaseJsonSlurper extends ExampleTestCase {
    def "User should be able to retrieve daily forecast"() {
        String locationIdValue = "629634"
        String unitsValue = "metric"
        String cntValue = "16"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve daily forecast for a city id"
        def response = getDailyWeatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING: "id={location}&units={units}&cnt={cnt}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                location: locationIdValue,
                                units   : unitsValue,
                                cnt     : cntValue,
                                id      : idValue
                        ]
        )

        def result = new JsonSlurper().parseText(response)

        then: "Number of lines returned by this API call is displayed in response"
        (String) result.cnt == cntValue
    }
}