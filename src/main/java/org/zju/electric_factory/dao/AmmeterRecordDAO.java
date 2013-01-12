package org.zju.electric_factory.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;

public interface AmmeterRecordDAO {
	/**
	 * get record by id
	 */
	public AmmeterRecord get(Long id);
	
	/**
	 *get record by date period 
	 */
	public List<AmmeterRecord> getByPeriod(Date startDate,Date endDate);
	
	public List<AmmeterRecord> getByAmmeterIdInPeriod(Long ammeterId, Date startDate, Date endDate);
	
	/*
	 *add a record 
	 */
	public void add(AmmeterRecord ammeterRecord);
	/**
	 * get all
	 */
	public List<AmmeterRecord> getAll();
	/**
	 * get by ammeter
	 */
	public List<AmmeterRecord> getByAmmeter(Long ammeterId);
	
	/**
	 * get ammeter record by order
	 * @param ColumnToSort
	 * @param isAsc
	 * @return
	 */
	public List<AmmeterRecord> getByOrder(String ColumnToSort, boolean isAsc);
	

}
