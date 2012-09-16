package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserManager;
import org.zju.electric_factory.service.UserProjectLinkManager;
import org.zju.electric_factory.vo.UserProjectVO;


@Controller
@RequestMapping("/up")
@Transactional
public class ProjectUserController {
	
	@Autowired
	private UserProjectLinkManager userProjectManager;
	
	@Autowired 
	private UserManager userManager;
	
	@Autowired
	private ProjectManager projectManager;
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
	public @ResponseBody List<UserProjectVO> listUserProjects(){
		return userProjectManager.getAllUserProjectLinkForView();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+userName)")
    public @ResponseBody List<UserProjectVO> listUserProjectsAscbyUserName(){
		return userProjectManager.getAllUserProjectAscByUserNameForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-userName)")
    public @ResponseBody List<UserProjectVO> listUserProjectsDescByCompnayName(){
        return userProjectManager.getAllUserProjectDescByUserNameForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+userId)")
    public @ResponseBody List<UserProjectVO> listUserProjectsAscByUserId(){
        return userProjectManager.getAllUserProjectAscByUserIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-userId)")
    public @ResponseBody List<UserProjectVO> listUserProjectsDescByUserId(){
        return userProjectManager.getAllUserProjectDescByUserIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectId)")
    public @ResponseBody List<UserProjectVO> listUserProjectsAscbyProjectId(){
		return userProjectManager.getAllUserProjectAscByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectId)")
    public @ResponseBody List<UserProjectVO> listUserProjectsDescByProjectId(){
        return userProjectManager.getAllUserProjectDescByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectName)")
    public @ResponseBody List<UserProjectVO> listUserProjectsAscbyProjectName(){
        return userProjectManager.getAllUserProjectAscByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectName)")
    public @ResponseBody List<UserProjectVO> listUserProjectsDescByProjectName(){
        return userProjectManager.getAllUserProjectDescByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteUserProjectbyId(@PathVariable String id){
    	if(null != id){
    		userProjectManager.deleteUserProjectLink(Long.parseLong(id));
    	}
        return true;
    }
    
    
//    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
//    public @ResponseBody UserProjectVO updateAmmeter(@PathVariable String id,@ReqcuestBody UserProjectVO user){
//        UserProjectLink userProjectLink = userProjectManager.getUserProjectLinkById(id);
//    	userProjectManager.edit(userProjectLink);
//        return user;
//    }
    
    @RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody UserProjectVO addAmmeter(UserProjectVO userProjectVO ){
    	UserProjectLink userProjectLink = new UserProjectLink();
    	User user = userManager.getUserByUserName(userProjectVO.getUserName());
    	Project project = projectManager.getProjectByProjectName(userProjectVO.getProjectName());
    	if((null != user)&&(null != project)){
    		userProjectLink.setUserId(user.getId());
        	userProjectLink.setProjectId(project.getId());
            userProjectManager.addUserProjectLink(userProjectLink);
            return userProjectVO;
    	}
    	return null;
    	
    }

}
