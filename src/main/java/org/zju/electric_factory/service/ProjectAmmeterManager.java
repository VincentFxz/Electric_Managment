package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.vo.ProjectAmmeterVO;

public interface ProjectAmmeterManager {
	public List<ProjectAmmeterVO> getAllProjectAmmeterLinkForView();
	public void addProjectAmmeterLink(ProjectAmmeterLink companyProjectLink);
	public void deleteProjectAmmeterLink(Long id);
	public void edit(ProjectAmmeterLink companyProjectLink);
	public ProjectAmmeterLink getProjectAmmeterLinkById(String id);
	public ProjectAmmeterLink getProjectAmmeterLinkByAmmeterId(String ammeterId);
	public ProjectAmmeterLink getProjectAmmeterLinkByAmmeterId(Long ammeterId);
	public List<ProjectAmmeterLink> getProjectAmmeterLinkByProjectId(String projectId);
	
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByAmmeterIdForView();
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByAmmeterIdForView();
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByProjectIdForView();
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByProjectIdForView();
	
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByAmmeterNameForView();
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByAmmeterNameForView();
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByProjectNameForView();
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByProjectNameForView();

}
