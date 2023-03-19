package com._5557.notes.controllers;

import com._5557.notes.model.*;
import com._5557.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/addNote/{email}")
    public ResponseEntity<Response> addNote(@PathVariable String email, @RequestBody NoteRequest noteRequest) {
        return ResponseEntity.ok(noteService.addNoteToUser(email, noteRequest));
    }
    @GetMapping("/getNotes/{email}")
    public List<Note> getNotes(@PathVariable String email) {
        return noteService.getNotesWhitUserId(email);
    }

}
