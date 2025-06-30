package cz.dan.notes.infra.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cz.dan.notes.elasticsearch")
@Data
public class ElasticsearchConfigProperties {

    String host;

    int port;

    String username;

    String password;

}
