package com.company.dao.impl;


import com.company.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    public List<User> getAll(String name, String surname, Integer nationalityId);
    public User getById(int id);
    public boolean updateUser(User u);
    public boolean addUser(User u);
    public boolean removeUser(int id);
    public User findByEmailandPassword(String email,String password);
    public User findByEmail(String email);
}
