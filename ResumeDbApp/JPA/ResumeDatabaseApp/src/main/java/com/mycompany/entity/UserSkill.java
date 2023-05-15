/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GASIMOV
 */
@Entity
@Table(name = "user_skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSkill.findAll", query = "SELECT u FROM UserSkill u"),
    @NamedQuery(name = "UserSkill.findById", query = "SELECT u FROM UserSkill u WHERE u.id = :id"),
    @NamedQuery(name = "UserSkill.findByPower", query = "SELECT u FROM UserSkill u WHERE u.power = :power")})
public class UserSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "power")
    private Integer power;
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne
    private Skill skillId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public UserSkill() {
    }

    public UserSkill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Skill getSkillId() {
        return skillId;
    }

    public void setSkillId(Skill skillId) {
        this.skillId = skillId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSkill)) {
            return false;
        }
        UserSkill other = (UserSkill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.UserSkill[ id=" + id + " ]";
    }
    
}
