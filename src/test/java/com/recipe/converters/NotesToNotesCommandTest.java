package com.recipe.converters;

import com.recipe.commands.NotesCommand;
import com.recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static final  Long id = 1L;
    public static final String recipeNotes = "Notes";

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(id);
        notes.setRecipeNotes(recipeNotes);

        NotesCommand command = converter.convert(notes);
        assertNotNull(notes);
        assertEquals(id, command.getId());
        assertEquals(recipeNotes, command.getRecipeNotes());
    }
}