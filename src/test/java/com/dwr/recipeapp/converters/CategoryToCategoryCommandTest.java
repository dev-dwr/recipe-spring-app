package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.CategoryCommand;
import com.dwr.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    private static final Long ID_VALUE = 1L;

    private static final String DESCRIPTION = "description";

    CategoryToCategoryCommand categoryToCategoryCommand;

    @BeforeEach
    void setUp() {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    void nullObjTest() {
        assertNull(categoryToCategoryCommand.convert(null));
    }
    @Test
    void emptyObjTest(){
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }
    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setDescription(DESCRIPTION);
        category.setId(ID_VALUE);

        //when
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}