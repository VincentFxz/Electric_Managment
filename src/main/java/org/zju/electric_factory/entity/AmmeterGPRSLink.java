package org.zju.electric_factory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AG_LINK")
public class AmmeterGPRSLink {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "AMMETER_ID")
	private Long ammeterId;
	
	@Column(name = "GPRS_ID")
	private Long gprsId;

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
	
	
	
}
