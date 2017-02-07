package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import org.custommonkey.xmlunit.XMLUnit

class Get3HourForecastTestCaseXmlUnit extends ExampleTestCase{
    def "User should be able to retrieve the same three hour weather forecast two times in a row"() {
        String latValue="52.099998"
        String lonValue="23.700001"
        String modeValue = "xml"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve 3 hour weather forecast"
        def response = getWeatherForecastApiHttpClient.send(
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
        def anotherResponse = getWeatherForecastApiHttpClient.send(
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
        String firstDayDate = result.forecast.time[0].@from
        String secondDayDate = result.forecast.time[8].@from
        String thirdDayDate = result.forecast.time[16].@from
        Date firstDay = Date.parse("yyyy-MM-dd'T'HH:mm:ss", firstDayDate)
        Date secondDay = Date.parse("yyyy-MM-dd'T'HH:mm:ss", secondDayDate)
        Date thirdDay = Date.parse("yyyy-MM-dd'T'HH:mm:ss", thirdDayDate)

//        String newDate = date.format( 'M-d-yyyy' )
//        println newDate
//        String newTimeDate = date.format( 'hh:mm:ss' )
//        println newTimeDate
////        println "date read: ${date}"
//        println "timestamp: ${date.time}"

        then: "The same responses are displayed"
        XMLUnit.compareXML(response, anotherResponse).identical()

        and: "The first day is displayed in response"
        firstDay != null

        and: "The second day is displayed in response"
        secondDay == firstDay + 1

        and: "The third day is displayed in response"
        thirdDay == firstDay + 2
    }
}