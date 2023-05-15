package com.company;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
    UserRepositoryCustom userDao;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeClass // her bir test metdu ayri ishe dushur
    public void setUp(){
        System.out.println("setup called");

        List<User> list = new ArrayList<>();
        User u = new User();
        u.setName("test");
        u.setSurname("test");
        u.setEmail("test@test.com");

        list.add(u);
        Mockito.when(userDao.getAll(null,null,null)).thenReturn(list);
    }

    @Before //testden evvel ishe dushur
    public void before(){
        System.out.println("before called");
    }

    @Test
    public void testGivenNullThenGetAll(){
        UserServiceImpl userService = new UserServiceImpl();
        List<User> list = userService.getAll(null,null,null);

        Assert.assertEquals("user size must be 2",2, list.size());
    }

    @Test
    public void testGivenAllParamsThenGetAllByFilter(){
        UserServiceImpl userService = new UserServiceImpl();
        List<User> list = userService.getAll("test","test",1);

        Assert.assertTrue("user size must grater than 1",list.size()>1);

        User user = list.get(0);

        Assert.assertEquals("name wrong","test",user.getName());
        Assert.assertEquals("surname wrong","test",user.getSurname());
        Assert.assertEquals("nat id wrong",1L,(long)user.getNationalityId().getId());
    }

    @Test
    public void testFindByEmailandPassword(){
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.findByEmailandPassword(null,null);

        Assert.assertNull("user must be null",user);
    }
}
