package com._5557.notes.service;

import com._5557.notes.model.*;
import com._5557.notes.repository.NoteRepository;
import com._5557.notes.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public Response addNoteToUser(String email, NoteRequest noteRequest) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No User Found whit email " + email));

        String createAndLastUpdatedAt = String.valueOf(new Date(System.currentTimeMillis()));

        Note note = new Note(0L, noteRequest.isFinished(), noteRequest.getContent(), createAndLastUpdatedAt, createAndLastUpdatedAt);

        user.getNoteList().add(note);

        userRepository.save(user);

        return new Response(Status.SUCCESS);

    }

    public List<Note> getNotesWhitUserId(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No User Found whit email " + email));

        return user.getNoteList();

    }
    public Note getNoteWhitUserId(String email, Integer id) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No User Found whit email " + email));
        return user.getNoteList().get(id-1);
    }
    public Response updateNoteToUser(String email,Integer id, NoteRequest noteRequest) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No User Found whit email " + email));

        String lastUpdatedAt = String.valueOf(new Date(System.currentTimeMillis()));

        Note note = user.getNoteList().get(id-1);
        note.setContent(noteRequest.getContent());
        note.setLastUpdateAt(lastUpdatedAt);
        note.setFinished(noteRequest.isFinished());

//        user.getNoteList().add(id-1,note);
        noteRepository.save(note);

        return new Response(Status.SUCCESS);
    }
    public Response deleteNoteToUser(String email,Integer id){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No User Found whit email " + email));
        Note note = noteRepository.findById(Long.valueOf(id)).orElseThrow(NoSuchElementException::new);
        user.getNoteList().remove(note);
//        Note note = user.getNoteList().get(id-1);
        noteRepository.delete(note);

        return new Response(Status.SUCCESS);
    }
}
