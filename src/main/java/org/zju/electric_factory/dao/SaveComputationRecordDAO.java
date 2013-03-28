package org.zju.electric_factory.dao;

import java.util.List;

import org.zju.electric_factory.entity.SaveComputationRecord;

public interface SaveComputationRecordDAO {
	
	public List<SaveComputationRecord> getAll();
	public SaveComputationRecord get(Long id);
	public void add(SaveComputationRecord saveComputationRecord);
	
	

}
