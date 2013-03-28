package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.entity.SaveComputationRecord;
import org.zju.electric_factory.service.SaveComputationRecordManager;

@Controller
@RequestMapping("/scr")
@Transactional
public class SaveComputationRecordController {
	
	@Autowired 
	private SaveComputationRecordManager saveComputationRecordManager;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
	public @ResponseBody List<SaveComputationRecord> getAll(){
		return saveComputationRecordManager.getAllSaveComputationRecords();
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/list")
	public @ResponseBody void add(@RequestBody SaveComputationRecord saveComputationRecord) {
		saveComputationRecordManager.addSaveComputationRecord(saveComputationRecord);
	}

}
