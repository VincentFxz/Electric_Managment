package org.zju.electric_factory.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.AmmeterRecordManager;
import org.zju.electric_factory.service.EnergyCostManager;
import org.zju.electric_factory.vo.EnergyCost;

public class EnergyCostManagerImpl implements EnergyCostManager{
	
	@Autowired
	private AmmeterRecordManager ammeterRecordManager;
	@Autowired
	private AmmeterManager ammeterManager;

	@Override
	public List<EnergyCost> getAmmeterDailyEnergyCostsDayByDate(Long ammeterId,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnergyCost getAmmeterEneryCostByDate(Long ammeterId, Date date) {
		Ammeter ammeter = null;
		Float sensorRate = null;
		if(null != ammeterId){
			ammeter = ammeterManager.getAmmeterById(ammeterId);
			sensorRate = ammeter.getSensorRate();
//			List<AmmeterRecord> ammeterRecords = ammeterRecordManager.getAmmeterRecordByAmmeterIdInPeriod(ammeterId, startDate, endDate);
		}
		
		
		
		return null;
	}

}
