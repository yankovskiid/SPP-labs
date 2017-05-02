package com.bsuir.petition.dao.category;

import com.bsuir.petition.bean.entity.Category;
import com.bsuir.petition.dao.impl.CategoryDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryDaoTest {

    @InjectMocks
    private CategoryDaoImpl categoryDaoImpl;

    @Mock
    private SessionFactory sessionFactory;

    private Session session;
    private Query query;

    @Before
    public void setUp() {
        session = mock(Session.class);
        query = mock(Query.class);
    }

    @Test
    public void getCategory_id_shouldReturnCategory() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        when(session.get(eq(Category.class), anyLong())).thenReturn(new Category());

        Category category = categoryDaoImpl.getCategory(0);

        Assert.assertNotNull(category);
        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).get(eq(Category.class), anyLong());
    }

    @Test(expected = NullPointerException.class)
    public void getCategory_id_shouldThrowNullPointer() throws Exception {
        when(sessionFactory.getCurrentSession()).thenReturn(null);

        Category category = categoryDaoImpl.getCategory(0);
    }

    @Test
    public void getCategory_name_shouldReturnCategory() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsCategory();

        Category category = categoryDaoImpl.getCategory("");

        Assert.assertNotNull(category);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetSingleResultWasCalled();
    }

    @Test
    public void getCategories_shouldListOfCategory() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsListOfCategory();

        List<Category> categories = categoryDaoImpl.getCategories();

        Assert.assertNotNull(categories);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQueryListWasCalled();
    }

    @Test
    public void addCategory_category() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        categoryDaoImpl.addCategory(new Category());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).save(any());
    }

    @Test
    public void updateCategory_category() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        categoryDaoImpl.updateCategory(new Category());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).saveOrUpdate(any());

    }

    @Test
    public void deleteCategory_category() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        categoryDaoImpl.deleteCategory(new Category());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).delete(any());
    }

    private void givenQueryReturnsListOfCategory() {
        when(query.list()).thenReturn(Collections.emptyList());
    }

    private void givenSessionFactoryReturnsCurrentSession() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    private void givenQueryReturnsCategory() {
        when(query.getSingleResult()).thenReturn(new Category());
    }

    private void giverSessionReturnsQuery() {
        when(session.createQuery(anyString())).thenReturn(query);
    }

    private void verifyThatGetCurrentSessionWasCalled() {
        verify(sessionFactory, times(1)).getCurrentSession();
    }

    private void verifyThatSessionCreateQueryWasCalled() {
        verify(session, times(1)).createQuery(anyString());
    }

    private void verifyThatQuerySetParameterWasCalled() {
        verify(query, times(1)).setParameter(anyString(), anyString());
    }

    private void verifyThatQueryGetSingleResultWasCalled() {
        verify(query, times(1)).getSingleResult();
    }

    private void verifyThatQueryListWasCalled() {
        verify(query, times(1)).list();
    }
}
