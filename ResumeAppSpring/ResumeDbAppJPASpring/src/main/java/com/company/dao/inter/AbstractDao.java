package com.company.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao {
    public Connection connect() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "2905";
        Connection c = DriverManager.getConnection(url,username,password);
        return c;
    }
    public static EntityManagerFactory emf = null;
    
    public EntityManager em(){
        if(emf==null){
            emf = Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager entitymanager = emf.createEntityManager();
        return entitymanager;
    }
    
    public void closeEmf(){
        emf.close();
    }
}
