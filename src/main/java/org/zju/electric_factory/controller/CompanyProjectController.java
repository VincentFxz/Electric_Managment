package org.zju.electric_factory.controller;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.Company;
import org.zju.electric_factory.entity.CompanyProjectLink;
import org.zju.electric_factory.entity.Project;
import org.zju.electric_factory.service.CompanyManager;
import org.zju.electric_factory.service.CompanyProjectManager;
import org.zju.electric_factory.service.ProjectManager;
import org.zju.electric_factory.vo.CompanyProjectVO;

@Controller
@RequestMapping("cp")
public class CompanyProjectController {
	
	
	@Autowired
	private CompanyProjectManager companyProjectManager;
	
	@Autowired 
	private CompanyManager companyManager;
	
	@Autowired
	private ProjectManager projectManager;
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="/list",headers="Accept=application/json")
	public @ResponseBody List<CompanyProjectVO> listCompanyProjects(){
		return companyProjectManager.getAllCompanyProjectLinkForView();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+companyName)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsAscbyCompanyName(){
		return companyProjectManager.getAllCompanyProjectAscByCompanyNameForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-companyName)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsDescByCompnayName(){
        return companyProjectManager.getAllCompanyProjectDescByCompanyNameForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+companyId)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsAscByCompanyId(){
        return companyProjectManager.getAllCompanyProjectAscByCompanyIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-companyId)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsDescByCompanyId(){
        return companyProjectManager.getAllCompanyProjectDescByCompanyIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectId)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsAscbyProjectId(){
		return companyProjectManager.getAllCompanyProjectAscByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectId)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsDescByProjectId(){
        return companyProjectManager.getAllCompanyProjectDescByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(+projectName)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsAscbyProjectName(){
        return companyProjectManager.getAllCompanyProjectAscByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/list/",headers="Accept=application/json", params="sort(-projectName)")
    public @ResponseBody List<CompanyProjectVO> listCompanyProjectsDescByProjectName(){
        return companyProjectManager.getAllCompanyProjectDescByProjectIdForView();
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody boolean deleteCompanyProjectbyId(@PathVariable String id){
    	if(null != id){
    		companyProjectManager.deleteCompanyProjectLink(Long.parseLong(id));
    	}
        return true;
    }
    
    
//    @RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
//    public @ResponseBody CompanyProjectVO updateAmmeter(@PathVariable String id,@ReqcuestBody CompanyProjectVO company){
//        CompanyProjectLink companyProjectLink = companyProjectManager.getCompanyProjectLinkById(id);
//    	companyProjectManager.edit(companyProjectLink);
//        return company;
//    }
    
    @RequestMapping(method=RequestMethod.POST,value="/add",headers="Accept=application/json")
    public @ResponseBody CompanyProjectVO addAmmeter(CompanyProjectVO companyProjectVO ){
    	CompanyProjectLink companyProjectLink = new CompanyProjectLink();
    	Company company = companyManager.getCompanyByCompanyName(companyProjectVO.getCompanyName());
    	Project project = projectManager.getProjectByProjectName(companyProjectVO.getProjectName());
    	if((null != company)&&(null != project)){
    		companyProjectLink.setCompanyId(company.getId());
        	companyProjectLink.setProjectId(project.getId());
            companyProjectManager.addCompanyProjectLink(companyProjectLink);
            return companyProjectVO;
    	}
    	return null;
    	
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/list",headers="Accept=application/json")
    public @ResponseBody CompanyProjectVO addCompanyProject(@RequestBody CompanyProjectVO companyProjectVO ){
    	CompanyProjectLink companyProjectLink = new CompanyProjectLink();
    	Company company = companyManager.getCompanyByCompanyName(companyProjectVO.getCompanyName());
    	Project project = projectManager.getProjectByProjectName(companyProjectVO.getProjectName());
    	if((null != company)&&(null != project)){
    		companyProjectLink.setCompanyId(company.getId());
        	companyProjectLink.setProjectId(project.getId());
            companyProjectManager.addCompanyProjectLink(companyProjectLink);
            return companyProjectVO;
    	}
    	return null;
    	
    }
}
