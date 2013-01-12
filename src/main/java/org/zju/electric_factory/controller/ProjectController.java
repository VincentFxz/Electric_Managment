package org.zju.electric_factory.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.entity.Role;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.CompanyManager;
import org.zju.electric_factory.service.CompanyProjectManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserManager;
import org.zju.electric_factory.vo.ProjectVO;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectManager projectManager;
	
	@Autowired
	private ProjectAmmeterManager projectAmmeterManager;
	
	@Autowired
	private CompanyProjectManager companyProjectManager;
	
	@Autowired
	private CompanyManager companyManager;
	
	@Autowired
	private UserManager userManager;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/company/{companyId}", headers = "Accept=application/json")
	public @ResponseBody List<ProjectVO> listProjectsByCompanyId(@PathVariable String companyId){
		List<Project> projects = projectManager.getProjectsOwnByCompany(companyId);
		List<ProjectVO> projectVOs = null;
		if(null != projects){
			projectVOs = new ArrayList<ProjectVO>();
			for(Project project : projects){
				ProjectVO projectVO = new ProjectVO();
				projectVO.setId(project.getId());
				projectVO.setEndDate(project.getEndDate());
				projectVO.setProjectDescription(project.getProjectDescription());
				projectVO.setProjectName(project.getProjectName());
				projectVO.setStartDate(project.getStartDate());
				projectVO.setPartsRatio(project.getPartsRatio());
				projectVO.setElectricityCharge(project.getElectricityCharge());
				projectVOs.add(projectVO);
			}
		}
		return projectVOs;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/{id}", headers = "Accept=application/json")
	public @ResponseBody ProjectVO getProjectById(@PathVariable String id){
		if(null != id){
			Project project = projectManager.getById(Long.parseLong(id));
			ProjectVO projectVO = new ProjectVO();
			projectVO.setId(project.getId());
			projectVO.setEndDate(project.getEndDate());
			projectVO.setProjectDescription(project.getProjectDescription());
			projectVO.setProjectName(project.getProjectName());
			projectVO.setStartDate(project.getStartDate());
			return projectVO;
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
	public @ResponseBody List<Project> listProjects(){
		boolean userHasAdminRole = false;
		User currentUser = userManager.getCurrentUser();
		Set<Role> userRoles = currentUser.getRoles();
		if (null != userRoles) {
			for (Role role : userRoles) {
				if ("admin".equals(role.getName())) {
					userHasAdminRole = true;
				}
			}
			if (userHasAdminRole) {
				return projectManager.getProjects();
			}
		}
		
		return projectManager.getProjectsOwnByUser(currentUser.getId());
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+id)")
    public @ResponseBody List<Project> listProjectsAscbyId(){
    	return projectManager.getProjectsAscById();
    	        
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-id)")
    public @ResponseBody List<Project> listProjectsDescbyId(){
        return projectManager.getProjectsDescById();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectName)")
    public @ResponseBody List<Project> listProjectsAscbyName(){
        return projectManager.getProjectsAscByName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectName)")
    public @ResponseBody List<Project> listProjectsDescByName(){
        return projectManager.getProjectsDescByName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+startDate)")
    public @ResponseBody List<Project> listProjectsAscbyStartDate(){
        return projectManager.getProjectsAscByStartDate();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-startDate)")
    public @ResponseBody List<Project> listProjectsDescByStartDate(){
        return projectManager.getProjectsDescByStartDate();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+endDate)")
    public @ResponseBody List<Project> listProjectsAscbyEndDate(){
        return projectManager.getProjectsAscByEndDate();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-endDate)")
    public @ResponseBody List<Project> listProjectsDescByEndDate(){
        return projectManager.getProjectsDescByEndDate();
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteProjectbyId(@PathVariable String id){
    	List<ProjectAmmeterLink> projectAmmeterLinks = projectAmmeterManager.getProjectAmmeterLinkByProjectId(id);
    	if(null != projectAmmeterLinks){
    		for(ProjectAmmeterLink projectAmmeterLink : projectAmmeterLinks){
    			projectAmmeterManager.deleteProjectAmmeterLink(projectAmmeterLink.getId());
    		}
    	}
        projectManager.deleteProjectById(id);
        return true;
    }
    
    
    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody Project updateAmmeter(@PathVariable String id,@RequestBody Project project){
        projectManager.editProject(project);
        return project;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody Project addAmmeter(Project project , String companyName){
    	projectManager.add(project);
    	if(null != companyName){
    		CompanyProjectLink companyProjectLink = new CompanyProjectLink();
    		Company company = companyManager.getCompanyByCompanyName(companyName);
    	
    		if(null != company){
    			companyProjectLink.setCompanyId(company.getId());
    			companyProjectLink.setProjectId(project.getId());
    			companyProjectManager.addCompanyProjectLink(companyProjectLink);
    		}
    	}
        return project;
    }

}
