package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.IngredientCommand;
import com.dwr.recipeapp.domain.Ingredient;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand != null){
            final Ingredient ingredient = new Ingredient();
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setId(ingredientCommand.getId());
            ingredient.setUom(uomConverter.convert(ingredientCommand.getUom()));
            return ingredient;
        }
        return null;
    }
}
