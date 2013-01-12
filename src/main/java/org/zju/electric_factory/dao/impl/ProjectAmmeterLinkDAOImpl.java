package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.ProjectAmmeterLinkDAO;
import org.zju.electric_factory.entity.ProjectAmmeterLink;

@Repository
public class ProjectAmmeterLinkDAOImpl extends HibernateDAO<ProjectAmmeterLink, Long> implements ProjectAmmeterLinkDAO{

    @Override
    public List<ProjectAmmeterLink> getByProjectId(Long projectId) {
        return findBy("projectId",projectId);
    }

    @Override
    public ProjectAmmeterLink getByAmmeterId(Long ammeterId) {
        return findUniqueBy("ammeterId", ammeterId);
    }

    @Override
    public ProjectAmmeterLink getById(Long id) {
        return get(id);
    }
    
    @Override
    public List<ProjectAmmeterLink> getAll() {
        return super.getAll();
    }

	@Override
	public void add(ProjectAmmeterLink projectAmmeterLink) {
		super.save(projectAmmeterLink);
		
	}

    
    

}
