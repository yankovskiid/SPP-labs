package com.bsuir.petition.service.role;

import com.bsuir.petition.bean.dto.role.RoleDTO;
import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.dao.RoleDao;
import com.bsuir.petition.service.role.exception.RoleNotFoundException;
import com.bsuir.petition.service.role.impl.RoleServiceImpl;
import com.bsuir.petition.service.role.util.DtoExchangerRole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleServiceImpl;

    @Mock
    private RoleDao roleDao;

    @Mock
    private DtoExchangerRole dtoExchangerRole;

    private Role role;
    @Before
    public void setUp() {
        role = mock(Role.class);
    }

    @Test
    public void getRoles_shouldReturnListOfRoles() throws Exception{
        givenRoleDaoReturnsListOfRoles();
        givenDtoExchangerRoleReturnsRoleListDto();

        RoleListDTO actualDto = roleServiceImpl.getRoles();

        Assert.assertNotNull(actualDto);
        verifyThatRoleDaoGetRolesWasCalled();
        verifyThatDtoExchangerRoleGetRoleListDtoWasCalled();
    }

    @Test
    public void deleteRole_id() throws Exception {
        when(roleDao.getRoleById(anyLong())).thenReturn(new Role());

        roleServiceImpl.deleteRole(0);

        verify(roleDao, times(1)).getRoleById(anyLong());
        verify(roleDao, times(1)).deleteRole(any());
    }

    @Test(expected = RoleNotFoundException.class)
    public void deleteRole_id_shouldRoleNotFound() throws Exception {
        when(roleDao.getRoleById(anyLong())).thenReturn(null);

        roleServiceImpl.deleteRole(0);
    }

    @Test
    public void updateRole_dto_id() throws Exception {
        when(roleDao.getRoleById(anyLong())).thenReturn(role);

        roleServiceImpl.updateRole(new RoleDTO(), 0);

        verify(roleDao, times(1)).getRoleById(anyLong());
        verify(role, times(1)).setRoleName(anyString());
        verify(roleDao, times(1)).updateRole(role);
    }

    @Test(expected = NullPointerException.class)
    public void updateRole_dto_id_shouldThrowNullPointer() throws Exception {
        when(roleDao.getRoleById(anyLong())).thenReturn(role);

        roleServiceImpl.updateRole(null, 0);
    }

    @Test(expected = RoleNotFoundException.class)
    public void updateRole_dto_id_shouldThrowRoleNotFound() throws Exception {
        when(roleDao.getRoleById(anyLong())).thenReturn(null);

        roleServiceImpl.updateRole(new RoleDTO(), 0);
    }

    @Test
    public void addRole_dto() throws Exception {
        roleServiceImpl.addRole(new RoleDTO());

        verify(roleDao, times(1)).createRole(any());
    }

    @Test(expected = NullPointerException.class)
    public void addRole_dto_shouldThrowNullPointer() throws Exception {
        roleServiceImpl.addRole(null);
    }

    private void givenDtoExchangerRoleReturnsRoleListDto() {
        when(dtoExchangerRole.getRoleListDTO(any())).thenReturn(new RoleListDTO());
    }

    private void givenRoleDaoReturnsListOfRoles() {
        when(roleDao.getRoles()).thenReturn(Collections.emptyList());
    }

    private void verifyThatRoleDaoGetRolesWasCalled() {
        verify(roleDao, times(1)).getRoles();
    }

    private void verifyThatDtoExchangerRoleGetRoleListDtoWasCalled() {
        verify(dtoExchangerRole, times(1)).getRoleListDTO(any());
    }
}
