package com.mycompany.entity;

import com.mycompany.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-15T15:00:30")
@StaticMetamodel(Country.class)
public class Country_ { 

    public static volatile SingularAttribute<Country, String> nationality;
    public static volatile CollectionAttribute<Country, User> userCollection;
    public static volatile SingularAttribute<Country, String> name;
    public static volatile CollectionAttribute<Country, User> userCollection1;
    public static volatile SingularAttribute<Country, Integer> id;

}