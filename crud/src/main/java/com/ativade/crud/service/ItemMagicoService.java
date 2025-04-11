package com.ativade.crud.service;

import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import com.ativade.crud.repository.ItemMagicoRepository;
import com.ativade.crud.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository repository;

    public List<ItemMagico> find() {
        return repository.findAll();
    }

    public ItemMagico findById(final UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ItemMagico not found."));
    }
}
