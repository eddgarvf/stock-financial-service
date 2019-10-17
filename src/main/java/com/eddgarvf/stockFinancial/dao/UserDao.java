package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.User;
import java.util.List;

public interface UserDao {

    List<User> getAll();
    User getById(int userId);
    void add(User user);
    void update(User user);
    void delete(int userId);
}
