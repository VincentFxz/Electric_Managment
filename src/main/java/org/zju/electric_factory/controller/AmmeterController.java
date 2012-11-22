package org.zju.electric_factory.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.entity.Role;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.CompanyManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserManager;
/**
 * 
 * @author vincent
 *
 */
@Controller
@RequestMapping("/ammeter")
@Transactional
public class AmmeterController {

	@Autowired
	private AmmeterManager ammeterManager;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ProjectAmmeterManager projectAmmeterManager;
	
	@Autowired
	private ProjectManager projectManager;
	

	@RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
	public @ResponseBody
	List<Ammeter> listAmmeters() {
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
				return ammeterManager.getAllAmmeter();
			}
		}
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(+id)")
	public @ResponseBody
	List<Ammeter> listAmmeterAscbyId() {
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
				return ammeterManager.getAllAmmeterAscById();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
		

	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(-id)")
	public @ResponseBody
	List<Ammeter> listAmmeterDescbyId() {
		
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
				return ammeterManager.getAllAmmeterDescById();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
		
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(+name)")
	public @ResponseBody
	List<Ammeter> listAmmeterAscbyName() {
		
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
				return ammeterManager.getAllAmmeterAscByName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(-name)")
	public @ResponseBody
	List<Ammeter> listAmmeterDescByName() {
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
				return ammeterManager.getAllAmmeterDescByName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(-pumpName)")
	public @ResponseBody
	List<Ammeter> listAmmeterDescByPumpName() {
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
				return ammeterManager.getAllAmmeterDescByPumpName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(+pumpName)")
	public @ResponseBody
	List<Ammeter> listAmmeterAscbyPumpName() {
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
				return ammeterManager.getAllAmmeterAscByPumpName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(-projectName)")
	public @ResponseBody
	List<Ammeter> listAmmeterDescbyProjectName() {
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
				return ammeterManager.getAllAmmeterDescByProjectName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(+projectName)")
	public @ResponseBody
	List<Ammeter> listAmmeterAscbyProjectName() {
		
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
				return ammeterManager.getAllAmmeterAscByProjectName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(-companyName)")
	public @ResponseBody
	List<Ammeter> listAmmeterDescbyCompanyName() {
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
				return ammeterManager.getAllAmmeterDescByCompanyName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "sort(+companyName)")
	public @ResponseBody
	List<Ammeter> listAmmeterAscbyCompanyName() {
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
				return ammeterManager.getAllAmmeterDescByCompanyName();
			}
		}
		
		return ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/list/{id}", headers = "Accept=application/json")
	public @ResponseBody
	boolean deleteAmmeterbyId(@PathVariable String id) {
		List<ProjectAmmeterLink> projectAmmeterLinks = projectAmmeterManager.getProjectAmmeterLinkByAmmeterId(id);
		if(null != projectAmmeterLinks){
			for(ProjectAmmeterLink projectAmmeterLink : projectAmmeterLinks){
				projectAmmeterManager.deleteProjectAmmeterLink(projectAmmeterLink.getId());
			}
		}
		ammeterManager.deleteAmmeterbyId(id);
		return true;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/list/{id}", headers = "Accept=application/json")
	public @ResponseBody
	Ammeter updateAmmeter(@PathVariable String id, @RequestBody Ammeter ammeter) {
		ammeterManager.editAmmeter(ammeter);
		return ammeter;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/list")
	public @ResponseBody
	Ammeter addAmmeterByPost(Ammeter ammeter) {
		Project project = projectManager.getProjectByProjectName(ammeter.getProjectName());
		ammeterManager.add(ammeter);
		if(null != project){
			ProjectAmmeterLink projectAmmeterLink = new ProjectAmmeterLink();
			projectAmmeterLink.setAmmeterId(ammeter.getId());
			projectAmmeterLink.setProjectId(project.getId());
			projectAmmeterManager.addProjectAmmeterLink(projectAmmeterLink);
		}
		return ammeter;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
	public @ResponseBody
	Ammeter addAmmeter(Ammeter ammeter) {
		Project project = projectManager.getProjectByProjectName(ammeter.getProjectName());
		ammeterManager.add(ammeter);
		if(null != project){
			ProjectAmmeterLink projectAmmeterLink = new ProjectAmmeterLink();
			projectAmmeterLink.setAmmeterId(ammeter.getId());
			projectAmmeterLink.setProjectId(project.getId());
			projectAmmeterManager.addProjectAmmeterLink(projectAmmeterLink);
		}
		
		return ammeter;
	}
}
