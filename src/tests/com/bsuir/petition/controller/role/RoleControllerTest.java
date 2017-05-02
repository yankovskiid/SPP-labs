package com.bsuir.petition.controller.role;

import com.bsuir.petition.bean.dto.role.RoleDTO;
import com.bsuir.petition.bean.dto.role.RoleListDTO;
import com.bsuir.petition.controller.role.impl.RoleControllerImpl;
import com.bsuir.petition.service.role.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    @InjectMocks
    private RoleControllerImpl roleControllerImpl;

    @Mock
    private RoleService roleService;

    @Test
    public void getRoles_shouldReturnRoleListDto() throws Exception {
        when(roleService.getRoles()).thenReturn(new RoleListDTO());

        RoleListDTO roles = roleControllerImpl.getRoles();

        Assert.assertNotNull(roles);
        verify(roleService, times(1)).getRoles();
    }

    @Test
    public void updateRole_dto_id() throws Exception {
        roleControllerImpl.updateRole(new RoleDTO(), 0);

        verify(roleService, times(1)).updateRole(any(), anyLong());
    }

    @Test
    public void deleteRole_id() throws Exception {
        roleControllerImpl.deleteRole(0);

        verify(roleService, times(1)).deleteRole(anyLong());
    }

    @Test
    public void addRole_dto() throws Exception {
        roleControllerImpl.addRole(new RoleDTO());

        verify(roleService, times(1)).addRole(any());
    }
}
