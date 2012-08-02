package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.UserCompanyLink;

public interface UserCompanyLinkDAO {
    
    public List<UserCompanyLink> getByUserId(Long userId);
    public List<UserCompanyLink> getByCompanyId(Long companyId);
    public UserCompanyLink getById(Long id);
    public List<UserCompanyLink> getAll();
}
