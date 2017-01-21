package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import org.custommonkey.xmlunit.XMLUnit

class SearchRegionsTestCaseXmlUnit extends ExampleTestCase{
    def "User should be able to find cities by keyword"() {
        def latValue="52.099998"
        def lonValue="23.700001"
        def modeValue = "xml"
        def typeValue = "like"
        def idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

        when: "I retrieve cities by keyword"
            def response = searchRegionsApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "lat={lat}&lon=&type={type}&mode={mode}&APPID={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    lat : latValue,
                                    lon : lonValue,
                                    type  : typeValue,
                                    mode  : modeValue,
                                    id  : idValue
                            ]
            )

        def anotherResponse = searchRegionsApiHttpClient.send(
                REQUEST_PARAMS_STRING : "lat={lat}&lon=&type={type}&mode={mode}&APPID={id}",
                REQUEST_PARAMS_VARIABLES :
                        [
                                lat : latValue,
                                lon : lonValue,
                                type  : typeValue,
                                mode  : modeValue,
                                id  : idValue
                        ]
        )

        then: "All countries with this city are found"
            !XMLUnit.compareXML(response, anotherResponse).identical()
    }
}