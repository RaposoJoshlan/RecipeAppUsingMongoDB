package com.recipe.converters;

import com.recipe.commands.CategoryCommand;
import com.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID_VALUE = 1L;

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void convert() {

        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = converter.convert(category);

        assertEquals(ID_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());

    }

    @Test
    void testNullObjects() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObjects() {
        assertNotNull(converter.convert(new Category()));
    }
}