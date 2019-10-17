package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.UserDao;
import com.eddgarvf.stockFinancial.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    UserDao userDao;

    UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao);
    }

    @Test
    public void testGetAllUsers(){
        List<User> usersList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("Ed");
        user1.setEmail("e@h.com");
        User user2 = new User();
        user2.setId(2);
        user2.setName("Albert");
        user2.setEmail("albert@lol.com");
        usersList.add(user1);
        usersList.add(user2);
        when(userDao.getAll()).thenReturn(usersList);
        userService.getAll();

        verify(userDao, times(1)).getAll();
    }


}
