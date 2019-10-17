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
    public List<User> getAll() {
        return entityManager.createQuery("FROM User ORDER BY id").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int userId) {
        entityManager.remove(getById(userId));
    }
}
