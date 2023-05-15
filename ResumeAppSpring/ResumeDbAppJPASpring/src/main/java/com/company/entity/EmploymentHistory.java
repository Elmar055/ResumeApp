/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author GASIMOV
 */
@Entity
@Table(name = "employment_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmploymentHistory.findAll", query = "SELECT e FROM EmploymentHistory e"),
    @NamedQuery(name = "EmploymentHistory.findById", query = "SELECT e FROM EmploymentHistory e WHERE e.id = :id"),
    @NamedQuery(name = "EmploymentHistory.findByHeader", query = "SELECT e FROM EmploymentHistory e WHERE e.header = :header"),
    @NamedQuery(name = "EmploymentHistory.findByBeginDate", query = "SELECT e FROM EmploymentHistory e WHERE e.beginDate = :beginDate"),
    @NamedQuery(name = "EmploymentHistory.findByEndDate", query = "SELECT e FROM EmploymentHistory e WHERE e.endDate = :endDate")})
public class EmploymentHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "header")
    private String header;
    @Lob
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "begin_date")
    @Temporal(TemporalType.DATE)
    private Date beginDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public EmploymentHistory() {
    }

    public EmploymentHistory(Integer id) {
        this.id = id;
    }

    public EmploymentHistory(Integer id, String header, java.sql.Date beginDate, java.sql.Date endDate,
                             String jobDescription, User user) {
        this.id = id;
        this.header = header;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
        this.userId = user;

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        if (!(object instanceof EmploymentHistory)) {
            return false;
        }
        EmploymentHistory other = (EmploymentHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.EmploymentHistory[ id=" + id + " ]";
    }
    
}
