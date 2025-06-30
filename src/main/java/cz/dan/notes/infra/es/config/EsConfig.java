package cz.dan.notes.infra.es.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static org.apache.http.conn.ssl.TrustAllStrategy.INSTANCE;

@Configuration
@RequiredArgsConstructor
public class EsConfig extends ElasticsearchConfiguration {

    private final ElasticsearchConfigProperties properties;

    @Override
    public ClientConfiguration clientConfiguration() {
        String hostAndPort = properties.getHost() + ":" + properties.getPort();

        return ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .withBasicAuth(properties.getUsername(), properties.getPassword())
                .build();
    }

    private static SSLContext sslContext() {
        try {
            return new SSLContextBuilder()
                    .loadTrustMaterial(null, INSTANCE)
                    .build();
        } catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
