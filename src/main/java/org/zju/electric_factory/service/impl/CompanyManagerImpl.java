package org.zju.electric_factory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zju.electric_factory.dao.CompanyDAO;
import org.zju.electric_factory.dao.UserCompanyLinkDAO;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.UserCompanyLink;
import org.zju.electric_factory.service.CompanyManager;

@Component
public class CompanyManagerImpl implements CompanyManager {

	@Autowired
	private UserCompanyLinkDAO userCompanyLinkDAO;
	@Autowired
	private CompanyDAO companyDAO;
	
	@Override
	public Company getCompanyByUserId(Long id) {
		Company company = null;
		List<UserCompanyLink> companyUserLink = userCompanyLinkDAO.getByUserId(id);
		if(null != companyUserLink){
			if(0 < companyUserLink.size()){
				Long companyId = companyUserLink.get(0).getCompanyId();
				company = companyDAO.getCompanyById(companyId);
			}
		}
		return company;
	}

	@Override
	public List<Company> getCompanies() {
		return companyDAO.getAllCompanies();
	}

	@Override
	public List<Company> getCompaniesAscById() {
		return companyDAO.getAllByOrder("id", true);
	}

	@Override
	public List<Company> getCompaniesDescById() {
		return companyDAO.getAllByOrder("id", false);
	}

	@Override
	public List<Company> getCompaniesAscByName() {
		return companyDAO.getAllByOrder("companyName", true);
	}

	@Override
	public List<Company> getCompanesDescByName() {
		return companyDAO.getAllByOrder("companyName", false);
	}

	@Override
	public void deleteCompanyById(String id) {
		if(null != id){
			Long companyId = Long.parseLong(id);
				companyDAO.delete(companyId);
		}
		
	}

	@Override
	public void editCompany(Company company) {
		companyDAO.update(company);
	}

	@Override
	public void add(Company company) {
		companyDAO.add(company);
	}

	@Override
	public Company getById(Long id) {
		return companyDAO.getCompanyById(id);
	}

	@Override
	public Company getCompanyByCompanyName(String companyName) {
		return companyDAO.getCompanyByName(companyName);
	}
	
}
