package com.ativade.crud.controller;

import com.ativade.crud.dto.PersonagemDTO;
import com.ativade.crud.dto.UpdateNomeDTO;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import com.ativade.crud.service.PersonagemService;
import com.ativade.crud.utils.URIUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/personagens")
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

    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagico> findAmuleto(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findAmuleto(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid final PersonagemDTO personagem) throws Exception {
        final Personagem personagemSaved = service.save(personagem);
        final URI uri = URIUtils.build(personagemSaved.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}/nome-aventureiro")
    public ResponseEntity<Void> put(@PathVariable final UUID id, @RequestBody @Valid final UpdateNomeDTO updateNome) throws Exception {
        service.putNomeAventureiro(id, updateNome);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put() {
        throw new UnsupportedOperationException("Endpoint desabilitado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
