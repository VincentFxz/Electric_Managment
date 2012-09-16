package org.zju.electric_factory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.UserCompanyLinkDAO;
import org.zju.electric_factory.entity.UserCompanyLink;
import org.zju.electric_factory.service.UserCompanyLinkManager;

@Service
@Transactional
public class UserCompanyLinkManagerImpl implements UserCompanyLinkManager {
	
	@Autowired
	private UserCompanyLinkDAO userCompanyLinkDAO;
	
	@Override
	public UserCompanyLink getById(String id) {
		if(null != id){
			return userCompanyLinkDAO.getById(Long.parseLong(id));
		}
		return null;
	}

	@Override
	public List<UserCompanyLink> getByCompanyId(String id) {
		if(null != id){
			return userCompanyLinkDAO.getByCompanyId(Long.parseLong(id));
		}
		return null;
	}

	@Override
	public List<UserCompanyLink> getByUserId(String id) {
		if(null != id){
			return userCompanyLinkDAO.getByUserId(Long.parseLong(id));
		}
		return null;
	}

	@Override
	public List<UserCompanyLink> getAll() {
		return userCompanyLinkDAO.getAll();
	}

	@Override
	public void add(UserCompanyLink userCompanyLink) {
		userCompanyLinkDAO.add(userCompanyLink);
	}

}
