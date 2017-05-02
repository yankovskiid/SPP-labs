package com.bsuir.petition.service.petition;

import com.bsuir.petition.bean.dto.petition.AddPetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionDTO;
import com.bsuir.petition.bean.dto.petition.PetitionListDTO;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.service.petition.impl.PetitionServiceImpl;
import com.bsuir.petition.service.petition.util.PetitionDtoExchanger;
import com.bsuir.petition.service.petition.util.PetitionExchanger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class PetitionServiceTest {

    @InjectMocks
    private PetitionServiceImpl petitionServiceImpl;

    @Mock
    private UserDao userDao;

    @Mock
    private PetitionDao petitionDao;

    @Mock
    private PetitionDtoExchanger petitionDtoExchanger;

    @Mock
    private PetitionExchanger petitionExchanger;

    @Mock
    private SecurityContextHolder securityContextHolder;

    private TokenAuthentication tokenAuthentication;
    private SecurityContext securityContext = SecurityContextHolder.getContext();
    private Long object;
    @Before
    public void setUp() {
        securityContext = mock(SecurityContext.class);
        tokenAuthentication = mock(TokenAuthentication.class);
//        object = mock(Long.class);
    }

    /*PetitionService.getPetitions()*/
    @Test
    public void getPetitions_shouldReturnListOfPetitionDto() throws Exception {
        givenPetitionDaoReturnsListOfPetitions();
        givenPetitionDtoExchangerReturnsPetitionListDto();

        PetitionListDTO actualDto = petitionServiceImpl.getPetitions();

        Assert.assertNotNull(actualDto);
        verifyThatPetitionDaoGetPetitionsWasCalled();
        verifyThatPetitionDtoExchangerGetPetitionListDtoWasCalled();
    }


    /*PetitionService.getPetition(id)*/
    @Test
    public void getPetition_petitionId_shouldReturnPetitionDto() throws Exception {
        givenPetitionDaoReturnsPetitionById();
        givenPetitionDtoExchangerReturnPetitionDto();

        PetitionDTO actualDto = petitionServiceImpl.getPetition(0);

        Assert.assertNotNull(actualDto);
        verifyThatPetitionDaoGetPetitionByIdWasCalled();
        verifyThatPetitionDtoExchangerGetPetitionDtoWasCalled();
    }


    /*PetitionService.deletePetition(id)*/
    @Test
    public void deletePetition_petitionId() throws Exception {
        givenPetitionDaoReturnsPetitionById();

        petitionServiceImpl.deletePetition(0);

        verifyThatPetitionDaoGetPetitionByIdWasCalled();
        verifyThatPetitionDaoDeleteWasCalled();
    }

    /*PetitionService.addPetition(dto)*/
//    @Test
//    public void addPetition_petitionDto() throws Exception {
//        when(securityContext.getAuthentication()).thenReturn(tokenAuthentication);
//        when(tokenAuthentication.getDetails()).thenReturn(object);
//        when(userDao.getUserById(object)).thenReturn(new User());
//        when(petitionExchanger.getPetition(any(), any())).thenReturn(new Petition());
//        givenSecurityContextReturnsToken();
//        givenUserDaoReturnsUserById();
//        givenPetitionExchangerReturnsPetition();
//
//        petitionServiceImpl.addPetition(new AddPetitionDTO());
//
//        verify(securityContext, times(1)).getAuthentication();
//        verify(tokenAuthentication, times(1)).getDetails();
//        verify(userDao, times(1)).getUserById(anyLong());
//        verify(petitionExchanger, times(1)).getPetition(any(), any());
//        verifyThatSecurityContextWasCalled();
//        verifyThatUserDaoGetUserByIdWasCalled();
//        verifyThatPetitionExchangerGetPetitionWasCalled();
//        verifyThatPetitionDaoAddWasCalled();
//    }

    /*PetitionService.getPetitions()*/
    private void givenPetitionDaoReturnsListOfPetitions() {
        when(petitionDao.getPetitions()).thenReturn(Collections.emptyList());
    }

    private void givenPetitionDtoExchangerReturnsPetitionListDto() {
        when(petitionDtoExchanger.getPetitionListDTO(any())).thenReturn(new PetitionListDTO());
    }

    private void verifyThatPetitionDaoGetPetitionsWasCalled() {
        verify(petitionDao, times(1)).getPetitions();
    }

    private void verifyThatPetitionDtoExchangerGetPetitionListDtoWasCalled() {
        verify(petitionDtoExchanger, times(1)).getPetitionListDTO(any());
    }


    /*PetitionService.getPetition(id)*/
    private void givenPetitionDaoReturnsPetitionById() {
        when(petitionDao.getPetition(anyLong())).thenReturn(new Petition());
    }

    private void givenPetitionDtoExchangerReturnPetitionDto() {
        when(petitionDtoExchanger.getPetitionDTO(any())).thenReturn(new PetitionDTO());
    }

    private void verifyThatPetitionDaoGetPetitionByIdWasCalled() {
        verify(petitionDao, times(1)).getPetition(anyLong());
    }

    private void verifyThatPetitionDtoExchangerGetPetitionDtoWasCalled() {
        verify(petitionDtoExchanger, times(1)).getPetitionDTO(any());
    }


    /*PetitionService.deletePetition(id)*/
    private void verifyThatPetitionDaoDeleteWasCalled() {
        verify(petitionDao, times(1)).deletePetition(any());
    }


    /*PetitionService.addPetition(dto)*/
    private void givenSecurityContextReturnsToken() {
        when(securityContext.getAuthentication()).thenReturn(new TokenAuthentication());
    }

    private void givenUserDaoReturnsUserById() {
        when(userDao.getUserById(anyLong())).thenReturn(new User());
    }

    private void givenPetitionExchangerReturnsPetition() {
        when(petitionExchanger.getPetition(any(), any())).thenReturn(new Petition());
    }

    private void verifyThatSecurityContextWasCalled() {
        verify(securityContext, times(1)).getAuthentication();
    }

    private void verifyThatUserDaoGetUserByIdWasCalled() {
        verify(userDao, times(1)).getUserById(anyLong());
    }

    private void verifyThatPetitionExchangerGetPetitionWasCalled() {
        verify(petitionExchanger, times(1)).getPetition(any(), any());
    }

    private void verifyThatPetitionDaoAddWasCalled() {
        verify(petitionDao, times(1)).addPetition(any());
    }
}
