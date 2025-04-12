package com.ativade.crud.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record UpdateNomeDTO(

        @NotBlank(message = "Nome aventureiro não pode ser vazio")
        @Max(value = 100, message = "Nome de aventureiro não pode passar de 100 caracteres")
        String nomeAventureiro
) {
}
