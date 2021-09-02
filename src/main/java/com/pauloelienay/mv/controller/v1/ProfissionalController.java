package com.pauloelienay.mv.controller.v1;

import com.pauloelienay.mv.domain.Profissional;
import com.pauloelienay.mv.service.ProfissionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/profissionais")
public class ProfissionalController {
    private final ProfissionalService service;

    @GetMapping
    public ResponseEntity<Page<EntityModel<Profissional>>> getPageableProfissionais
            (@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return new ResponseEntity<>(service.getPageableProfissionais(pageable).map(this::addLinks), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Profissional>> getProfissionalById(@PathVariable long id) {
        return new ResponseEntity<>(addLinksById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profissional> createNewProfissional(@RequestBody Profissional profissional) {
        // especificacao do metodo POST https://httpwg.org/specs/rfc7231.html#POST
        // retorna a localizacao da entidade no header, mas nao retorna a entidade em si
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(ProfissionalController.class).slash(service.create(profissional).getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfissinalById(@PathVariable long id) {
        service.deleteProfissionalById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> updateProfissionalById
            (@PathVariable long id, @RequestBody Profissional profissional) {
        // retorna `204` em caso de sucesso
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/PUT#responses
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(ProfissionalController.class)
                .slash(service.update(id, profissional).getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    EntityModel<Profissional> addLinks(Profissional profissional) {
        EntityModel<Profissional> model = EntityModel.of(profissional);
        model.add(linkTo(methodOn(this.getClass()).getProfissionalById(profissional.getId()))
                .withSelfRel().withType("GET"));
        model.add(linkTo(methodOn(this.getClass()).deleteProfissinalById(profissional.getId()))
                .withRel("delete").withType("DELETE"));
        model.add(linkTo(methodOn(this.getClass()).updateProfissionalById(profissional.getId(), null))
                .withRel("update").withType("PUT"));
        model.add(linkTo(methodOn(this.getClass()).getPageableProfissionais(null))
                .withRel("profissionais").withType("GET"));
        return model;
    }

    EntityModel<Profissional> addLinksById(long id) {
        return addLinks(service.getProfissionalById(id));
    }
}
