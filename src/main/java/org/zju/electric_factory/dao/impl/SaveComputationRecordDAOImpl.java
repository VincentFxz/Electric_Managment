package org.zju.electric_factory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zju.electric_factory.dao.SaveComputationRecordDAO;
import org.zju.electric_factory.entity.SaveComputationRecord;

@Repository
public class SaveComputationRecordDAOImpl  extends HibernateDAO<SaveComputationRecord, Long> implements SaveComputationRecordDAO{

	@Override
	public List<SaveComputationRecord> getAll(){
		return super.getAll();
	}
	
	@Override
	public SaveComputationRecord get(Long id){
		return super.findUniqueBy("id", id);
	}
	
	@Override
	public void add(SaveComputationRecord saveComputationRecord) {
		super.save(saveComputationRecord);
	}

}
