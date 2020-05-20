package com.dwr.recipeapp.services;

import com.dwr.recipeapp.commands.UnitOfMeasureCommand;
import com.dwr.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.dwr.recipeapp.domain.UnitOfMeasure;
import com.dwr.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {
        //given
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        unitOfMeasureSet.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasureSet.add(unitOfMeasure2);

        when(repository.findAll()).thenReturn(unitOfMeasureSet);
        //when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();
        //then
        assertEquals(2, commands.size());
        verify(repository, times(1)).findAll();

    }
}