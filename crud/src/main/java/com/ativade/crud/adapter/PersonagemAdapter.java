package com.ativade.crud.adapter;

import com.ativade.crud.dto.ItemMagicoDTO;
import com.ativade.crud.dto.PersonagemDTO;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonagemAdapter implements Adapter<Personagem, PersonagemDTO> {

    private final ItemMagicoAdapter itemAdapter;

    @Override
    public Personagem toEntity(PersonagemDTO DTO) {

        final List<ItemMagico> itens = adapterItens(DTO.itens());

        return Personagem.builder()
                .nome(DTO.nome())
                .nomeAventureiro(DTO.nomeAventureiro())
                .classe(DTO.classe())
                .level(DTO.level())
                .forcaInicial(DTO.forca())
                .defesaInicial(DTO.defesa())
                .itens(itens)
                .build();
    }

    private List<ItemMagico> adapterItens(List<ItemMagicoDTO> itens) {
        return itens.stream()
                .map(itemAdapter::toEntity)
                .toList();
    }
}
