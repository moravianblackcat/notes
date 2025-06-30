package cz.dan.notes.domain.note.controller;

import java.util.Set;

public record CreateNoteRequestDto(
    String text,
    String source,
    String language,
    String author,
    Set<String> tags,
    Set<Long> personIds,
    Set<Long> teamIds,
    Set<Long> gameIds
) {}
