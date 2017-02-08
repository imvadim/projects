package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase

class TC00005GetCurrentWeather_Correct_City_ID_By_City_Name extends OpenWeatherMapTestCase{

    def "User should be able to retrieve weather condition"() {

        when: "I retrieve weather by city name"
        def response = currentWeatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&APPID={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                location : locationValue,
                                mode : modeValue,
                                id  : idValue
                        ],
                REQUEST_METHOD : "GET"
        )

        def result = new XmlSlurper().parseText(response)

        then: "There is correct city id in response"
        result.city.@id == cityId

        where:
        locationValue   ||cityId
        "Brest,by"      ||629634
        "Hrodna"        ||627904
        "Minsk"         ||625144
    }
}