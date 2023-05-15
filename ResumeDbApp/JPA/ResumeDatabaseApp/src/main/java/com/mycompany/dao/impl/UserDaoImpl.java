package com.mycompany.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.Country;
import com.mycompany.entity.User;
import java.security.CryptoPrimitive;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {
    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        EntityManager em = em();
        String jpql = "select u from User u where 1=1";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name ";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname ";
        }
        if (nationalityId != null) {
            jpql += " and u.nationalityId.id=:nid ";

        }

        Query query = em.createQuery(jpql, User.class);

        int i = 1;
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }
        if (nationalityId != null) {
            query.setParameter("nid", nationalityId);
        }

        return query.getResultList();
    }

    @Override
    public User getById(int userId) {
        EntityManager em = em();
        User u = em.find(User.class,
                 userId);
        em.close();
        return u;
    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        EntityManager em = em();

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = em();

        User u = em.find(User.class,
                 id);

        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public User findByEmailandPassword(String email, String password) {
        EntityManager em = em();
        Query q = em.createQuery("select u from User u where u.email=:e and u.password=:p", User.class
        );
        q.setParameter("e", email);
        q.setParameter("p", password);

        // getResultList() - eger eyni emaile ve parola sahib cox user varsa islenir
        // singleResult() - eger eyni emaile ve parola sahib bir user varsa ishlenir. netice birden coxdursa error verir
        List<User> list = q.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }
    
//    //CriteraiBuilder
//    @Override
//    public User findByEmailandPassword(String email, String password) {
//                EntityManager em = em();
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q1 = cb.createQuery(User.class);
//        Root<User> postRoot = q1.from(User.class);
//        CriteriaQuery<User> q2 = q1
//                .where(cb.equal(postRoot.get("email"), email),
//                        cb.equal(postRoot.get("password"), password));
//        
//
//        Query query = em.createQuery(q2);
//
////        query.setParameter(1, email);
////        query.setParameter(2, password);
////        
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }
    
        //jpql
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//        Query q = em.createQuery("select u from User u where u.email= :e", User.class);
//        q.setParameter("e", email);
//
//        List<User> list = q.getResultList();//list=0
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }

    
    
    //CriteriaBuilder
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q1 = cb.createQuery(User.class);
//        Root<User> postRoot = q1.from(User.class);
//        CriteriaQuery<User> q2 = q1
//                .where(cb.equal(postRoot.get("email"), email));
//        
//
//        Query query = em.createQuery(q2);
//
////        query.setParameter(1, email);
////        query.setParameter(2, password);
////        
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//
//    }
    
    
    //NamedQuery
    @Override
    public User findByEmail(String email) {
        EntityManager em = em();

        Query query= em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
//        
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;

    }
    
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//        Query q = em.createQuery("select u from User u where u.email=:e", User.class
//        );
//        q.setParameter("e", email);
//
//        // getResultList() - eger eyni emaile ve parola sahib cox user varsa islenir
//        // singleResult() - eger eyni emaile ve parola sahib bir user varsa ishlenir. netice birden coxdursa error verir
//        List<User> list = q.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }
    
        //Native SQL
//     @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//
//        Query query= em.createNativeQuery("select * from user where email = ?", User.class);
//        query.setParameter(1, email);
////        
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        
//        em.close();
//
//        return null;
//
//    }

}
