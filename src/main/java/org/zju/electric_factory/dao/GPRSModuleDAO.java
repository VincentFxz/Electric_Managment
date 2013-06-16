package org.zju.electric_factory.dao;

import org.zju.electric_factory.entity.GPRSModule;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/10/13
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GPRSModuleDAO {
    public GPRSModule getGPRSModuleById (Long id) throws Exception;
    public GPRSModule getGPRSModuleByIdentifier (String Identifier) throws Exception;
    public void delete (GPRSModule gprsModule);
    public void save (GPRSModule gprsModule);
    public List<GPRSModule> getAllGPRSModules () throws  Exception;
}
