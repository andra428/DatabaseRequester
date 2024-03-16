package com.example.demo.repository;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDTO,String>{
        StudentDTO findByIdStud(int id_stud);
        ArrayList<StudentDTO> findAll();

        Optional<StudentDTO> findFirstByOrderByIdStudDesc();

        StudentDTO findByNume(String nume);

        StudentDTO findByPrenume(String prenume);

        ArrayList<StudentDTO> findByAn(int an);

        ArrayList<StudentDTO> findByGrupa(String grupa);
}
