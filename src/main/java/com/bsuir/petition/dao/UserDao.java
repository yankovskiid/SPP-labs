package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;

public interface UserDao {
    User getUserById(long id);
    User getUserByEmail(String email);
    UserInformation getUserInformation(long id);
    void addUser(User user);
    void updateUserById(long id, User user);
    void deleteUserById(long id);
}
