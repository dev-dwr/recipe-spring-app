package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.CategoryCommand;
import com.dwr.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    CategoryCommandToCategory categoryCommandToCategory;

    private static final Long ID_VALUE = 1L;

    private static final String DESCRIPTION = "description";

    @BeforeEach
    void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    void nullObjTest(){
        assertNull(categoryCommandToCategory.convert(null));
    }
    @Test
    void emptyObjTest(){
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = categoryCommandToCategory.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());

    }
}