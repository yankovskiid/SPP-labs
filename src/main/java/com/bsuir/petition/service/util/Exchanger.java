package com.bsuir.petition.service.util;

import com.bsuir.petition.bean.dto.request.UserRegistrationDTO;
import com.bsuir.petition.bean.entity.User;

public interface Exchanger {
    User getUser(UserRegistrationDTO userRegistrationDTO);
}
