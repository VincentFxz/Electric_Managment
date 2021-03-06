package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.dao.CompanyProjectLinkDAO;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.service.CompanyManager;
import org.zju.electric_factory.service.CompanyProjectManager;

@Controller
@RequestMapping("/company")
@Transactional
public class CompanyController {
	
	@Autowired
	private CompanyManager companyManager;
	
	@Autowired 
	private CompanyProjectManager companyProjectManager;
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json", params = "companyId")
	public @ResponseBody Company getCompanyById(@RequestParam("companyId") Long companyId){
		return companyManager.getById(companyId);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
	public @ResponseBody List<Company> listCompanies(){
		return companyManager.getCompanies();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+id)")
    public @ResponseBody List<Company> listCompaniesAscbyId(){
    	return companyManager.getCompaniesAscById();
    	        
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-id)")
    public @ResponseBody List<Company> listCompaniesDescbyId(){
        return companyManager.getCompaniesDescById();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+companyName)")
    public @ResponseBody List<Company> listCompaniesAscbyName(){
        return companyManager.getCompaniesAscByName();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-companyName)")
    public @ResponseBody List<Company> listCompaniesDescByName(){
        return companyManager.getCompanesDescByName();
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteCompanybyId(@PathVariable String id){
        companyManager.deleteCompanyById(id);
        List<CompanyProjectLink> companyProjectLinks = companyProjectManager.getCompanyProjectLinksByCompanyId(id);
        if(null != companyProjectLinks){
        	for(CompanyProjectLink companyProjectLink : companyProjectLinks){
        		companyProjectManager.deleteCompanyProjectLink(companyProjectLink.getId());
        	}
        }
        return true;
    }
    
    
    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody Company updateAmmeter(@PathVariable String id,@RequestBody Company company){
        companyManager.editCompany(company);
        return company;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/list",headers="Accept=application/json")
    public @ResponseBody Company addAmmeter(@RequestBody Company company ){
        companyManager.add(company);
        return company;
    }
}
