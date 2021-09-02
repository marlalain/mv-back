package com.pauloelienay.mv.controller.v1;

import com.pauloelienay.mv.domain.Profissional;
import com.pauloelienay.mv.service.ProfissionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/profissionais")
public class ProfissionalController {
    private final ProfissionalService service;

    @GetMapping
    public ResponseEntity<Page<EntityModel<Profissional>>> getPageableProfissionais
            (@PageableDefault(size = 10, sort = {"id"})Pageable pageable) {
        return new ResponseEntity<>(service.getPageableProfissionais(pageable).map(this::addLinks), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Profissional>> getProfissionalById(@PathVariable long id) {
        return new ResponseEntity<>(addLinksById(id), HttpStatus.OK);
    }

    EntityModel<Profissional> addLinks(Profissional profissional) {
        EntityModel<Profissional> model = EntityModel.of(profissional);
        model.add(linkTo(methodOn(this.getClass()).getProfissionalById(profissional.getId())).withSelfRel());
        model.add(linkTo(methodOn(this.getClass()).getPageableProfissionais(null)).withRel("profissionais"));
        return model;
    }

    EntityModel<Profissional> addLinksById(long id) {
        return addLinks(service.getProfissionalById(id));
    }
}
