package specs.example

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper

class GetWeatherGroupTestCase extends ExampleTestCase{
    def "User should be able to retrieve weather"() {
        def locationId1 = "629634"
        def locationId2 = "625144"
        def unitsTypeVale = "metric"
        def idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

//        http://api.openweathermap.org/data/2.5/group?id=629634,625144&units=metric&APPID=33245aa9f05b63ee57f32f9b3cbbd1b7

        when: "I retrieve weather for a city"
            def response = weatherGroupApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "id={location1},{location2}&units={unitsType}&APPID={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    location1 : locationId1,
                                    location2 : locationId2,
                                    unitsType : unitsTypeVale,
                                    id : idValue
                            ]
            )
            def slurper = new JsonSlurper()
            def result = slurper.parseText(response)

        then: "Max and min temperature is displayed in response"
            result.dt.id == locationId1
//            result.main.temp_min > 0
    }
}