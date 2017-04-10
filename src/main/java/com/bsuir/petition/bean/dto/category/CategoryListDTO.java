package com.bsuir.petition.bean.dto.category;

import com.bsuir.petition.bean.entity.Category;

import java.util.ArrayList;

public class CategoryListDTO {
    private ArrayList<Category> categories = new ArrayList<>(0);

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
