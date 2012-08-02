package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.UserProjectLinkDAO;
import org.zju.electric_factory.entity.UserProjectLink;

@Repository
public class UserProjectLinkDAOImpl extends HibernateDAO<UserProjectLink, Long> implements UserProjectLinkDAO{

    @Override
    public List<UserProjectLink> getByUserId(Long userId) {
        return findBy("userId", userId);
    }

    @Override
    public List<UserProjectLink> getByProjectId(Long projectId) {
        return findBy("projectId",projectId);
    }

    @Override
    public UserProjectLink getById(Long id) {
        return get(id);
    }

    @Override
    public List<UserProjectLink> getAll() {
        return super.getAll();
    }

}
