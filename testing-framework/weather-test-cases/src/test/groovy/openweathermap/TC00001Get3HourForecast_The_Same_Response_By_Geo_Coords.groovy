package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase
import org.custommonkey.xmlunit.XMLUnit
import java.text.SimpleDateFormat

class TC00001Get3HourForecast_The_Same_Response_By_Geo_Coords extends OpenWeatherMapTestCase {
    def "User should be able to retrieve the same three hour weather forecast two times in a row"() {

        when: "I retrieve 3 hour weather forecast"
        def response = weatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING: "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode: modeValue,
                                id  : idValue
                        ],
                REQUEST_METHOD: "GET"
        )

        and: "And retrieve 3 hour weather forecast again"
        def anotherResponse = weatherForecastApiHttpClient.send(
                REQUEST_PARAMS_STRING: "lat={lat}&lon={lon}&mode={mode}&appid={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                lat : latValue,
                                lon : lonValue,
                                mode: modeValue,
                                id  : idValue
                        ],
                REQUEST_METHOD: "GET"
        )

        def result = new XmlSlurper().parseText(response)
        def now = new Date()
        String dateTimeString = "yyyy-MM-dd"
        def dateFormat = new SimpleDateFormat(dateTimeString)

        def datesFromResponse = result.forecast.time.@from.collect {
            dateFormat.format(dateFormat.parse(it.toString()))
        }

        def listOfCurrentDates = (0..2).collect {
            dateFormat.format(now + it)
        }

        then: "The same responses are displayed"
        assert datesFromResponse.containsAll(listOfCurrentDates)
        assert XMLUnit.compareXML(response, anotherResponse).identical()

        where:
        latValue  | lonValue  | modeValue
        52.099998 | 23.700001 | "xml"
        53.900002 | 27.566668 | "xml"
        53.6884   | 23.8258   | "xml"
    }
}