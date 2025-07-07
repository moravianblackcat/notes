package cz.dan.notes.integration.config;

import cz.dan.integrationtests.http.HttpAutoConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringTestConfig.class, webEnvironment = DEFINED_PORT)
@ActiveProfiles("integration-tests")
@ImportAutoConfiguration({HttpAutoConfiguration.class})
public class CucumberSpringContextConfig {

}
