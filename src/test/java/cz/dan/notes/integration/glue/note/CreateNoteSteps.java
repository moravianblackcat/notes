package cz.dan.notes.integration.glue.note;

import cz.dan.integrationtests.util.ClassPathResourceUtil;
import cz.dan.notes.integration.helper.es.EsHelper;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static cz.dan.await.AwaitHelper.assertMapsAreLenientEqual;

@RequiredArgsConstructor
public class CreateNoteSteps {

    private final ClassPathResourceUtil classPathResourceUtil;

    private final EsHelper esHelper;

    @Then("[{int}s] A note with fields from {} is created")
    public void noteWithFieldsFromIsCreated(int timeoutInSeconds, String fieldsJsonPath) {
        assertMapsAreLenientEqual(timeoutInSeconds, this::getNote,
                classPathResourceUtil.getMapFromJsonPath(fieldsJsonPath));
    }

    private Map<String, Object> getNote() {
        return esHelper.getFirstDocumentAsMap("note");
    }

}
