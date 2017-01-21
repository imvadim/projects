package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase

class SearchCountryTestCaseXmlSlurper extends ExampleTestCase{
    def "User should be able to find a city of a country by keywords"() {
        def locationValue = "Brest,BY"
        def modeValue = "xml"
        def typeValue = "like"
        def idValue = "33245aa9f05b63ee57f32f9b3cbbd1b7"

        when: "I retrieve cities by keyword"
            def response = searchCountryApiHttpClient.send(
                    REQUEST_PARAMS_STRING : "q={location}&type={type}&mode={mode}&APPID={id}",
                    REQUEST_PARAMS_VARIABLES :
                            [
                                    location : locationValue,
                                    type  : typeValue,
                                    mode  : modeValue,
                                    id  : idValue
                            ]
            )

        def result = new XmlSlurper().parseText(response)
        def countryResult = result.list.item.city.country

        then: "The country with this city are found"
        assert countryResult.text() == 'BY'
    }
}