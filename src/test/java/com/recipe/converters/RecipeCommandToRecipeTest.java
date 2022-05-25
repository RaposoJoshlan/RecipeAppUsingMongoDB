package com.recipe.converters;

import com.recipe.commands.CategoryCommand;
import com.recipe.commands.IngredientCommand;
import com.recipe.commands.NotesCommand;
import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Difficulty;
import com.recipe.domain.Notes;
import com.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final  Long id = 1L;
    public static final  String description = "Description";
    public static final  Integer prepTime = 10;
    public static final Integer cookTime = 10;
    public static final Integer servings = 4;
    public static final String source = "Source";
    public static final String url = "url";
    public static final String directions = "Follow";
    public static final Difficulty difficulty = Difficulty.EASY;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand command = new RecipeCommand();
        command.setId(id);
        command.setDescription(description);
        command.setCookTime(cookTime);
        command.setPrepTime(prepTime);
        command.setDifficulty(difficulty);
        command.setDirections(directions);
        command.setServings(servings);
        command.setSource(source);
        command.setUrl(url);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        command.setNotes(notes);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category1.setId(CAT_ID_2);

        command.getCategories().add(category1);
        command.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        command.getIngredients().add(ingredient);
        command.getIngredients().add(ingredient2);

        Recipe recipe = converter.convert(command);
        assertNotNull(recipe);
        assertEquals(id, recipe.getId());
        assertEquals(cookTime, recipe.getCookTime());
        assertEquals(prepTime, recipe.getPrepTime());
        assertEquals(description, recipe.getDescription());
        assertEquals(difficulty, recipe.getDifficulty());
        assertEquals(directions, recipe.getDirections());
        assertEquals(servings, recipe.getServings());
        assertEquals(source, recipe.getSource());
        assertEquals(url, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}