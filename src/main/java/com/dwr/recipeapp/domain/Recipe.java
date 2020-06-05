package com.dwr.recipeapp.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    @ManyToMany
    @JoinTable(name ="recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name ="category_id"))
    private Set<Category> categories = new HashSet<>();
    //inverseJoinColumns allows us to "take" category_id from Category entity

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob //Lob storage large objects
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) //Recipe is going to be owner of this relationship. By cascade we mean when we delete Recipe, note will disappear as well
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") //"Go look over on the bean property named 'recipe' on the thing I have a collection of to find the configuration.
    private Set<Ingredient> ingredients = new HashSet<>();


    public void setNotes(Notes notes) {
        if(notes != null){
            this.notes = notes;
            notes.setRecipe(this);
        }

    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return  this;
    }

}
