package com.mycompany.dao.inter;


import com.mycompany.entity.Country;
import java.util.List;

public interface CountryDaoInter {
    public List<Country> getAll();

    public Country getById(int id);

    boolean updateCountry(Country u);

    boolean removeCountry(int id);
}
