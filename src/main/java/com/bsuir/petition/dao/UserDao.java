package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;

import java.util.List;

public interface UserDao {
    User getUserById(long id);
    User getUserByEmail(String email);

    UserInformation getUserInformationById(long id);
    void updateUserInformation(UserInformation userInformation);
    void addUserInformation(UserInformation userInformation);

    void addUser(User user);
    void updateUser(User user);
    List<User> getUsers();
}
