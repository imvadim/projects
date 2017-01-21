package specs.example

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class GetWeatherTestCase extends ExampleTestCase{
    def "User should be able to retrieve weather"() {
        def locationValue = "Brest,by"
//        def modeValue = "xml"
        def idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

        when: "I retrieve weather for a city"
            def response = weatherApiHttpClient.send(
//                    REQUEST_PARAMS_STRING : "q={location}&mode={mode}&APPID={id}",
                    REQUEST_PARAMS_STRING : "q={location}&APPID={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    location : locationValue,
//                                    mode  : modeValue,
                                    id  : idValue
                            ]
            )
            def slurper = new JsonSlurper()
            def result = slurper.parseText(response)

        then: "Max and min temperature is displayed in response"
            result.main.temp_max > 260
            result.main.temp_min > 260
    }
}