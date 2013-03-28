package org.zju.electric_factory.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SAVE_COMPUTATION_RECORD")
public class SaveComputationRecord {
	
    @Id
    @GeneratedValue
    private Long id;
	
	@Column (name = "AMMETER_NAME")
	private String ammeterName;
	
	@Column (name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "START_VALUE")
	private Float startValue;
	
	@Column (name = "END_VALUE")
	private Float endValue;
	
	@Column (name = "START_TIME_SUM")
	private Float startTimeSum;
	
	@Column (name = "END_TIME_SUM")
	private Float endTimeSum;
	
	@Column (name = "SENSOR_RATE")
	private Float sensorRate;
	
	@Column (name = "REAL_COST")
    private Float realCost;
	
	@Column (name = "ELECTRIC_SAVE")
    private Float eletricSave;
	
	@Column (name = "ELECTRIC_CHARGE_SAVE")
    private Float eletricChargeSave;
	
	public Float getElectricityCharge() {
		return electricityCharge;
	}

	public void setElectricityCharge(Float electricityCharge) {
		this.electricityCharge = electricityCharge;
	}
	@Column (name = "ELECTRIC_CHARGE")
    private Float eletricCharge;

	@Column (name = "PARTS_RATIO")
    private Float partsRatio;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "FORMER_COST")
	private Float formerCost;
	
	@Column(name = "ELECTRICITY_CHARGE")
	private Float electricityCharge;
	
	@Column (name = "STANDARD_COAL_RATIO")
	private Float standardCoalRatio;
	
	@Column (name = "COAL_SAVE")
	private Float coalSave;
	
	@Column (name = "THE_PARTY_BONUS")
    private Float thePartyBonus;
	
	@Column (name = "THE_OTHER_PARTY_BONUS")
	private Float theOtherPartyBouns;
	
	public String getAmmeterName() {
		return ammeterName;
	}

	public void setAmmeterName(String ammeterName) {
		this.ammeterName = ammeterName;
	}

	public Float getStartValue() {
		return startValue;
	}

	public void setStartValue(Float startValue) {
		this.startValue = startValue;
	}

	public Float getEndValue() {
		return endValue;
	}

	public void setEndValue(Float endValue) {
		this.endValue = endValue;
	}

	public Float getStartTimeSum() {
		return startTimeSum;
	}

	public void setStartTimeSum(Float startTimeSum) {
		this.startTimeSum = startTimeSum;
	}

	public Float getEndTimeSum() {
		return endTimeSum;
	}

	public void setEndTimeSum(Float endTimeSum) {
		this.endTimeSum = endTimeSum;
	}

	public Float getSensorRate() {
		return sensorRate;
	}

	public void setSensorRate(Float sensorRate) {
		this.sensorRate = sensorRate;
	}

	public Float getRealCost() {
		return realCost;
	}

	public void setRealCost(Float realCost) {
		this.realCost = realCost;
	}

	public Float getEletricSave() {
		return eletricSave;
	}

	public void setEletricSave(Float eletricSave) {
		this.eletricSave = eletricSave;
	}

	public Float getEletricChargeSave() {
		return eletricChargeSave;
	}

	public void setEletricChargeSave(Float eletricChargeSave) {
		this.eletricChargeSave = eletricChargeSave;
	}

	public Float getEletricCharge() {
		return eletricCharge;
	}

	public void setEletricCharge(Float eletricCharge) {
		this.eletricCharge = eletricCharge;
	}

	public Float getPartsRatio() {
		return partsRatio;
	}

	public void setPartsRatio(Float partsRatio) {
		this.partsRatio = partsRatio;
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

	public Float getFormerCost() {
		return formerCost;
	}

	public void setFormerCost(Float formerCost) {
		this.formerCost = formerCost;
	}


	public Float getStandardCoalRatio() {
		return standardCoalRatio;
	}

	public void setStandardCoalRatio(Float standardCoalRatio) {
		this.standardCoalRatio = standardCoalRatio;
	}

	public Float getThePartyBonus() {
		return thePartyBonus;
	}

	public void setThePartyBonus(Float thePartyBonus) {
		this.thePartyBonus = thePartyBonus;
	}

	public Float getTheOtherPartyBouns() {
		return theOtherPartyBouns;
	}

	public void setTheOtherPartyBouns(Float theOtherPartyBouns) {
		this.theOtherPartyBouns = theOtherPartyBouns;
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

	public Float getCoalSave() {
		return coalSave;
	}
	public void setCoalSave(Float coalSave) {
		this.coalSave = coalSave;
	}



}
