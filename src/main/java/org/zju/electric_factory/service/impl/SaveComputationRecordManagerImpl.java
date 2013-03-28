package org.zju.electric_factory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.SaveComputationRecordDAO;
import org.zju.electric_factory.entity.SaveComputationRecord;
import org.zju.electric_factory.service.SaveComputationRecordManager;

@Service
@Transactional
public class SaveComputationRecordManagerImpl implements SaveComputationRecordManager{
	
	@Autowired 
	private SaveComputationRecordDAO saveComputationRecordDAO;

	@Override
	public List<SaveComputationRecord> getAllSaveComputationRecords() {
		// TODO Auto-generated method stub
		return saveComputationRecordDAO.getAll();
	}

	@Override
	public SaveComputationRecord getSaveComputationRecordByID(String id) {
		// TODO Auto-generated method stub
		SaveComputationRecord saveComputationRecord = null;
		if(null != id){
			saveComputationRecord = saveComputationRecordDAO.get(Long.parseLong(id));
		}
		return saveComputationRecord;
	}

	@Override
	public void addSaveComputationRecord(
			SaveComputationRecord saveComputationRecord) {
		if(null != saveComputationRecord){
			saveComputationRecordDAO.add(saveComputationRecord);
		}
	}

}
