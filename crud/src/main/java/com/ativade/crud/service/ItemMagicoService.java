package com.ativade.crud.service;

import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import com.ativade.crud.repository.ItemMagicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository repository;

    private final PersonagemService personagemService;

    public List<ItemMagico> find() {
        return repository.findAll();
    }

    public ItemMagico findById(final UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ItemMagico not found."));
    }

    public ItemMagico createByPersonagem(final UUID idpersonagem, final ItemMagico itemMagico) throws Exception {

        final Personagem personagem = personagemService.findById(idpersonagem);
        personagem.getItens().add(itemMagico);

        personagemService.save(personagem);
        return itemMagico;
    }

    public List<ItemMagico> findByPersonagem(final UUID idpersonagem) {
        return repository.findByPersonagemId(idpersonagem);
    }

    public void delete(final UUID id) {

        final ItemMagico item = findById(id);
        repository.delete(item);
        updateDefesaForcaPersonagem(item.getPersonagem(), item.getForca(), item.getDefesa());
    }

    private void updateDefesaForcaPersonagem(final Personagem personagem, final int forcaItem, final int defesaItem) {

        int forca = personagem.getForca() - forcaItem;
        int defesa = personagem.getDefesa() - defesaItem;

        personagem.buildTotalForcaDefesa(forca, defesa);
        personagemService.simpleSave(personagem);
    }
}
