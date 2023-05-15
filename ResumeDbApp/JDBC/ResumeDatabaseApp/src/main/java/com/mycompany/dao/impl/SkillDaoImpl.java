package com.mycompany.dao.impl;



import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.SkillDaoInter;
import com.mycompany.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {
    private Skill getSkill(ResultSet rs) throws Exception {
        int skillId = rs.getInt("id");
        String skillName = rs.getString("name");
        return new Skill(skillId,skillName);
    }
    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try(Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * FROM resume.skill;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill s = getSkill(rs);
                result.add(s);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insertSkill(Skill skl) {
        Connection conn;
        boolean b = true;
        
        try{
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("insert skill (name) values (?);",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getName());
            b = stmt.execute();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            
            if(generatedKeys.next()){
                skl.setId(generatedKeys.getInt(1));
            }
            
        }catch(Exception ex){
            System.err.println(ex);
            b = false;
        }
         return b;
    }
}
