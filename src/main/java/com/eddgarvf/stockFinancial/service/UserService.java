package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.UserDao;
import com.eddgarvf.stockFinancial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int userId) {
        return userDao.getById(userId);
    }

    public void add(User user) {
        userDao.add(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(int userId) {
        userDao.delete(userId);
    }

}
