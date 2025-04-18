package com.ativade.crud.dto;

import com.ativade.crud.enums.TipoItem;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ItemMagicoDTO(

        @NotBlank(message = "Nome obrigatório")
        @Length(max = 100, message = "Máximo de 100 caracteres")
        String nome,

        @NotNull(message = "Tipo Item não pode ser nulo")
        TipoItem tipoItem,

        @NotNull(message = "Força não pode ser nulo")
        @Min(value = 0, message = "Força Mínima 0")
        @Max(value = 10, message = "Força Máxima 10")
        Integer forca,

        @NotNull(message = "Defesa não pode ser nulo")
        @Min(value = 0, message = "Defesa Mínima 0")
        @Max(value = 10, message = "Defesa Máxima 10")
        Integer defesa

) {
}
