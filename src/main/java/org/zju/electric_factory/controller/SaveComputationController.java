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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.entity.Role;
import org.zju.electric_factory.entity.SaveComputationRecord;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.AmmeterRecordManager;
import org.zju.electric_factory.service.ProjectAmmeterManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.service.UserManager;

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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json", params = "startDate")
	public @ResponseBody
	SaveComputationRecord getSaveComputation(
			@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate,
			@RequestParam("ammeterName") String ammeterName) {
		boolean userHasAdminRole = false;
		SaveComputationRecord saveComputationRecord = null;
		Ammeter ammeter = null;
		User currentUser = userManager.getCurrentUser();
		Set<Role> userRoles = currentUser.getRoles();

		if (null != startDate && null != endDate && null != ammeterName) {

			if (null != userRoles) {
				for (Role role : userRoles) {
					if ("admin".equals(role.getName())) {
						userHasAdminRole = true;
					}
				}
				if (userHasAdminRole) {
					ammeter = ammeterManager.getAmmeterByName(ammeterName);
				} else {
					ammeter = ammeterManager.getAmmeterByName(ammeterName);
				}
			}

			if (null != ammeter) {
				List<AmmeterRecord> ammeterRecords = ammeterRecordManager
						.getAmmeterRecordByAmmeterIdInPeriod(ammeter.getId(),
								startDate, endDate);
				Project project = projectManager.getProjectByAmmeterId(ammeter
						.getId());
				if (null != ammeterRecords && (0 != ammeterRecords.size())
						&& (null != project)) {
					saveComputationRecord = new SaveComputationRecord();
					saveComputationRecord.setAmmeterName(ammeter.getName());
					saveComputationRecord.setStartDate(startDate);
					saveComputationRecord.setEndDate(endDate);
					saveComputationRecord.setStartTimeSum(ammeterRecords.get(0)
							.getTimeSum());
					saveComputationRecord.setEndTimeSum(ammeterRecords.get(
							ammeterRecords.size() - 1).getTimeSum());
					saveComputationRecord.setEletricCharge(project
							.getElectricityCharge());
					saveComputationRecord
							.setFormerCost(ammeter.getFormerCost());
					saveComputationRecord
							.setPartsRatio(project.getPartsRatio());
					saveComputationRecord.setStartValue(ammeterRecords.get(0)
							.getAmmeterValue());
					saveComputationRecord.setEndValue(ammeterRecords.get(
							ammeterRecords.size() - 1).getAmmeterValue());
					saveComputationRecord
							.setSensorRate(ammeter.getSensorRate());
					saveComputationRecord.setProjectName(project
							.getProjectName());

					float timeSum = ammeterRecords.get(
							ammeterRecords.size() - 1).getTimeSum()
							- ammeterRecords.get(0).getTimeSum();
					float realCost = (ammeterRecords.get(
							ammeterRecords.size() - 1).getAmmeterValue() - ammeterRecords
							.get(0).getAmmeterValue())
							* ammeter.getSensorRate() / (timeSum);
					float eletricSave = (ammeter.getFormerCost() - realCost)
							* timeSum;
					float theOtherPartyBonus = eletricSave
							* project.getElectricityCharge()
							* project.getPartsRatio();
					float thePartyBonus = eletricSave
							* project.getElectricityCharge()
							* (1 - project.getPartsRatio());
					float defaultCoalRatio = 0f;
					float coalSave = eletricSave / 10000 * defaultCoalRatio;
					float eletricChargeSave = eletricSave
							* project.getElectricityCharge();

					saveComputationRecord
							.setEletricChargeSave(eletricChargeSave);
					saveComputationRecord.setEletricSave(eletricSave);
					saveComputationRecord.setRealCost(realCost);
					saveComputationRecord
							.setTheOtherPartyBouns(theOtherPartyBonus);
					saveComputationRecord.setThePartyBonus(thePartyBonus);
					saveComputationRecord
							.setStandardCoalRatio(defaultCoalRatio);
					saveComputationRecord.setCoalSave(coalSave);
				}

			}
		}
		return saveComputationRecord;

	}

	// @RequestMapping(method = RequestMethod.GET, value = "/list/", headers =
	// "Accept=application/json", params = "startDate")
	// public @ResponseBody
	// List<SaveComputationRecord> getSaveComputation(@RequestParam("startDate")
	// Date startDate, @RequestParam("endDate") Date endDate) {
	// boolean userHasAdminRole = false;
	// List<SaveComputationRecord> SaveComputationRecords = null;
	// List<Ammeter> ammeters = null;
	// User currentUser = userManager.getCurrentUser();
	// Set<Role> userRoles = currentUser.getRoles();
	// if (null != userRoles) {
	// for (Role role : userRoles) {
	// if ("admin".equals(role.getName())) {
	// userHasAdminRole = true;
	// }
	// }
	// if (userHasAdminRole) {
	// ammeters = ammeterManager.getAllAmmeter();
	// }else{
	// ammeters= ammeterManager.getAmmetersOwnByUser(currentUser.getId());
	// }
	// }
	//
	// if (null != ammeters) {
	// SaveComputationRecords = new ArrayList<SaveComputationRecord>();
	// for (Ammeter ammeter : ammeters) {
	// List<AmmeterRecord> ammeterRecords = ammeterRecordManager
	// .getAmmeterRecordByAmmeterIdInPeriod(ammeter.getId(),
	// startDate, endDate);
	// Project project = projectManager.getProjectByAmmeterId(ammeter.getId());
	// if(null != ammeterRecords && (0 != ammeterRecords.size())&&(null !=
	// project)){
	// SaveComputationRecord saveComputationRecord = new
	// SaveComputationRecord();
	// saveComputationRecord.setAmmeterName(ammeter.getName());
	// saveComputationRecord.setStartDate(startDate);
	// saveComputationRecord.setEndDate(endDate);
	// saveComputationRecord.setStartTimeSum(ammeterRecords.get(0).getTimeSum());
	// saveComputationRecord.setEndTimeSum(ammeterRecords.get(ammeterRecords.size()
	// -1).getTimeSum());
	// saveComputationRecord.setEletricCharge(project.getElectricityCharge());
	// saveComputationRecord.setFormerCost(ammeter.getFormerCost());
	// saveComputationRecord.setPartsRatio(project.getPartsRatio());
	// saveComputationRecord.setStartValue(ammeterRecords.get(0).getAmmeterValue());
	// saveComputationRecord.setEndValue(ammeterRecords.get(ammeterRecords.size()
	// - 1).getAmmeterValue());
	// saveComputationRecord.setSensorRate(ammeter.getSensorRate());
	// saveComputationRecord.setProjectName(project.getProjectName());
	//
	// float timeSum = ammeterRecords.get(ammeterRecords.size() -
	// 1).getTimeSum() - ammeterRecords.get(0).getTimeSum();
	// float realCost = (ammeterRecords.get(ammeterRecords.size() -
	// 1).getAmmeterValue() -
	// ammeterRecords.get(0).getAmmeterValue())*ammeter.getSensorRate()/(timeSum);
	// float eletricSave = (ammeter.getFormerCost() - realCost) * timeSum;
	// float theOtherPartyBonus = eletricSave * project.getElectricityCharge() *
	// project.getPartsRatio();
	// float thePartyBonus = eletricSave * project.getElectricityCharge() *(1 -
	// project.getPartsRatio());
	// float defaultCoalRatio = 0f;
	// float coalSave = eletricSave/10000*defaultCoalRatio;
	// float eletricChargeSave = eletricSave * project.getElectricityCharge();
	//
	// saveComputationRecord.setEletricChargeSave(eletricChargeSave);
	// saveComputationRecord.setEletricSave(eletricSave);
	// saveComputationRecord.setRealCost(realCost);
	// saveComputationRecord.setTheOtherPartyBouns(theOtherPartyBonus);
	// saveComputationRecord.setThePartyBonus(thePartyBonus);
	// saveComputationRecord.setStandardCoalRatio(defaultCoalRatio);
	// saveComputationRecord.setCoalSave(coalSave);
	//
	// SaveComputationRecords.add(saveComputationRecord);
	//
	// }
	// }
	// }
	// return SaveComputationRecords;
	//
	// }

	@RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
	public @ResponseBody
	List<SaveComputationRecord> getSaveComputationByPeriod() {
		boolean userHasAdminRole = false;
		List<SaveComputationRecord> SaveComputationRecords = null;
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
			} else {
				ammeters = ammeterManager.getAmmetersOwnByUser(currentUser
						.getId());
			}
		}
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.add(Calendar.DATE, -30);
		Date monthAgoDate = calendar.getTime();

		if (null != ammeters) {
			SaveComputationRecords = new ArrayList<SaveComputationRecord>();
			for (Ammeter ammeter : ammeters) {
				List<AmmeterRecord> ammeterRecords = ammeterRecordManager
						.getAmmeterRecordByAmmeterIdInPeriod(ammeter.getId(),
								monthAgoDate, nowDate);
				Project project = projectManager.getProjectByAmmeterId(ammeter
						.getId());
				if (null != ammeterRecords && (0 != ammeterRecords.size())
						&& (null != project)) {
					SaveComputationRecord saveComputationRecord = new SaveComputationRecord();
					saveComputationRecord.setAmmeterName(ammeter.getName());
					saveComputationRecord.setStartDate(monthAgoDate);
					saveComputationRecord.setEndDate(nowDate);
					saveComputationRecord.setStartTimeSum(ammeterRecords.get(0)
							.getTimeSum());
					saveComputationRecord.setEndTimeSum(ammeterRecords.get(
							ammeterRecords.size() - 1).getTimeSum());
					saveComputationRecord.setEletricCharge(project
							.getElectricityCharge());
					saveComputationRecord
							.setFormerCost(ammeter.getFormerCost());
					saveComputationRecord
							.setPartsRatio(project.getPartsRatio());
					saveComputationRecord.setStartValue(ammeterRecords.get(0)
							.getAmmeterValue());
					saveComputationRecord.setEndValue(ammeterRecords.get(
							ammeterRecords.size() - 1).getAmmeterValue());
					saveComputationRecord
							.setSensorRate(ammeter.getSensorRate());
					saveComputationRecord.setProjectName(project
							.getProjectName());

					float timeSum = ammeterRecords.get(
							ammeterRecords.size() - 1).getTimeSum()
							- ammeterRecords.get(0).getTimeSum();
					float realCost = (ammeterRecords.get(
							ammeterRecords.size() - 1).getAmmeterValue() - ammeterRecords
							.get(0).getAmmeterValue())
							* ammeter.getSensorRate() / (timeSum);
					float eletricSave = (ammeter.getFormerCost() - realCost)
							* timeSum;
					float theOtherPartyBonus = eletricSave
							* project.getElectricityCharge()
							* project.getPartsRatio();
					float thePartyBonus = eletricSave
							* project.getElectricityCharge()
							* (1 - project.getPartsRatio());
					float defaultCoalRatio = 0f;
					float coalSave = eletricSave / 10000 * defaultCoalRatio;

					saveComputationRecord.setEletricSave(eletricSave);
					saveComputationRecord.setRealCost(realCost);
					saveComputationRecord
							.setTheOtherPartyBouns(theOtherPartyBonus);
					saveComputationRecord.setThePartyBonus(thePartyBonus);
					saveComputationRecord
							.setStandardCoalRatio(defaultCoalRatio);
					saveComputationRecord.setCoalSave(coalSave);
					SaveComputationRecords.add(saveComputationRecord);

				}
			}
		}
		return SaveComputationRecords;

	}

}
