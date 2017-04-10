package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Category;

public interface CategoryDao {
    Category getCategory(long id);
    Category getCategory(String name);
    void updateCategory(Category category);
    void deleteCategory(long id);
}
