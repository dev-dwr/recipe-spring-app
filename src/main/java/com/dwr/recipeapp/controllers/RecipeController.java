package com.dwr.recipeapp.controllers;


import com.dwr.recipeapp.commands.RecipeCommand;
import com.dwr.recipeapp.exceptions.NotFoundException;
import com.dwr.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    private static final String RECIPE_RECIPE_FORM_URL = "recipe/recipeform";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/show"; //view name
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_RECIPE_FORM_URL;
    }


    @GetMapping("/recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return RECIPE_RECIPE_FORM_URL;
    }


    //When using the @ModelAttribute annotation as a method parameter, it binds the form data with a POJO bean.
    // It has a value attribute which acts as a name of the model attribute to bind.
    @PostMapping("/recipe") //it's going to bind recipeCommand to the Model so it's get returned on this. Binding result it's result of validation
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult result){

        if(result.hasErrors()){
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPE_FORM_URL;
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + savedCommand.getId();

    }


    @GetMapping("/recipe/delete/{id}")
    public String deleteById(@PathVariable String id){
        log.debug("deleting id " + id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

}
