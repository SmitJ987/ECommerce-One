package com.smitjdev.demospringbootone.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //to tell spring that, whenever you are running the application, create object of this 'configuration' class
public class ApplicationConfiguration {

    @Bean   //this will create the rest template one time, and will inject it wherever the use of rest template is there
    public RestTemplate createRestTemplate()
    {
        return new RestTemplate();
    }

    //why @Bean
    //difference between @Service and @Bean

    //when I have defined a class myself like FakeStoreProductService: then I have to write @Service

    //when Iam using class of springboot: like the RestTemplate: then write @Bean
    //just that...

    //now where we will use this rest template:
    //into FakeStoreProductService, as service class handles calls to 3rd party api and all
    //so there this rest template would be used
}
