package com.ativade.crud.dto;

import com.ativade.crud.enums.Classe;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record PersonagemDTO(

        @NotBlank(message = "Nome obrigatório")
        @Length(max = 100, message = "Máximo de 100 caracteres")
        String nome,

        @NotBlank(message = "Nome Aventureiro obrigatório")
        @Length(max = 100, message = "Máximo de 100 caracteres")
        String nomeAventureiro,

        @NotNull
        Classe classe,

        @NotNull
        @Min(value = 0, message = "Minimo 0")
        Integer level,

        @NotNull(message = "Força não pode ser nulo")
        @Min(value = 0, message = "Minimo 0")
        @Max(value = 10, message = "Máximo 10")
        Integer forca,

        @NotNull(message = "Defesa não pode ser nulo")
        @Min(value = 0, message = "Minimo 0")
        @Max(value = 10, message = "Minimo 10")
        Integer defesa,

        @Valid
        List<ItemMagicoDTO> itens
) {
}
