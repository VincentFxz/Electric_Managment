package org.zju.electric_factory.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.CompanyProjectLinkDAO;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.service.CompanyManager;
import org.zju.electric_factory.service.CompanyProjectManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.vo.CompanyProjectVO;


@Service
@Transactional
public class CompanyProjectManagerImpl implements CompanyProjectManager{
	
	@Autowired
	private ProjectManager projectManager;
	
	@Autowired
	private CompanyManager companyManager;
	
	@Autowired
	private CompanyProjectLinkDAO companyProjectLinkDAO;

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectLinkForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
			
		}
		return companyProjectVOs;
	}

	@Override
	public void addCompanyProjectLink(CompanyProjectLink companyProjectLink) {
		companyProjectLinkDAO.add(companyProjectLink);
	}

	@Override
	public void deleteCompanyProjectLink(Long id) {
		companyProjectLinkDAO.deleteById(id);
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectAscByCompanyIdForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o1.getCompanyId() > o2.getCompanyId() ? 0 : 1;
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectDescByCompanyIdForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return (o1.getCompanyId() > o2.getCompanyId()) ? 0 : 1;
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectAscByProjectIdForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o1.getProjectId() > o2.getProjectId() ? 0 : 1;
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectDescByProjectIdForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o2.getProjectId() > o1.getProjectId() ? 0 : 1;
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectAscByCompanyNameForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o1.getCompanyName().compareTo(o2.getCompanyName());
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectDescByCompanyNameForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
			
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o2.getCompanyName().compareTo(o1.getCompanyName());
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectAscByProjectNameForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
			
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o1.getProjectName().compareTo(o2.getProjectName());
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public List<CompanyProjectVO> getAllCompanyProjectDescByProjectNameForView() {
		List<CompanyProjectVO> companyProjectVOs = new ArrayList<CompanyProjectVO>();
		
		List<CompanyProjectLink> allCompanyProjectLinks = companyProjectLinkDAO.getAll();
		for(CompanyProjectLink companyProjectLink : allCompanyProjectLinks){
			
			Company company = companyManager.getById(companyProjectLink.getCompanyId());
			Project project = projectManager.getById(companyProjectLink.getProjectId());
			
			CompanyProjectVO companyProjectVO = new CompanyProjectVO();
			companyProjectVO.setId(companyProjectLink.getId());
			companyProjectVO.setCompanyId(company.getId());
			companyProjectVO.setCompanyName(company.getCompanyName());
			companyProjectVO.setProjectId(project.getId());
			companyProjectVO.setProjectName(project.getProjectName());
			
			companyProjectVOs.add(companyProjectVO);
			
		}
		
		Collections.sort(companyProjectVOs, new Comparator<CompanyProjectVO>() {

			@Override
			public int compare(CompanyProjectVO o1, CompanyProjectVO o2) {
				return o2.getProjectName().compareTo(o1.getProjectName());
			}
			
		});
		
		return companyProjectVOs;
	}

	@Override
	public void edit(CompanyProjectLink companyProjectLink) {
		companyProjectLinkDAO.update(companyProjectLink);
		
	}

	@Override
	public CompanyProjectLink getCompanyProjectLinkById(String id) {
		if(null != id){
			return companyProjectLinkDAO.getById(Long.parseLong(id));
		}
		return null;
	}

}
