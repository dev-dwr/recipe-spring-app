package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.NotesCommand;
import com.dwr.recipeapp.domain.Notes;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    NotesToNotesCommand converter;

    private static Long ID_VALUE = 1L;
    private static String RECIPE_NOTES = "NOTES";

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }
    @Test
    void nullObjTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjTest(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        //given
        Notes notes = new Notes();
        notes.setRecipeNotes(RECIPE_NOTES);
        notes.setId(ID_VALUE);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertNotNull(notesCommand);
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}