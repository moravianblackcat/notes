package cz.dan.notes.domain.note.service;

import cz.dan.notes.domain.note.controller.CreateNoteRequestDto;

public interface NoteService {

    void createNote(CreateNoteRequestDto createRequest);

}
