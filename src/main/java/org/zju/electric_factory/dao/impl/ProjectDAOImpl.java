package org.zju.electric_factory.dao.impl;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.ProjectDAO;
import org.zju.electric_factory.entity.Project;

@Repository
public class ProjectDAOImpl extends HibernateDAO<Project, Long> implements ProjectDAO {

    @Override
    public Project getById(Long projectId) {
        return super.get(projectId);
    }

}
