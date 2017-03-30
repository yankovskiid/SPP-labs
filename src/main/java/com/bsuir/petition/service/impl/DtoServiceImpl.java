package com.bsuir.petition.service.impl;

import com.bsuir.petition.bean.dto.response.UserInformationDTO;
import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.DtoService;
import org.springframework.stereotype.Service;

@Service
public class DtoServiceImpl implements DtoService {

    @Override
    public UserInformationDTO getUserInformationDTO(User user) {

        if (user == null) {
            return null;
        }
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        UserInformation userInformation = user.getUserInformation();
        if (userInformation == null) {
            return null;
        }
        City city = userInformation.getCity();
        if (city != null) {
            userInformationDTO.setCity(city.getName());
        }
        userInformationDTO.setUsername(userInformation.getName());
        userInformationDTO.setSurname(userInformation.getSurname());
        return userInformationDTO;
    }

}
