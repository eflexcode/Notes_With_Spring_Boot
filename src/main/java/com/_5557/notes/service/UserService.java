package com._5557.notes.service;

import com._5557.notes.model.Response;
import com._5557.notes.model.Status;
import com._5557.notes.model.User;
import com._5557.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Response createUser(User user) {

        User savedUser = userRepository.save(user);

        return new Response(Status.SUCCESS);

    }

    public Response deleteUser(String id) {

        User user = userRepository.findByEmail(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + id));

        userRepository.deleteById(user.getId());

        return new Response(Status.SUCCESS);

    }

    public User getUserWithId(String id) {

        return userRepository.findByEmail(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + id));

    }

    public Response updateUserWithId(User user) {

        User newUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with id " + user.getEmail()));
        newUser.setLastname(user.getLastname());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);


        return new Response(Status.SUCCESS);
    }


}
