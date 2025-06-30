package cz.dan.notes.domain.note.controller;

import cz.dan.notes.domain.note.document.Note;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NoteMapperTest {

    private final NoteMapper sut = new NoteMapperImpl();

    @Test
    void correctlyMapsFieldForCreationRequest() {
        CreateNoteRequestDto request = new CreateNoteRequestDto(
            "Random text",
            "Random source",
            "en",
            "Random Author",
            Set.of("tag1", "tag2"),
            Set.of(1L, 2L),
            Set.of(10L, 20L),
            Set.of(100L, 200L)
        );

        Note result = sut.from(request);

        assertThat(result)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .extracting(
                    Note::getText,
                    Note::getSource,
                    Note::getLanguage,
                    Note::getAuthor,
                    Note::getTags,
                    Note::getPersonIds,
                    Note::getTeamIds,
                    Note::getGameIds
                )
                .containsExactly(
                    "Random text",
                    "Random source",
                    "en",
                    "Random Author",
                    Set.of("tag1", "tag2"),
                    Set.of(1L, 2L),
                    Set.of(10L, 20L),
                        Set.of(100L, 200L)
                );
    }

}