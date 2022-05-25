package com.recipe.services;

import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);

    void deleteById(Long id);
}
