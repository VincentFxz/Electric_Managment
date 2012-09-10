package org.zju.electric_factory.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.ProjectAmmeterLinkDAO;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.vo.ProjectAmmeterVO;


@Service
@Transactional
public class ProjectAmmeterManagerImpl implements ProjectAmmeterManager {

	@Autowired
	private ProjectAmmeterLinkDAO projectAmmeterLinkDAO;

	@Autowired
	private ProjectManager projectManager;

	@Autowired
	private AmmeterManager ammeterManager;

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterLinkForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}
		return projectAmmeterVOs;
	}

	@Override
	public void addProjectAmmeterLink(ProjectAmmeterLink projectAmmeterLink) {
		projectAmmeterLinkDAO.add(projectAmmeterLink);

	}

	@Override
	public void deleteProjectAmmeterLink(Long id) {
		projectAmmeterLinkDAO.delete(id);

	}

	@Override
	public void edit(ProjectAmmeterLink projectAmmeterLink) {
		projectAmmeterLinkDAO.save(projectAmmeterLink);

	}

	@Override
	public ProjectAmmeterLink getProjectAmmeterLinkById(String id) {
		if (null != id) {
			return projectAmmeterLinkDAO.getById(Long.parseLong(id));
		}
		return null;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByProjectIdForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o1.getProjectId() < o2.getId() ? 0 : 1;
			}

		});
		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByProjectNameForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o1.getProjectName().compareTo(o2.getProjectName());
			}

		});

		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByProjectNameForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o2.getProjectName().compareTo(o1.getProjectName());
			}

		});

		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByAmmeterIdForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o1.getAmmeterId() > o2.getAmmeterId() ? 0 : 1;
			}

		});
		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByAmmeterIdForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o1.getAmmeterId() < o2.getAmmeterId() ? 0 : 1;
			}

		});
		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByProjectIdForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o1.getProjectId() > o2.getId() ? 0 : 1;
			}

		});
		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterAscByAmmeterNameForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o1.getAmmeterName().compareTo(o2.getAmmeterName());
			}

		});

		return projectAmmeterVOs;
	}

	@Override
	public List<ProjectAmmeterVO> getAllProjectAmmeterDescByAmmeterNameForView() {
		List<ProjectAmmeterVO> projectAmmeterVOs = new ArrayList<ProjectAmmeterVO>();

		List<ProjectAmmeterLink> allProjectAmmeterLinks = projectAmmeterLinkDAO
				.getAll();
		for (ProjectAmmeterLink projectAmmeterLink : allProjectAmmeterLinks) {
			Project project = projectManager.getById(projectAmmeterLink
					.getProjectId());
			Ammeter ammeter = ammeterManager.getAmmeterById(projectAmmeterLink
					.getAmmeterId());

			ProjectAmmeterVO projectAmmeterVO = new ProjectAmmeterVO();
			projectAmmeterVO.setId(projectAmmeterLink.getId());
			projectAmmeterVO.setProjectId(project.getId());
			projectAmmeterVO.setProjectName(project.getProjectName());
			projectAmmeterVO.setAmmeterId(ammeter.getId());
			projectAmmeterVO.setAmmeterName(ammeter.getName());

			projectAmmeterVOs.add(projectAmmeterVO);

		}

		Collections.sort(projectAmmeterVOs, new Comparator<ProjectAmmeterVO>() {

			@Override
			public int compare(ProjectAmmeterVO o1, ProjectAmmeterVO o2) {
				return o2.getAmmeterName().compareTo(o1.getAmmeterName());
			}

		});

		return projectAmmeterVOs;
	}

}
