package cz.dan.notes.domain.note.controller;

import cz.dan.notes.domain.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<Void> createNote(@RequestBody CreateNoteRequestDto request) {
        noteService.createNote(request);
        return ResponseEntity.status(201).build();
    }

}
