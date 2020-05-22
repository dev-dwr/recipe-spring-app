package com.dwr.recipeapp.services;

import com.dwr.recipeapp.domain.Recipe;
import com.dwr.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received a file");
        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();
            Byte[] byteObj = new Byte[file.getBytes().length];

            //iterator to iterate over and convert over the byteObjects
            //converting from java primitive to the wrapper obj
            int i =0;
            for(byte b : file.getBytes()){
                byteObj[i++] = b;
            }

            recipe.setImage(byteObj);

            recipeRepository.save(recipe);

        }catch (IOException e){
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
