package cz.dan.notes.integration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class SpringTestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
