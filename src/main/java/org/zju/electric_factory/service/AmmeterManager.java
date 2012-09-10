package org.zju.electric_factory.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.entity.Ammeter;

public interface AmmeterManager {
    /**
     * add a ammeter
     * 
     * @param ammeter
     */
    public void add(Ammeter ammeter);

    /**
     * get ammeter by Id
     * 
     * @param id
     * @return
     */
    public Ammeter getAmmeterById(Long id);

    /**
     * get ammeter by name
     * 
     * @param name
     * @return
     */
    public Ammeter getAmmeterByName(String name);

    /**
     * get all ammeter
     * 
     * @return
     */
    public List<Ammeter> getAllAmmeter();

    /**
     * get ammeter by company
     * 
     * @return
     */
    public List<Ammeter> getAmmeterByCompany(String company);

    /**
     * get ammeter by project
     * 
     * @return
     */
    public List<Ammeter> getAmmeterByProject(String project);

    /**
     * detele ammeter
     * 
     * @param ammeter
     */
    public void deleteAmmeter(Ammeter ammeter);

    /**
     * delete id
     * 
     * @param id
     */
    public void deleteAmmeter(Long id);

    /**
     * edit ammeter
     * 
     * @param ammeter
     */
    public void editAmmeter(Ammeter ammeter);

    /**
     * get all by id asc
     */
    public List<Ammeter> getAllAmmeterAscById();

    public List<Ammeter> getAllAmmeterDescById();

    public List<Ammeter> getAllAmmeterAscByName();

    public List<Ammeter> getAllAmmeterDescByName();

    public List<Ammeter> getAllAmmeterAscByPumpName();

    public List<Ammeter> getAllAmmeterDescByPumpName();

    public List<Ammeter> getAllAmmeterAscByProjectName();

    public List<Ammeter> getAllAmmeterDescByProjectName();

    public List<Ammeter> getAllAmmeterAscByCompanyName();

    public List<Ammeter> getAllAmmeterDescByCompanyName();

    public void deleteAmmeterbyId(String id);
    
    public List<Ammeter> getAmmetersOwnByUser(Long userId);
    
    public List<Ammeter> getAmmetersOwnByProject(Long projectId);
    
    public List<Ammeter> getAmmetersOwnByCompany(Long companyId);
}
