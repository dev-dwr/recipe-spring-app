package com.dwr.recipeapp.converters;


import com.dwr.recipeapp.commands.RecipeCommand;
import com.dwr.recipeapp.domain.Recipe;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesCommandConverter;
    private final IngredientToIngredientCommand ingredientCommandConverter;
    private final CategoryToCategoryCommand categoriesCommandConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesCommandConverter,
                                 IngredientToIngredientCommand ingredientCommandConverter,
                                 CategoryToCategoryCommand categoriesCommandConverter) {
        this.notesCommandConverter = notesCommandConverter;
        this.ingredientCommandConverter = ingredientCommandConverter;
        this.categoriesCommandConverter = categoriesCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe != null) {
            final RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(recipe.getId());
            recipeCommand.setDescription(recipe.getDescription());
            recipeCommand.setPrepTime(recipe.getPrepTime());
            recipeCommand.setCookTime(recipe.getCookTime());
            recipeCommand.setServings(recipe.getServings());
            recipeCommand.setSource(recipe.getSource());
            recipeCommand.setUrl(recipe.getUrl());
            recipeCommand.setImage(recipe.getImage());
            recipeCommand.setDirections(recipe.getDirections());
            recipeCommand.setDifficulty(recipe.getDifficulty());
            recipeCommand.setNotes(notesCommandConverter.convert(recipe.getNotes()));

            if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
                recipe.getIngredients().forEach(ingredient -> recipeCommand.getIngredients()
                        .add(ingredientCommandConverter.convert(ingredient)));
            }
            if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
                recipe.getCategories()
                        .forEach(category -> recipeCommand.getCategories()
                                .add(categoriesCommandConverter.convert(category)));
            }
            return recipeCommand;

        }
        return null;
    }
}
