package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.RecipeCommand;
import com.dwr.recipeapp.domain.Recipe;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }


    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand != null) {
            final Recipe recipe = new Recipe();
            recipe.setUrl(recipeCommand.getUrl());
            recipe.setId(recipeCommand.getId());
            recipe.setCookTime(recipeCommand.getCookTime());
            recipe.setDescription(recipeCommand.getDescription());
            recipe.setDifficulty(recipeCommand.getDifficulty());
            recipe.setDirections(recipeCommand.getDirections());
            recipe.setPrepTime(recipeCommand.getPrepTime());
            recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));
            recipe.setServings(recipeCommand.getServings());
            recipe.setSource(recipeCommand.getSource());

            if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
                recipeCommand.getCategories()
                        .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
            }
            if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
                recipeCommand.getIngredients()
                        .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
            }
            return recipe;
        }
        return null;
    }
}
