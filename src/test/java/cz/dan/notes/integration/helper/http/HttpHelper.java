package cz.dan.notes.integration.helper.http;

import cz.dan.notes.integration.config.rest.RestConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RequiredArgsConstructor
@TestComponent
public class HttpHelper {

    private final RestConfigProperties restConfigProperties;

    private final RestTemplate restTemplate;

    public void postWithJsonRequestBody(String requestBody, String endpoint) {
        String url = getUrl(endpoint);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, getJsonHeaders());
        restTemplate.postForEntity(URI.create(url), entity, String.class);
    }

    private String getUrl(String endpoint) {
        return "http://" + restConfigProperties.getHost() + ":" + restConfigProperties.getPort() + endpoint;
    }

    private HttpHeaders getJsonHeaders() {
        HttpHeaders result = new HttpHeaders();
        result.setContentType(APPLICATION_JSON);

        return result;
    }

}
