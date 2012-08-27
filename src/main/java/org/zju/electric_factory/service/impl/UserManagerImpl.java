package org.zju.electric_factory.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.UserDAO;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.UserManager;

@Component
@Transactional
public class UserManagerImpl implements UserManager {

	private static final Logger logger = LoggerFactory
			.getLogger(UserManagerImpl.class);
	@Autowired
	private UserDAO userDAO;

	public User getCurrentUser() {
		final Long currentUserId = (Long) SecurityUtils.getSubject()
				.getPrincipal();
		if (currentUserId != null) {
			return getUser(currentUserId);
		} else {
			return null;
		}
	}

	public void createUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(new Sha256Hash(password).toHex());
		userDAO.createUser(user);
	}

	public void createUser(User user) {
		
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		logger.info("create user : name '" + user.getUsername()
				+ "', password '" + user.getPassword() + "', Email '"
				+ user.getEmail() + "'");
		userDAO.createUser(user);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public User getUser(Long userId) {
		return userDAO.getUser(userId);
	}

	public void deleteUser(Long userId) {
		userDAO.deleteUser(userId);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public User getUserByName(String name) {
		return userDAO.findUser(name);
	}
	
	

}
