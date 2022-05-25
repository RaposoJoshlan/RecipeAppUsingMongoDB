package com.recipe.converters;

import com.recipe.commands.CategoryCommand;
import com.recipe.commands.IngredientCommand;
import com.recipe.commands.NotesCommand;
import com.recipe.commands.RecipeCommand;
import com.recipe.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setDescription(description);
        recipe.setCookTime(cookTime);
        recipe.setPrepTime(prepTime);
        recipe.setDifficulty(difficulty);
        recipe.setDirections(directions);
        recipe.setServings(servings);
        recipe.setSource(source);
        recipe.setUrl(url);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);

        Category category2 = new Category();
        category1.setId(CAT_ID_2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand command = converter.convert(recipe);
        assertNotNull(command);
        assertEquals(id, command.getId());
        assertEquals(cookTime, command.getCookTime());
        assertEquals(prepTime, command.getPrepTime());
        assertEquals(description, command.getDescription());
        assertEquals(difficulty, command.getDifficulty());
        assertEquals(directions, command.getDirections());
        assertEquals(servings, command.getServings());
        assertEquals(source, command.getSource());
        assertEquals(url, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}