package by.vadzimnovikau1.module7

import com.ihg.middleware.test.ExampleTestCase
import org.custommonkey.xmlunit.XMLUnit

class Spock extends ExampleTestCase{
    def "User should be able to retrieve the same three hour weather forecast each time on the same conditions"() {
        String modeValue = "xml"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve 3 hour weather forecast two times in a row"
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

        then: "The same responses are displayed"
            XMLUnit.compareXML(response, anotherResponse).identical()

        where: "For cities with geo coords"
        latValue    ||   lonValue
        52.099998   ||   23.700001
        53.900002   ||   27.566668
        53.6884     ||   23.8258

    }

    def "User should be able to retrieve weather condition"() {
        String modeValue = "xml"
        String idValue = ValuesFromProperties.getID()

        when: "I retrieve weather by city name"
        def response2 = getCurrentWeatherApiHttpClient.send(
                REQUEST_PARAMS_STRING : "q={location}&mode={mode}&APPID={id}",
                REQUEST_PARAMS_VARIABLES:
                        [
                                location : locationValue,
                                mode : modeValue,
                                id  : idValue
                        ],
                REQUEST_METHOD : "GET"
        )

        def result2 = new XmlSlurper().parseText(response2)

        then: "More info Weather condition codes is displayed"
        result2.weather.@value != null

        and: "There is correct city id in response"
        result2.city.@id == cityId

        where: "For cities"
        locationValue   ||  cityId
        "Brest,by"      ||  629634
        "Hrodna"        ||  627904
        "Minsk"         ||  625144
    }

    //        then: "Correct time of data forecasted is displayed"
//        list.dt
//
//        then: "Metric unit works correctly/right"
//        list.temp.day

//        then: "Temperature exists"
//        list.temp
//        list.temp.day
//        list.temp.min
//        list.temp.max
//        list.temp.night
//        list.temp.eve
//        list.temp.morn
//
//        then: "Humidity looks like real/not null or less/is set/is different from default"
//        list.pressure
//
//        then: "Atmospheric pressure looks like real/not null or less/is set/is different from default"
//        list.pressure
//
//        then: "More info Weather condition codes is displayed"
//        list.weather.id
}