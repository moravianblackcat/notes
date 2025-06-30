package cz.dan.notes.integration.glue.note;

import cz.dan.notes.integration.config.rest.RestConfigProperties;
import cz.dan.notes.integration.helper.es.EsHelper;
import cz.dan.notes.integration.helper.http.HttpHelper;
import cz.dan.notes.integration.util.ClassPathResourceUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Map;

import static cz.dan.await.AwaitHelper.assertMapsAreLenientEqual;

@RequiredArgsConstructor
@EnableConfigurationProperties(RestConfigProperties.class)
public class CreateNoteSteps {

    private final ClassPathResourceUtil classPathResourceUtil;

    private final EsHelper esHelper;

    private final HttpHelper httpHelper;

    @When("I send POST request to {} with request body from {}")
    public void postRequestToWithRequestBody(String endpoint, String requestBodyJsonPath) {
        httpHelper.postWithJsonRequestBody(classPathResourceUtil.getStringFromJsonPath(requestBodyJsonPath), endpoint);
    }

    @Then("[{int}s] A note with some ID and fields from {} is created")
    public void noteWithSomeIdAndFieldsFromIsCreated(int timeoutInSeconds, String fieldsJsonPath) {
        assertMapsAreLenientEqual(timeoutInSeconds, this::getNote, classPathResourceUtil.getMapFromJsonPath(fieldsJsonPath));
    }

    private Map<String, Object> getNote() {
        return esHelper.getFirstDocumentAsMap("note");
    }

}
