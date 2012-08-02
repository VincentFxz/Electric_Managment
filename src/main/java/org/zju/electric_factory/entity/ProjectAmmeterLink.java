package org.zju.electric_factory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PA_LINK")
public class ProjectAmmeterLink {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name="PROJECT_ID")
    private Long projectId;
    
    @Column(name="AMMETER_ID")
    private Long ammeterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getAmmeterId() {
        return ammeterId;
    }

    public void setAmmeterId(Long ammeterId) {
        this.ammeterId = ammeterId;
    }
    
    
}
