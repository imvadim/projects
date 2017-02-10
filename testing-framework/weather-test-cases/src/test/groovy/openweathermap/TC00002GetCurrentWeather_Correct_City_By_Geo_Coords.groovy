package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase

class TC00002GetCurrentWeather_Correct_City_By_Geo_Coords extends OpenWeatherMapTestCase {
    def "User should be able to retrieve weather"() {

        when: "I retrieve weather by geographic coordinates"
        def response = currentWeatherApiHttpClient.send(
                REQUEST_PARAMS_STRING: "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode: modeValue,
                                id  : idValue
                        ],
                REQUEST_METHOD : "GET"
        )

        def result = new XmlSlurper().parseText(response)

        then: "Correct city is founded"
        assert result.city.@name == name

        where:
        latValue    |lonValue   |modeValue  |name
        52.099998   |23.700001  |"xml"      |"Brest"
    }
}