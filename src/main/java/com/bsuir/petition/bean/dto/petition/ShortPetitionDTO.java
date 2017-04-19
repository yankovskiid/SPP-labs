package com.bsuir.petition.bean.dto.petition;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;

public class ShortPetitionDTO {
    protected String name;
    protected String description;
    protected String status;
    protected int numberNecessaryVotes;
    protected int numberVotes;
    protected CategoryListDTO categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberNecessaryVotes() {
        return numberNecessaryVotes;
    }

    public void setNumberNecessaryVotes(int numberNecessaryVotes) {
        this.numberNecessaryVotes = numberNecessaryVotes;
    }

    public int getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(int numberVotes) {
        this.numberVotes = numberVotes;
    }

    public CategoryListDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoryListDTO categories) {
        this.categories = categories;
    }
}
