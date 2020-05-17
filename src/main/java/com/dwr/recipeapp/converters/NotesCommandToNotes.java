package com.dwr.recipeapp.converters;

import com.dwr.recipeapp.commands.NotesCommand;
import com.dwr.recipeapp.domain.Notes;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {


    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand!=null){
            final Notes notes = new Notes();
            notes.setId(notesCommand.getId());
            notes.setRecipeNotes(notesCommand.getRecipeNotes());
            return notes;
        }

        return null;
    }
}
