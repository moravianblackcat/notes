package cz.dan.notes.domain.note.controller;

import cz.dan.notes.domain.note.document.Note;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface NoteMapper {

    Note from(CreateNoteRequestDto createRequest);

}
