package com.bsuir.petition.service.user.util;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;

public interface UserCreator {
    UserInformation getUserInformation(User user, String cityName);
}
