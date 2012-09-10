package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.CompanyProjectLink;

public interface CompanyProjectLinkDAO {
	public List<CompanyProjectLink> getAll();
	public List<CompanyProjectLink> getByCompanyId(Long companyId);
	public List<CompanyProjectLink> getByProjectId(Long projectId);
	public CompanyProjectLink getById(Long id);
	public void add(CompanyProjectLink companyProjectLink);
	public void deleteById(Long id);
	public void delete(CompanyProjectLink companyProjectLink);
	public void update(CompanyProjectLink companyProjectLink);

}
