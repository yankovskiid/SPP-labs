package com.bsuir.petition.bean.dto.petition;

import com.bsuir.petition.bean.dto.category.CategoryDTO;
import com.bsuir.petition.bean.dto.category.CategoryListDTO;

import java.util.ArrayList;
import java.util.Date;

public class ShortPetitionDTO {
    private long id;
    protected String name;
    protected String description;
    protected String status;
    protected Date expiryDate;
    protected int numberNecessaryVotes;
    protected int numberVotes;
    protected int numberComments;

    private ArrayList<CategoryDTO> categories = new ArrayList<>(0);

    public ArrayList<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryDTO> categories) {
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setNumberComments(int numberComments) {
        this.numberComments = numberComments;
    }

    public int getNumberComments() {
        return this.numberComments;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }
}
