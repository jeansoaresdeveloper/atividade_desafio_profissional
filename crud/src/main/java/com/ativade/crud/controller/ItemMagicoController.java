package com.ativade.crud.controller;

import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import com.ativade.crud.service.ItemMagicoService;
import com.ativade.crud.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/personagens")
public class ItemMagicoController {

    private final ItemMagicoService service;

    @GetMapping
    public ResponseEntity<List<ItemMagico>> find() {
        return ResponseEntity.ok(service.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
