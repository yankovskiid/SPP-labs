package com.bsuir.petition.dao;

import com.bsuir.petition.bean.User;

public interface UserDao {
    User getUserById(long id);
    void updateUserById(long id, User user);
    void deleteUserById(long id);
    void addUser(User user);
}
