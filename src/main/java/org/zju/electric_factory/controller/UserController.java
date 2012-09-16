package org.zju.electric_factory.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.Role;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.entity.UserCompanyLink;
import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.service.CompanyManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserCompanyLinkManager;
import org.zju.electric_factory.service.UserManager;
import org.zju.electric_factory.service.UserProjectLinkManager;
import org.zju.electric_factory.vo.UserVO;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

	@Autowired
	private UserManager userManager;
	@Autowired
	private CompanyManager companyManager;
	@Autowired
	private ProjectManager projectManager;
	@Autowired
	private UserProjectLinkManager userProjectLinkManager;
	@Autowired
	private UserCompanyLinkManager userCompanyLinkManager;

	@RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
	public @ResponseBody List<UserVO> getUsers() {
		SecurityUtils.getSubject().getPrincipal();
		List<User> users = userManager.getAllUsers();
		List<UserVO> userVOs = new ArrayList<UserVO>();
		for(User user : users){
			UserVO userVo	= new UserVO();
			Company company = companyManager.getCompanyByUserId(user.getId());
			if(null != company){
				userVo.setCompany(company.getCompanyName());
			}
			userVo.setUsername(user.getUsername());
			userVo.setId(user.getId());
			userVo.setEmail(user.getEmail());
			StringBuilder sb = new StringBuilder();
			for(Role role : user.getRoles()){
				sb.append(role.getName());
				sb.append(" ");
			}
			userVo.setRoles(sb.toString());
			
			List<Project> projectOwnByUserList = projectManager.getProjectsOwnByUser(user.getId());
			sb = new StringBuilder();
			for(Project project : projectOwnByUserList){
				sb.append(project.getProjectName());
				sb.append(" ");
			}
			userVo.setProject(sb.toString());
			
			userVOs.add(userVo);
			
		}
		return userVOs;
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers ="Accept=application/json")
    public @ResponseBody User updateUser(@PathVariable String id,@RequestBody User user){
        userManager.updateUser(user);
        return user;
    }
	
	@RequestMapping(method=RequestMethod.GET, value="/list/{id}", headers="Accept=application/json")
    public @ResponseBody User getUser(@PathVariable String id){
		return userManager.getUser(Long.parseLong(id));
    }
	
	@RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody UserVO addUser(UserVO userVO,String password){
		User user = new User();
		user.setEmail(userVO.getEmail());
		user.setUsername(userVO.getUsername());
		user.setPassword(password);
		userManager.createUser(user);
		if(null != userVO.getProject()){
			Project project = projectManager.getProjectByProjectName(userVO.getProject());
			UserProjectLink userProjectLink = new UserProjectLink();
			userProjectLink.setProjectId(project.getId());
			userProjectLink.setUserId(user.getId());
			userProjectLinkManager.add(userProjectLink);
		}
		
		if(null != userVO.getCompany()){
			Company company = companyManager.getCompanyByCompanyName(userVO.getCompany());
			UserCompanyLink userCompanyLink = new UserCompanyLink();
			userCompanyLink.setCompanyId(company.getId());
			userCompanyLink.setUserId(user.getId());
			userCompanyLinkManager.add(userCompanyLink);
		}
		return userVO;
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/list/{id}", headers="Accept=application/json")
    public @ResponseBody boolean deleteUser(@PathVariable String id){
		userManager.deleteUser(Long.parseLong(id));
		return true;
    }
	
	@RequestMapping(method=RequestMethod.PUT,value="/add",headers="Accept=application/json")
    public @ResponseBody boolean putUser(UserVO userVO){
		return true;
    }
	
	

}
