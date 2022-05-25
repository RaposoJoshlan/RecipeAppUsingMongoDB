package com.recipe.controllers;

import com.recipe.commands.RecipeCommand;
import com.recipe.exceptions.NotFoundException;
import com.recipe.services.RecipeService;
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
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeForm";

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipe/{id}/show"})
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "/recipe/show";
    }

    @GetMapping({"recipe/new"})
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping({"recipe"})
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping({"recipe/{id}/delete"})
    public String deleteRecipeById(@PathVariable String id) {
        log.debug("Deleting Recipe: " + id);

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
