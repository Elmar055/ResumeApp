package com.mycompany.entity;

import com.mycompany.entity.Country;
import com.mycompany.entity.EmploymentHistory;
import com.mycompany.entity.UserSkill;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-15T15:00:30")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> birthdate;
    public static volatile CollectionAttribute<User, EmploymentHistory> employmentHistoryCollection;
    public static volatile SingularAttribute<User, String> profileDescription;
    public static volatile SingularAttribute<User, Country> nationalityId;
    public static volatile SingularAttribute<User, Country> birthplaceId;
    public static volatile SingularAttribute<User, String> adress;
    public static volatile CollectionAttribute<User, UserSkill> userSkillCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> nationality;
    public static volatile SingularAttribute<User, String> birthplace;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;

}