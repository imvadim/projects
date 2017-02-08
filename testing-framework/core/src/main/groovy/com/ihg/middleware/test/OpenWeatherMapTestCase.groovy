package com.ihg.middleware.test

import com.ihg.middleware.client.HttpClient
import com.ihg.middleware.context.OpenWeatherMapContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = OpenWeatherMapContext.class)
abstract class OpenWeatherMapTestCase extends Specification {
    /**
     * Client to call Weather API.
     */
    @Autowired
    HttpClient currentWeatherApiHttpClient

    @Autowired
    HttpClient weatherForecastApiHttpClient

    @Autowired
    HttpClient dailyWeatherForecastApiHttpClient

    @Autowired
    String idValue

    @Autowired
    String modeValue
}
