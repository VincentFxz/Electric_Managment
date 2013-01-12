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
    
    @Column(name = "SENSOR_RATE")
    private Float sensorRate;
    
    @Column(name = "FORMER_COST")
    private Float formerCost;
    
    @Column(name = "UPPER_LIMIT")
    private Float upperLimit;
    
    @Column(name = "LOWER_LIMIT")
    private Float lowerLimit;

    public Float getSensorRate() {
		return sensorRate;
	}

	public void setSensorRate(Float sensorRate) {
		this.sensorRate = sensorRate;
	}

	public Float getFormerCost() {
		return formerCost;
	}

	public void setFormerCost(Float formerCost) {
		this.formerCost = formerCost;
	}


	public Float getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Float upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Float getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Float lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

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


















