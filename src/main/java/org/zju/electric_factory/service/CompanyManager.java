package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.Company;

public interface CompanyManager {
	
	public Company getCompanyByUserId(Long id);
	public Company getCompanyByCompanyName(String CompanyName);
	public List<Company> getCompanies();
	public List<Company> getCompaniesAscById();
	public List<Company> getCompaniesDescById();
	public List<Company> getCompaniesAscByName();
	public List<Company> getCompanesDescByName();
	public void deleteCompanyById(String id);
	public void editCompany(Company company);
	public void add(Company company);
	public Company getById(Long id);
}
