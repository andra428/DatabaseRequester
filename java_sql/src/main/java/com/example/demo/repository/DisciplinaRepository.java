package com.example.demo.repository;
import com.example.demo.dto.DisciplinaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaDTO,Integer> {
    DisciplinaDTO findByIdDis(int id_dis);
    ArrayList<DisciplinaDTO> findAll();
    List<DisciplinaDTO> findByLegaturi_Student(StudentDTO stud);

    Optional<DisciplinaDTO> findFirstByOrderByIdDisDesc();

    DisciplinaDTO findByNume(String nume);

    ArrayList<DisciplinaDTO> findByAn(int an);

    ArrayList<DisciplinaDTO> findByNrCredite(int nrCredite);
}
/*
public class DisciplinaRepository {

    ArrayList<DisciplinaDTO> lista=new ArrayList<>();
    int id = 1;
    public ArrayList<DisciplinaDTO> findAllDiscipline(){
        return lista;
    }
    public DisciplinaDTO getDisciplinaById(int id){

        return lista.stream()
                .filter(disciplina -> disciplina.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public void insertDisciplina(DisciplinaDTO d){
        d.setId(id++);
        lista.add(d);
    }
}
 */