package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class GetWeatherDailyForecastTestCaseJsonSlurper extends ExampleTestCase{
    def "User should be able to retrieve daily forecast"() {
        String locationIdValue = "629634"
        String unitsValue = "metric"
        String cntValue = "16"
        String idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

        when: "I retrieve daily forecast for a city id"
            def response = getDailyWeatherForecastApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "id={location}&units={units}&cnt={cnt}&appid={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    location : locationIdValue,
                                    units : unitsValue,
                                    cnt : cntValue,
                                    id  : idValue
                            ]
            )
            def slurper = new JsonSlurper()
            def result = slurper.parseText(response)


        then: "First and last day is displayed in response"
        result.list.dt[0] > 0
        result.list.dt[(cntValue as int) -1] > 0
    }
}