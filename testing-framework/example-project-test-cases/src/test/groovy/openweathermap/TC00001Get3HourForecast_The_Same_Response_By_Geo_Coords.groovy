package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase
import org.custommonkey.xmlunit.XMLUnit
import com.ihg.middleware.parser.DateParser as d

class TC00001Get3HourForecast_The_Same_Response_By_Geo_Coords extends OpenWeatherMapTestCase{
    def "User should be able to retrieve the same three hour weather forecast two times in a row"() {

        when: "I retrieve 3 hour weather forecast"
        def response = weatherForecastApiHttpClient.send(
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
        def anotherResponse = weatherForecastApiHttpClient.send(
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

        def result = new XmlSlurper().parseText(response)

        then: "The same responses are displayed"
        assert XMLUnit.compareXML(response, anotherResponse).identical()
        assert d.te(result.forecast.time[0].@from.toString()) != null
        assert d.te(result.forecast.time[8].@from.toString()) != null
        assert d.te(result.forecast.time[16].@from.toString()) != null

        where:
        latValue    |lonValue
        "52.099998" |"23.700001"
        "53.900002" |"27.566668"
        "53.6884"   |"23.8258"
    }
}