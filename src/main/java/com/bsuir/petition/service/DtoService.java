package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.user.UserInformationDTO;
import com.bsuir.petition.bean.entity.UserInformation;

public interface DtoService {
    UserInformationDTO getUserInformationDTO(UserInformation user);
}
