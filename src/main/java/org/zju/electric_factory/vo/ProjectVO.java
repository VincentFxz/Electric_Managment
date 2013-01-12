package org.zju.electric_factory.vo;

import java.util.Date;

import javax.persistence.Column;


public class ProjectVO {

    private Long id;
    
    private String projectName;
    
    private String projectDescription;
    
    private Date startDate;
    
    private Date endDate;
    
    private Float electricityCharge;
    
    private Float partsRatio;
    
    public Long getId() {
        return id;
    }

    public Float getElectricityCharge() {
		return electricityCharge;
	}

	public void setElectricityCharge(Float electricityCharge) {
		this.electricityCharge = electricityCharge;
	}

	public Float getPartsRatio() {
		return partsRatio;
	}

	public void setPartsRatio(Float partsRatio) {
		this.partsRatio = partsRatio;
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
