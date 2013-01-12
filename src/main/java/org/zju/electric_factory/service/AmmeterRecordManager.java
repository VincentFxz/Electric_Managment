package org.zju.electric_factory.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;

public interface AmmeterRecordManager {
    /**
     * add a ammeter record
     * 
     * @param ammeter
     */
    public void add(AmmeterRecord ammeterRecord);

    /**
     * get all record
     */
    public List<AmmeterRecord> getAll();

    /**
     * get record by id
     * 
     * @param id
     */
    public AmmeterRecord get(Long id);

    /**
     * get record by ammeter id
     * 
     * @param ammeterId
     * @return
     */
    public List<AmmeterRecord> getByAmmeter(Long ammeterId);

    public List<AmmeterRecord> getAllAmmeterRecordAscById();

    public List<AmmeterRecord> getAllAmmeterRecordDescById();
    
    public List<AmmeterRecord> getAllAmmeterRecordsByAmmeterId(Long ammeterId);

    public List<AmmeterRecord> getAllAmmeterRecordAscByAmmeterName();

    public List<AmmeterRecord> getAllAmmeterRecordDescByAmmeterName();

    public List<AmmeterRecord> getAllAmmeterRecordAscByRecordDate();

    public List<AmmeterRecord> getAllAmmeterRecordDescByRecordDate();
    
    public List<AmmeterRecord> getAllAmmeterRecordAscByAmmeterValue();

    public List<AmmeterRecord> getAllAmmeterRecordDescByAmmeterValue();
    
    public List<AmmeterRecord> getAmmeterRecordsByPeriod(Date startDate, Date endDate);
    
    public List<AmmeterRecord> getAmmeterRecordByAmmeterIdInPeriod(Long ammeterId, Date startDate, Date endDate);
    

}
