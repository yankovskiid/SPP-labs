package com.bsuir.petition.service.user.util;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;

public interface DtoService {
    UserInformationDTO getUserInformationDTO(UserInformation user);
    UserDTO getUserDTO(User user);
}
