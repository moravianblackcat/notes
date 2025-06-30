package cz.dan.notes.domain.note.service;

import cz.dan.notes.domain.note.controller.CreateNoteRequestDto;
import cz.dan.notes.domain.note.controller.NoteMapper;
import cz.dan.notes.domain.note.document.Note;
import cz.dan.notes.domain.note.document.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;

    private final NoteRepository noteRepository;

    @Override
    public void createNote(CreateNoteRequestDto createRequest) {
        Note note = noteMapper.from(createRequest);

        noteRepository.save(note);
    }

}
