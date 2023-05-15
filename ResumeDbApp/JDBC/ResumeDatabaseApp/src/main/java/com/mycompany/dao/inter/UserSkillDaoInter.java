package com.mycompany.dao.inter;


import com.mycompany.entity.UserSkill;
import java.util.List;

public interface UserSkillDaoInter {
    public List<UserSkill> getAllSkillByUserId(int userId);
    public boolean removeUserSkill(int id);
    public boolean insertUserSkill(UserSkill u);
    public boolean updateUserSkill(UserSkill u);
}
