package org.zju.electric_factory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zju.electric_factory.dao.GPRSModuleDAO;
import org.zju.electric_factory.dao.impl.GPRSModuleDAOImpl;
import org.zju.electric_factory.entity.GPRSModule;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.service.GPRSModuleManager;
import org.zju.electric_factory.service.impl.GPRSModuleManagerImpl;

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
@Transactional
public class GPRSModuleController {

    @Autowired
    private GPRSModuleManager gprsModuleManager;



    @RequestMapping(method = RequestMethod.GET, value = "/list/", headers = "Accept=application/json")
    public @ResponseBody
    List<GPRSModule> list() throws Exception {
        return gprsModuleManager.getGPRSModules();
    }

    @RequestMapping(method=RequestMethod.POST,value="/list",headers="Accept=application/json")
    public @ResponseBody GPRSModule deleteGPRS(@RequestBody GPRSModule gprs ) throws Exception{
        gprsModuleManager.add(gprs);
        return gprs;
    }

    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteGPRS(@PathVariable String id ) throws Exception{
        gprsModuleManager.deleteGPRSModule(Long.parseLong(id));
        return true;
    }
}
