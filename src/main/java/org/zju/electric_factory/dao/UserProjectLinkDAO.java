package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.UserProjectLink;

public interface UserProjectLinkDAO {

    public List<UserProjectLink> getByUserId(Long userId);
    public List<UserProjectLink> getByProjectId(Long projectId);
    public UserProjectLink getById(Long id);
    public List<UserProjectLink> getAll();
    public void add(UserProjectLink userProjectLink);
	public void deleteById(Long id);
}
