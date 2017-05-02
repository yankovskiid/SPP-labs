package com.bsuir.petition.service.category;

import com.bsuir.petition.bean.dto.category.CategoryListDTO;
import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.CategoryDao;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.category.impl.CategoryServiceImpl;
import com.bsuir.petition.service.category.util.CategoryDataValidator;
import com.bsuir.petition.service.category.util.DtoExchangerCategory;
import com.bsuir.petition.service.category.util.ExchangerCategory;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryDataValidator categoryDataValidator;

    @Mock
    private CategoryDao categoryDao;

    @Mock
    private DtoExchangerCategory dtoExchangerCategory;

    @Mock
    private ExchangerCategory exchangerCategory;

    /*CategoryService.getCategories()*/

    @Test
    public void getCategories_shouldReturnListOfCategories() throws Exception {
        givenCategoryDaoReturnsListOfCategories();
        givenDtoExchangerCategoryReturnsListOfCategoryDto();

        CategoryListDTO actualDto = categoryServiceImpl.getCategories();

        Assert.assertNotNull(actualDto);
        verifyThatCategoryDaoWasCalled();
        verifyThatDtoExchangerCategory();
    }

    @Test(expected = ServerException.class)
    public void getCategories_hibernateErrorInCategoryDao_shouldThrowException() throws Exception {
        givenCategoryDaoThrowsHibernateException();
        givenDtoExchangerCategoryReturnsListOfCategoryDto();

        categoryServiceImpl.getCategories();
    }

    @Test(expected = ServerException.class)
    public void getCategories_exceptionInExchanger_shouldThrowException() throws Exception {
        givenCategoryDaoReturnsListOfCategories();
        givenDtoExchangerCategoryThrowsException();

        categoryServiceImpl.getCategories();
    }

    /*CategoryService.deleteCategory(id)*/
    @Test
    public void deleteCategory_categoryId() throws Exception {
        givenCategoryDaoReturnsCategory();

        categoryServiceImpl.deleteCategory(0);

        verifyThatCategoryDaoGetCategoryWasCalled();
    }

    @Test(expected = CategoryNotFoundException.class)
    public void deleteCategory_categoryId_shouldThrowCategoryNotFound() throws Exception {
        givenCategoryDaoReturnsNull();

        categoryServiceImpl.deleteCategory(0);
    }

    /*CategoryService.updateCategory(shortCategoryDto, id)*/
    @Test
    public void updateCategory_shortDto_categoryId() throws Exception {
        givenExchangerCategoryReturnsCategory();

        categoryServiceImpl.updateCategory(any(), 0);

        verifyThatCategoryDataValidatorWasCalled();
        verifyThatExchangerCategoryWasCalled();
        verifyThatCategoryDaoUpdateCategoryWasCalled();
    }

    /*CategoryService.addCategory(shortCategoryDto)*/
    @Test
    public void addCategory_shortDto() throws Exception {
        givenExchangerCategoryReturnsCategory();

        categoryServiceImpl.addCategory(any());

        verifyThatCategoryDataValidatorWasCalled();
        verifyThatExchangerCategoryWasCalledByShort();
        verifyThatCategoryDaoAddCategoryWasCalled();
    }


    /*CategoryService.getCategories()*/
    private void givenCategoryDaoReturnsListOfCategories() {
        when(categoryDao.getCategories()).thenReturn(Collections.emptyList());
    }

    private void givenDtoExchangerCategoryReturnsListOfCategoryDto() {
        when(dtoExchangerCategory.getCategoryListDTO(any())).thenReturn(new CategoryListDTO());
    }

    private void givenCategoryDaoThrowsHibernateException() {
        when(categoryDao.getCategories()).thenThrow(HibernateException.class);
    }

    private void givenDtoExchangerCategoryThrowsException(){
        when(dtoExchangerCategory.getCategoryListDTO(any())).thenThrow(new RuntimeException());
    }

    private void verifyThatCategoryDaoWasCalled() {
        verify(categoryDao, times(1)).getCategories();
    }

    private void verifyThatDtoExchangerCategory() {
        verify(dtoExchangerCategory, times(1)).getCategoryListDTO(any());
    }


    /*CategoryService.deleteCategory(id)*/
    private void givenCategoryDaoReturnsCategory() {
        when(categoryDao.getCategory(anyLong())).thenReturn(new Category());
    }

    private void givenCategoryDaoReturnsNull() {
        when(categoryDao.getCategory(anyLong())).thenThrow(CategoryNotFoundException.class);
    }

    private void verifyThatCategoryDaoGetCategoryWasCalled() {
        verify(categoryDao, times(1)).getCategory(anyLong());
    }


    /*CategoryService.updateCategory(shortCategoryDto, id)*/
    private void givenExchangerCategoryReturnsCategory() {
        when(exchangerCategory.getCategory(any())).thenReturn(new Category());
    }

    private void verifyThatCategoryDataValidatorWasCalled() throws ErrorInputException {
        verify(categoryDataValidator, times(1)).validate(any());
    }

    private void verifyThatExchangerCategoryWasCalled() {
        verify(exchangerCategory, times(1)).getCategory(any(), anyLong());
    }

    private void verifyThatCategoryDaoUpdateCategoryWasCalled() {
        verify(categoryDao, times(1)).updateCategory(any());
    }

    /*CategoryService.addCategory(shortCategoryDto)*/
    private void verifyThatCategoryDaoAddCategoryWasCalled() {
        verify(categoryDao, times(1)).addCategory(any());
    }

    private void verifyThatExchangerCategoryWasCalledByShort() {
        verify(exchangerCategory, times(1)).getCategory(any());
    }
}
