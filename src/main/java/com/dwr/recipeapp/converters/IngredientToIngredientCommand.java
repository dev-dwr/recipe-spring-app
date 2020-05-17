package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.IngredientCommand;
import com.dwr.recipeapp.domain.Ingredient;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand uomc;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomc) {
        this.uomc = uomc;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient != null){
            final IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setUom(uomc.convert(ingredient.getUom()));
            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setDescription(ingredient.getDescription());
            return ingredientCommand;
        }
        return null;

    }
}
