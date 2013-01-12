package org.zju.electric_factory.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.Role;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.AmmeterRecordManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserManager;
import org.zju.electric_factory.vo.SaveComputationVO;

@Controller
@RequestMapping("/saveComputation")
@Transactional
public class SaveComputationController {
	@Autowired
	private AmmeterManager ammeterManager;

	@Autowired
	private UserManager userManager;

	@Autowired
	private ProjectAmmeterManager projectAmmeterManager;

	@Autowired
	private ProjectManager projectManager;

	@Autowired
	AmmeterRecordManager ammeterRecordManager;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "startDate")
	public @ResponseBody
	List<SaveComputationVO> getSaveComputation(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
		System.out.println(startDate);
		System.out.println(endDate);
		boolean userHasAdminRole = false;
		List<SaveComputationVO> saveComputationVOs = null;
		List<Ammeter> ammeters = null;
		User currentUser = userManager.getCurrentUser();
		Set<Role> userRoles = currentUser.getRoles();
		if (null != userRoles) {
			for (Role role : userRoles) {
				if ("admin".equals(role.getName())) {
					userHasAdminRole = true;
				}
			}
			if (userHasAdminRole) {
				ammeters = ammeterManager.getAllAmmeter();
			}else{
				ammeters= ammeterManager.getAmmetersOwnByUser(currentUser.getId());
			}
		}

		if (null != ammeters) {
			saveComputationVOs = new ArrayList<SaveComputationVO>();
			for (Ammeter ammeter : ammeters) {
				List<AmmeterRecord> ammeterRecords = ammeterRecordManager
						.getAmmeterRecordByAmmeterIdInPeriod(ammeter.getId(),
								startDate, endDate);
				Project project = projectManager.getProjectByAmmeterId(ammeter.getId());
				if(null != ammeterRecords && (0 != ammeterRecords.size())){
					SaveComputationVO saveComputationVO = new SaveComputationVO();
					saveComputationVO.setAmmeterName(ammeter.getName());
					saveComputationVO.setStartDate(startDate);
					saveComputationVO.setEndDate(endDate);
					saveComputationVO.setStartTimeSum(ammeterRecords.get(0).getTimeSum());
					saveComputationVO.setEndTimeSum(ammeterRecords.get(ammeterRecords.size() -1).getTimeSum());
					saveComputationVO.setEletricCharge(project.getElectricityCharge());
					saveComputationVO.setFormerCost(ammeter.getFormerCost());
					saveComputationVO.setPartsRatio(project.getPartsRatio());
					saveComputationVO.setStartValue(ammeterRecords.get(0).getAmmeterValue());
					saveComputationVO.setEndValue(ammeterRecords.get(ammeterRecords.size() - 1).getAmmeterValue());
					saveComputationVO.setSensorRate(ammeter.getSensorRate());
					float timeSum = ammeterRecords.get(ammeterRecords.size() - 1).getTimeSum() - ammeterRecords.get(0).getTimeSum();
					float realCost = (ammeterRecords.get(ammeterRecords.size() - 1).getAmmeterValue() - ammeterRecords.get(0).getAmmeterValue())*ammeter.getSensorRate()/(timeSum);
					float eletricSave = (ammeter.getFormerCost() - realCost) * timeSum;
					saveComputationVO.setEletricSave(eletricSave);
					saveComputationVO.setRealCost(realCost);
					saveComputationVO.setBonus(eletricSave * project.getElectricityCharge() * project.getPartsRatio());
					
					saveComputationVOs.add(saveComputationVO);
					
				}
			}
		}
		return saveComputationVOs;

	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/list", headers = "Accept=application/json")
	public @ResponseBody
	List<SaveComputationVO> getSaveComputationByPeriod() {
		boolean userHasAdminRole = false;
		List<SaveComputationVO> saveComputationVOs = null;
		List<Ammeter> ammeters = null;
		User currentUser = userManager.getCurrentUser();
		Set<Role> userRoles = currentUser.getRoles();
		if (null != userRoles) {
			for (Role role : userRoles) {
				if ("admin".equals(role.getName())) {
					userHasAdminRole = true;
				}
			}
			if (userHasAdminRole) {
				ammeters = ammeterManager.getAllAmmeter();
			}else{
				ammeters= ammeterManager.getAmmetersOwnByUser(currentUser.getId());
			}
		}
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DATE, -30);
		Date monthAgoDate = calendar.getTime();

		if (null != ammeters) {
			saveComputationVOs = new ArrayList<SaveComputationVO>();
			for (Ammeter ammeter : ammeters) {
				List<AmmeterRecord> ammeterRecords = ammeterRecordManager
						.getAmmeterRecordByAmmeterIdInPeriod(ammeter.getId(),
								monthAgoDate, nowDate);
				Project project = projectManager.getProjectByAmmeterId(ammeter.getId());
				if(null != ammeterRecords && (0 != ammeterRecords.size())&&(null != project)){
					SaveComputationVO saveComputationVO = new SaveComputationVO();
					saveComputationVO.setAmmeterName(ammeter.getName());
					saveComputationVO.setStartDate(monthAgoDate);
					saveComputationVO.setEndDate(nowDate);
					saveComputationVO.setStartTimeSum(ammeterRecords.get(0).getTimeSum());
					saveComputationVO.setEndTimeSum(ammeterRecords.get(ammeterRecords.size() -1).getTimeSum());
					saveComputationVO.setEletricCharge(project.getElectricityCharge());
					saveComputationVO.setFormerCost(ammeter.getFormerCost());
					saveComputationVO.setPartsRatio(project.getPartsRatio());
					saveComputationVO.setStartValue(ammeterRecords.get(0).getAmmeterValue());
					saveComputationVO.setEndValue(ammeterRecords.get(ammeterRecords.size() - 1).getAmmeterValue());
					saveComputationVO.setSensorRate(ammeter.getSensorRate());
					float timeSum = ammeterRecords.get(ammeterRecords.size() - 1).getTimeSum() - ammeterRecords.get(0).getTimeSum();
					float realCost = (ammeterRecords.get(ammeterRecords.size() - 1).getAmmeterValue() - ammeterRecords.get(0).getAmmeterValue())*ammeter.getSensorRate()/(timeSum);
					float eletricSave = (ammeter.getFormerCost() - realCost) * timeSum;
					saveComputationVO.setEletricSave(eletricSave);
					saveComputationVO.setRealCost(realCost);
					saveComputationVO.setBonus(eletricSave * project.getElectricityCharge() * project.getPartsRatio());
					
					saveComputationVOs.add(saveComputationVO);
					
				}
			}
		}
		return saveComputationVOs;

	}

}
