package com.mycompany.dao.inter;


import com.mycompany.entity.Skill;
import java.util.List;

public interface SkillDaoInter {
    public List<Skill> getAll();
    public boolean insertSkill(Skill skl);
}
