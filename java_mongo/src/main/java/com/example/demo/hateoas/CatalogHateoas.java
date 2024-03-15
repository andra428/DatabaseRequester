package com.example.demo.hateoas;

import com.example.demo.models.disciplina;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.demo.controller.catalogController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CatalogHateoas implements RepresentationModelAssembler<disciplina, EntityModel<disciplina>> {
    @Override
    public EntityModel<disciplina> toModel(disciplina d) {

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();

        Link selfLink = Link.of(builder.build().toUriString(), "selfRel");

        EntityModel<disciplina> cm = EntityModel.of(d,
                selfLink,
                linkTo(methodOn(catalogController.class).getAllCatalogs()).withRel("parent"),
                linkTo(methodOn(catalogController.class).getById(d.getId().toString())).withRel("findByID"),
                linkTo(methodOn(catalogController.class).addDisciplina(null)).withRel("Add")

        );
        return cm;
    }
}