package com.mycompany.dao.impl;



import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.entity.Country;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {
    private Country getCountry(ResultSet rs) throws Exception {
        int countryId = rs.getInt("id");
        String countryName = rs.getString("name");
        String nationality = rs.getString("nationality");

        return new Country(countryId,countryName,nationality);
    }
    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try(Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * FROM resume.country;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country cnt = getCountry(rs);
                result.add(cnt);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
