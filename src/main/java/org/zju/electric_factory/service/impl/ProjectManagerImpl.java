package org.zju.electric_factory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.AmmeterDAO;
import org.zju.electric_factory.dao.ProjectAmmeterLinkDAO;
import org.zju.electric_factory.dao.ProjectDAO;
import org.zju.electric_factory.dao.UserCompanyLinkDAO;
import org.zju.electric_factory.dao.UserProjectLinkDAO;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.entity.UserCompanyLink;
import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.CompanyProjectManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;

@Service
@Transactional
public class ProjectManagerImpl implements ProjectManager {

    @Autowired
    private UserProjectLinkDAO userProjectLinkDAO;

    @Autowired
    private ProjectDAO projectDAO;
    
    @Autowired
    private UserCompanyLinkDAO userCompanyLinkDAO;
    
    @Autowired 
    private CompanyProjectManager companyProjectManager;
    
    @Autowired
    private ProjectAmmeterManager projectAmmeterManager; 
    

    @Override
    public List<Project> getProjectsOwnByUser(Long userId) {
        List<Project> projectsOwnByUser=null;
        List<UserProjectLink> userProjectLinks = userProjectLinkDAO
                .getByUserId(userId);
        if(null!=userProjectLinks){
            projectsOwnByUser=new ArrayList<Project>();
            for(UserProjectLink userProjectLink: userProjectLinks){
                Project project=projectDAO.getById(userProjectLink.getProjectId());
                projectDAO.init(project);
                projectsOwnByUser.add(project);
            }
        }
        
        return projectsOwnByUser;
    }
    
    @Override
    @SuppressWarnings("null")
    public List<Project> getProjectsOwnByCompany(String companyId) {
    	List<Project> projectForCompanyList = new ArrayList<Project>();
		List<CompanyProjectLink> cpLinksForCompany = companyProjectManager.getCompanyProjectLinksByCompanyId(companyId);
		if(null != cpLinksForCompany){
			
			for(CompanyProjectLink cpLink : cpLinksForCompany){
				Project project = projectDAO.getById(cpLink.getProjectId());
				projectForCompanyList.add(project);
			}
		}
		return projectForCompanyList;
//        List<Project> projectsOwnByCompany=null;
//        List<UserCompanyLink> userCompanyLinks=userCompanyLinkDAO.getByCompanyId(companyId);
//        if(null!=userCompanyLinks){
//            for(UserCompanyLink userCompanyLink : userCompanyLinks){
//                List<Project> projectsOwnByUser = this.getProjectsOwnByUser(userCompanyLink.getUserId());
//                if(null!=projectsOwnByUser){
//                    projectsOwnByCompany.addAll(projectsOwnByUser);
//                }
//            }
//        }
//        return projectsOwnByCompany;
    }


	@Override
	public List<Project> getProjects() {
		return projectDAO.getAllProjects();
	}

	@Override
	public List<Project> getProjectsAscById() {
		return projectDAO.getAllByOrder("id", true);
	}

	@Override
	public List<Project> getProjectsDescById() {
		return projectDAO.getAllByOrder("id", false);
	}

	@Override
	public List<Project> getProjectsAscByName() {
		return projectDAO.getAllByOrder("projectName", true);
	}

	@Override
	public List<Project> getProjectsDescByName() {
		return projectDAO.getAllByOrder("projectName", false);
	}

	@Override
	public List<Project> getProjectsAscByStartDate() {
		return projectDAO.getAllByOrder("startDate", true);
	}

	@Override
	public List<Project> getProjectsDescByStartDate() {
		return projectDAO.getAllByOrder("startDate", false);
	}

	@Override
	public List<Project> getProjectsAscByEndDate() {
		return projectDAO.getAllByOrder("endDate", true);
	}

	@Override
	public List<Project> getProjectsDescByEndDate() {
		return projectDAO.getAllByOrder("endDate", false);
	}

	@Override
	public void deleteProjectById(String id) {
		if(null != id){
			projectDAO.delete(Long.parseLong(id));
		}
	}

	@Override
	public void editProject(Project project) {
		projectDAO.update(project);
		
	}

	@Override
	public void add(Project project) {
		projectDAO.add(project);
		
	}

	@Override
	public Project getById(Long id) {
		return projectDAO.getById(id);
	}

	@Override
	public Project getProjectByProjectName(String projectName) {
		return projectDAO.getByProjectName(projectName);
	}

	@Override
	public Project getProjectByAmmeterId(Long ammeterId) {
		// TODO Auto-generated method stub
		ProjectAmmeterLink projectAmmeterLink = projectAmmeterManager.getProjectAmmeterLinkByAmmeterId(String.valueOf(ammeterId));
		if(null != projectAmmeterLink){
			return projectDAO.getById(projectAmmeterLink.getProjectId());
		}
		return null;
	}

}
