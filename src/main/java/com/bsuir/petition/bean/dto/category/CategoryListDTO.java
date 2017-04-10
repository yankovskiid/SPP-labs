package com.bsuir.petition.bean.dto.category;

import java.util.ArrayList;

public class CategoryListDTO {
    private ArrayList<CategoryDTO> categories = new ArrayList<>(0);

    public ArrayList<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryDTO> categories) {
        this.categories = categories;
    }
}
