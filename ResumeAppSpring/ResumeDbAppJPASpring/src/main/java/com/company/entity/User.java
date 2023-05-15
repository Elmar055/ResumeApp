/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author GASIMOV
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Lob
    @Column(name = "profile_description")
    private String profileDescription;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(name = "adress")
    private String adress;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "birthplace")
    private String birthplace;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "userId")
    private Collection<UserSkill> userSkillCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<EmploymentHistory> employmentHistoryCollection;
    @JoinColumn(name = "birthplace_id", referencedColumnName = "id")
    @ManyToOne
    private Country birthplaceId;
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    @ManyToOne
    private Country nationalityId;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<UserSkill> getUserSkillCollection() {
        return userSkillCollection;
    }

    public void setUserSkillCollection(Collection<UserSkill> userSkillCollection) {
        this.userSkillCollection = userSkillCollection;
    }

    @XmlTransient
    public Collection<EmploymentHistory> getEmploymentHistoryCollection() {
        return employmentHistoryCollection;
    }

    public void setEmploymentHistoryCollection(Collection<EmploymentHistory> employmentHistoryCollection) {
        this.employmentHistoryCollection = employmentHistoryCollection;
    }

    public Country getBirthplaceId() {
        return birthplaceId;
    }

    public void setBirthplaceId(Country birthplaceId) {
        this.birthplaceId = birthplaceId;
    }

    public Country getNationalityId() {
        return birthplaceId;
    }

    public void setNationalityId(Country nationalityId) {
        this.nationalityId = nationalityId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.User[ id=" + id + "     name="+name+"]";
    }
    
}
