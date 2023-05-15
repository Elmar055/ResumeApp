package com.mycompany.dao.impl;



import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.UserSkillDaoInter;
import com.mycompany.entity.Skill;
import com.mycompany.entity.User;
import com.mycompany.entity.UserSkill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {
    private UserSkill getUserSkill(ResultSet rs) throws Exception{
        int userSkillId = rs.getInt("user_skill_id");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        int power = rs.getInt("power");
        String skillName = rs.getString("skill_name");

        return new UserSkill(userSkillId,new User(userId),new Skill(skillId,skillName),power);
    }
    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT " +
                    "us.id as user_skill_id, "+
                    "u.*, " +
                    "us.skill_id, " +
                    "s.name as skill_name, " +
                    "us.power " +
                    "from resume.user_skill us " +
                    "left join resume.user u on us.user_id = u.id " +
                    "left join resume.skill s on us.skill_id = s.id " +
                    "where us.user_id = ?;");
            stmt.setInt(1,userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeUserSkill(int id) {
        Connection conn;
        try{
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("delete from user_skill where id = ?");
            stmt.setInt(1,id);
            System.out.println("id: "+String.valueOf(id));
            return stmt.execute();
        }catch(Exception ex){
            System.out.println("ex");
            return false;
        }
    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        Connection conn;
        boolean b = true;
        try{
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("insert into user_skill (skill_id,user_id,power) values (?,?,?)");
            
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2,u.getUser().getId());
            stmt.setInt(3,u.getPower());
            
            b = stmt.execute();
        }catch(Exception ex){
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        Connection conn;
        boolean b = true;
        try{
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("update user_skill set skill_id=? ,user_id = ?, power = ? where id=?");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());
            stmt.setInt(4, u.getId());
            
            b = stmt.execute();
        }catch(Exception ex){
            System.err.println(ex);
            b = false;
        }
        return b;
    }
}
