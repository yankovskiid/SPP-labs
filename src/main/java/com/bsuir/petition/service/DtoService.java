package com.bsuir.petition.service;

import com.bsuir.petition.bean.dto.response.UserInformationDTO;
import com.bsuir.petition.bean.entity.User;

public interface DtoService {
    UserInformationDTO getUserInformationDTO(User user);
}
