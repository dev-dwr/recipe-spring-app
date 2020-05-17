package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.IngredientCommand;
import com.dwr.recipeapp.commands.UnitOfMeasureCommand;
import com.dwr.recipeapp.domain.Ingredient;
import com.dwr.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long ID_VALUE = 1L;
    private static final Long UOM_ID = 2L;

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }
    @Test
    void nullObjTest() {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjTest() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setId(ID_VALUE);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);

        ingredient.setUom(unitOfMeasure);


        //when
        IngredientCommand ingredientCommandObj =  converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommandObj);
        assertEquals(DESCRIPTION, ingredientCommandObj.getDescription());
        assertEquals(AMOUNT, ingredientCommandObj.getAmount());
        assertEquals(ID_VALUE, ingredientCommandObj.getId());
        assertEquals(UOM_ID, ingredientCommandObj.getUnitOfMeasure().getId());

    }

    @Test
    void convertWithNullUOM(){
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setId(ID_VALUE);

        //when
        IngredientCommand ingredientCommandObj =  converter.convert(ingredient);

        //then
        assertNull(ingredientCommandObj.getUnitOfMeasure());
        assertEquals(DESCRIPTION, ingredientCommandObj.getDescription());
        assertEquals(AMOUNT, ingredientCommandObj.getAmount());
        assertEquals(ID_VALUE, ingredientCommandObj.getId());
        assertNotNull(ingredientCommandObj);


    }
}