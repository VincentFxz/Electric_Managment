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

    /**
     * 互感器读数
     */
    @Column(name = "SENSOR_RATE")
    private Float sensorRate;

    /**
     * 技改前能耗
     */
    @Column(name = "FORMER_COST")
    private Float formerCost;

    /**
     * 上限
     */
    @Column(name = "UPPER_LIMIT")
    private Float upperLimit;

    /**
     * 下限
     */
    @Column(name = "LOWER_LIMIT")
    private Float lowerLimit;

    /**
     * 电表标识码
     */
    @Column(name = "IDENTIFIER")
    private String identifier;

    /**
     * 累时器标识码
     */
    @Column(name = "TIMESUM_RECORDER_IDENTIFIER")
    private String timeSumRecorderIdentifier;

    /**
     * 电表读数标识码
     */
    @Column(name = "AMMETER_RECORD_IDENTIFIER")
    private String ammeterRecordIndentifier;

    public String getTimeSumRecorderIdentifier() {
        return timeSumRecorderIdentifier;
    }

    public void setTimeSumRecorderIdentifier(String timeSumRecorderIdentifier) {
        this.timeSumRecorderIdentifier = timeSumRecorderIdentifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

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


















