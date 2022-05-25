package com.recipe.converters;

import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long id = 1L;
    public static final String description = "Description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(id);
        unitOfMeasure.setDescription(description);

        UnitOfMeasureCommand command = converter.convert(unitOfMeasure);
        assertNotNull(command);
        assertEquals(id, command.getId());
        assertEquals(description, command.getDescription());
    }
}