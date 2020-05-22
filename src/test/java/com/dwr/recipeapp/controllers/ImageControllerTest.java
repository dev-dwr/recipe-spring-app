package com.dwr.recipeapp.controllers;

import com.dwr.recipeapp.commands.RecipeCommand;
import com.dwr.recipeapp.services.ImageService;
import com.dwr.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {
    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(imageService, recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void getImageFormTest() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/image")).andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService,times(1)).findCommandById(anyLong());
    }

    @Test
    void handleImagePostTest() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt",
                        "text/plain","Spring Framework dwr".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/show/1"));
        verify(imageService,times(1)).saveImageFile(anyLong(), any());
    }
}