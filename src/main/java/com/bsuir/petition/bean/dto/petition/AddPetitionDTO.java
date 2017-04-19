package com.bsuir.petition.bean.dto.petition;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;

import java.util.Date;

public class AddPetitionDTO {

    private String name;
    private String description;
    private Date expiryDate;
    private int numberNecessaryVotes;
    private CategoryListDTO categories;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

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

    public int getNumberNecessaryVotes() {
        return numberNecessaryVotes;
    }

    public void setNumberNecessaryVotes(int numberNecessaryVotes) {
        this.numberNecessaryVotes = numberNecessaryVotes;
    }

    public CategoryListDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoryListDTO categories) {
        this.categories = categories;
    }
}
