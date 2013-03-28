package org.zju.electric_factory.vo;

import java.util.Date;

public class EnergyCost {
	private String ammeterName;
	private float energyCost;
	private Date computeDate;
	
	public String getAmmeterName() {
		return ammeterName;
	}

	public void setAmmeterName(String ammeterName) {
		this.ammeterName = ammeterName;
	}

	public Date getComputeDate() {
		return computeDate;
	}

	public void setComputeDate(Date computeDate) {
		this.computeDate = computeDate;
	}

	public float getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(float energyCost) {
		this.energyCost = energyCost;
	}

}
