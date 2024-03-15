package com.example.demo.controller;
import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.hateoas.DisciplinaHateoas;
import com.example.demo.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gradebook")
public class DisciplinaController {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaHateoas disciplinaHateoas;


    @GetMapping("/lectures")
    public ResponseEntity<?> getAllDiscipline(){
        ArrayList<DisciplinaDTO> discipline = disciplinaRepository.findAll();
        if(discipline.isEmpty()){
            String e = "Nu exista discipline in baza de date";
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
        CollectionModel<EntityModel<DisciplinaDTO>> disc = disciplinaHateoas.toCollectionModel(discipline);
        return ResponseEntity.ok(disc);
    }

    @GetMapping("/lectures/{lid}")
    public ResponseEntity<Object> getDisciplinaByID(@PathVariable int lid){
        DisciplinaDTO disciplina =  disciplinaRepository.findByIdDis(lid);
        if(disciplina==null){
            String e = "Disciplina cu ID-ul " + lid + " nu exista";
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
        EntityModel<DisciplinaDTO> disc = disciplinaHateoas.toModel(disciplina);
        return ResponseEntity.ok(disc);
    }

    @GetMapping("/lectures/cautaDupaNume/{nume}")
    public ResponseEntity<Object> getLectureByName(@PathVariable String nume) {

        DisciplinaDTO disc = disciplinaRepository.findByNume(nume);

        if (disc == null) {
            String em = "Nu exista disciplina cu numele: " + nume;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        EntityModel<DisciplinaDTO> d = disciplinaHateoas.toModel(disc);
        return ResponseEntity.ok(d);
    }

    @GetMapping("/lectures/cautaDupaAn/{an}")
    public ResponseEntity<?> getLecturesByYear(@PathVariable int an) {
        if(an>4){
            String em = "Nu exista anul " + an + " in facultate";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em);
        }
        ArrayList<DisciplinaDTO> discs = disciplinaRepository.findByAn(an);

        if (discs.isEmpty()) {
            String em = "Nu exista discipline in anul " + an;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        CollectionModel<EntityModel<DisciplinaDTO>> d = disciplinaHateoas.toCollectionModel(discs);
        return ResponseEntity.ok(d);
    }

    @GetMapping("/lectures/cautaDupaNrCredite/{nrCredite}")
    public ResponseEntity<?> getLecturesByCredits(@PathVariable int nrCredite) {
        if(nrCredite>5){
            String em = "Nu exista disciplina cu atat de multe credite " + nrCredite + " in facultate";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(em);
        }
        ArrayList<DisciplinaDTO> discs = disciplinaRepository.findByNrCredite(nrCredite);

        if (discs.isEmpty()) {
            String em = "Nu sunt discipline in BD cu atatea credite " + nrCredite;
            return ResponseEntity.status(HttpStatus.OK).body(em);
        }

        CollectionModel<EntityModel<DisciplinaDTO>> d = disciplinaHateoas.toCollectionModel(discs);
        return ResponseEntity.ok(d);
    }

    @PostMapping("/lectures/insertLecture")
    public ResponseEntity<EntityModel<DisciplinaDTO>> insertDisciplina(@RequestBody DisciplinaDTO d) {
        DisciplinaDTO sd = disciplinaRepository.save(d);
        EntityModel<DisciplinaDTO> disc = disciplinaHateoas.toModel(sd);
        return ResponseEntity.status(HttpStatus.CREATED).body(disc);
    }

    // inlocuieste o resursa existenta
    @PutMapping("/lectures/updateLecture/{lid}")
    public ResponseEntity<?> updateDisciplina(@PathVariable int lid, @RequestBody DisciplinaDTO ud) {
        DisciplinaDTO ed = disciplinaRepository.findByIdDis(lid);

        if (ed == null) {
            Optional<DisciplinaDTO> maxDisciplina = disciplinaRepository.findFirstByOrderByIdDisDesc();
            Integer ldi = maxDisciplina.map(DisciplinaDTO::getId).orElse(null);

            if (lid != ldi + 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id-ul disciplinei nu respecta regulile de creare");
            }
            DisciplinaDTO sd = disciplinaRepository.save(ud);
            EntityModel<DisciplinaDTO> disc = disciplinaHateoas.toModel(sd);
            return ResponseEntity.status(HttpStatus.CREATED).body(disc);
        }
        else {
            ed.setNume(ud.getNume());
            ed.setAn(ud.getAn());
            ed.setNrCredite(ud.getNrCredite());
            DisciplinaDTO sd = disciplinaRepository.save(ed);
            EntityModel<DisciplinaDTO> disc = disciplinaHateoas.toModel(sd);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(disc);
        }
        //return ResponseEntity.ok(disc);

    }

/*
    in the making
    @DeleteMapping("/lectures/deleteLectureByID/{lid}")
    public ResponseEntity<?> stergeDisciplinaByID(@PathVariable int lid){
        DisciplinaDTO d =disciplinaRepository.findByIdDis(lid);
        if(d==null){
            String em = "Disciplina cu ID-ul " + lid + " nu exista";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
        }
        //String ldi=String.valueOf(lid);
        disciplinaRepository.deleteById(lid);
        EntityModel<DisciplinaDTO> dd = disciplinaHateoas.toModel(d);

        return ResponseEntity.status(HttpStatus.OK).body(dd);
    }
 */

    // updateaza partial o inregistrare existenta
    @PatchMapping("/lectures/updateLecturePartial/{lid}")
    public ResponseEntity<?> updateDisciplinaPartial(
            @PathVariable int lid,
            @RequestBody Map<String, Object> updates
    ) {
        DisciplinaDTO ed = disciplinaRepository.findByIdDis(lid);

        if (ed == null) {
            String e = "Disciplina cu ID-ul " + lid + " nu exista";
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String f = entry.getKey();
            Object v = entry.getValue();

            switch (f) {
                case "nume":
                    ed.setNume((String) v);
                    break;
                case "an":
                    ed.setAn((Integer) v);
                    break;
                case "nrCredite":
                    ed.setNrCredite((Integer) v);
                    break;
                default:
                    break;
            }
        }

        DisciplinaDTO ud = disciplinaRepository.save(ed);
        EntityModel<DisciplinaDTO> disc = disciplinaHateoas.toModel(ud);
        return ResponseEntity.ok(disc);
    }
}