package com.example.demo.hateoas;

import com.example.demo.controller.DisciplinaController;
import com.example.demo.dto.DisciplinaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class DisciplinaHateoas implements RepresentationModelAssembler<DisciplinaDTO, EntityModel<DisciplinaDTO>> {
    @Override
    public EntityModel<DisciplinaDTO> toModel(DisciplinaDTO d) {

       ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();

        Link selfLink = Link.of(builder.build().toUriString(), "selfRel");

        EntityModel<DisciplinaDTO> dm = EntityModel.of(d,
                selfLink,
                linkTo(methodOn(DisciplinaController.class).getAllDiscipline()).withRel("parent"),
                linkTo(methodOn(DisciplinaController.class).insertDisciplina(null)).withRel("insertLecture"),
                linkTo(methodOn(DisciplinaController.class).updateDisciplina(d.getId(), null)).withRel("updateLecture"),
                linkTo(methodOn(DisciplinaController.class).updateDisciplinaPartial(d.getId(), null)).withRel("updateLecturePartial"),
                linkTo(methodOn(DisciplinaController.class).getLectureByName(d.getNume())).withRel("findByName"),
                linkTo(methodOn(DisciplinaController.class).getLecturesByYear(d.getAn())).withRel("findByYear"),
                linkTo(methodOn(DisciplinaController.class).getLecturesByCredits(d.getNrCredite())).withRel("findByCredits")
        );
        return dm;
    }
}


/*
@Component
public class DisciplinaHateoas implements RepresentationModelAssembler<DisciplinaDTO, EntityModel<DisciplinaDTO>> {
    @Override
    public EntityModel<DisciplinaDTO> toModel(DisciplinaDTO d){
        EntityModel<DisciplinaDTO> dm=EntityModel.of(d,
                linkTo(methodOn(DisciplinaController.class).getDisciplinaByID(d.getId())).withSelfRel(),
                linkTo(methodOn(DisciplinaController.class).getAllDiscipline()).withRel("parent"),
                linkTo(methodOn(DisciplinaController.class).insertDisciplina(null)).withRel("insertLecture"),
                linkTo(methodOn(DisciplinaController.class).updateDisciplina(d.getId(),null)).withRel("updateLecture"),
                linkTo(methodOn(DisciplinaController.class).updateDisciplinaPartial(d.getId(),null)).withRel("updateLecturePartial"),
                linkTo(methodOn(DisciplinaController.class).getLectureByName(d.getNume())).withRel("findByName"),
                linkTo(methodOn(DisciplinaController.class).getLecturesByYear(d.getAn())).withRel("findByYear"),
                linkTo(methodOn(DisciplinaController.class).getLecturesByCredits(d.getNrCredite())).withRel("findByCredits")
        );
        return dm;
    }
}

 */
