package org.zju.electric_factory.vo;

public class LastAmmeterStatusVo {
	private String ammeterName;
	private Float ammeterValue;
	private Float timeSum;
	private Float costPerHour;
	private String warningStatus;
	public String getAmmeterName() {
		return ammeterName;
	}
	public void setAmmeterName(String ammeterName) {
		this.ammeterName = ammeterName;
	}
	public Float getAmmeterValue() {
		return ammeterValue;
	}
	public void setAmmeterValue(Float ammeterValue) {
		this.ammeterValue = ammeterValue;
	}
	public Float getTimeSum() {
		return timeSum;
	}
	public void setTimeSum(Float timeSum) {
		this.timeSum = timeSum;
	}
	public Float getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(Float costPerHour) {
		this.costPerHour = costPerHour;
	}
	public String getWarningStatus() {
		return warningStatus;
	}
	public void setWarningStatus(String warningStatus) {
		this.warningStatus = warningStatus;
	}

}
