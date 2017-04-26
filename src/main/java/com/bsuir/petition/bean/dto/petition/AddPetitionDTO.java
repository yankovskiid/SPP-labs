package com.bsuir.petition.bean.dto.petition;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.category.CategoryListDTO;

import java.util.ArrayList;
import java.util.Date;

public class AddPetitionDTO {

    private String name;
    private String description;
    private Date expiryDate;
    private int numberNecessaryVotes;
    private ArrayList<CategoryDTO> categories = new ArrayList<>(0);

    public ArrayList<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryDTO> categories) {
        this.categories = categories;
    }


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

}
