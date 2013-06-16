package org.zju.electric_factory.dao.impl;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.GPRSModuleDAO;
import org.zju.electric_factory.entity.GPRSModule;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/10/13
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GPRSModuleDAOImpl extends HibernateDAO<GPRSModule, Long> implements GPRSModuleDAO {
    @Override
    public GPRSModule getGPRSModuleById(Long id) throws Exception {
        return super.findUniqueBy("id", id);
    }

    @Override
    public GPRSModule getGPRSModuleByIdentifier(String Identifier) throws Exception {
        return super.findUniqueBy("identifier", Identifier);
    }

    @Override
    public List<GPRSModule> getAllGPRSModules() throws Exception {
        return super.getAll();
    }
}
