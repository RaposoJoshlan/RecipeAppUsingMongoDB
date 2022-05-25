package com.recipe.controllers;

import com.recipe.commands.IngredientCommand;
import com.recipe.commands.RecipeCommand;
import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.services.IngredientService;
import com.recipe.services.RecipeService;
import com.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 13/04/2022
 */

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping({"recipe/{id}/ingredients"})
    public String listIngredients(@PathVariable String id, Model model) {
        log.debug("Getting ingredient list for recipe id: " + id);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/ingredient/list";
    }

    @GetMapping({"recipe/{recipeId}/ingredient/{ingredientId}/show"})
    public String showByRecipeIdAndIngredientId(@PathVariable String recipeId, @PathVariable String ingredientId,
                                                Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping({"recipe/{recipeId}/ingredient/new"})
    public String newRecipe(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientForm";
    }

    @GetMapping({"recipe/{recipeId}/ingredient/{ingredientId}/update"})
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping({"recipe/{recipeId}/ingredient/{ingredientId}/delete"})
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
        log.debug("Deleting Ingredient: " + ingredientId);

        ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
