package cz.dan.notes.infra.es.index;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.index.Settings;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ElasticsearchIndexInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> beansWithDocumentAnnotation = event.getApplicationContext().getBeansWithAnnotation(Document.class);

        for (Object bean : beansWithDocumentAnnotation.values()) {
            createIndexIfDoesNotExist(bean.getClass());
        }
    }

    private void createIndexIfDoesNotExist(Class<?> documentClass) {
        IndexOperations indexOperations = elasticsearchOperations.indexOps(documentClass);

        if (!indexOperations.exists()) {
            org.springframework.data.elasticsearch.core.document.Document mapping = indexOperations.createMapping();
            Settings settings = indexOperations.createSettings();
            indexOperations.create(settings, mapping);
        }
    }

}
