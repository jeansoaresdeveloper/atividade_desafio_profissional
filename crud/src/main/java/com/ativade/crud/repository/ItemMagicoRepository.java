package com.ativade.crud.repository;

import com.ativade.crud.enums.TipoItem;
import com.ativade.crud.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagico, UUID> {

    List<ItemMagico> findByPersonagemId(final UUID personagemId);

    ItemMagico findByPersonagemIdAndTipoItem(final UUID personagemId, final TipoItem tipoItem);

}
