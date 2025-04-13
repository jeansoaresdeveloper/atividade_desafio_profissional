package com.ativade.crud.model;

import com.ativade.crud.enums.Classe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome"})
public class Personagem {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String nome;

    @Setter
    private String nomeAventureiro;

    @Enumerated(value = EnumType.STRING)
    private Classe classe;

    private Integer level;

    private Integer forca;

    private Integer defesa;

    @JsonIgnore
    private Integer forcaInicial = 0;

    @JsonIgnore
    private Integer defesaInicial = 0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personagem_id")
    private List<ItemMagico> itens = new ArrayList<>();

    public void buildTotalForcaDefesa(final Integer forca, final Integer defesa) {
        this.forca = forca;
        this.defesa = defesa;
    }

}
