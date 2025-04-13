package com.ativade.crud.service.validators;

import com.ativade.crud.enums.TipoItem;
import com.ativade.crud.exceptions.PersonagemException;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class PersonagemValidator {

    private static final Integer QUANTIDADE_AMULETOS_PERMITIDOS = 1;
    private static final Integer FORCA_DEFESA_PERMITIDA = 10;
    private static final Integer FORCA_MINIMA = 0;
    private static final Integer DEFESA_MINIMA = 0;

    public void validate(final Personagem personagem) throws PersonagemException {
        forcaEDefesa(personagem);
        tipoArma(personagem);
        tipoArmadura(personagem);
        itemForcaEDefesa(personagem);
        validateTipoAmuleto(personagem);
    }

    private void forcaEDefesa(final Personagem personagem) throws PersonagemException {

        final int forcaComDefesa = personagem.getForcaInicial() + personagem.getDefesaInicial();

        if (forcaComDefesa > FORCA_DEFESA_PERMITIDA)
            throw new PersonagemException("Soma da força mais defesa não pode ser maior que 10.");

    }

    private void tipoArma(final Personagem personagem) throws PersonagemException {

        final Optional<ItemMagico> invalidItem = filterItem(item -> TipoItem.ARMA.equals(item.getTipoItem()) && !DEFESA_MINIMA.equals(item.getDefesa()), personagem);

        if (invalidItem.isPresent())
            throw new PersonagemException("Não pode existir item do tipo arma com defesa maior que zero.");
    }

    private void tipoArmadura(final Personagem personagem) throws PersonagemException {

        final Optional<ItemMagico> invalidItem = filterItem(item -> TipoItem.ARMADURA.equals(item.getTipoItem()) && !FORCA_MINIMA.equals(item.getForca()), personagem);

        if (invalidItem.isPresent())
            throw new PersonagemException("Não pode existir item do tipo armadura com força maior que zero.");
    }

    private void itemForcaEDefesa(final Personagem personagem) throws PersonagemException {

        final Optional<ItemMagico> invalidItem = filterItem(item -> FORCA_MINIMA.equals(item.getForca()) && DEFESA_MINIMA.equals(item.getDefesa()), personagem);

        if (invalidItem.isPresent())
            throw new PersonagemException("Não pode existir itens com zero de defesa e zero de força.");
    }

    private void validateTipoAmuleto(final Personagem personagem) throws PersonagemException {

        final long quantidadeAmuletos = personagem.getItens()
                .stream()
                .filter(item -> TipoItem.AMULETO.equals(item.getTipoItem()))
                .count();

        if (quantidadeAmuletos > QUANTIDADE_AMULETOS_PERMITIDOS)
            throw new PersonagemException("Só um item de amuleto é permitido.");

    }

    private static Optional<ItemMagico> filterItem(final Predicate<ItemMagico> filter, final Personagem personagem) {
        return personagem.getItens()
                .stream()
                .filter(filter)
                .findAny();
    }

}
