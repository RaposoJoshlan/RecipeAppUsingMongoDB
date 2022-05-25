package com.recipe.converters;

import com.recipe.commands.UnitOfMeasureCommand;
import com.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long id = 1L;
    public static final String description = "Description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(id);
        command.setDescription(description);

        UnitOfMeasure unitOfMeasure = converter.convert(command);
        assertNotNull(unitOfMeasure);
        assertEquals(id, unitOfMeasure.getId());
        assertEquals(description, unitOfMeasure.getDescription());
    }
}