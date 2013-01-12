package org.zju.electric_factory.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.entity.Role;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.AmmeterRecordManager;
import org.zju.electric_factory.service.UserManager;
import org.zju.electric_factory.vo.LastAmmeterStatusVo;

@Controller
@RequestMapping("/lastAmmeterStatus")
public class LastAmmeterStatusController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private AmmeterManager ammeterManager;
	
	@Autowired
	private AmmeterRecordManager ammeterRecordManager;
	
	@RequestMapping(method = RequestMethod.GET, value ="/list", headers = "Accept=application/json")
	public @ResponseBody List<LastAmmeterStatusVo> getLastAmmeterStatusVos(){
		boolean userHasAdminRole = false;
		List<Ammeter> ammeters = null;
		List<LastAmmeterStatusVo> lastAmmeterStatusVos = new ArrayList<LastAmmeterStatusVo>();
		Date endDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.HOUR, -1);
		Date startDate = calendar.getTime();
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
		
		if(null != ammeters){
			for(Ammeter ammeter : ammeters){
				List<AmmeterRecord> ammeterRecords = ammeterRecordManager.getAmmeterRecordByAmmeterIdInPeriod(ammeter.getId(), startDate, endDate);
				if(null != ammeterRecords&&(ammeterRecords.size() > 0)){
					Float lastAmmeterValue = ammeterRecords.get(ammeterRecords.size() - 1).getAmmeterValue();
					Float ammeterValueHourAgo = ammeterRecords.get(0).getAmmeterValue();
					Float costPerHour = (lastAmmeterValue - ammeterValueHourAgo) * ammeter.getSensorRate();
					LastAmmeterStatusVo lastAmmeterStatusVo = new LastAmmeterStatusVo();
					lastAmmeterStatusVo.setAmmeterName(ammeter.getName());
					lastAmmeterStatusVo.setAmmeterValue(lastAmmeterValue);
					lastAmmeterStatusVo.setCostPerHour(costPerHour);
					lastAmmeterStatusVo.setTimeSum(ammeterRecords.get(ammeterRecords.size() - 1).getTimeSum());
					if(costPerHour < ammeter.getLowerLimit()){
						lastAmmeterStatusVo.setWarningStatus("下限报警");
					}else if(costPerHour > ammeter.getUpperLimit()){
						lastAmmeterStatusVo.setWarningStatus("上限报警");
					}else{
						lastAmmeterStatusVo.setWarningStatus("无报警");
					}
					
					lastAmmeterStatusVos.add(lastAmmeterStatusVo);
					
				}
			}
		}

		return lastAmmeterStatusVos;
	}
}
