package org.zju.electric_factory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.dao.impl.AmmeterDAOImpl;
import org.zju.electric_factory.dao.impl.AmmeterGPRSLinkDAOImpl;
import org.zju.electric_factory.dao.impl.GPRSModuleDAOImpl;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterGPRSLink;
import org.zju.electric_factory.entity.GPRSModule;
import org.zju.electric_factory.vo.AmmeterGPRSVO;

@Controller
@RequestMapping("/ammetergprs")
@Transactional
public class AmmeterGPRSLinkController {
	@Autowired
	private AmmeterGPRSLinkDAOImpl ameterGprsLinkDAOImpl;
	@Autowired
	private AmmeterDAOImpl ammeterDAOImpl;
	@Autowired 
	private GPRSModuleDAOImpl gprsModuleDAOImpl;

	@RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
	public @ResponseBody List<AmmeterGPRSVO> getAllAmmeterGPRSLinks() {
		List<AmmeterGPRSVO> ammeterGPRSVOs = null;
		List<AmmeterGPRSLink> ammeterGPRSLinks = ameterGprsLinkDAOImpl.getAll();
		if(null != ammeterGPRSLinks){
			ammeterGPRSVOs = new ArrayList<AmmeterGPRSVO>();
			for(AmmeterGPRSLink ammeterGPRSLink : ammeterGPRSLinks) {
				Ammeter ammeter = ammeterDAOImpl.findUniqueBy("id", ammeterGPRSLink.getAmmeterId());
				GPRSModule gprsModule = gprsModuleDAOImpl.findUniqueBy("id", ammeterGPRSLink.getGprsId());
				if((null != ammeter)&&(null != gprsModule)){
					AmmeterGPRSVO ammeterGPRSVO = new AmmeterGPRSVO();
					ammeterGPRSVO.setId(ammeterGPRSLink.getId());
					ammeterGPRSVO.setAmmeterId(ammeterGPRSLink.getAmmeterId());
					ammeterGPRSVO.setGprsId(ammeterGPRSLink.getGprsId());
					ammeterGPRSVO.setAmmeterName(ammeter.getName());
					ammeterGPRSVO.setGprsName(gprsModule.getName());
					ammeterGPRSVOs.add(ammeterGPRSVO);
				}
			}
		}
		return ammeterGPRSVOs;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/list", headers = "Accept=application/json")
	public @ResponseBody AmmeterGPRSLink addAmmeterGPRSLinks(@RequestBody AmmeterGPRSVO ammeterGPRSVO) {
		System.out.println(ammeterGPRSVO.getAmmeterName());
		Ammeter ammeter = ammeterDAOImpl.findUniqueBy("name", ammeterGPRSVO.getAmmeterName());
		GPRSModule gprsModule = gprsModuleDAOImpl.findUniqueBy("name", ammeterGPRSVO.getGprsName());
		if((null != ammeter)&&(null != gprsModule)){
			AmmeterGPRSLink ammeterGPRSLink = new AmmeterGPRSLink();
			ammeterGPRSLink.setAmmeterId(ammeter.getId());
			ammeterGPRSLink.setGprsId(gprsModule.getId());
			ameterGprsLinkDAOImpl.save(ammeterGPRSLink);
		}
		return null;
	}
}
