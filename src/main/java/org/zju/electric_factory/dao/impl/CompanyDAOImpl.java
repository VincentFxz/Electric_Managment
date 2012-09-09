package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.CompanyDAO;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Company;

@Repository
public class CompanyDAOImpl extends HibernateDAO<Company, Long> implements CompanyDAO {

	@Override
	public List<Company> getAllCompanies() {
		return super.getAll();
	}

	@Override
	public Company getCompanyById(Long id) {
		return super.findUniqueBy("id", id);
	}

	@Override
	public List<Company> getAllByOrder(String columnToSort, boolean isAsc) {
		if(isAsc){
			return super.getAll(columnToSort, true);
		}else{
			return super.getAll(columnToSort, false);
		}
	}
	
	public void delete(Company company) {
		super.delete(company);
	}
	/**
	 * delete by id
	 */
	public void delete(Long id){
		super.delete(id);
	}
	
	public void update(Company company){
		super.save(company);
	}

	@Override
	public void add(Company company) {
		super.save(company);
	}
	

}
