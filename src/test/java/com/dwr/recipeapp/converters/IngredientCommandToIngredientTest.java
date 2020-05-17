package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.IngredientCommand;
import com.dwr.recipeapp.commands.UnitOfMeasureCommand;
import com.dwr.recipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    IngredientCommandToIngredient ingredient;


    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long ID_VALUE = 1L;
    private static final Long UOM_ID = 2L;

    @BeforeEach
    void setUp() {
        ingredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void nullObjTest() {
        assertNull(ingredient.convert(null));
    }

    @Test
    void emptyObjTest() {
        assertNotNull(ingredient.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);

        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);
        ingredientCommand.setId(ID_VALUE);

        //when
        Ingredient ingredient = this.ingredient.convert(ingredientCommand);

        //then
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    void convertWithNullUOMCommand(){
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setId(ID_VALUE);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();

        //when
        Ingredient ingredient = this.ingredient.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());


    }
}