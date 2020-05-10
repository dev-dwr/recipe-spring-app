package com.dwr.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id will be generated from DB,we get it from db
    private Long id;

    @ManyToOne
    private Recipe recipe;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER) //its gonna be retrived every time in db
    private UnitOfMeasure uom;

    public  Ingredient(){

    }
    public Ingredient( String description, BigDecimal amount, UnitOfMeasure uom,Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }
    public Ingredient( String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

}
