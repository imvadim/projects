package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import org.custommonkey.xmlunit.XMLUnit

class Spock extends ExampleTestCase{
    def "User should be able to retrieve the same three hour weather forecast each time on the same conditions"() {
        String modeValue = "xml"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve 3 hour weather forecast two times in a row"
            def response = getWeatherForecastApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    lat : latValue,
                                    lon : lonValue,
                                    mode  : modeValue,
                                    id : idValue
                            ],
                    REQUEST_METHOD : "GET"
            )

        def anotherResponse = getWeatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode  : modeValue,
                                id : idValue
                        ],
                REQUEST_METHOD : "GET"
        )

        then: "The same responses are displayed"
            XMLUnit.compareXML(response, anotherResponse).identical()

        where: "For cities with geo coords"
        latValue    ||   lonValue
        52.099998   ||   23.700001
        53.900002   ||   27.566668
        53.6884     ||   23.8258

    }
}