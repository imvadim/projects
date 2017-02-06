package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonParserType
import groovy.json.JsonSlurper

class GetWeatherDailyForecastTestCaseJsonParser extends ExampleTestCase {
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

        def jsonSlurper = new JsonSlurper(type: JsonParserType.INDEX_OVERLAY)
        def result = jsonSlurper.parseText(response)

        then: "First and last day is displayed in response"
        result.list.dt[0] > 0
        result.list.dt[(cntValue as int) - 1] > 0
    }
}