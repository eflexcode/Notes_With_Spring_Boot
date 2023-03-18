package com._5557.notes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "_user")
public class User {

    @Id @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastname;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> noteList = new ArrayList<>();

}
