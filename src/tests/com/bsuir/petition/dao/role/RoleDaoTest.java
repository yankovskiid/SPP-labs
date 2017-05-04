package com.bsuir.petition.dao.role;

import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.dao.RoleDao;
import com.bsuir.petition.dao.impl.RoleDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import org.hibernate.query.*;

/**
 * Created by User on 03.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleDaoTest {

    @InjectMocks
    private RoleDaoImpl roleDaoImpl;

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
    public void getRole_name_shouldReturnRole() throws  Exception {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(new Role());

        roleDaoImpl.getRoleByName("");

        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).createQuery(anyString());
        verify(query, times(1)).setParameter(anyString(), any());
        verify(query,times(1)).getSingleResult();

    }

    @Test
    public void getRole_id_shouldReturnRole() throws Exception {

    }
}
