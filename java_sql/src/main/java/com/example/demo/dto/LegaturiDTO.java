package com.example.demo.dto;

import jakarta.persistence.*;
@Entity
@Table(name="legaturi")
public class LegaturiDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_leg", nullable = false)
    private int idLeg;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private StudentDTO student;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false)
    private DisciplinaDTO disciplina;

    public LegaturiDTO(){}

    public LegaturiDTO(StudentDTO stud, DisciplinaDTO dis){

        this.student=stud;
        this.disciplina=dis;
    }

    public DisciplinaDTO getDisc() {
        return disciplina;
    }

    public StudentDTO getStud() {
        return student;
    }

    public void setDisc(DisciplinaDTO dis) {
        this.disciplina = dis;
    }

    public void setStud(StudentDTO stud) {
        this.student = stud;
    }

    public int getIdLeg() {
        return idLeg;
    }

    public void setIdLeg(int idLeg) {
        this.idLeg = idLeg;
    }
}
