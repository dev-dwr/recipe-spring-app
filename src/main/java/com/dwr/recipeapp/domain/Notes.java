package com.dwr.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob //by Lob we are not worry about size of the string
    private String recipeNotes;

    public Notes() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Notes;
    }

}
