package org.zju.electric_factory.service.impl;

import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zju.electric_factory.dao.AmmeterDAO;
import org.zju.electric_factory.dao.ProjectAmmeterLinkDAO;
import org.zju.electric_factory.dao.UserCompanyLinkDAO;
import org.zju.electric_factory.dao.UserProjectLinkDAO;
import org.zju.electric_factory.entity.Ammeter;
import org.zju.electric_factory.entity.ProjectAmmeterLink;
import org.zju.electric_factory.entity.UserCompanyLink;
import org.zju.electric_factory.entity.UserProjectLink;
import org.zju.electric_factory.service.AmmeterManager;

@Service
@Transactional
public class AmmeterManagerImpl implements AmmeterManager{
	@Autowired
	private AmmeterDAO ammeterDAO;
	
	@Autowired
    private UserProjectLinkDAO userProjectLinkDAO;

    @Autowired
    private ProjectAmmeterLinkDAO projectAmmeterLinkDAO;
    
    @Autowired
    private UserCompanyLinkDAO userCompanyLinkDAO;
    

	public void add(Ammeter ammeter) {
		ammeterDAO.add(ammeter);
	}

	public Ammeter getAmmeterById(Long id) {
		return ammeterDAO.get(id);
	}

	public Ammeter getAmmeterByName(String name) {
		return ammeterDAO.getByName(name);
	}

	public List<Ammeter> getAllAmmeter() {
		return ammeterDAO.getAll();
	}

	public List<Ammeter> getAmmeterByCompany(String company) {
		return ammeterDAO.getByCompany(company);
	}

	public List<Ammeter> getAmmeterByProject(String project) {
		return ammeterDAO.getByProject(project);
	}

	public void deleteAmmeter(Ammeter ammeter) {
		ammeterDAO.delete(ammeter);
	}

	public void deleteAmmeter(Long id) {
		ammeterDAO.delete(id);
	}

	public void editAmmeter(Ammeter ammeter) {
		ammeterDAO.update(ammeter);
	}
	
	public List<Ammeter> getAllAmmeterAscById(){
		return ammeterDAO.getByOrder("id",true);
	}
	
	public List<Ammeter> getAllAmmeterDescById(){
		return ammeterDAO.getByOrder("id",false);
	}
	
	public List<Ammeter> getAllAmmeterAscByName(){
		return ammeterDAO.getByOrder("name",true);
	}
	
	public List<Ammeter> getAllAmmeterDescByName(){
		return ammeterDAO.getByOrder("name",false);
	}
	
	public List<Ammeter> getAllAmmeterAscByPumpName(){
		return ammeterDAO.getByOrder("pumpName",true);
	}
	
	public List<Ammeter> getAllAmmeterDescByPumpName(){
		return ammeterDAO.getByOrder("pumpName",false);
	}
	
	public List<Ammeter> getAllAmmeterAscByProjectName(){
		return ammeterDAO.getByOrder("projectName",true);
	}
	
	public List<Ammeter> getAllAmmeterDescByProjectName(){
		return ammeterDAO.getByOrder("projectName",false);
	}
	
	public List<Ammeter> getAllAmmeterAscByCompanyName(){
		return ammeterDAO.getByOrder("companyName",true);
	}
	
	public List<Ammeter> getAllAmmeterDescByCompanyName(){
		return ammeterDAO.getByOrder("companyName",false);
	}
	
	public void deleteAmmeterbyId(String id){
		ammeterDAO.delete(Long.parseLong(id));
	}

    @Override
    public List<Ammeter> getAmmetersOwnByUser(Long userId) {
        List<Ammeter> ammetersOwnByUser=null;
        List<UserProjectLink> userProjectLinks = userProjectLinkDAO
                .getByUserId(userId);
        if (null != userProjectLinks) {
            ammetersOwnByUser= new ArrayList<Ammeter>();
            for (UserProjectLink userProjectLink : userProjectLinks) {
                ammetersOwnByUser.addAll(this.getAmmetersOwnByProject(userProjectLink.getProjectId()));
            }
        }
        return ammetersOwnByUser;
    }

    @Override
    public List<Ammeter> getAmmetersOwnByProject(Long projectId) {
        List<Ammeter> ammetersOwnByProject = null;
        List<ProjectAmmeterLink> projectAmmeterLinks = projectAmmeterLinkDAO
                .getByProjectId(projectId);
        if(null!=projectAmmeterLinks){
            ammetersOwnByProject= new ArrayList<Ammeter>();
            List<Long> ammeterIds = new ArrayList<Long>();
            if(projectAmmeterLinks.size() > 0){
            	for (ProjectAmmeterLink projectAmmeterLink : projectAmmeterLinks) {
                	ammeterIds.add(projectAmmeterLink.getAmmeterId());
                }
                ammetersOwnByProject = ammeterDAO.get(ammeterIds);
            }
        }
        return ammetersOwnByProject;
    }

    @Override
    public List<Ammeter> getAmmetersOwnByCompany(Long companyId) {
        List<Ammeter> ammeterOwnByCompany=null;
        List<UserCompanyLink> userCompanyLinks=userCompanyLinkDAO.getByCompanyId(companyId);
        if(null!=userCompanyLinks){
            ammeterOwnByCompany=new ArrayList<Ammeter>();
            for(UserCompanyLink userCompanyLink : userCompanyLinks){
                List<Ammeter> ammetersOwnByUser = this.getAmmetersOwnByUser(userCompanyLink.getUserId());
                if(null!=ammetersOwnByUser){
                    ammeterOwnByCompany.addAll(ammetersOwnByUser);
                }
            }
        }
        return ammeterOwnByCompany;
    }

	@Override
	public void deleteAmmeterbyId(Long id) {
		ammeterDAO.delete(id);
	}
	

}
