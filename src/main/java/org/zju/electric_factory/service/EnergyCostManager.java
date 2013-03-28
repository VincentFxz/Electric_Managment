package org.zju.electric_factory.service;

import java.util.Date;
import java.util.List;

import org.zju.electric_factory.vo.EnergyCost;

public interface EnergyCostManager {
	public List<EnergyCost> getAmmeterDailyEnergyCostsDayByDate(Long ammeterId, Date startDate, Date endDate);
	public EnergyCost getAmmeterEneryCostByDate(Long ammeterId, Date date);

}
