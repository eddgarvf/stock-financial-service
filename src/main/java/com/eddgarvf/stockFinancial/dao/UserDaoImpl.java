package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User ORDER BY id").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int userId) {
        entityManager.remove(getUserById(userId));
    }
}
