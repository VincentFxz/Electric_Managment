package org.zju.electric_factory.vo;

public class AmmeterGPRSVO {
	private Long id;
	private Long ammeterId;
	private Long gprsId;
	private String ammeterName;
	private String gprsName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAmmeterId() {
		return ammeterId;
	}
	public void setAmmeterId(Long ammeterId) {
		this.ammeterId = ammeterId;
	}
	public Long getGprsId() {
		return gprsId;
	}
	public void setGprsId(Long gprsId) {
		this.gprsId = gprsId;
	}
	public String getAmmeterName() {
		return ammeterName;
	}
	public void setAmmeterName(String ammeterName) {
		this.ammeterName = ammeterName;
	}
	public String getGprsName() {
		return gprsName;
	}
	public void setGprsName(String gprsName) {
		this.gprsName = gprsName;
	}

}
