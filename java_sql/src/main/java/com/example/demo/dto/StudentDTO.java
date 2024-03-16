package com.example.demo.dto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="students")
public class StudentDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_stud", nullable = false)
    private int idStud;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "an_studiu", nullable = false)
    private int an;

    @Column(name = "grupa", nullable = false)
    private String grupa;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<LegaturiDTO> legaturi;

    public StudentDTO(){}
    public  StudentDTO(int id_stud, String nume,String prenume, int an_studiu, String grupa){
        this.idStud=id_stud;
        this.nume=nume;
        this.prenume=prenume;
        this.an=an_studiu;
        this.grupa=grupa;
    }

    public String getNume(){
        return this.nume;
    }

    public int getAn() {
        return this.an;
    }

    public String getPrenume() {
        return this.prenume;
    }

    public String getGrupa() {
        return this.grupa;
    }

    public int getId() {
        return this.idStud;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public void setId(int id) {
        this.idStud = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

}
