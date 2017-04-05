package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.DtoService;
import org.springframework.stereotype.Service;

@Service
public class DtoServiceImpl implements DtoService {

    @Override
    public UserInformationDTO getUserInformationDTO(UserInformation userInformation) {

        if (userInformation == null) {
            return null;
        }
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setCity(userInformation.getCity().getName());
        userInformationDTO.setUsername(userInformation.getName());
        userInformationDTO.setSurname(userInformation.getSurname());
        return userInformationDTO;
    }

}
