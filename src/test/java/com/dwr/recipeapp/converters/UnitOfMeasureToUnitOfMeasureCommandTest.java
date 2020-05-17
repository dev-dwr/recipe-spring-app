package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.UnitOfMeasureCommand;
import com.dwr.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    UnitOfMeasureToUnitOfMeasureCommand converter;
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "DESCRIPTION";

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure uom= new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        //then
        assertNotNull(uomCommand);
        assertEquals(ID_VALUE, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());

    }

    @Test
    void nullObjTest() {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjTest() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }
}