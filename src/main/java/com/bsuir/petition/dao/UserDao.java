package com.bsuir.petition.dao;

import com.bsuir.petition.bean.entity.User;

public interface UserDao {
    User getUserById(long id);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUserById(long id, User user);
    void deleteUserById(long id);
}
