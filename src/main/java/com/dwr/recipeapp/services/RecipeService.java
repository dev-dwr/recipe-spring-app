package com.dwr.recipeapp.services;

import com.dwr.recipeapp.commands.RecipeCommand;
import com.dwr.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
