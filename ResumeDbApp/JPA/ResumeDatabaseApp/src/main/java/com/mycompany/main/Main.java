package com.mycompany.main;

import com.mycompany.dao.impl.UserDaoImpl;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.User;



public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();
        
       User u = userDao.findByEmail("elmar@gmail.com");
       
        System.out.println("User:"+u);
    }


}
