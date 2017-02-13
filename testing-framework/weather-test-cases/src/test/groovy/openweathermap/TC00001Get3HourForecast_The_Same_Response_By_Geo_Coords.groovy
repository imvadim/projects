package openweathermap

import com.ihg.middleware.test.OpenWeatherMapTestCase
import org.custommonkey.xmlunit.XMLUnit

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate

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

        def datesFromResponse = result.forecast.time.@from.collect {
            Date.parse("yyyy-MM-dd'T'HH:mm:ss", it.toString()).format("yyyy-MM-dd")
        }

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//        def datesFromResponse = result.forecast.time.@from.collect {
//            dateFormat.parse(it.toString())
//        }

        def listOfCurrentDates = (0..2).collect {LocalDate.now().plusDays(it).toString() }

        then: "The same responses are displayed"
        assert XMLUnit.compareXML(response, anotherResponse).identical()
        assert datesFromResponse.containsAll(listOfCurrentDates)

        where:
        latValue  | lonValue  | modeValue
        52.099998 | 23.700001 | "xml"
        53.900002 | 27.566668 | "xml"
        53.6884   | 23.8258   | "xml"
    }
}