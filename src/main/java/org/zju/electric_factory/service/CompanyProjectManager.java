package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.vo.CompanyProjectVO;

public interface CompanyProjectManager {
	
	public List<CompanyProjectVO> getAllCompanyProjectLinkForView();
	public void addCompanyProjectLink(CompanyProjectLink companyProjectLink);
	public void deleteCompanyProjectLink(Long id);
	public void edit(CompanyProjectLink companyProjectLink);
	public CompanyProjectLink getCompanyProjectLinkById(String id);
	
	public List<CompanyProjectVO> getAllCompanyProjectAscByCompanyIdForView();
	public List<CompanyProjectVO> getAllCompanyProjectDescByCompanyIdForView();
	public List<CompanyProjectVO> getAllCompanyProjectAscByProjectIdForView();
	public List<CompanyProjectVO> getAllCompanyProjectDescByProjectIdForView();
	
	public List<CompanyProjectVO> getAllCompanyProjectAscByCompanyNameForView();
	public List<CompanyProjectVO> getAllCompanyProjectDescByCompanyNameForView();
	public List<CompanyProjectVO> getAllCompanyProjectAscByProjectNameForView();
	public List<CompanyProjectVO> getAllCompanyProjectDescByProjectNameForView();
	
	public List<CompanyProjectLink> getCompanyProjectLinksByCompanyId(String companyId);
	public List<CompanyProjectLink> getCompanyProjectLinksByCompanyId(Long companyId);

}
