package org.zju.electric_factory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.entity.Ammeter;

public interface AmmeterDAO {
	/**
	 * get ammeter by project
	 */
	public List<Ammeter> getByProject(String project);
	/**
	 * get ammeter by company
	 */
	public List<Ammeter> getByCompany(String company);
	/**
	 * get all 
	 */
	public List<Ammeter> getAll();
	
	/**
	 *  get ammeter by id
	 */
	public Ammeter get(Long id);
	
	/**
	 * get ammeter by name
	 */
	public List<Ammeter> getByName(String name);
	
	/**
	 *  upate ammeter
	 */
	public void update(Ammeter ammeter);
	
	/**
	 * add a ammeter
	 */
	public void add(Ammeter ammeter);
	
	/**
	 * delete
	 */
	public void delete(Ammeter ammeter);
	
	public void delete(Long id);
	
	public List<Ammeter> getByOrder(String ColumnToSort, boolean isAsc);

}
