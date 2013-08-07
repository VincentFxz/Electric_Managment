package org.zju.electric_factory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zju.electric_factory.service.impl.AmmeterClient;

@Controller
@RequestMapping("/gprsserver")
@Transactional
public class GPRSServerController {
	
	@Autowired
	private AmmeterClient ammeterClient;
	@RequestMapping(method = RequestMethod.GET, value = "/start", headers = "Accept=application/json")
	public void startGPRSServer() throws Exception{
		ammeterClient.moniter();
	}

}
