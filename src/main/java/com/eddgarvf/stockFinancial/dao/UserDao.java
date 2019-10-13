package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User getUserById(int userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
