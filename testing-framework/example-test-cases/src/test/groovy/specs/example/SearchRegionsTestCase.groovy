package specs.example

import com.ihg.middleware.test.ExampleTestCase
import groovy.json.JsonSlurper
import org.custommonkey.xmlunit.XMLUnit

class SearchRegionsTestCase extends ExampleTestCase{
    def "User should be able to find cities by keyword"() {
        def locationValue = "Brest"
        def modeValue = "xml"
        def typeValue = "like"
        def idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

        when: "I retrieve cities by keyword"
            def response = searchRegionsApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "q={location}&type={type}&mode={mode}&APPID={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    location : locationValue,
                                    type  : typeValue,
                                    mode  : modeValue,
                                    id  : idValue
                            ]
            )

        def anotherResponse = searchRegionsApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&type={type}&mode={mode}&APPID={id}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                location : locationValue,
                                type  : typeValue,
                                mode  : modeValue,
                                id  : idValue
                        ]
        )

//        def result = new XmlSlurper().parseText(response)


        then: "All countries with this city are found"
            XMLUnit.compareXML(response, anotherResponse).identical()
    }
}