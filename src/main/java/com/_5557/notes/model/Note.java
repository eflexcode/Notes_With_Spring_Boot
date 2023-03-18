package com._5557.notes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue
    private Long id;
    private boolean finished = Boolean.FALSE;
    private String content;
    private String createdAt;
    private String lastUpdateAt;

}
