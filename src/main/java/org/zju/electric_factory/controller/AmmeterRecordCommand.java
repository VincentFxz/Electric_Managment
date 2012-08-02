package org.zju.electric_factory.controller;

import java.util.Date;

import org.springframework.stereotype.Component;

public class AmmeterRecordCommand {
	/**
	 * 记录号
	 */
	private int id;
	/**
	 * 记录的电表名称
	 */
	private String ammeterName;
	/**
	 * 记录的时间
	 */
	private Date recordDate;
	/**
	 * 记录的数值
	 */
	private float ammeterValue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAmmeterName() {
		return ammeterName;
	}
	public void setAmmeterName(String ammeterName) {
		this.ammeterName = ammeterName;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public float getAmmeterValue() {
		return ammeterValue;
	}
	public void setAmmeterValue(float ammeterValue) {
		this.ammeterValue = ammeterValue;
	} 
	
	
	
	
}
