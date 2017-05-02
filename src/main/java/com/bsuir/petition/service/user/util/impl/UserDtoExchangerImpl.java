package com.bsuir.petition.service.user.util.impl;

import com.bsuir.petition.bean.dto.user.ShortUserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserListDTO;
import com.bsuir.petition.bean.entity.Role;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.service.user.util.UserDtoExchanger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserDtoExchangerImpl implements UserDtoExchanger {

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

    @Override
    public UserListDTO getUserListDTO(List<User> users) {
        UserListDTO userListDTO = new UserListDTO();
        for (User user : users) {
            ShortUserInformationDTO shortUserInformationDTO = new ShortUserInformationDTO();
            shortUserInformationDTO.setEmail(user.getEmail());
            shortUserInformationDTO.setId(user.getId());
            shortUserInformationDTO.setNick(user.getNick());
            shortUserInformationDTO.setBlocked(user.isBlocked());

            ArrayList<String> temp = new ArrayList<>(0);
            Set<Role> roles = user.getRoles();

            for (Role role : roles) {
                temp.add(role.getRoleName());
            }
            shortUserInformationDTO.setRoles(temp);

            userListDTO.getUsers().add(shortUserInformationDTO);
        }
        return userListDTO;
    }

}
