package org.zju.electric_factory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AMMETER")
public class Ammeter {
    
    @Id
    @GeneratedValue
    private Long id;
    
    /**
     *  电表名称
     */
    @Column(name="AMMETER_NAME")
    private String name;
    
    /**
     * 泵名称
     */
    @Column(name="PUMP_NAME")
    private String pumpName;
    
    /**
     * 项目名称
     */
    @Column(name="PROJECT_NAME")
    private String projectName;
    
    /**
     * 公司名称
     */
    @Column(name="COMPANY_NAME")
    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPumpName() {
        return pumpName;
    }

    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}


















