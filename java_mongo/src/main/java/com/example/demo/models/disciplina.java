package com.example.demo.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Document(collection = "discipline")
public class disciplina {
    @Id
    private ObjectId id;
    private String denumire_materie;
    private String denumire_program_studii;
    private int an_studii;
    private String nume_titular;

    private List<student> studenti;

}
