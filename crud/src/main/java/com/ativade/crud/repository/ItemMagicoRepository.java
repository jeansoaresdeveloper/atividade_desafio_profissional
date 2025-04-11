package com.ativade.crud.repository;

import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagico, UUID> {
}
