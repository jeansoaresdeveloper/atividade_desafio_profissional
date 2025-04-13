package com.ativade.crud.service;

import com.ativade.crud.adapter.ItemMagicoAdapter;
import com.ativade.crud.dto.ItemMagicoDTO;
import com.ativade.crud.exceptions.NotFoundException;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import com.ativade.crud.repository.ItemMagicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository repository;
    private final ItemMagicoAdapter adapter;

    private final PersonagemService personagemService;

    public List<ItemMagico> find() {
        return repository.findAll();
    }

    public ItemMagico findById(final UUID id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Item Mágico %s não encontrado.", id)));
    }

    public ItemMagico createByPersonagem(final UUID idpersonagem, final ItemMagicoDTO itemMagicoToSave) throws Exception {

        final Personagem personagem = personagemService.findById(idpersonagem);
        final ItemMagico itemMagico = adapter.toEntity(itemMagicoToSave);

        personagem.getItens().add(itemMagico);

        final int forcaTotal = personagem.getForca() + itemMagico.getForca();
        final int defesaTotal = personagem.getDefesa() + itemMagico.getDefesa();

        personagem.buildTotalForcaDefesa(forcaTotal, defesaTotal);

        personagemService.simpleSave(personagem);
        return itemMagico;
    }

    public List<ItemMagico> findByPersonagem(final UUID idpersonagem) {
        return repository.findByPersonagemId(idpersonagem);
    }

    public void delete(final UUID id) throws NotFoundException {

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
