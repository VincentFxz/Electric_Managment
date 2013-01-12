package org.zju.electric_factory.vo;

import java.util.Date;


public class SaveComputationVO {
	
	private String ammeterName;
	private Date startDate;
	private Date endDate;
	private Float startValue;
	private Float endValue;
	private Float startTimeSum;
	private Float endTimeSum;
	private Float sensorRate;
	private Float formerCost;
    private Float realCost;
    private Float eletricSave;
    private Float eletricCharge;
    private Float partsRatio;
    private Float bonus;
    public String getAmmeterName() {
		return ammeterName;
	}
	public void setAmmeterName(String ammeterName) {
		this.ammeterName = ammeterName;
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
	public Float getFormerCost() {
		return formerCost;
	}
	public void setFormerCost(Float formerCost) {
		this.formerCost = formerCost;
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
	public Float getBonus() {
		return bonus;
	}
	public void setBonus(Float bonus) {
		this.bonus = bonus;
	}
	

}
