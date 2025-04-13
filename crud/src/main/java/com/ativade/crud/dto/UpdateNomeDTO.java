package com.ativade.crud.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateNomeDTO(

        @NotBlank(message = "Nome aventureiro não pode ser vazio")
        @Length(max = 100, message = "Nome de aventureiro não pode passar de 100 caracteres")
        String nomeAventureiro
) {
}
