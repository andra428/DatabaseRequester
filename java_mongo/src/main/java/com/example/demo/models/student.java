package com.example.demo.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class student {
    @Id
    private ObjectId id_student;
    private String prenume;
    private String nume;
    private String stare;
    private ObjectId id_catalog_promovat;
    private List<nota> note;
    private Double medie;
}
