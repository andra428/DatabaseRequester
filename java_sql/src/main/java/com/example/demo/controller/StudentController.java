package com.example.demo.controller;
import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.LegaturiDTO;
import com.example.demo.hateoas.DisciplinaHateoas;
import com.example.demo.hateoas.StudentHateoas;
import com.example.demo.dto.StudentDTO;
import com.example.demo.repository.DisciplinaRepository;
import com.example.demo.repository.LegaturiRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gradebook")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    LegaturiRepository legaturiRepository;

    @Autowired
    private StudentHateoas studentHateoas;

    @Autowired
    private DisciplinaHateoas disciplinaHateoas;

    @GetMapping("/students")
    public ResponseEntity<?> getAll() {
        ArrayList<StudentDTO> students = studentRepository.findAll();

        if (students.isEmpty()) {
            String em = "Nu exista studenti in baza de date";
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        CollectionModel<EntityModel<StudentDTO>> studs = studentHateoas.toCollectionModel(students);
        return ResponseEntity.ok(studs);
    }

    @GetMapping("/students/{sid}")
    public ResponseEntity<Object> getStudentByID(@PathVariable int sid) {
        StudentDTO student = studentRepository.findByIdStud(sid);

        if (student == null) {
            String em = "Studentul cu ID-ul " + sid + " nu exista";
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }
        EntityModel<StudentDTO> stud = studentHateoas.toModel(student);
        return ResponseEntity.ok(stud);
    }
    @GetMapping("/students/cautaDupaNume/{nume}")
    public ResponseEntity<Object> getStudentByLastName(@PathVariable String nume) {

        StudentDTO student = studentRepository.findByNume(nume);

        if (student == null) {
            String em = "Nu exista studentul/a cu numele: " + nume;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        EntityModel<StudentDTO> stud = studentHateoas.toModel(student);
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/students/cautaDupaPrenume/{prenume}")
    public ResponseEntity<Object> getStudentByFirstName(@PathVariable String prenume) {

        StudentDTO student = studentRepository.findByPrenume(prenume);

        if (student == null) {
            String em = "Nu exista studentul/a cu prenumele: " + prenume;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        EntityModel<StudentDTO> stud = studentHateoas.toModel(student);
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/students/cautaDupaAn/{an}")
    public ResponseEntity<?> getStudentsByYear(@PathVariable int an) {
        if(an>4){
            String em = "Nu exista anul " + an + " in facultate";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em);
        }
        ArrayList<StudentDTO> students = studentRepository.findByAn(an);

        if (students.isEmpty()) {
            String em = "Nu exista studenti in anul " + an;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        CollectionModel<EntityModel<StudentDTO>> stud = studentHateoas.toCollectionModel(students);
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/students/cautaDupaGrupa/{grupa}")
    public ResponseEntity<?> getStudentsByGroup(@PathVariable String grupa) {

        ArrayList<StudentDTO> students = studentRepository.findByGrupa(grupa);

        if (students.isEmpty()) {
            String em = "Nu exista studenti in grupa " + grupa;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        CollectionModel<EntityModel<StudentDTO>> stud = studentHateoas.toCollectionModel(students);
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/students/{sid}/lectures")
    public ResponseEntity<?> getLecturesForStudent(@PathVariable int sid) {
        StudentDTO student = studentRepository.findByIdStud(sid);
        List<DisciplinaDTO> discipline = disciplinaRepository.findByLegaturi_Student(student);

        if (discipline.isEmpty()) {
            String em = "Studentul cu ID-ul " + sid + " nu are discipline asociate";
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        CollectionModel<EntityModel<DisciplinaDTO>> discStud = disciplinaHateoas.toCollectionModel(discipline);
        return ResponseEntity.ok(discStud);
    }

    // creem o inregistrare noua (un student)
    @PostMapping("/students/insertStudent")
    public ResponseEntity<EntityModel<StudentDTO>> insertStudent(@RequestBody StudentDTO s) {
        StudentDTO ss = studentRepository.save(s);
        EntityModel<StudentDTO> stud = studentHateoas.toModel(ss);

        return ResponseEntity.status(HttpStatus.CREATED).body(stud);
    }
    // inlocuieste o inregistrare existenta (student existent)
    @PutMapping("/students/updateStudent/{sid}")
    public ResponseEntity<?> updateStudent(@PathVariable int sid, @RequestBody StudentDTO us) {

        StudentDTO es = studentRepository.findByIdStud(sid);

        if (es == null) {

            Optional<StudentDTO> maxDisciplina = studentRepository.findFirstByOrderByIdStudDesc();
            Integer sdi = maxDisciplina.map(StudentDTO::getId).orElse(null);

            if (sid != sdi + 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id-ul studentului nu respecta regulile de creare");
            }else{
                StudentDTO sd = studentRepository.save(us);
                EntityModel<StudentDTO> d = studentHateoas.toModel(sd);
                return ResponseEntity.status(HttpStatus.CREATED).body(d);
            }
        }
        es.setNume(us.getNume());
        es.setPrenume(us.getPrenume());
        es.setAn(us.getAn());
        es.setGrupa(us.getGrupa());

        StudentDTO ss = studentRepository.save(es);

        EntityModel<StudentDTO> stud = studentHateoas.toModel(ss);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stud);
    }
/*
    in the making
    @DeleteMapping("/students/deleteStudentByID/{sid}")
    public ResponseEntity<?> stergeStudentByID(@PathVariable int sid){
            StudentDTO s =studentRepository.findByIdStud(sid);
            if(s==null){
                String em = "Studentul cu ID-ul " + sid + " nu exista";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
            }
            //LegaturiDTO leg=legaturiRepository.findById(sid);

            String sdi=String.valueOf(sid);
            studentRepository.deleteById(sdi);
        EntityModel<StudentDTO> stud = studentHateoas.toModel(s);

        return ResponseEntity.status(HttpStatus.OK).body(stud);
    }
 */
    // updateaza partial o inregistrare existenta
    @PatchMapping("/students/updateStudentPartial/{sid}")
    public ResponseEntity<?> updateStudentPartial(
            @PathVariable int sid,
            @RequestBody Map<String, Object> updates) {
        StudentDTO es = studentRepository.findByIdStud(sid);

        if (es == null) {
            String em = "Studentul cu ID-ul " + sid + " nu exista";
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String f = entry.getKey();
            Object v = entry.getValue();

            switch (f) {
                case "nume":
                    es.setNume((String) v);
                    break;
                case "prenume":
                    es.setPrenume((String) v);
                    break;
                case "an":
                    es.setAn((Integer) v);
                    break;
                case "grupa":
                    es.setGrupa((String) v);
                    break;
                default:

                    break;
            }
        }

        StudentDTO us = studentRepository.save(es);
        EntityModel<StudentDTO> stud = studentHateoas.toModel(us);
        return ResponseEntity.ok(stud);
    }

}
