package com.company.dao.impl;

import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>, UserRepositoryCustom {
    User findByName(String name);

    @Query(value = "select u from User u where u.email=?1")
    User findByEmail(String email);

}
