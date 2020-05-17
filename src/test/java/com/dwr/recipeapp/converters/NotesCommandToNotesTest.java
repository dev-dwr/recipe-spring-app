package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.NotesCommand;
import com.dwr.recipeapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    NotesCommandToNotes converter;

    private static Long ID_VALUE = 1L;
    private static String RECIPE_NOTES = "NOTES";

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }
    @Test
    void nullObjTest(){
        assertNull(converter.convert(null));
    }
    @Test
    void emptyObjTest(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());

    }
}