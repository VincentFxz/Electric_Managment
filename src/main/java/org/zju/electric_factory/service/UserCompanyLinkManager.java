package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.UserCompanyLink;

public interface UserCompanyLinkManager {
	
	public UserCompanyLink getById(String id);
	public List<UserCompanyLink> getByCompanyId(String id);
	public List<UserCompanyLink> getByUserId(String id);
	public List<UserCompanyLink> getAll();
	public void add(UserCompanyLink userCompanyLink);
	

}
