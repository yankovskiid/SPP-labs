package com.bsuir.petition.service.user.util;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.dto.user.UserListDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;

import java.util.List;

public interface UserDtoExchanger {
    UserInformationDTO getUserInformationDTO(UserInformation user);
    UserDTO getUserDTO(User user);
    UserListDTO getUserListDTO(List<User> users);
}
