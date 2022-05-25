package com.recipe.services;

import com.recipe.converters.RecipeCommandToRecipe;
import com.recipe.converters.RecipeToRecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.exceptions.NotFoundException;
import com.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);

    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();

        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned, "Null recipe received");

        verify(recipeRepository, times(1)).findById(anyLong());

        verify(recipeRepository, never()).findAll();

    }

    @Test
    void testDeleteById() {
        Long idToDelete = Long.valueOf(2L);
        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {
        // given
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        NotFoundException notFoundException = assertThrows(
                NotFoundException.class, () -> recipeService.findById(1L),
                "Expected exception to throw an error. But it didn't"
        );

        // then
        assertTrue(notFoundException.getMessage().contains("Recipe with ID value: 1 not found"));
    }
}