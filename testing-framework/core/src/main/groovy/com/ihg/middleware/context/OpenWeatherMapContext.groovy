package com.ihg.middleware.context

import com.ihg.middleware.client.HttpClient
import com.ihg.middleware.test.ExampleTestCase
import com.ihg.middleware.test.OpenWeatherMapTestCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration

import static java.lang.System.getenv

@ContextConfiguration
class OpenWeatherMapContext extends AbstractContext {

    @Bean
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        def propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer()
        def resources = [
                new ClassPathResource("conf/environments/${getenv(ENV)}/example.properties"),
        ]
        propertyPlaceholderConfigurer.locations = resources
        propertyPlaceholderConfigurer.ignoreResourceNotFound = true
        propertyPlaceholderConfigurer
    }

    @Bean
    @Value('${weather.forecast.host.url}')
    HttpClient weatherForecastApiHttpClient( String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${current.weather.host.url}')
    HttpClient currentWeatherApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${weather.daily.forecast.host.url}')
    HttpClient dailyWeatherForecastApiHttpClient(String url) {
        new HttpClient(url)
    }
}
