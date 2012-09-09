package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Company;

public interface CompanyDAO {
	public List<Company> getAllCompanies();
	public Company getCompanyById(Long id);
	public List<Company> getAllByOrder(String columnToSort, boolean isAsc);
	/**
	 * delete
	 */
	public void delete(Company company);
	
	public void delete(Long id);
	
	public void update(Company company);
	
	public void add(Company company);
	
}
