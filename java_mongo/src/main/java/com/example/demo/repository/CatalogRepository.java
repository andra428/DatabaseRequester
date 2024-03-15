package com.example.demo.repository;

import com.example.demo.models.disciplina;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends MongoRepository<disciplina, String> {

}
