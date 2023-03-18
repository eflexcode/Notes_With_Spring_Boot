package com._5557.notes.service;

import com._5557.notes.model.*;
import com._5557.notes.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final UserRepository userRepository;

    public Response addNoteToUser(String email, NoteRequest noteRequest){

        User user = userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("No User Found wit email "+email));

        String createAndLastUpdatedAt = String.valueOf(new Date(System.currentTimeMillis()));

        Note note = new Note(0L,noteRequest.isFinished(),noteRequest.getContent(),createAndLastUpdatedAt,createAndLastUpdatedAt);

        user.getNoteList().add(note);

        userRepository.save(user);

        return new Response(Status.SUCCESS);

    }
//    public Response getNoteWhitUserId(String email, NoteRequest noteRequest){
//
//        User user = userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("No User Found wit email "+email));
//
//        String createAndLastUpdatedAt = String.valueOf(new Date(System.currentTimeMillis()));
//
//        Note note = new Note(0L,noteRequest.isFinished(),noteRequest.getContent(),createAndLastUpdatedAt,createAndLastUpdatedAt);
//
//        user.getNoteList().add(note);
//
//        return new Response(Status.SUCCESS);
//
//    }

}
