package com.ativade.crud.controller;

import com.ativade.crud.model.Personagem;
import com.ativade.crud.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/personagens")
public class PersonagemController {

    private final PersonagemService service;

    @GetMapping
    public ResponseEntity<List<Personagem>> find() {
        return ResponseEntity.ok(service.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final Personagem personagem) throws Exception {
        service.create(personagem);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable final UUID id, @RequestBody final Personagem personagem) throws Exception {
        service.put(id, personagem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> find(@PathVariable final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
