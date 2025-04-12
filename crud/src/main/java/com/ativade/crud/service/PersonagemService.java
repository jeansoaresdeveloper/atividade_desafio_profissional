package com.ativade.crud.service;

import com.ativade.crud.adapter.PersonagemAdapter;
import com.ativade.crud.dto.PersonagemDTO;
import com.ativade.crud.dto.UpdateNomeDTO;
import com.ativade.crud.enums.TipoItem;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import com.ativade.crud.repository.ItemMagicoRepository;
import com.ativade.crud.repository.PersonagemRepository;
import com.ativade.crud.service.validators.PersonagemValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository repository;
    private final ItemMagicoRepository itemMagicoRepository;

    private final PersonagemValidator validator;
    private final PersonagemAdapter adapter;

    public List<Personagem> find() {
        return repository.findAll();
    }

    public Personagem findById(final UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem not found."));
    }

    public ItemMagico findAmuleto(final UUID id) {
        return itemMagicoRepository.findByPersonagemIdAndTipoItem(id, TipoItem.AMULETO);
    }

    public Personagem save(final PersonagemDTO personagemDTO) throws Exception {
        final Personagem personagem = adapter.toEntity(personagemDTO);
        return save(personagem);
    }

    public Personagem save(final Personagem personagem) throws Exception {
        validator.validate(personagem);
        buildForcaDefesa(personagem);
        return repository.save(personagem);
    }

    public void simpleSave(final Personagem personagem) {
        repository.save(personagem);
    }

    public void putNomeAventureiro(final UUID id, final UpdateNomeDTO updateNome) {
        final Personagem personagem = findById(id);

        personagem.setNomeAventureiro(updateNome.nomeAventureiro());
        repository.save(personagem);
    }

    public void delete(final UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    private void buildForcaDefesa(final Personagem personagem) {

        int forcaTotal = personagem.getForcaInicial();
        int defesaTotal = personagem.getDefesaInicial();

        for (ItemMagico item : personagem.getItens()) {
            forcaTotal += item.getForca();
            defesaTotal += item.getDefesa();
        }

        personagem.buildTotalForcaDefesa(forcaTotal, defesaTotal);
    }
}
