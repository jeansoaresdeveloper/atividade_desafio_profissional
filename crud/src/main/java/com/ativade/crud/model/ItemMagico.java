package com.ativade.crud.model;

import com.ativade.crud.enums.TipoItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonIgnore
    private Personagem personagem;

    @NotNull
    private String nome;

    private TipoItem tipoItem;

    private Integer forca;

    private Integer defesa;

}
