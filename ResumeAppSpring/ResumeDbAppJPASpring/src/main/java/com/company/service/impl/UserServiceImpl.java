package com.company.service.impl;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    @Qualifier("userDaoImpl1")
    private UserRepositoryCustom userDao;
    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name,surname,nationalityId);
    }

    @Override
    public User findByEmailandPassword(String email,String password){
        return userDao.findByEmailandPassword(email,password);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean updateUser(User u) {
        return userDao.updateUser(u);
    }

    @Override
    public boolean removeUser(int id) {
        return userDao.removeUser(id);
    }
    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public boolean addUser(User u) {
        return userDao.addUser(u);
    }



}
