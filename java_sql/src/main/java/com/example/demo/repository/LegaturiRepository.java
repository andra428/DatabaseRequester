package com.example.demo.repository;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.LegaturiDTO;
import com.example.demo.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LegaturiRepository extends JpaRepository<LegaturiDTO,Integer> {
    LegaturiDTO findById(int id_student);


}