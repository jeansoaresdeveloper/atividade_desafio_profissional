package com.ativade.crud.service;

import com.ativade.crud.model.Personagem;
import com.ativade.crud.repository.PersonagemRepository;
import com.ativade.crud.service.validators.PersonagemValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository repository;

    private final PersonagemValidator validator;

    public List<Personagem> find() {
        return repository.findAll();
    }

    public Personagem findById(final UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem not found."));
    }

    public Personagem create(final Personagem personagem) throws Exception {
        validator.validate(personagem);
        return repository.save(personagem);
    }

    public void put(final UUID id, final Personagem personagem) throws Exception {
        findById(id);
        validator.validate(personagem);
        personagem.setId(id);
        repository.save(personagem);
    }

    public void delete(final UUID id) {
        findById(id);
        repository.deleteById(id);
    }
}
