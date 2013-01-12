package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.ProjectDAO;
import org.zju.electric_factory.entity.Project;

@Repository
public class ProjectDAOImpl extends HibernateDAO<Project, Long> implements ProjectDAO {

    @Override
    public Project getById(Long projectId) {
        return super.findUniqueBy("id", projectId);
    }

	@Override
	public List<Project> getAllProjects() {
		return super.getAll();
	}

	@Override
	public List<Project> getAllByOrder(String columnToSort, boolean isAsc) {
		if(isAsc){
			return super.getAll(columnToSort, true);
		}else{
			return super.getAll(columnToSort, false);
		}
		
	}

	@Override
	public void update(Project project) {
		super.save(project);
		
	}

	@Override
	public void add(Project project) {
		super.save(project);
	}

	@Override
	public Project getByProjectName(String projectName) {
		return super.findUniqueBy("projectName", projectName);
	}

	@Override
	public void init(Project project) {
		super.initProxyObject(project);
		
	}

}
