package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Project;

public interface ProjectManager {
    public List<Project> getProjectsOwnByUser(Long userId);
    public List<Project> getProjectsOwnByCompany(Long userId);
}
