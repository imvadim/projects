package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase

class GetCurrentWeatherTestCaseXmlSlurper extends ExampleTestCase {
    def "User should be able to retrieve weather"() {
        String latValue = "52.099998"
        String lonValue = "23.700001"
        String modeValue = "xml"
        String typeValue = "like"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve weather by geographic coordinates"
        def response = getCurrentWeatherApiHttpClient.send(
                REQUEST_PARAMS_STRING: "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                lat : latValue,
                                lon : lonValue,
                                type: typeValue,
                                mode: modeValue,
                                id  : idValue
                        ]
        )

        def result = new XmlSlurper().parseText(response)

        then: "Correct city is founded"
        result.city.@name == 'Brest'
    }
}