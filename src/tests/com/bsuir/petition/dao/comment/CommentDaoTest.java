package com.bsuir.petition.dao.comment;

import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.dao.impl.CommentDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentDaoTest {

    @InjectMocks
    private CommentDaoImpl commentDaoImpl;

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
    public void getComments_petitionId_shouldReturnListOfComment() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsListOfCategory();

        List<Comment> comments = commentDaoImpl.getComments(0);

        Assert.assertNotNull(comments);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetResultListWasCalled();
    }

    @Test(expected = NullPointerException.class)
    public void getComments_petitionId_shouldThrowNullPointer() throws Exception {
        when(sessionFactory.getCurrentSession()).thenReturn(null);

        List<Comment> comment = commentDaoImpl.getComments(0);
    }

    @Test
    public void getCommentById_id_shouldReturnComment() throws Exception {
        givenSessionFactoryReturnsCurrentSession();
        giverSessionReturnsQuery();
        givenQueryReturnsComment();

        Comment comment = commentDaoImpl.getCommentById(0);

        Assert.assertNotNull(comment);
        verifyThatGetCurrentSessionWasCalled();
        verifyThatSessionCreateQueryWasCalled();
        verifyThatQuerySetParameterWasCalled();
        verifyThatQueryGetSingleResultWasCalled();
    }

    @Test
    public void addComment_comment() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        commentDaoImpl.addComment(new Comment());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).save(any());
    }

    @Test
    public void deleteComment_comment() throws Exception {
        givenSessionFactoryReturnsCurrentSession();

        commentDaoImpl.deleteComment(new Comment());

        verifyThatGetCurrentSessionWasCalled();
        verify(session, times(1)).delete(any());
    }

    private void givenQueryReturnsListOfCategory() {
        when(query.getResultList()).thenReturn(Collections.emptyList());
    }

    private void givenSessionFactoryReturnsCurrentSession() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    private void givenQueryReturnsComment() {
        when(query.getSingleResult()).thenReturn(new Comment());
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

    private void verifyThatQueryGetResultListWasCalled() {
        verify(query, times(1)).getResultList();
    }
}
