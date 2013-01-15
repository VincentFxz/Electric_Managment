package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.vo.ProjectAmmeterVO;


@Controller
@RequestMapping("pa")
public class ProjectAmmeterController {
	
	@Autowired
	private ProjectAmmeterManager projectAmmeterManager;
	
	@Autowired 
	private AmmeterManager ammeterManager;
	
	@Autowired
	private ProjectManager projectManager;
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
	public @ResponseBody List<ProjectAmmeterVO> listProjectAmmeters(){
		return projectAmmeterManager.getAllProjectAmmeterLinkForView();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+ammeterName)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersAscbyAmmeterName(){
		return projectAmmeterManager.getAllProjectAmmeterAscByAmmeterNameForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-ammeterName)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersDescByCompnayName(){
        return projectAmmeterManager.getAllProjectAmmeterDescByAmmeterNameForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+ammeterId)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersAscByAmmeterId(){
        return projectAmmeterManager.getAllProjectAmmeterAscByAmmeterIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-ammeterId)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersDescByAmmeterId(){
        return projectAmmeterManager.getAllProjectAmmeterDescByAmmeterIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectId)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersAscbyProjectId(){
		return projectAmmeterManager.getAllProjectAmmeterAscByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectId)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersDescByProjectId(){
        return projectAmmeterManager.getAllProjectAmmeterDescByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectName)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersAscbyProjectName(){
        return projectAmmeterManager.getAllProjectAmmeterAscByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectName)")
    public @ResponseBody List<ProjectAmmeterVO> listProjectAmmetersDescByProjectName(){
        return projectAmmeterManager.getAllProjectAmmeterDescByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteProjectAmmeterbyId(@PathVariable String id){
    	if(null != id){
    		projectAmmeterManager.deleteProjectAmmeterLink(Long.parseLong(id));
    	}
        return true;
    }
    
    
//    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
//    public @ResponseBody ProjectAmmeterVO updateAmmeter(@PathVariable String id,@ReqcuestBody ProjectAmmeterVO ammeter){
//        ProjectAmmeterLink projectAmmeterLink = projectAmmeterManager.getProjectAmmeterLinkById(id);
//    	projectAmmeterManager.edit(projectAmmeterLink);
//        return ammeter;
//    }
    
    @RequestMapping(method=RequestMethod.POST,value="/list",headers="Accept=application/json")
    public @ResponseBody ProjectAmmeterVO addAmmeter(@RequestBody ProjectAmmeterVO projectAmmeterVO ){
    	ProjectAmmeterLink projectAmmeterLink = new ProjectAmmeterLink();
    	Ammeter ammeter = ammeterManager.getAmmeterByName(projectAmmeterVO.getAmmeterName());
    	Project project = projectManager.getProjectByProjectName(projectAmmeterVO.getProjectName());
    	if((null != ammeter)&&(null != project)){
    		projectAmmeterLink.setAmmeterId(ammeter.getId());
        	projectAmmeterLink.setProjectId(project.getId());
            projectAmmeterManager.addProjectAmmeterLink(projectAmmeterLink);
            return projectAmmeterVO;
    	}
    	return null;
    	
    }
}
