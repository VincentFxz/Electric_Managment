package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.UserCompanyLinkDAO;
import org.zju.electric_factory.entity.UserCompanyLink;

@Repository
public class UserCompanyLinkDAOImpl extends HibernateDAO<UserCompanyLink, Long> implements UserCompanyLinkDAO  {

    @Override
    public List<UserCompanyLink> getByUserId(Long userId) {
        return findBy("userId", userId);
    }

    @Override
    public List<UserCompanyLink> getByCompanyId(Long companyId) {
        return findBy("companyId", companyId);
    }

    @Override
    public UserCompanyLink getById(Long id) {
        return get(id);
    }
    
    @Override
    public List<UserCompanyLink> getAll() {
        return super.getAll();
    }
}
