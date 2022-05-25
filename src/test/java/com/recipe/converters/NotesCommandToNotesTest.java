package com.recipe.converters;

import com.recipe.commands.NotesCommand;
import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final Long id = 1L;
    public static final  String recipeNotes = "Description";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(id);
        command.setRecipeNotes(recipeNotes);

        Notes notes = converter.convert(command);
        assertNotNull(notes);
        assertEquals(id, notes.getId());
        assertEquals(recipeNotes, notes.getRecipeNotes());
    }
}