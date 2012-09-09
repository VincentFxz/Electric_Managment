package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.Project;

public interface ProjectDAO {
    public Project getById(Long projectId);
    
    public List<Project> getAllProjects();
	public List<Project> getAllByOrder(String columnToSort, boolean isAsc);
	/**
	 * delete
	 */
	public void delete(Project project);
	
	public void delete(Long id);
	
	public void update(Project project);
	
	public void add(Project project);
	
}
