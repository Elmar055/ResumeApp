package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.List;

@Repository("userDaoImpl2")
public class UserDaoImpl2 implements UserRepositoryCustom {

    public UserDaoImpl2(){
        System.out.println("UserDaoImpl created");
    }
    @PersistenceContext
    EntityManager em;

    private User getUser(ResultSet rs) throws Exception {
        return null;
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
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
        User u = em.find(User.class,
                userId);
        return u;
    }

    @Override
    public boolean updateUser(User u) {
        em.merge(u);
        return true;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        em.persist(u);
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
        return true;
    }

    @Override
    public User findByEmailandPassword(String email, String password) {
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

    //NamedQuery
    @Override
    public User findByEmail(String email) {
        Query query = em.createNamedQuery("User.findByEmail", User.class);
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


}
