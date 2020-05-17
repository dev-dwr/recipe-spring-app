package com.dwr.recipeapp.converters;


import com.dwr.recipeapp.commands.UnitOfMeasureCommand;
import com.dwr.recipeapp.domain.UnitOfMeasure;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand != null){
            final UnitOfMeasure uom = new UnitOfMeasure();
            uom.setId(unitOfMeasureCommand.getId());
            uom.setDescription(unitOfMeasureCommand.getDescription());

            return uom;
        }
        return null;
    }
}
