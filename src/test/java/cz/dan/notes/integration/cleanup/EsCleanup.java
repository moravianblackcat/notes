package cz.dan.notes.integration.cleanup;
import io.cucumber.java.Before;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.IOException;

@RequiredArgsConstructor
public class EsCleanup {

    private final RestClient restClient;
    private final EsCleanupProperties cleanupProperties;

    @Before
    public void cleanUp() {
        String[] indices = cleanupProperties.getIndexes().split(",");
        for (String index : indices) {
            try {
                Request request = new Request("POST", "/" + index + "/_delete_by_query");
                request.setJsonEntity("{\"query\": {\"match_all\": {}}}");
                restClient.performRequest(request);
            } catch (IOException e) {
                throw new RuntimeException("Failed to clean up index: " + index, e);
            }
        }
    }

    @ConfigurationProperties("es.cleanup")
    @Value
    public static class EsCleanupProperties {
        String indexes;
    }
}
