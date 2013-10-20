package org.zju.electric_factory.service;

import org.zju.electric_factory.entity.GPRSModule;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/13/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GPRSModuleManager {
    public List<GPRSModule> getGPRSModules() throws Exception;
    public void deleteGPRSModule (Long gprsModule) throws Exception;
    public void editGPRSModule (GPRSModule gprsModule) throws Exception;
    public void add(GPRSModule gprsModule) throws Exception;
}
