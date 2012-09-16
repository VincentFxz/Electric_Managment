package org.zju.electric_factory.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.UserProjectLinkDAO;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserManager;
import org.zju.electric_factory.service.UserProjectLinkManager;
import org.zju.electric_factory.vo.UserProjectVO;

@Service
@Transactional
public class UserProjectLinkManagerImpl implements UserProjectLinkManager{
	
	@Autowired
	private UserProjectLinkDAO userProjectLinkDAO;
	
	@Autowired
	private ProjectManager projectManager;
	
	@Autowired
	private UserManager userManager;
	
	@Override
	public UserProjectLink getById(String id) {
		if(null != id){
			return userProjectLinkDAO.getById(Long.parseLong(id));
		}
		return null;
		
	}

	@Override
	public List<UserProjectLink> getByUserId(String id) {
		if(null != id){
			return userProjectLinkDAO.getByUserId(Long.parseLong(id));
		}
		
		return null;
		
	}

	@Override
	public List<UserProjectLink> getByProjectId(String id) {
		if(null != id){
			return userProjectLinkDAO.getByProjectId(Long.parseLong(id));
		}
		return null;
		
	}

	@Override
	public List<UserProjectLink> getAll() {
		return getAll();
	}

	@Override
	public void add(UserProjectLink userProjectLink) {
		userProjectLinkDAO.add(userProjectLink);
	}

	@Override
	public List<UserProjectVO> getAllUserProjectLinkForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
			
		}
		return userProjectVOs;
	}

	@Override
	public void addUserProjectLink(UserProjectLink userProjectLink) {
		userProjectLinkDAO.add(userProjectLink);
	}

	@Override
	public void deleteUserProjectLink(Long id) {
		userProjectLinkDAO.deleteById(id);
	}

	@Override
	public List<UserProjectVO> getAllUserProjectAscByUserIdForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o1.getUserId() > o2.getUserId() ? 0 : 1;
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectDescByUserIdForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return (o1.getUserId() > o2.getUserId()) ? 0 : 1;
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectAscByProjectIdForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o1.getProjectId() > o2.getProjectId() ? 0 : 1;
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectDescByProjectIdForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o2.getProjectId() > o1.getProjectId() ? 0 : 1;
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectAscByUserNameForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o1.getUserName().compareTo(o2.getUserName());
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectDescByUserNameForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
			
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o2.getUserName().compareTo(o1.getUserName());
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectAscByProjectNameForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
			
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o1.getProjectName().compareTo(o2.getProjectName());
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public List<UserProjectVO> getAllUserProjectDescByProjectNameForView() {
		List<UserProjectVO> userProjectVOs = new ArrayList<UserProjectVO>();
		
		List<UserProjectLink> allUserProjectLinks = userProjectLinkDAO.getAll();
		for(UserProjectLink userProjectLink : allUserProjectLinks){
			
			User user = userManager.getUser(userProjectLink.getUserId());
			Project project = projectManager.getById(userProjectLink.getProjectId());
			
			UserProjectVO userProjectVO = new UserProjectVO();
			userProjectVO.setId(userProjectLink.getId());
			userProjectVO.setUserId(user.getId());
			userProjectVO.setUserName(user.getUsername());
			userProjectVO.setProjectId(project.getId());
			userProjectVO.setProjectName(project.getProjectName());
			
			userProjectVOs.add(userProjectVO);
			
		}
		
		Collections.sort(userProjectVOs, new Comparator<UserProjectVO>() {

			@Override
			public int compare(UserProjectVO o1, UserProjectVO o2) {
				return o2.getProjectName().compareTo(o1.getProjectName());
			}
			
		});
		
		return userProjectVOs;
	}

	@Override
	public void deleteUserProjectLink(UserProjectLink userProjectLink) {
		// TODO Auto-generated method stub
		
	}

}
