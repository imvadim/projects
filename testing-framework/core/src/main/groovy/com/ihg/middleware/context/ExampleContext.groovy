package com.ihg.middleware.context

import com.ihg.middleware.client.HttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration

import static java.lang.System.getenv

/**
 * Created by Uladzimir_Ramaniuk on 11/5/2014.
 */
@ContextConfiguration
class ExampleContext extends AbstractContext {

    @Bean
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        def propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer()
        def resources = [
//                new ClassPathResource("conf/environments/${getenv(ENV)}/example.properties"),
                    new ClassPathResource("conf/environments/qa-devtest/example.properties"),
        ]
        propertyPlaceholderConfigurer.locations = resources
        propertyPlaceholderConfigurer.ignoreResourceNotFound = true
        propertyPlaceholderConfigurer
    }

    @Bean
    @Value('${weather.host.url}')
    HttpClient weatherApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${search.regions.host.url}')
    HttpClient searchRegionsApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${weather.group.host.url}')
    HttpClient weatherGroupApiHttpClient(String url) {
        new HttpClient(url)
    }

    @Bean
    @Value('${daily.host.url}')
    HttpClient weatherDailyForecastApiHttpClient(String url) {
        new HttpClient(url)
    }

}
