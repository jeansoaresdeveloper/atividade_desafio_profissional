package com.ativade.crud.model;

import com.ativade.crud.enums.Classe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String nome;

    private String nomeAventureiro;

    private Classe classe;

    private Integer level;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_id")
    private final List<ItemMagico> itens = new ArrayList<>();

    private Integer forca;

    private Integer defesa;
}
