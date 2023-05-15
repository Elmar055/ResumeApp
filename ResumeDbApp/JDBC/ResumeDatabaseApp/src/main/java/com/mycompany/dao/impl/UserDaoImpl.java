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

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        String adress = rs.getString("adress");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country country = new Country(nationalityId,null,nationalityStr);
        Country birthplace = new Country(birthplaceId,birthplaceStr,null);

        return new User(id, name, surname, email, phone, profileDesc, adress ,(java.sql.Date) birthdate, country,birthplace);
    }
    
    private User getUserSimple(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        String adress = rs.getString("adress");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        Date birthdate = rs.getDate("birthdate");


        User user = new User(id, name, surname, email, phone, profileDesc, adress ,(java.sql.Date) birthdate, null,null);
        user.setPassword(rs.getString("password"));
        
        return user;
    }


    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try(Connection c = connect()) {
            
            String sql = "select " +
                    "u.*, " +
                    "n.nationality, " +
                    "c.name as birthplace " +
                    "from resume.user  u " +
                    "left join resume.country  n on u.nationality_id = n.id " +
                    "left join resume.country  c on u.birthplace_id = c.id where 1=1 ";
            if(name!=null && !name.trim().isEmpty()){
                sql += " and u.name=? ";
            }
            if(surname!=null && !surname.trim().isEmpty()){
                sql+=" and u.surname=? ";
            }
            if(nationalityId!=null){
                sql+=" and u.nationality_id=? ";
                
            }
            PreparedStatement stmt = c.prepareStatement(sql);
            
            
            int i=1;
            if(name!=null && !name.trim().isEmpty() ){
                stmt.setString(i, name);
                i++;
            }if(surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i, surname);
                i++;
            }if(nationalityId!=null){
                stmt.setInt(i, nationalityId);
            }
            
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try(Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    "u.*, " +
                    "n.nationality, " +
                    "c.name as birthplace " +
                    "from resume.user  u " +
                    "left join resume.country  n on u.nationality_id = n.id " +
                    "left join resume.country  c on u.birthplace_id = c.id where u.id = "+userId);
                stmt.execute("select * from resume.user where resume.user.id = "+userId); 
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u){
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,email=?,phone=?,profile_description=?,birthdate=?,adress=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1,u.getName());
            stmt.setString(2,u.getSurname());
            stmt.setString(3,u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setDate(6, u.getBirthDate());
            stmt.setString(7, u.getAdress());
            stmt.setInt(8, u.getBirthplace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10,u.getId());
            return stmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();
    @Override
    public boolean addUser(User u) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname ,email,phone,password,profile_description) values(?,?,?,?,?,?)");
            
            stmt.setString(1,u.getName());
            stmt.setString(2,u.getSurname());
            stmt.setString(3,u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, crypt.hashToString(4, u.getPassword().toCharArray()));
            stmt.setString(6, u.getProfileDesc());
            return stmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean removeUser(int id) {
        try(Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id = "+id);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByEmailandPassword(String email, String password) {
        User result = null;
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                result = getUserSimple(rs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
 User result = null;
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("select * from user where email=?");
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ 
                result = getUserSimple(rs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
