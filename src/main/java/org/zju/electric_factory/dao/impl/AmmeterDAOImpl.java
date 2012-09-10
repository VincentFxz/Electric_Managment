package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.AmmeterDAO;
import org.zju.electric_factory.entity.Ammeter;

@Repository
public class AmmeterDAOImpl extends HibernateDAO<Ammeter, Long> implements AmmeterDAO{
	

	public List<Ammeter> getAll(){
		return super.getAll();
	}
	/**
	 *  get ammeter by id
	 */
	public Ammeter get(Long id) {
		return super.get(id);
	}

	/**
	 * get ammeter by name
	 */
	public Ammeter getByName(String name) {
		return this.findUniqueBy("name", name);
	}

	/**
	 *  upate ammeter
	 */
	public void update(Ammeter ammeter) {
		super.save(ammeter);
	}

	/**
	 * add a ammeter
	 */
	public void add(Ammeter ammeter) {
		super.save(ammeter);
	}

	/**
	 * delete
	 */
	public void delete(Ammeter ammeter) {
		super.delete(ammeter);
	}
	/**
	 * delete by id
	 */
	public void delete(Long id){
		super.delete(id);
	}
	/**
	 * get by company
	 */
	public List<Ammeter> getByCompany(String company) {
	    return this.findBy("company", company);
	}
	/**
	 * get by project
	 */
	public List<Ammeter> getByProject(String project) {
	    return this.findBy("project", project);
	}
	
	public List<Ammeter> getByOrder(String columnToSort, boolean isAsc){
		return super.getAll(columnToSort,isAsc);
	}
}
