package org.zju.electric_factory.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT")
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="PROJECT_NAME")
    private String projectName;
    
    @Column(name="PROJECT_DESCRIPTION")
    private String projectDescription;
    
    @Column(name="START_DATE")
    private Date startDate;
    
    @Column(name="END_DATE")
    private Date endDate;
    
    @Column(name="ELECTRICITY_CHARGE")
    private Float electricityCharge;
    
    @Column(name="PARTS_RATIO")
    private Float partsRatio;
    
    public Float getPartsRatio() {
		return partsRatio;
	}

	public void setPartsRatio(Float partsRatio) {
		this.partsRatio = partsRatio;
	}

	public Float getElectricityCharge() {
		return electricityCharge;
	}

	public void setElectricityCharge(Float electricityCharge) {
		this.electricityCharge = electricityCharge;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
