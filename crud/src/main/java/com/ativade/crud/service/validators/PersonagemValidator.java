package com.ativade.crud.service.validators;

import com.ativade.crud.enums.TipoItem;
import com.ativade.crud.model.ItemMagico;
import com.ativade.crud.model.Personagem;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class PersonagemValidator {

    private static final Integer QUANTIDADE_AMULETOS_PERMITIDOS = 1;
    private static final Integer FORCA_MINIMA = 0;
    private static final Integer DEFESA_MINIMA = 0;

    public void validate(final Personagem personagem) throws Exception {
        validateSumItens(personagem);
        validateTipoArma(personagem);
        validateTipoArmadura(personagem);
        validateForcaEDefesa(personagem);
        validateTipoAmuleto(personagem);
    }

    private void validateSumItens(final Personagem personagem) throws Exception {

        final Integer sumItens = personagem.getItens()
                .stream()
                .map(a -> a.getForca() + a.getDefesa())
                .reduce(0, Integer::sum);

        if (sumItens > 10)
            throw new Exception("Soma dos itens não pode ser maior que 10.");

    }

    private void validateTipoArma(final Personagem personagem) throws Exception {

        final Optional<ItemMagico> invalidItem = filterItem(item -> TipoItem.ARMA.equals(item.getTipoItem()) && item.getDefesa() > DEFESA_MINIMA, personagem);

        if (invalidItem.isPresent())
            throw new Exception("Não pode existir item do tipo arma com defesa maior que zero.");
    }

    private void validateTipoArmadura(final Personagem personagem) throws Exception {

        final Optional<ItemMagico> invalidItem = filterItem(item -> TipoItem.ARMADURA.equals(item.getTipoItem()) && item.getForca() > FORCA_MINIMA, personagem);

        if (invalidItem.isPresent())
            throw new Exception("Não pode existir item do tipo armadura com força maior que zero.");
    }

    private void validateForcaEDefesa(final Personagem personagem) throws Exception {

        final Optional<ItemMagico> invalidItem = filterItem(item -> item.getForca() > FORCA_MINIMA && item.getDefesa() > DEFESA_MINIMA, personagem);

        if (invalidItem.isPresent())
            throw new Exception("Não pode existir itens com zero de defesa e zero de força.");
    }

    private void validateTipoAmuleto(final Personagem personagem) throws Exception {

        final long quantidadeAmuletos = personagem.getItens()
                .stream()
                .filter(item -> TipoItem.AMULETO.equals(item.getTipoItem()))
                .count();

        if (quantidadeAmuletos > QUANTIDADE_AMULETOS_PERMITIDOS)
            throw new Exception("Só um item de amuleto é permitido.");

    }

    private static Optional<ItemMagico> filterItem(final Predicate<ItemMagico> filter, final Personagem personagem) {
        return personagem.getItens()
                .stream()
                .filter(filter)
                .findAny();
    }

}
