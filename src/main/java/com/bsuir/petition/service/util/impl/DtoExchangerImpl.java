package com.bsuir.petition.service.util.impl;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.util.DtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class DtoExchangerImpl implements DtoService {

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

    @Override
    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setBlocked(user.isBlocked());
        ArrayList<String> temp = new ArrayList<>(0);
        Set<Role> roles = user.getRoles();

        for (Role role : roles) {
            temp.add(role.getRoleName());
        }
        userDTO.setRoles(temp);

        return userDTO;
    }

}
