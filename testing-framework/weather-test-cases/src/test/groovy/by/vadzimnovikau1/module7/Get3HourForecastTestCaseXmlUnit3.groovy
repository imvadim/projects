package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import org.custommonkey.xmlunit.XMLUnit

class Get3HourForecastTestCaseXmlUnit3 extends ExampleTestCase{
    def "User should be able to retrieve the same three hour weather forecast two times in a row"() {
        String latValue="52.099998"
        String lonValue="23.700001"
        String modeValue = "xml"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve 3 hour weather forecast"
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

        and: "And retrieve 3 hour weather forecast again"
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

    }
}