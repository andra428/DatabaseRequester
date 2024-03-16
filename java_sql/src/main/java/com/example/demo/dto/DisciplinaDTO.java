package com.example.demo.dto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="lectures")
public class DisciplinaDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dis", nullable = false)
    private int idDis;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "an_studiu", nullable = false)
    private int an;

    @Column(name = "nr_credite", nullable = false)
    private int nrCredite;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<LegaturiDTO> legaturi;

    public DisciplinaDTO(){}

    public  DisciplinaDTO(int id_dis, String nume, int an_studiu,int nr_credite){
        this.idDis=id_dis;
        this.nume=nume;
        this.an=an_studiu;
        this.nrCredite=nr_credite;
    }

    public String getNume(){
        return this.nume;
    }

    public int getAn() {
        return this.an;
    }


    public int getId() {
        return this.idDis;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public int getNrCredite() {
        return nrCredite;
    }

    public void setNrCredite(int nrCredite) {
        this.nrCredite = nrCredite;
    }

    public void setId(int id) {
        this.idDis = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

}
