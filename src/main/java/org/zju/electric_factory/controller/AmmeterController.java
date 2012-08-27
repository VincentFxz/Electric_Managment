package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.service.AmmeterManager;
import org.zju.electric_factory.service.AmmeterRecordManager;


@Controller
@RequestMapping("/ammeter")
@Transactional
public class AmmeterController {

    	
    @Autowired
    private AmmeterManager ammeterManager;
    
    
    @RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
    public  @ResponseBody List<Ammeter> listAmmeters(){
    	return ammeterManager.getAllAmmeter();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+id)")
    public @ResponseBody List <Ammeter> listAmmeterAscbyId(){
    	return ammeterManager.getAllAmmeterAscById();
    	        
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-id)")
    public @ResponseBody List<Ammeter> listAmmeterDescbyId(){
        return ammeterManager.getAllAmmeterDescById();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+name)")
    public @ResponseBody List<Ammeter> listAmmeterAscbyName(){
        return ammeterManager.getAllAmmeterAscByName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-name)")
    public @ResponseBody List<Ammeter> listAmmeterDescByName(){
        return ammeterManager.getAllAmmeterDescByName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-pumpName)")
    public @ResponseBody List<Ammeter> listAmmeterDescByPumpName(){
        return ammeterManager.getAllAmmeterDescByPumpName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+pumpName)")
    public @ResponseBody List<Ammeter> listAmmeterAscbyPumpName(){
        return ammeterManager.getAllAmmeterAscByPumpName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectName)")
    public @ResponseBody List<Ammeter> listAmmeterDescbyProjectName(){
        return ammeterManager.getAllAmmeterDescByProjectName();
    }


    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectName)")
    public @ResponseBody List<Ammeter> listAmmeterAscbyProjectName(){
        return ammeterManager.getAllAmmeterAscByProjectName();
    }


    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-companyName)")
        public @ResponseBody List<Ammeter> listAmmeterDescbyCompanyName(){
        return ammeterManager.getAllAmmeterDescByProjectName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+companyName)")
    public @ResponseBody List<Ammeter> listAmmeterAscbyCompanyName(){
        return ammeterManager.getAllAmmeterAscByProjectName();
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteAmmeterbyId(@PathVariable String id){
        ammeterManager.deleteAmmeterbyId(id);
        return true;
    }
    
    
    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody Ammeter updateAmmeter(@PathVariable String id,@RequestBody Ammeter ammeter){
        ammeterManager.editAmmeter(ammeter);
        return ammeter;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/list")
    public  @ResponseBody Ammeter addAmmeterByPost(Ammeter ammeter){
        ammeterManager.add(ammeter);
        return ammeter;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody Ammeter addAmmeter(Ammeter ammeter ){
        ammeterManager.add(ammeter);
        return ammeter;
    }
}
