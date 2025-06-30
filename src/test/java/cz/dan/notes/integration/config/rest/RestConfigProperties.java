package cz.dan.notes.integration.config.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.test")
@Data
public class RestConfigProperties {

    String host;

    int port;

}
