package com.ativade.crud.controller;

import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.service.ItemMagicoService;
import com.ativade.crud.utils.URIUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itens")
public class ItemMagicoController {

    private final ItemMagicoService service;

    @GetMapping
    public ResponseEntity<List<ItemMagico>> find() {
        return ResponseEntity.ok(service.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> findById(@PathVariable final UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/personagem")
    public ResponseEntity<List<ItemMagico>> findByPersonagem(@PathVariable final UUID id) {
        return ResponseEntity.ok(service.findByPersonagem(id));
    }

    @PostMapping("/{id}/personagem")
    public ResponseEntity<ItemMagico> create(@PathVariable final UUID id, @RequestBody final ItemMagico itemMagico) throws Exception {
        final ItemMagico itemCreated = service.createByPersonagem(id, itemMagico);
        final URI uri = URIUtils.build(itemCreated.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
