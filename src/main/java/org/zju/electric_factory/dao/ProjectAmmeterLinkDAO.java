package org.zju.electric_factory.dao;

import java.util.List;

import org.hibernate.sql.Update;
import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.entity.ProjectAmmeterLink;

public interface ProjectAmmeterLinkDAO {
    public List<ProjectAmmeterLink> getByProjectId(Long projectId);
    public List<ProjectAmmeterLink> getByAmmeterId(Long ammeterId);
    public ProjectAmmeterLink getById(Long id);
    public List<ProjectAmmeterLink> getAll();
    public void add(ProjectAmmeterLink projectAmmeterLink);
    public void delete(Long id);
    public void delete(ProjectAmmeterLink projectAmmeterLink);
    public void save(ProjectAmmeterLink projectAmmeterLink);
    
}
