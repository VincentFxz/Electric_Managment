package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.UserDAO;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.impl.UserManagerImpl;

@Repository
public class UserDAOImpl extends HibernateDAO<User, Long> implements UserDAO{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDAOImpl.class);

	@Override
	public User getUser(Long userId) {
		return super.get(userId);
	}

	@Override
	public User findUser(String username) {
		return super.findUniqueBy("username",username);
	}

	@Override
	public void createUser(User user) {
		super.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return super.getAll();
	}

	@Override
	public void deleteUser(Long userId) {
		super.delete(userId);
	}

	@Override
	public void updateUser(User user) {
		super.save(user);
	}

}
