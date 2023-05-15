package com.mycompany.main;

import com.mycompany.dao.impl.UserDaoImpl;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.entity.User;



public class Main {

    public static void main(String[] args) throws Exception {
        CountryDaoInter c = Context.instanceCountryDao();

        User u = new User(0,"test","test","test","test",null,null,null,null,null);
        u.setPassword("12345");
        new UserDaoImpl().addUser(u);
        System.out.println(c.getAll());
    }


}
