package org.zju.electric_factory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.GPRSModule;
import org.zju.electric_factory.service.GPRSModuleManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 6/13/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/gprs")
public class GPRSModuleController {

    @Autowired
    private GPRSModuleManager gprsModuleManager;


    @RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json")
    public @ResponseBody
    List<GPRSModule> list() throws Exception {
        return gprsModuleManager.getGPRSModules();
    }

    @RequestMapping(method=RequestMethod.POST,value="/list",headers="Accept=application/json")
    public @ResponseBody GPRSModule addGPRS(@RequestBody GPRSModule gprs ) throws Exception{
        gprsModuleManager.add(gprs);
        return gprs;
    }
}
