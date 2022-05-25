package com.recipe.converters;

import com.recipe.commands.CategoryCommand;
import com.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final Long id_value = 1L;
    public static final String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void convert() {
        //Given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(id_value);
        categoryCommand.setDescription(DESCRIPTION);

        //When
        Category category = converter.convert(categoryCommand);

        //Then
        assertEquals(id_value, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

    @Test
    void testNullObjects() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObjects() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }
}