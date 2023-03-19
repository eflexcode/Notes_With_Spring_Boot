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
    @GetMapping("/getNote/{email}/{id}")
    public Note getNoteWhitUserId(@PathVariable String email, @PathVariable Integer id) {
        return noteService.getNoteWhitUserId(email, id);
    }
    @PutMapping("/updateNote/{email}/{id}")
    public Response updateNoteToUser(@PathVariable String email,@PathVariable Integer id, @RequestBody NoteRequest noteRequest) {
        return noteService.updateNoteToUser(email, id, noteRequest);
    }
    @DeleteMapping("/delete/{email}/{id}")
    public Response deleteNoteToUser(@PathVariable String email,@PathVariable Integer id){
        return noteService.deleteNoteToUser(email, id);
    }

}
