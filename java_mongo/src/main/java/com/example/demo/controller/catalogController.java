package com.example.demo.controller;

import com.example.demo.hateoas.CatalogHateoas;
import com.example.demo.models.disciplina;
import com.example.demo.repository.CatalogRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gradebook")
public class catalogController {

    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    CatalogHateoas catHateoas;

    @GetMapping("/catalog")
    public ResponseEntity<?> getAllCatalogs() {
        List<disciplina> catalogs = catalogRepository.findAll();

        if (catalogs.isEmpty()) {
            String e = "Nu exista discipline in baza de date";
            return ResponseEntity.status(HttpStatus.OK).body(e);
        } else {
            CollectionModel<EntityModel<disciplina>> d = catHateoas.toCollectionModel(catalogs);
            return ResponseEntity.ok(d);
        }
    }

    @GetMapping("/catalog/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Optional<disciplina> od = catalogRepository.findById(id);

        if (od.isPresent()) {
            disciplina fd = od.get();
            EntityModel<disciplina> d = catHateoas.toModel(fd);
            return ResponseEntity.ok(d);
        } else {
            String e = "Nu exista disciplina cu acest ID in baza de date";
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
    }


    @PostMapping("/catalog/add")
    public ResponseEntity<?> addDisciplina(@RequestBody disciplina disc){
        disciplina d = catalogRepository.save(disc);
        EntityModel<disciplina> dd = catHateoas.toModel(d);
        return ResponseEntity.status(HttpStatus.CREATED).body(dd);
    }
}
