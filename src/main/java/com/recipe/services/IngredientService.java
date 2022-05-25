package com.recipe.services;

import com.recipe.commands.IngredientCommand;

/**
 * Created on 13/04/2022
 */

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(String recipeId, String ingredientId);
}
