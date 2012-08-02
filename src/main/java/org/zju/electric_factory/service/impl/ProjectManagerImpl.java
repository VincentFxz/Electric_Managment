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
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.entity.UserCompanyLink;
import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.service.AmmeterManager;
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
    

    @Override
    public List<Project> getProjectsOwnByUser(Long userId) {
        List<Project> projectsOwnByUser=null;
        List<UserProjectLink> userProjectLinks = userProjectLinkDAO
                .getByUserId(userId);
        if(null!=userProjectLinks){
            projectsOwnByUser=new ArrayList<Project>();
            for(UserProjectLink userProjectLink: userProjectLinks){
                Project project=projectDAO.getById(userProjectLink.getProjectId());
                projectsOwnByUser.add(project);
            }
        }
        
        return projectsOwnByUser;
    }
    
    @Override
    @SuppressWarnings("null")
    public List<Project> getProjectsOwnByCompany(Long companyId) {
        List<Project> projectsOwnByCompany=null;
        List<UserCompanyLink> userCompanyLinks=userCompanyLinkDAO.getByCompanyId(companyId);
        if(null!=userCompanyLinks){
            for(UserCompanyLink userCompanyLink : userCompanyLinks){
                List<Project> projectsOwnByUser = this.getProjectsOwnByUser(userCompanyLink.getUserId());
                if(null!=projectsOwnByUser){
                    projectsOwnByCompany.addAll(projectsOwnByUser);
                }
            }
        }
        return projectsOwnByCompany;
    }

}
