package com.dwr.recipeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class RecipeappApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeappApplication.class, args);
    }

}
