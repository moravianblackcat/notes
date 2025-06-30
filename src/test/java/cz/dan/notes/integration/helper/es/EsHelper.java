package cz.dan.notes.integration.helper.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EsHelper {

    private final ElasticsearchClient client;
    private final ObjectMapper objectMapper;

    public Map<String, Object> getFirstDocumentAsMap(String index) {
        SearchRequest searchRequest = SearchRequest.of(builder -> builder.index(index).size(1));

        try {
            SearchResponse<ObjectNode> response = client.search(searchRequest, ObjectNode.class);

            return objectMapper.convertValue(response.hits().hits().getFirst().source(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}