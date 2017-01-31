package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import org.custommonkey.xmlunit.XMLUnit
import com.ihg.middleware.context.ExampleContext

class Get3HourWeatherForecastTestCaseXmlUnit extends ExampleTestCase{
    def "User should be able to retrieve the same three hour weather forecast each time on the same conditions"() {
        def latValue="52.099998"
        def lonValue="23.700001"
        def modeValue = "xml"
        def idValue = getI
//        def idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

        when: "I retrieve 3 hour weather forecast two times in a row"
            def response = getWeatherForecastApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    lat : latValue,
                                    lon : lonValue,
                                    mode  : modeValue,
                                    id  : idValue
                            ]
            )

        def anotherResponse = getWeatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon={lon}&mode={mode}&APPID={id}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode  : modeValue,
                                id  : idValue
                        ]
        )

        then: "The same responses are displayed"
            XMLUnit.compareXML(response, anotherResponse).identical()
    }
}