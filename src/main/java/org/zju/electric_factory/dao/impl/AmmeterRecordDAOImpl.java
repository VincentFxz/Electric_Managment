package org.zju.electric_factory.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.AmmeterRecordDAO;
import org.zju.electric_factory.entity.AmmeterRecord;

@Repository
public class AmmeterRecordDAOImpl extends HibernateDAO<AmmeterRecord, Long> implements AmmeterRecordDAO {
    private final String FIND_BY_PERIOD_HQL="from "+this.entityClass.getName()+
            " ammeterRecord where ammeterRecord.recordDate>=? and ammeterRecord.recordDate<=?";

    /**
     * get record by id
     */
    public AmmeterRecord get(Long id) {
    	return super.get(id);
    }

    /**
     *get record by date period 
     */
    public List<AmmeterRecord> getByPeriod(Date startDate, Date endDate) {
        return find(FIND_BY_PERIOD_HQL, startDate, endDate);
    }
    
    /*
     *add a record 
     */
    public void add(AmmeterRecord ammeterRecord) {
        super.save(ammeterRecord);
    }
    
    /**
     * get all 
     */
    public List<AmmeterRecord> getAll(){
        return super.getAll();
    }

    /**
     * get by ammeter id
     */
    public List<AmmeterRecord> getByAmmeter(Long ammeterId) {
        return findBy("ammeterId",ammeterId);
    }

	@Override
	public List<AmmeterRecord> getByOrder(String columnToSort, boolean isAsc) {
		return super.getAll(columnToSort,isAsc);
	}
}
