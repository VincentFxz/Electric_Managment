package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.service.ProjectManager;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectManager projectManager;
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
	public @ResponseBody List<Project> listProjects(){
		return projectManager.getProjects();
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
        projectManager.deleteProjectById(id);
        return true;
    }
    
    
    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody Project updateAmmeter(@PathVariable String id,@RequestBody Project project){
        projectManager.editProject(project);
        return project;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody Project addAmmeter(Project project ){
        projectManager.add(project);
        return project;
    }

}
