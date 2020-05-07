package com.dwr.recipeapp.controllers;

import com.dwr.recipeapp.domain.Category;
import com.dwr.recipeapp.domain.UnitOfMeasure;
import com.dwr.recipeapp.repositories.CategoryRepository;
import com.dwr.recipeapp.repositories.UnitOfMeasureRepository;
import com.dwr.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class indexController {
    private final RecipeService recipeService;

    public indexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/", "","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

}
