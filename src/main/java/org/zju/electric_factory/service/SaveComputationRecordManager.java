package org.zju.electric_factory.service;

import java.util.List;

import org.zju.electric_factory.entity.SaveComputationRecord;

public interface SaveComputationRecordManager {
	
	public List<SaveComputationRecord> getAllSaveComputationRecords();
	public SaveComputationRecord getSaveComputationRecordByID(String id);
	public void addSaveComputationRecord(SaveComputationRecord saveComputationRecord);

}
