package com.recipe.services;

import com.recipe.commands.IngredientCommand;

/**
 * Created on 13/04/2022
 */

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long recipeId, Long ingredientId);
}
