package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.AmmeterRecord;
import org.zju.electric_factory.service.AmmeterRecordManager;


@Controller
@RequestMapping("/ammeter_record")
@Transactional
public class AmmeterRecordController {
    @Autowired
    private AmmeterRecordManager ammeterRecordManager;
    
    @RequestMapping(method=RequestMethod.GET,value="/list/{id}",headers="Accept=application/json")
    public  @ResponseBody List<AmmeterRecord> listAmmeterRecordsByAmmeterId(@PathVariable String id){
    	if(null == id) return null;
        return ammeterRecordManager.getByAmmeter(Long.parseLong(id));
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json")
    public  @ResponseBody List<AmmeterRecord> listAmmeterRecords(){
    	
        return ammeterRecordManager.getAll();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+id)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordAscbyId(){
        return ammeterRecordManager.getAllAmmeterRecordAscById();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-id)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordDescbyId(){
        return ammeterRecordManager.getAllAmmeterRecordDescById();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+ammeterName)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordAscbyAmmeterName(){
        return ammeterRecordManager.getAllAmmeterRecordAscByAmmeterName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-ammeterName)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordDescByAmmeterName(){
        return ammeterRecordManager.getAllAmmeterRecordDescByAmmeterName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(- recordDate)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordDescByRecordDate(){
        return ammeterRecordManager.getAllAmmeterRecordDescByRecordDate();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+recordDate)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordAscbyPumpName(){
        return ammeterRecordManager.getAllAmmeterRecordAscByRecordDate();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-ammeterValue)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordDescbyAmmeter(){
        return ammeterRecordManager.getAllAmmeterRecordDescByAmmeterValue();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+ammeterValue)")
    public @ResponseBody List<AmmeterRecord> listAmmeterRecordAscbyProjectName(){
        return ammeterRecordManager.getAllAmmeterRecordAscByAmmeterValue();
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/list")
    public  @ResponseBody AmmeterRecord addAmmeterRecordByPost(AmmeterRecord ammeterRecord){
        ammeterRecordManager.add(ammeterRecord);
        return ammeterRecord;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody AmmeterRecord addAmmeterRecord(AmmeterRecord ammeterRecord ){
        ammeterRecordManager.add(ammeterRecord);
        return ammeterRecord;
    }
}
