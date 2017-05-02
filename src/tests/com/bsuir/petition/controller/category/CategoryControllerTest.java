package com.bsuir.petition.controller.category;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.dto.category.ShortCategoryDTO;
import com.bsuir.petition.controller.category.impl.CategoryControllerImpl;
import com.bsuir.petition.service.category.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryControllerImpl categoryControllerImpl;

    @Mock
    private CategoryService categoryService;

    @Test
    public void getCategories() throws Exception {
        when(categoryService.getCategories()).thenReturn(new CategoryListDTO());

        CategoryListDTO actualDto = categoryControllerImpl.getCategories();

        Assert.assertNotNull(actualDto);
        verify(categoryService, times(1)).getCategories();
    }

    @Test
    public void deleteCategory_categoryId() throws Exception {

        categoryControllerImpl.deleteCategory(0);

        verify(categoryService, times(1)).deleteCategory(anyLong());
    }

    @Test
    public void updateCategory_dto_id() throws Exception {
        categoryControllerImpl.updateCategory(new ShortCategoryDTO(), 0);

        verify(categoryService, times(1)).updateCategory(any(), anyLong());
    }

    @Test
    public void addCategory_dto() throws Exception {
        categoryControllerImpl.addCategory(any());

        verify(categoryService, times(1)).addCategory(any());
    }
}
