package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.UserDao;
import com.eddgarvf.stockFinancial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> getAll() {
        try{
            return userDao.getAll();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public User getById(int userId) {
        try{
            return userDao.getById(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
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
