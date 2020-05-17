package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.IngredientCommand;
import com.dwr.recipeapp.commands.UnitOfMeasureCommand;
import com.dwr.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    UnitOfMeasureCommandToUnitOfMeasure converter;
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "DESCRIPTION";

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }
    @Test
    void nullObjTest() {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjTest() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID_VALUE);
        uomCommand.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(uomCommand);

        //then
        assertNotNull(uom);
        assertEquals(ID_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }
}