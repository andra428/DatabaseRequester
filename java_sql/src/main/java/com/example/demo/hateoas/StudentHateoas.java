package com.example.demo.hateoas;

import com.example.demo.controller.StudentController;
import com.example.demo.dto.StudentDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class StudentHateoas implements RepresentationModelAssembler<StudentDTO, EntityModel<StudentDTO>> {
    @Override
    public EntityModel<StudentDTO> toModel(StudentDTO s) {

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        Link selfLink = Link.of(builder.build().toUriString(), "selfRel");

        EntityModel<StudentDTO> sm = EntityModel.of(s,
                selfLink,
                linkTo(methodOn(StudentController.class).getAll()).withRel("parent"),
                linkTo(methodOn(StudentController.class).insertStudent(null)).withRel("insertStudent"),
                linkTo(methodOn(StudentController.class).updateStudent(s.getId(), null)).withRel("updateStudent"),
                linkTo(methodOn(StudentController.class).updateStudentPartial(s.getId(), null)).withRel("updateStudentPartial"),
                linkTo(methodOn(StudentController.class).getLecturesForStudent(s.getId())).withRel("LecturesForStudent"),
                linkTo(methodOn(StudentController.class).getStudentByLastName(s.getNume())).withRel("findByLastName"),
                linkTo(methodOn(StudentController.class).getStudentByFirstName(s.getPrenume())).withRel("findByFirstName"),
                linkTo(methodOn(StudentController.class).getStudentsByGroup(s.getGrupa())).withRel("findByGroup"),
                linkTo(methodOn(StudentController.class).getStudentsByYear(s.getAn())).withRel("findByYear")
        );
        return sm;
    }
}