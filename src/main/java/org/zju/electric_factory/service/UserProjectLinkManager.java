package org.zju.electric_factory.service;

import java.util.List;


import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.vo.UserProjectVO;

public interface UserProjectLinkManager {
	
	public UserProjectLink getById(String id);
	public List<UserProjectLink> getByUserId(String id);
	public List<UserProjectLink> getByProjectId(String id);
	public List<UserProjectLink> getAll();
	public void addUserProjectLink(UserProjectLink userProjectLink);
	public List<UserProjectVO> getAllUserProjectLinkForView();
	public List<UserProjectVO> getAllUserProjectDescByUserNameForView();
	public List<UserProjectVO> getAllUserProjectAscByUserIdForView();
	public List<UserProjectVO> getAllUserProjectDescByUserIdForView();
	public List<UserProjectVO> getAllUserProjectAscByProjectIdForView();
	public List<UserProjectVO> getAllUserProjectDescByProjectIdForView();
	public void deleteUserProjectLink(UserProjectLink userProjectLink);
	public List<UserProjectVO> getAllUserProjectAscByUserNameForView();
	public void deleteUserProjectLink(Long id);
	List<UserProjectVO> getAllUserProjectAscByProjectNameForView();
	List<UserProjectVO> getAllUserProjectDescByProjectNameForView();
	void add(UserProjectLink userProjectLink);

}
