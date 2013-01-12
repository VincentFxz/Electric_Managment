package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.Project;

public interface ProjectManager {
    public List<Project> getProjectsOwnByUser(Long userId);
    public Project getProjectByProjectName(String projectName);
    List<Project> getProjectsOwnByCompany(String companyId);
	public List<Project> getProjects();
	public List<Project> getProjectsAscById();
	public List<Project> getProjectsDescById();
	public List<Project> getProjectsAscByName();
	public List<Project> getProjectsDescByName();
	public List<Project> getProjectsAscByStartDate();
	public List<Project> getProjectsDescByStartDate();
	public List<Project> getProjectsAscByEndDate();
	public List<Project> getProjectsDescByEndDate();
	public void deleteProjectById(String id);
	public void editProject(Project project);
	public void add(Project project);
	public Project getById(Long id);
	public Project getProjectByAmmeterId(Long ammeterId);
}
