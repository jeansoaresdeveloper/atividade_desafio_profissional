package com.ativade.crud.adapter;

import com.ativade.crud.dto.ItemMagicoDTO;
import com.ativade.crud.dto.PersonagemDTO;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemMagicoAdapter implements Adapter<ItemMagico, ItemMagicoDTO> {


    @Override
    public ItemMagico toEntity(ItemMagicoDTO DTO) {

        return ItemMagico.builder()
                .nome(DTO.nome())
                .tipoItem(DTO.tipoItem())
                .forca(DTO.forca())
                .defesa(DTO.defesa())
                .build();
    }
}
