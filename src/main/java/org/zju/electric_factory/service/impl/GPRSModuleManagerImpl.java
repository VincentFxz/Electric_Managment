package org.zju.electric_factory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.GPRSModuleDAO;
import org.zju.electric_factory.entity.GPRSModule;
import org.zju.electric_factory.service.GPRSModuleManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/13/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class GPRSModuleManagerImpl implements GPRSModuleManager
{
    @Autowired
    private GPRSModuleDAO gprsModuleDAO;

    @Override
    public List<GPRSModule> getGPRSModules() throws Exception {
        return gprsModuleDAO.getAllGPRSModules();
    }

    @Override
    public void deleteGPRSModule(GPRSModule gprsModule) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void editGPRSModule(GPRSModule gprsModule) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
