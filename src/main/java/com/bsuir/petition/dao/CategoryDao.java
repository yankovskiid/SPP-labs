package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category getCategory(long id);
    Category getCategory(String name);
    List<Category> getCategories();
    void updateCategory(Category category);
    void deleteCategory(Category category);
    void addCategory(Category category);
}
