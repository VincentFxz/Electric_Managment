package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.ProjectAmmeterLink;

public interface ProjectAmmeterLinkDAO {
    public List<ProjectAmmeterLink> getByProjectId(Long projectId);
    public List<ProjectAmmeterLink> getByAmmeterId(Long ammeterId);
    public ProjectAmmeterLink getById(Long id);
    public List<ProjectAmmeterLink> getAll();
}
